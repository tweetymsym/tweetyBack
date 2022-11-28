package com.tweety.feedservice.controller;
import com.tweety.feedservice.dto.TweetInListDto;
import com.tweety.feedservice.service.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
@AllArgsConstructor
public class FeedController {

    private FeedService feedService;

    @GetMapping("/default")
    public ResponseEntity<List<TweetInListDto>> getDefaultFeed() {
        return new ResponseEntity<>(
                feedService.getDefaultFeed(),
                HttpStatus.OK
        );
    }
}
