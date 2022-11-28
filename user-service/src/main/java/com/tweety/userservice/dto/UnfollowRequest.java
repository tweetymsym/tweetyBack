package com.tweety.userservice.dto;

public record UnfollowRequest (
        Long CurrentUserId,
        Long UserToUnFollow
)
{}
