package com.tweety.userservice.ControllerAdvice;

import com.tweety.userservice.exception.TweetyException;
import com.tweety.userservice.exception.TweetyExceptionResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Objects;

@RestController
@ControllerAdvice
public class UserControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TweetyException.class)
    public ResponseEntity<TweetyExceptionResponse> handleTweetyException(
            TweetyException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                new TweetyExceptionResponse(
                        exception.getMessage(),
                        Instant.now(),
                        exception.getStatus()
                ), exception.getStatus()
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                new TweetyExceptionResponse(
                        Objects.requireNonNull(exception.getFieldError()).getDefaultMessage(),
                        Instant.now(),
                        status
                ),
                status
        );
    }
}
