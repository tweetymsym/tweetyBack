package com.tweety.userservice.controller;

import com.tweety.userservice.dto.*;
import com.tweety.userservice.model.User;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/follow")
@Slf4j
@CrossOrigin("*")
public class followController {

    private com.tweety.userservice.service.followService followService;


    @PostMapping("")
    public ResponseEntity<Void> FollowUser(
            @RequestBody FollowRequest followRequest
    ) {
        followService.FollowUser(followRequest);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }

    @GetMapping("/followers/{UserId}")
    public ResponseEntity<List<User>> getFollowers(
             @PathVariable String UserId )
    {
        return new ResponseEntity<>(
                followService.getFollowers(UserId),
                HttpStatus.OK
        );
    }

    @GetMapping("following/id-list/{UserId}")
    public ResponseEntity<UserIdListDto> getFollowingIdList(
            @PathVariable String UserId )
    {
        return new ResponseEntity<>(
                followService.getFollowingsIdList(UserId),
                HttpStatus.OK
        );
    }

    @GetMapping("following/{UserId}")
    public ResponseEntity<List<User>> getFollowing(
            @PathVariable String UserId )
    {
        return new ResponseEntity<>(
                followService.getFollowings(UserId),
                HttpStatus.OK
        );
    }

    @PostMapping("/unfollow")
    public ResponseEntity<Void> UnfollowUser(
            @RequestBody UnfollowRequest unfollowRequest

    )
            throws IllegalArgumentException
    {
        followService.unFollowUser(unfollowRequest);
        return new ResponseEntity<>(
                HttpStatus.OK
        );
    }

    @PostMapping("/removefromfollowers")
    public ResponseEntity<Void> UnfollowUser(
            @RequestBody RemoveFromFollowersRequest removeFromFollowersRequest
    )
            throws IllegalArgumentException
    {

            followService.removeFromMyFollowers(removeFromFollowersRequest);

        return new ResponseEntity<>(
                HttpStatus.CREATED
        );

    }

}
