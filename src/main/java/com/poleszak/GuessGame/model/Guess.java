package com.poleszak.GuessGame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Guess {

    private Long gameId;
    private int guess;
}
