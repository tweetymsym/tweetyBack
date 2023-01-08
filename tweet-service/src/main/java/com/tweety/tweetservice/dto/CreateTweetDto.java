package com.tweety.tweetservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CreateTweetDto { // Data Transfer Object
    @NotNull(message = "Tweet's content should not be null !")
    @NotEmpty(message = "Tweet's content should not be empty !")
    @Size(max = 280, message = "Tweet's content should not exceed 280 !")
    private String content;
}
