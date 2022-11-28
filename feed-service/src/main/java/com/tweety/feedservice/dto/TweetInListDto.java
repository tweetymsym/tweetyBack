package com.tweety.feedservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;
}
