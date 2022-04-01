package com.poleszak.GuessGame.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Guess {

    private Long gameId;
    private int guess;
}
