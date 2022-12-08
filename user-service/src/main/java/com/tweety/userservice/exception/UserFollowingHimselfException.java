package com.tweety.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserFollowingHimselfException extends TweetyException{
    public UserFollowingHimselfException(String userId) {
        super(
                "User can not follow himself [userId: '" + userId + "']",
                HttpStatus.BAD_REQUEST
        );
    }
}
