package com.tweety.tweetservice.service;

import com.tweety.tweetservice.dto.CreateTweetDto;
import com.tweety.tweetservice.dto.TweetInListDto;
import com.tweety.tweetservice.dto.UserIdListDto;
import com.tweety.tweetservice.mapper.TweetMapper;
import com.tweety.tweetservice.model.Tweet;
import com.tweety.tweetservice.repository.TweetRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TweetService {

    private TweetRepository tweetRepository;
    private TweetMapper tweetMapper;

    public Tweet createTweet(CreateTweetDto dto) {
        Tweet tweet = tweetMapper.createTweetDtoToTweet(dto);
        tweet.setDate(Date.from(Instant.now()));
        tweet.setUserId(1l);
        return tweetRepository.save(tweet);
    }

    public List<Tweet> getAllTweets() {
        return tweetRepository.findAll();
    }

    public List<TweetInListDto> getUserTweets(UserIdListDto dto) {

        return tweetRepository.findAllByUserIdIsIn(dto.getUsers())
                .stream()
                .map(
                        (Tweet tweet) -> tweetMapper.TweetToTweetInListDto(tweet)
                ).collect(Collectors.toList());
    }
}
