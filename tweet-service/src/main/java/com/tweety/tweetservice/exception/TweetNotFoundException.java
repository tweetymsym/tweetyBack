package com.tweety.tweetservice.exception;

public class TweetNotFoundException extends TweetyException {
    public TweetNotFoundException(String id) {
        super("Tweet with id '" + id + "' not found");
    }
}
