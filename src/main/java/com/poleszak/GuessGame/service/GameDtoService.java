package com.poleszak.GuessGame.service;

import com.poleszak.GuessGame.dto.GameBestTenDto;
import com.poleszak.GuessGame.dto.GuessGameDto;
import com.poleszak.GuessGame.dto.StartGameDto;
import com.poleszak.GuessGame.model.Game;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameDtoService {

    GameBestTenDto toBestTenDto(final Game game) {
        return new GameBestTenDto(game.getId(), game.getNumberOfAttempts(), game.getGameTimeInSeconds());
    }

    GuessGameDto createGuessGameDto(Long gameId, int numberOfAttempts, String message) {
        return new GuessGameDto(gameId, numberOfAttempts, message);
    }

    Game toGame(final StartGameDto gameDto) {
        return new Game();
    }
}
