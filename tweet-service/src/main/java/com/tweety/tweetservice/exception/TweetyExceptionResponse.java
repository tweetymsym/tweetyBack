package com.tweety.tweetservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
