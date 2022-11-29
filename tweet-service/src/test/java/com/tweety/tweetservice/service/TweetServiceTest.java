package com.tweety.tweetservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweety.tweetservice.dto.UserIdListDto;
import com.tweety.tweetservice.mapper.TweetMapper;
import com.tweety.tweetservice.mapper.TweetMapperImpl;
import com.tweety.tweetservice.model.Tweet;
import com.tweety.tweetservice.repository.TweetRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class) // JUnit 5
@ContextConfiguration(classes = {
        TweetMapperImpl.class,
})
class TweetServiceTest {

    @Autowired
    private TweetMapper tweetMapper;

    private static TweetRepository tweetRepository;

    @BeforeAll
    public static void start() {

        ObjectMapper objectMapper = new ObjectMapper();
        tweetRepository = mock(TweetRepository.class);
        Tweet[] array;
        List<Tweet> tweetList;
        try {
            String json = new String(
                    Objects.requireNonNull(
                          TweetServiceTest.class.getResourceAsStream("/tweet-test-list.json")
                    ).readAllBytes()
            );
            array = objectMapper.readValue(
                    json,
                    Tweet[].class
            );
            tweetList = List.of(array);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        when(tweetRepository.findAllByUserIdIsIn(anyList())).thenReturn(tweetList);
    }

    @Test
    void testGetUserTweets() {
        TweetService tweetService = new TweetService(tweetRepository, tweetMapper);
        assertEquals(6, tweetService.getUserTweets(new UserIdListDto(new ArrayList<>())).size());
    }
}