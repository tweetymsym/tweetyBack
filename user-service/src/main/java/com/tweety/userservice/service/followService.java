package com.tweety.userservice.service;


import com.tweety.userservice.dto.FollowRequest;
import com.tweety.userservice.dto.RemoveFromFollowersRequest;
import com.tweety.userservice.dto.UnfollowRequest;
import com.tweety.userservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class followService {

    private com.tweety.userservice.repository.followRepository followRepository;

    public Boolean FollowUser(FollowRequest followRequest)

    {
        Long currentUser = followRequest.getCurrentUserId();
        Long userToFollow = followRequest.getUserToFollow();

        if ( currentUser == userToFollow ) throw new IllegalArgumentException(" You can not follow your self");

        return  followRepository.followUser(currentUser, userToFollow);

    }


    public List<User> getFollowings(Long CurrentUserID)
    {
        return followRepository.getFollowings(CurrentUserID);

    }

    public List<User> getFollowers(Long CurrentUserID)
    {
        return followRepository.getFollowers(CurrentUserID);

    }

    public Boolean unFollowUser(UnfollowRequest unfollowRequest)

    {
        Long currentUser = unfollowRequest.getCurrentUserId();
        Long usertoUnFollow = unfollowRequest.getUserToUnFollow();

        if ( currentUser.equals(usertoUnFollow) ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return  followRepository.UnfollowUser(currentUser,usertoUnFollow);

    }



    public Boolean removeFromMyFollowers(RemoveFromFollowersRequest removeFromFollowersRequest)

    {
        Long currentUser = removeFromFollowersRequest.getCurrentUserId();
        Long UserToRemove = removeFromFollowersRequest.getUserToRemoveId();
        if ( currentUser.equals(UserToRemove) ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return  followRepository.removeFromMyFollowers(currentUser,UserToRemove);

    }

}
