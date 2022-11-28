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
}
