package com.poleszak.GuessGame.service;

import com.poleszak.GuessGame.dto.BestTenGameDto;
import com.poleszak.GuessGame.dto.GuessGameDto;
import com.poleszak.GuessGame.message.Message;
import com.poleszak.GuessGame.model.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static com.poleszak.GuessGame.message.Message.NUMBER_GUESSED;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class GameDtoServiceTest {

    @InjectMocks
    private GameDtoService gameDtoService;

    @Test
    void toBestTenDto() {
        //given
        var game = new Game(1L, 1, 0, true, LocalDateTime.now(), 0L);
        var expected = new BestTenGameDto(1L, 0, 0L);

        //when
        var actual = gameDtoService.toBestTenDto(game);

        //then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void createGuessGameDto() {
        //given
        var gameId = 1L;
        var numberOfAttempts = 10;
        var message = NUMBER_GUESSED;
        var expected = new GuessGameDto(1L, 10, NUMBER_GUESSED);

        //when
        var actual = gameDtoService.createGuessGameDto(gameId, numberOfAttempts, message);

        //then
        assertThat(actual).isEqualTo(expected);
    }
}