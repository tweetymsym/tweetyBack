package com.tweety.userservice.followership;


import com.tweety.userservice.dto.FollowRequest;
import com.tweety.userservice.dto.RemoveFromFollowersRequest;
import com.tweety.userservice.dto.UnfollowRequest;
import com.tweety.userservice.model.User;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class followService {

    private followRepository followRepository;

    public Boolean FollowUser(FollowRequest followRequest)

    {
        Long currentUser = followRequest.CurrentUserId();
        Long UsertoFollow = followRequest.UserToFollow();

        if ( currentUser == UsertoFollow ) throw new IllegalArgumentException(" You can not follow your self");

        return  followRepository.followUser(currentUser,UsertoFollow);

    }
    












}
