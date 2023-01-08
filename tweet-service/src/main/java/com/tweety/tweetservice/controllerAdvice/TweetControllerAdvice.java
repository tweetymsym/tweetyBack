package com.tweety.tweetservice.controllerAdvice;

import com.tweety.tweetservice.exception.TweetNotFoundException;
import com.tweety.tweetservice.exception.TweetyException;
import com.tweety.tweetservice.exception.TweetyExceptionResponse;
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
public class TweetControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity<TweetyExceptionResponse> handleTweetNotFoundException(
            TweetNotFoundException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                new TweetyExceptionResponse(
                        exception.getMessage(),
                        Instant.now(),
                        HttpStatus.NOT_FOUND
                ),
                HttpStatus.NOT_FOUND
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

    @ExceptionHandler(TweetyException.class)
    public ResponseEntity<TweetyExceptionResponse> handleTweetNotFoundException(
            TweetyException exception,
            WebRequest request
    ) {
        return new ResponseEntity<>(
                new TweetyExceptionResponse(
                        exception.getMessage(),
                        Instant.now(),
                        HttpStatus.INTERNAL_SERVER_ERROR
                ),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
