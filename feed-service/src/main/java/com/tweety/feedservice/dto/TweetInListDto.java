package com.tweety.feedservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TweetInListDto {
    private String userName;
    private String userImage;
    private String content;
    private Date date;
}
