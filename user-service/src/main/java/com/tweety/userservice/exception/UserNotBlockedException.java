package com.tweety.userservice.exception;

import org.springframework.http.HttpStatus;

public class UserNotBlockedException extends TweetyException {
    public UserNotBlockedException(String currentUserID, String userTOUnBlockID) {
        super(
                "User with id '" + currentUserID +
                        "' is not blocking user with id '" +
                        userTOUnBlockID + "'",
                HttpStatus.BAD_REQUEST
        );
    }
}
