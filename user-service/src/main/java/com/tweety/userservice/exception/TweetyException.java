package com.tweety.userservice.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class TweetyException extends RuntimeException{
    private String message;
    private HttpStatus status;

    public TweetyException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
