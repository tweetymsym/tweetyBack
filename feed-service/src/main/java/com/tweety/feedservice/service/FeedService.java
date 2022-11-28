package com.tweety.feedservice.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweety.feedservice.dto.TweetInListDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class FeedService {

    private ObjectMapper objectMapper;

    public List<TweetInListDto> getDefaultFeed() {
        TweetInListDto[] array = new TweetInListDto[0];

        try {
            String json = new String(
                    Objects.requireNonNull(
                            getClass().getResourceAsStream("/default-feed.json")
                    ).readAllBytes()
            );
            log.info(json);
            array = objectMapper.readValue(
                    json,
                    TweetInListDto[].class
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Arrays.asList(array);
    }
}
