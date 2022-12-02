package com.tweety.userservice.service;


import com.tweety.userservice.dto.FollowRequest;
import com.tweety.userservice.dto.RemoveFromFollowersRequest;
import com.tweety.userservice.dto.UnfollowRequest;
import com.tweety.userservice.dto.UserIdListDto;
import com.tweety.userservice.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class followService {

    private com.tweety.userservice.repository.followRepository followRepository;

    public Boolean FollowUser(FollowRequest followRequest)

    {
        String currentUser = followRequest.getCurrentUserId();
        String userToFollow = followRequest.getUserToFollow();

        if ( currentUser == userToFollow ) throw new IllegalArgumentException(" You can not follow your self");

        return  followRepository.followUser(currentUser, userToFollow);

    }


    public List<User> getFollowings(String CurrentUserID)
    {
        return followRepository.getFollowings(CurrentUserID);

    }

    public List<User> getFollowers(String CurrentUserID)
    {
        return followRepository.getFollowers(CurrentUserID);

    }

    public Boolean unFollowUser(UnfollowRequest unfollowRequest)

    {
        String currentUser = unfollowRequest.getCurrentUserId();
        String usertoUnFollow = unfollowRequest.getUserToUnFollow();

        if ( currentUser.equals(usertoUnFollow) ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return  followRepository.UnfollowUser(currentUser,usertoUnFollow);

    }



    public Boolean removeFromMyFollowers(RemoveFromFollowersRequest removeFromFollowersRequest)

    {
        String currentUser = removeFromFollowersRequest.getCurrentUserId();
        String UserToRemove = removeFromFollowersRequest.getUserToRemoveId();
        if ( currentUser.equals(UserToRemove) ) throw new IllegalArgumentException(" You can not Unfollow your self");

        return followRepository.removeFromMyFollowers(currentUser, UserToRemove);

    }

    public UserIdListDto getFollowingsIdList(String userId) {
        return new UserIdListDto(getFollowings(userId).stream()
                .map(User::getId)
                .collect(Collectors.toList()));
    }
}
