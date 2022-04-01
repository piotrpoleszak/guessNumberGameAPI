package com.poleszak.GuessGame.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

@Slf4j
@ControllerAdvice
public class GameExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GameException.class)
    public ResponseEntity<?> handlerGameException(GameException e, WebRequest request) {
        String message = ofNullable(e.getMessage())
                .orElse(format("GameException thrown: status %s, code %s, request %s",
                        e.getErrorCode(),
                        e.getErrorSubcode(),
                        request.getDescription(false)));
        log.error(message, e);

        return ResponseEntity
                .status(e.getErrorCode().getHttpStatus())
                .body(e.getErrorSubcode());
    }

}
