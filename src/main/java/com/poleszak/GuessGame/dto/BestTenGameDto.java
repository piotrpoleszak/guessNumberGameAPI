package com.poleszak.GuessGame.dto;

public record BestTenGameDto(Long id, int numberOfAttempts, Long gameTimeInSeconds) {
}
