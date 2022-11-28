package com.tweety.userservice.followership;


import com.tweety.userservice.dto.FollowRequest;
import com.tweety.userservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class followService {

    private followRepository followRepository;

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

    public Boolean UnFollowUser(UnfollowRequest unfollowRequest)

    {
        Long currentUser = unfollowRequest.getCurrentUserId();
        Long usertoUnFollow = unfollowRequest.getUserToFollow();

        if ( currentUser == UsertoUnFollow ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return  followRepository.UnfollowUser(currentUser,usertoUnFollow);

    }



    public Boolean removeFromMyFollowers(RemoveFromFollowersRequest removeFromFollowersRequest)

    {
        Long currentUser = removeFromFollowersRequest.CurrentUserId();
        Long UserToRemove = removeFromFollowersRequest.UserToRemoveId();
        if ( currentUser == UserToRemove ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return  followRepository.removeFromMyFollowers(currentUser,UserToRemove);

    }

}
