package com.tweety.feedservice.openfeign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tweety.feedservice.exception.TweetyException;
import com.tweety.feedservice.exception.TweetyExceptionResponse;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@AllArgsConstructor
public class FeedErrorDecoder implements ErrorDecoder {

    private ObjectMapper mapper;

    @Override
    public Exception decode(String methodKey, Response response) {

        TweetyExceptionResponse tweetyException = null;
        try {
            tweetyException = extractTweetyException(response);
        } catch (IOException e) {
            return new TweetyException(
                    e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        switch (response.status()) {
            case 404: {
                return new TweetyException(
                        tweetyException.getMessage(),
                        HttpStatus.NOT_FOUND
                );
            }
            default: {
                return new RuntimeException("Exception not defined");
            }
        }
    }

    private TweetyExceptionResponse extractTweetyException(Response response) throws IOException{
        TweetyExceptionResponse exceptionMessage = null;
        InputStream inputStream = null;
        //capturing error message from response body.
        try {
            inputStream = response.body().asInputStream();
            exceptionMessage = mapper.readValue(
                    inputStream,
                    TweetyExceptionResponse.class
            );

        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("IO Exception on reading exception message feign client" + e);
            }
        }
        return exceptionMessage;
    }


}
