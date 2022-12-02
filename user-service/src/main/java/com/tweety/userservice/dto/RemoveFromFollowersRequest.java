package com.tweety.userservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RemoveFromFollowersRequest{
       String currentUserId;
       String userToRemoveId;
}
