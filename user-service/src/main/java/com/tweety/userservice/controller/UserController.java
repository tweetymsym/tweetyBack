package com.tweety.userservice.controller;

import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.dto.FollowRequest;
import com.tweety.userservice.model.User;
import com.tweety.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/user")
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> createUser(
            @RequestBody CreateUserDto dto
            )
    {
        userService.createUser(dto);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );

    }

    @GetMapping("")
    public ResponseEntity<List<User>> GetAllUser() {
        //if (users.size()==0) return  new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> DeleteUser(@PathVariable Long userID)  {
            userService.deleteUserById(userID);
        return  new ResponseEntity<>(
            HttpStatus.NO_CONTENT
    );
    }

}
