package com.tweety.userservice.controller;

import com.tweety.userservice.dto.*;
import com.tweety.userservice.model.User;
import com.tweety.userservice.service.FollowService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/users/follow")
@Slf4j
public class FollowController {

    private FollowService followService;

    @PostMapping("")
    public ResponseEntity<Void> FollowUser(
            @RequestBody FollowRequestDto followRequestDto
    ) {
        followService.followUser(followRequestDto);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }

    @GetMapping("/followers/{userId}")
    public ResponseEntity<List<UserInListDto>> getFollowers(
             @PathVariable String userId )
    {
        return new ResponseEntity<>(
                followService.getFollowers(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("following/id-list/{userId}")
    public ResponseEntity<UserIdListDto> getFollowingIdList(
            @PathVariable("userId") String userId )
    {
        return new ResponseEntity<>(
                followService.getFollowingsIdList(userId),
                HttpStatus.OK
        );
    }

    @GetMapping("following/{UserId}")
    public ResponseEntity<List<UserInListDto>> getFollowing(
            @PathVariable String UserId )
    {
        return new ResponseEntity<>(
                followService.getFollowings(UserId),
                HttpStatus.OK
        );
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> UnfollowUser(
            @RequestBody UnfollowRequestDto unfollowRequest
    ) {
        followService.unFollowUser(unfollowRequest);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

}
