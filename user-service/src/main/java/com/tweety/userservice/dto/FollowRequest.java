package com.tweety.userservice.dto;

public record FollowRequest (
        Long CurrentUserId,
        Long UserToFollow
)
{}
