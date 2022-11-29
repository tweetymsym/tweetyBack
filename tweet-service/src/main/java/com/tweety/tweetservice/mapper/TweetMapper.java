package com.tweety.tweetservice.mapper;

import com.tweety.tweetservice.dto.CreateTweetDto;
import com.tweety.tweetservice.dto.TweetInListDto;
import com.tweety.tweetservice.model.Tweet;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TweetMapper {
    Tweet createTweetDtoToTweet(CreateTweetDto dto);
    TweetInListDto TweetToTweetInListDto(Tweet tweet);
}
