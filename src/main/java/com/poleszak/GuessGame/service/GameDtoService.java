package com.poleszak.GuessGame.service;

import com.poleszak.GuessGame.dto.GameDto;
import com.poleszak.GuessGame.model.Game;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GameDtoService {

    GameDto toBestTenDto(final Game game) {
        return new GameDto(game.getId());
    }
}
