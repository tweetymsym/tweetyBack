package com.tweety.tweetservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class TweetInListDto {
    private Long userId;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
