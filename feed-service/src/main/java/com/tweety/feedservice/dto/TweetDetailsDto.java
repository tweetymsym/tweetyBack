package com.tweety.feedservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TweetDetailsDto {
    private Long userId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
