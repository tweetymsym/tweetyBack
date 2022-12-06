package com.tweety.feedservice.openfeign;

import com.tweety.feedservice.dto.TweetInListDto;
import com.tweety.feedservice.dto.UserIdListDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "tweet-service")
public interface TweetServiceProxy {

    @GetMapping("api/tweet")
    ResponseEntity<List<TweetInListDto>> getTweets(
            @RequestBody UserIdListDto dto
    );
}
