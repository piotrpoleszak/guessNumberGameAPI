package com.poleszak.GuessGame.exception;

public enum ErrorCode {
    NOT_FOUND(404),
    GAME_ACTIVE_STATUS_NOT_MATCH(400);

    private final int httpStatus;

    ErrorCode(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }
}
