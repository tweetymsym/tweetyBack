package com.tweety.userservice.exception;

import org.springframework.http.HttpStatus;

public class EmailAlreadyExistsException extends TweetyException {
    public EmailAlreadyExistsException(String email) {
        super(
                "User with email '" + email + "' already exists",
                HttpStatus.CONFLICT
        );
    }
}
