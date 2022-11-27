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


    public List<User> getFollowings(Long CurrentUserID)
    {
        return followRepository.getFollowings(CurrentUserID);

    }

    public List<User> getFollowers(Long CurrentUserID)
    {
        return followRepository.getFollowers(CurrentUserID);

    }

    public Boolean UnFollowUser(UnfollowRequest unfollowRequest)

    {
        Long currentUser = unfollowRequest.CurrentUserId();
        Long UsertoUnFollow = unfollowRequest.UserToUnFollow();

        if ( currentUser == UsertoUnFollow ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return  followRepository.UnfollowUser(currentUser,UsertoUnFollow);

    }



    public Boolean RemoveFromMyFollowers(RemoveFromFollowersRequest removeFromFollowersRequest)

    {
        Long currentUser = removeFromFollowersRequest.CurrentUserId();
        Long UserToRemove = removeFromFollowersRequest.UserToRemoveId();
        if ( currentUser == UserToRemove ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return  followRepository.RemoveFromMyFollowers(currentUser,UserToRemove);

    }










}
