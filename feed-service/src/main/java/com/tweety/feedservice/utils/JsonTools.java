package com.tweety.feedservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class JsonTools {

    @Bean
    public ObjectMapper jsonObjectMapper() {
        return new ObjectMapper();
    }
}
