package com.tweety.userservice.controller;

import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.dto.UserDetailsDto;
import com.tweety.userservice.model.User;
import com.tweety.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping("")
    public ResponseEntity<Void> createUser(
            @Valid @RequestBody CreateUserDto dto
    ) {
        userService.createUser(dto);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );

    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser() {
        //if (users.size()==0) return  new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("userId") String userId
    ) {
        userService.deleteUserById(userId);
        return new ResponseEntity<>(
            HttpStatus.NO_CONTENT
        );
    }


    @GetMapping("/{userId}")
    public ResponseEntity<UserDetailsDto> getUser(
            @PathVariable("userId") String userId
    ) {
        UserDetailsDto user = userService.getUserById(userId);
        return  new ResponseEntity<>(
                user,
                HttpStatus.OK
        );
    }

}
