package com.tweety.userservice.service;


import com.tweety.userservice.dto.FollowRequestDto;
import com.tweety.userservice.dto.UnfollowRequestDto;
import com.tweety.userservice.dto.UserIdListDto;
import com.tweety.userservice.dto.UserInListDto;
import com.tweety.userservice.exception.UserFollowingHimselfException;
import com.tweety.userservice.exception.UserIdNotFoundException;
import com.tweety.userservice.exception.UserNotBlockedException;
import com.tweety.userservice.exception.UserNotFollowedException;
import com.tweety.userservice.mapper.UserMapper;
import com.tweety.userservice.model.User;
import com.tweety.userservice.repository.FollowRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class FollowService {

    private FollowRepository followRepository;
    private UserService userService;
    private UserMapper userMapper;

    public void followUser(FollowRequestDto followRequestDto) {
        String currentUser = followRequestDto.getCurrentUserId();
        String userToFollow = followRequestDto.getUserToFollow();
        if (currentUser.equals(userToFollow)) throw new UserFollowingHimselfException(currentUser);

        followRepository.followUser(currentUser, userToFollow);
    }


    public List<UserInListDto> getFollowings(String currentUserID) {
        if (!userService.userExistsById(currentUserID))
            throw new UserIdNotFoundException(currentUserID);

        return followRepository.getFollowings(currentUserID)
                .stream()
                .map(userMapper::userToUserInListDtoMapper)
                .collect(Collectors.toList());
    }

    public List<UserInListDto> getFollowers(String currentUserID) {
        if (!userService.userExistsById(currentUserID))
            throw new UserIdNotFoundException(currentUserID);

        return followRepository.getFollowers(currentUserID)
                .stream()
                .map(userMapper::userToUserInListDtoMapper)
                .collect(Collectors.toList());
    }

    public void unFollowUser(UnfollowRequestDto unfollowRequestDto) {
        String currentUser = unfollowRequestDto.getCurrentUserId();
        String userUnFollow = unfollowRequestDto.getUserToUnFollow();

        if (!userService.userExistsById(currentUser))
            throw new UserIdNotFoundException(currentUser);

        if (!userService.userExistsById(userUnFollow))
            throw new UserIdNotFoundException(userUnFollow);

        boolean followed = followRepository.UnfollowUser(currentUser, userUnFollow);

        if (!followed) throw new UserNotFollowedException(currentUser, userUnFollow);
    }

    public UserIdListDto getFollowingsIdList(String userId) {

        if (!userService.userExistsById(userId))
            throw new UserIdNotFoundException(userId);

        return new UserIdListDto(getFollowings(userId).stream()
                .map(UserInListDto::getId)
                .collect(Collectors.toList()));
    }
}
