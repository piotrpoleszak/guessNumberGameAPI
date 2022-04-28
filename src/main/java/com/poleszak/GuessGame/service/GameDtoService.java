package com.poleszak.GuessGame.service;

import com.poleszak.GuessGame.dto.BestTenGameDto;
import com.poleszak.GuessGame.dto.GuessGameDto;
import com.poleszak.GuessGame.dto.StartGameDto;
import com.poleszak.GuessGame.message.Message;
import com.poleszak.GuessGame.model.Game;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameDtoService {

    BestTenGameDto toBestTenDto(final Game game) {
        return new BestTenGameDto(game.getId(), game.getNumberOfAttempts(), game.getGameTimeInSeconds());
    }

    GuessGameDto createGuessGameDto(Long gameId, int numberOfAttempts, Message message) {
        return new GuessGameDto(gameId, numberOfAttempts, message);
    }

    Game toGame(final StartGameDto gameDto) {
        return new Game();
    }
}
