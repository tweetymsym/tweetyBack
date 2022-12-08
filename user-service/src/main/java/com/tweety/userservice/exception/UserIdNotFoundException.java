package com.tweety.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserIdNotFoundException extends TweetyException{
    public UserIdNotFoundException(String userId) {
        super(
               "User with id '" + userId + "' not found",
                HttpStatus.NOT_FOUND
        );
    }
}
