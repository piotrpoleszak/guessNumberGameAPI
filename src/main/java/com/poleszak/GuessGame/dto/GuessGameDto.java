package com.poleszak.GuessGame.dto;

import com.poleszak.GuessGame.message.Message;

public record GuessGameDto(Long id, int numberOfAttempts, Message message) {
}
