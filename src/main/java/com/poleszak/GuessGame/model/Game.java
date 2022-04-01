package com.poleszak.GuessGame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "game_id")
    private Long id;

    @Column(name = "secret_number")
    private int secretNumber;

    @Column(name = "number_of_attempts")
    private int numberOfAttempts;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "end_game_time")
    private LocalDateTime endGameTime;
}
