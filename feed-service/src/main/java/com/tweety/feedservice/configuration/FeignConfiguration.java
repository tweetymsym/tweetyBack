package com.tweety.feedservice.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweety.feedservice.openfeign.FeedErrorDecoder;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class FeignConfiguration {

    private ObjectMapper mapper;

    @Bean
    public ErrorDecoder costumErrorDecoder() {
        return new FeedErrorDecoder(mapper);
    }
}
