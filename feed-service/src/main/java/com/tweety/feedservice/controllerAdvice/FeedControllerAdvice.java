package com.tweety.feedservice.controllerAdvice;

import com.tweety.feedservice.exception.TweetyException;
import com.tweety.feedservice.exception.TweetyExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestController
@ControllerAdvice
public class FeedControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(TweetyException.class)
    public ResponseEntity<TweetyExceptionResponse> handleTweetyException(
            TweetyException exception,
            WebRequest webRequest
    ) {
        return new ResponseEntity<>(
                new TweetyExceptionResponse(
                        exception.getMessage(),
                        Instant.now(),
                        exception.getStatus()
                ),
                exception.getStatus()
        );
    }

}
