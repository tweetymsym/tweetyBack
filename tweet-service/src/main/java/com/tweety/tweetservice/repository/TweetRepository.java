package com.tweety.tweetservice.repository;

import com.tweety.tweetservice.model.Tweet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface TweetRepository extends MongoRepository<Tweet, String> {
    List<Tweet> findAllByDateAfter(Date afterDate);
    List<Tweet> findAllByUserIdIsIn(List<Long> userIdList);
}
