package com.poleszak.GuessGame.controller;

import com.poleszak.GuessGame.dto.GameDto;
import com.poleszak.GuessGame.model.Game;
import com.poleszak.GuessGame.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@AllArgsConstructor
public class GameController {

    @Autowired
    private final GameService gameService;

    @RequestMapping(value = "/start", method = POST)
    public ResponseEntity<GameDto> start() {
        var gameDto = gameService.startNewGame();


        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<GameDto>> getAllGames() {
        var allGames = gameService.getAllGames();
        return new ResponseEntity<List<GameDto>>(allGames).getBody();
    }


}
