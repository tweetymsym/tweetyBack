package com.tweety.feedservice.openfeign;

import com.tweety.feedservice.configuration.FeignConfiguration;
import com.tweety.feedservice.dto.TweetDetailsDto;
import com.tweety.feedservice.dto.TweetInListDto;
import com.tweety.feedservice.dto.UserIdListDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "tweet-service", configuration = {FeignConfiguration.class}, contextId = "tweet")
public interface TweetServiceProxy {

    @GetMapping("api/tweets")
    ResponseEntity<List<TweetInListDto>> getTweets(
            @RequestBody UserIdListDto dto
    );

}
