package com.tweety.userservice.followership;

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
public class followController {

    private followService followService;


    @PostMapping("/follow")
    public ResponseEntity<Void> FollowUser(
            @RequestBody FollowRequest followRequest
    )
    {

        followService.FollowUser(followRequest);

        return new ResponseEntity<>(
                HttpStatus.CREATED
        );

    }

    @GetMapping("followers/{UserId}")
    public List<User> getFollowers(
             @PathVariable Long UserId )
    {
        List<User> followers= followService.getFollowers(UserId);
        return followers;
    }

    @GetMapping("following/{UserId}")
    public List<User> getFollowing(
            @PathVariable Long UserId )
    {
        List<User> followers= followService.getFollowings(UserId);
        return followers;
    }

   













}
