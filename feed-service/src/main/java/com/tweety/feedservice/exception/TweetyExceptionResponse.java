package com.tweety.feedservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
public class TweetyExceptionResponse {
    private String message;
    private Instant timestamp;
    private HttpStatus status;
}
