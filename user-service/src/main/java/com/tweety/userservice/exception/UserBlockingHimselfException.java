package com.tweety.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserBlockingHimselfException extends TweetyException{
    public UserBlockingHimselfException(String userId) {
        super(
                "User can not block himself [userId: '" + userId + "']",
                HttpStatus.BAD_REQUEST
        );
    }
}
