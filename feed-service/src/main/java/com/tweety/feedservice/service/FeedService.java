package com.tweety.feedservice.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweety.feedservice.dto.TweetInListDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class FeedService {

    private ObjectMapper objectMapper;

    public List<TweetInListDto> getDefaultFeed() {
        TweetInListDto[] array = new TweetInListDto[0];

        try {
            array = objectMapper.readValue(
                    Objects.requireNonNull(getClass().getResource("/default-feed.json")).getFile(),
                    TweetInListDto[].class
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(array);
    }
}
