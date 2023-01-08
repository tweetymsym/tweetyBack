package com.tweety.feedservice.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TweetyExceptionResponse {
    private String message;
    private Instant timestamp;
    private HttpStatus status;
}
