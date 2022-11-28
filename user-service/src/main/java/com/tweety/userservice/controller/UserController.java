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
        User user  = userService.createUser(dto);

        return new ResponseEntity<>(
                HttpStatus.CREATED
        );

    }


    @GetMapping("/users")
    public List<User> GetAllUser()
    {

        List<User> users= userService.getAllUsers();
        //if (users.size()==0) return  new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.NO_CONTENT);
        return  userService.getAllUsers();
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<Void> DeleteUser(@PathVariable Long userID)  {
            userService.deleteUserById(userID);
        return  new ResponseEntity<>(
            HttpStatus.OK
    );

    }

}
