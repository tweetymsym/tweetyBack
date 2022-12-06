package com.tweety.feedservice.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
public class TweetyException extends RuntimeException{
    private String message;
    private HttpStatus status;

    public TweetyException(String message) {
        super(message);
        this.message = message;
    }
}
