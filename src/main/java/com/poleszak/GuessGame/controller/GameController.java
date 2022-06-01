package com.poleszak.GuessGame.controller;

import com.poleszak.GuessGame.dto.BestTenGameDto;
import com.poleszak.GuessGame.dto.GuessGameDto;
import com.poleszak.GuessGame.model.Guess;
import com.poleszak.GuessGame.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/guess")
    public ResponseEntity<GuessGameDto> guess(@RequestBody Guess guess) {
        return ResponseEntity.ok(gameService.guess(guess));
    }

    @GetMapping("/getBesScores")
    public ResponseEntity<List<BestTenGameDto>> bestScores() {
        return ResponseEntity.ok(gameService.getBestScores());
    }
}
