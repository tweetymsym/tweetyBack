package com.tweety.tweetservice.exception;

import org.springframework.http.HttpStatus;

public class TweetNotFoundException extends TweetyException {
    public TweetNotFoundException(String id) {
        super("Tweet with id '" + id + "' not found", HttpStatus.NOT_FOUND);
    }
}
