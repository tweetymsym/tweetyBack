package com.tweety.userservice.controller;

import com.tweety.userservice.dto.BlockRequest;
import com.tweety.userservice.dto.UnBlockRequest;
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
@RequestMapping("api/block")
@Slf4j
public class BlockController {

    private BlockService blockService;

    @PostMapping("")

    public ResponseEntity<Void> blockUser(
            @RequestBody BlockRequest blockRequest
            )
    {
       blockService.blockUser(blockRequest);

       return new ResponseEntity<>(
               HttpStatus.CREATED
       );

    }


    @DeleteMapping("")

    public ResponseEntity<Void> UnblockUser(
            @RequestBody UnBlockRequest unBlockRequest
    )
    {
        blockService.UnblockUser(unBlockRequest);

        return new ResponseEntity<>(
                HttpStatus.CREATED
        );

    }


    @GetMapping("/{UserId}")
    public ResponseEntity<List<User>> getBlockedUsers(
            @PathVariable String UserId
    )

    {
        return  new ResponseEntity<>(
                blockService.getBlockedUsers(UserId),
                HttpStatus.OK
        );
    }

}
