package com.tweety.feedservice.openfeign;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tweety.feedservice.exception.TweetyException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FeedErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        TweetyException tweetyException = extractTweetyException(response);
        switch (response.status()) {
            case 404: {
                return tweetyException;
            }
            default: {
                return new RuntimeException("Exception not defined");
            }
        }
    }

    private TweetyException extractTweetyException(Response response) {
        TweetyException exceptionMessage = null;
        InputStream inputStream = null;
        //capturing error message from response body.
        try {
            inputStream = response.body().asInputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            exceptionMessage = mapper.readValue(inputStream,
                    TweetyException.class);
        } catch (IOException e) {
            log.error("IO Exception on reading exception message feign client" + e);
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
