package com.poleszak.GuessGame.controller;

import com.poleszak.GuessGame.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class GameController {

    @Autowired
    private final GameService gameService;

    @PostMapping("/start")
    public ResponseEntity<Long> start() {
        var gameId = gameService.startNewGame();
        return ResponseEntity.ok(gameId);
    }
}
