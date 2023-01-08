package com.tweety.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotFollowedException extends TweetyException {
    public UserNotFollowedException(String currentUserID, String userTOUnBlockID) {
        super(
                "User with id '" + currentUserID +
                        "' is not following user with id '" +
                        userTOUnBlockID + "'",
                HttpStatus.BAD_REQUEST
        );
    }
}
