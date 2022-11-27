package com.tweety.userservice.dto;

public record RemoveFromFollowersRequest
       (
       Long CurrentUserId,
       Long UserToRemoveId
)
{}
