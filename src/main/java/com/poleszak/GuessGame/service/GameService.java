package com.poleszak.GuessGame.service;

import com.poleszak.GuessGame.dto.GameDto;
import com.poleszak.GuessGame.exception.ErrorSubcode;
import com.poleszak.GuessGame.exception.GameException;
import com.poleszak.GuessGame.model.Game;
import com.poleszak.GuessGame.repository.GameRepository;
import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import static com.poleszak.GuessGame.exception.ErrorCode.GAME_ACTIVE_STATUS_NOT_MATCH;

@Service
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final Mapper mapper;

    public GameDto startNewGame() {
        gameActiveStatusValidation();
        var newGame = new Game();
        newGame.setActive(true);
        newGame.setCreationDate(LocalDateTime.now());
        newGame.setSecretNumber(4);
        newGame.setNumberOfAttempts(0);

        gameRepository.save(newGame);

        return null;
    }

    public List<GameDto> getAllGames() {
        var allGames = gameRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Game::getId))
                .map(mapper::toDto)
                .toList();

        return allGames;
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
