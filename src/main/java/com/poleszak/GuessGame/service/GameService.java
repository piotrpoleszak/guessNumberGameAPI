package com.poleszak.GuessGame.service;

import com.poleszak.GuessGame.dto.GameBestTenDto;
import com.poleszak.GuessGame.dto.GuessGameDto;
import com.poleszak.GuessGame.dto.StartGameDto;
import com.poleszak.GuessGame.exception.GameException;
import com.poleszak.GuessGame.message.Message;
import com.poleszak.GuessGame.model.Game;
import com.poleszak.GuessGame.model.Guess;
import com.poleszak.GuessGame.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.poleszak.GuessGame.exception.ErrorCode.GAME_ACTIVE_STATUS_NOT_MATCH;
import static com.poleszak.GuessGame.exception.ErrorCode.NOT_FOUND;
import static com.poleszak.GuessGame.exception.ErrorSubcode.*;
import static com.poleszak.GuessGame.message.Message.*;

@Service
@AllArgsConstructor
public class GameService {

    @Autowired
    private final GameRepository gameRepository;
    @Autowired
    private final GameDtoService gameDtoService;

    public Long startNewGame(StartGameDto startGameDto) {
        gameActiveStatusValidation();
        var newGame = gameDtoService.toGame(startGameDto);
        newGame.setActive(true);
        newGame.setCreationDate(LocalDateTime.now());
        newGame.setSecretNumber(secretNumberGenerator());
        newGame.setNumberOfAttempts(0);

        gameRepository.save(newGame);

        return newGame.getId();
    }

    public List<GameBestTenDto> getBestScores() {
        List<GameBestTenDto> allGames = gameRepository.findAll()
                .stream()
                .filter(v -> v.getGameTimeInSeconds() != null)
                .sorted(Comparator.comparing(Game::getId))
                .limit(10)
                .map(gameDtoService::toBestTenDto)
                .toList();

        return allGames;
    }

    public GuessGameDto guess(Guess guess) {
        var gameId = gameRepository.getById(guess.getGameId())
                .getId();
        var userGuess = guess.getGuess();

        gameValidation(gameId);
        guessGameValidation(gameId);
        var message = guessMessage(gameId, userGuess);
        var game = gameRepository.getById(gameId);
        updateAfterGuess(game, userGuess);
        var numberOfAttempts = gameRepository.getById(guess.getGameId())
                .getNumberOfAttempts();

        return gameDtoService.createGuessGameDto(gameId, numberOfAttempts, message);
    }




    private int secretNumberGenerator() {
        var rnd = new Random();
        return rnd.nextInt(100) + 1;
    }

    private void gameActiveStatusValidation() {
        var isEmpty = gameRepository.findAll().isEmpty();

        if (!isEmpty)
        {
            var isLastGameActive = gameRepository.findAll()
                    .stream()
                    .sorted(Comparator.comparing(Game::getId).reversed())
                    .findFirst()
                    .get()
                    .isActive();

            if (isLastGameActive) {
                throw new GameException(GAME_ACTIVE_STATUS_NOT_MATCH,
                        LAST_GAME_IS_STILL_ACTIVE);
            }
        }
    }

    private void gameValidation(Long gameId) {
        var isGameInDb = gameRepository.existsById(gameId);

        if (!isGameInDb) {
            throw new GameException(NOT_FOUND,
                    GAME_NOT_FOUND_IN_DATABASE);
        }
    }

    private void guessGameValidation(Long gameId) {
        var isGameActive = gameRepository.getById(gameId)
                .isActive();

        if (!isGameActive) {
            throw new GameException(GAME_ACTIVE_STATUS_NOT_MATCH,
                    GAME_IS_NO_LONGER_ACTIVE);
        }
    }

    private Message guessMessage(Long gameId, int userGuess) {
        var secretNumber = gameRepository.getById(gameId)
                .getSecretNumber();

        if (secretNumber == userGuess) {
            return NUMBER_GUESSED;
        }
        else if (secretNumber > userGuess) {
            return TOO_SMALL;
        }
        else {
            return TOO_LARGE;
        }
    }

    private void updateAfterGuess(Game game, int userGuess) {
        var numberOfAttempts = game.getNumberOfAttempts();
        numberOfAttempts++;
        game.setNumberOfAttempts(numberOfAttempts);

        if (game.getSecretNumber() == userGuess) {
            var gameTimeInSeconds = ChronoUnit.SECONDS.between(game.getCreationDate(), LocalDateTime.now());
            game.setGameTimeInSeconds(gameTimeInSeconds);
            game.setActive(false);
        }

        gameRepository.save(game);
    }
}
