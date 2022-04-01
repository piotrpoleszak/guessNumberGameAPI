package com.poleszak.GuessGame.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GameException extends RuntimeException{

    private final ErrorCode errorCode;
    private final ErrorSubcode errorSubcode;

    public GameException(ErrorCode errorCode, ErrorSubcode errorSubcode) {
        this.errorCode = errorCode;
        this.errorSubcode = errorSubcode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public ErrorSubcode getErrorSubcode() {
        return errorSubcode;
    }

    @Override
    public String toString() {
        return "GameException{" +
                "errorCode=" + errorCode +
                ", errorSubcode=" + errorSubcode +
                '}';
    }
}
