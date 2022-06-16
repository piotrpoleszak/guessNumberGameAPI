package com.poleszak.GuessGame.unit.service;

import com.poleszak.GuessGame.dto.BestTenGameDto;
import com.poleszak.GuessGame.dto.GuessGameDto;
import com.poleszak.GuessGame.message.Message;
import com.poleszak.GuessGame.model.Game;
import com.poleszak.GuessGame.model.Guess;
import com.poleszak.GuessGame.repository.GameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDateTime;
import java.util.List;

import static com.poleszak.GuessGame.message.Message.NUMBER_GUESSED;
import static com.poleszak.GuessGame.message.Message.TOO_LARGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

    @InjectMocks
    private GameService gameService;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private GameDtoService gameDtoService;

    @Captor
    private ArgumentCaptor<Game> gameArgumentCaptor;

    @Test
    void startNewGame() {
        //given
        //when
        gameService.startNewGame();

        //then
        verify(gameRepository).save(gameArgumentCaptor.capture());
        var actual = gameArgumentCaptor.getValue();

        assertThat(actual.isActive()).isTrue();
        assertThat(actual.getCreationDate()).isNotNull();
        assertThat(actual.getSecretNumber()).isBetween(0, 100);
    }

    @Test
    void getBestScores() {
        //given
        var expected = new BestTenGameDto(1L, 0, 0L);
        var data = List.of(new Game(1L, 20, 0, false, LocalDateTime.now(), 0L));

        //when
        when(gameRepository.findAll()).thenReturn(data);
        when(gameDtoService.toBestTenDto(any(Game.class))).thenReturn(expected);
        var actual = gameService.getBestScores();

        //then
        assertThat(actual.get(0)).isEqualTo(expected);
    }

    @Test
    void shouldGuessANumberAndSetGameAsNotActive() {
        //given
        var game = new Game(1L, 1, 0, true, LocalDateTime.now(), 0L);
        var guess = new Guess(1L, 1);

        //when
        when(gameDtoService.createGuessGameDto(1L, 1, NUMBER_GUESSED)).thenReturn(new GuessGameDto(1L, 1, NUMBER_GUESSED));
        when(gameRepository.existsById(anyLong())).thenReturn(true);
        when(gameRepository.getById(anyLong())).thenReturn(game);

        var response = gameService.guess(guess);

        //then
        assertThat(game.isActive()).isFalse();
        assertThat(response.message()).isEqualTo(NUMBER_GUESSED);
    }

    @Test
    void shouldNotGuessANumberAndReturnMessage() {
        //given
        var game = new Game(1L, 1, 0, true, LocalDateTime.now(), 0L);
        var guess = new Guess(1L, 20);

        //when
        when(gameDtoService.createGuessGameDto(1L, 1, TOO_LARGE)).thenReturn(new GuessGameDto(1L, 1, TOO_LARGE));
        when(gameRepository.existsById(anyLong())).thenReturn(true);
        when(gameRepository.getById(anyLong())).thenReturn(game);

        var response = gameService.guess(guess);

        //then
        assertThat(game.isActive()).isTrue();
        assertThat(response.message()).isEqualTo(TOO_LARGE);
    }
}