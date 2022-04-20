package com.poleszak.GuessGame.service;

import com.poleszak.GuessGame.dto.GameDto;
import com.poleszak.GuessGame.exception.ErrorSubcode;
import com.poleszak.GuessGame.exception.GameException;
import com.poleszak.GuessGame.model.Game;
import com.poleszak.GuessGame.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static com.poleszak.GuessGame.exception.ErrorCode.GAME_ACTIVE_STATUS_NOT_MATCH;

@Service
@AllArgsConstructor
public class GameService {

    @Autowired
    private final GameRepository gameRepository;
    @Autowired
    private final GameDtoService gameDtoService;

    public Long startNewGame() {
        gameActiveStatusValidation();
        var newGame = new Game();
        newGame.setActive(true);
        newGame.setCreationDate(LocalDateTime.now());
        newGame.setSecretNumber(secretNumberGenerator());
        newGame.setNumberOfAttempts(0);

        gameRepository.save(newGame);

        return newGame.getId();
    }

    public List<GameDto> getAllGames() {
        List<GameDto> allGames = gameRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Game::getId))
                .map(gameDtoService::toBestTenDto)
                .toList();

        return allGames;
    }


    private int secretNumberGenerator() {
        var rnd = new Random();
        return rnd.nextInt(100) + 1;
    }

    private void gameActiveStatusValidation() {
        var isLastGameActive = gameRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Game::getId).reversed())
                .findFirst()
                .get()
                .isActive();

        if (isLastGameActive) {
            throw new GameException(GAME_ACTIVE_STATUS_NOT_MATCH,
                    ErrorSubcode.LAST_GAME_IS_STILL_ACTIVE);
        }
    }
}
