package com.tweety.userservice.exception;

import org.springframework.http.HttpStatus;

public class UsernameAlreadyExistsException extends TweetyException {
    public UsernameAlreadyExistsException(String userName) {
        super(
                "User with username '" + userName + "' already exists",
                HttpStatus.CONFLICT
        );
    }
}
