package com.tweety.userservice.controller;

import com.tweety.userservice.dto.BlockRequestDto;
import com.tweety.userservice.dto.UnBlockRequestDto;
import com.tweety.userservice.model.User;
import com.tweety.userservice.service.BlockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/users/block")
@Slf4j
@CrossOrigin("*")
public class BlockController {

    private BlockService blockService;

    @PostMapping("")
    public ResponseEntity<Void> blockUser(
            @RequestBody BlockRequestDto blockRequest
    ) {
       blockService.blockUser(blockRequest);
       return new ResponseEntity<>(
               HttpStatus.CREATED
       );
    }

    @DeleteMapping("")
    public ResponseEntity<Void> unblockUser(
            @RequestBody UnBlockRequestDto unBlockRequest
    ) {
        blockService.UnblockUser(unBlockRequest);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<User>> getBlockedUsers(
            @PathVariable("userId") String userId
    ) {
        return  new ResponseEntity<>(
                blockService.getBlockedUsers(userId),
                HttpStatus.OK
        );
    }

}
