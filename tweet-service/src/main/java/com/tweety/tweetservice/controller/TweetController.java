package com.tweety.tweetservice.controller;

import com.tweety.tweetservice.dto.CreateTweetDto;
import com.tweety.tweetservice.dto.TweetDetailsDto;
import com.tweety.tweetservice.dto.TweetInListDto;
import com.tweety.tweetservice.dto.UserIdListDto;
import com.tweety.tweetservice.model.Tweet;
import com.tweety.tweetservice.service.TweetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    private TweetService tweetService;

    @PostMapping("")
    public ResponseEntity<Void> createTweet(
            @Valid @RequestBody CreateTweetDto dto
    ) {
        Tweet tweet = tweetService.createTweet(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tweet.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .build();
    }

    @GetMapping("")
    public ResponseEntity<List<TweetInListDto>> getTweets(
            @RequestBody UserIdListDto dto
            ) {
        return new ResponseEntity<>(
                tweetService.getUserTweets(dto),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TweetDetailsDto> getTweetWithId(
            @PathVariable("id") String id
    ) {
        return new ResponseEntity<>(
                tweetService.getTweetWithId(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/test")
    public ResponseEntity<List<Tweet>> getAllTweetsForTest() {
        return new ResponseEntity<>(
                tweetService.getAllTweets(),
                HttpStatus.OK
        );
    }
}
