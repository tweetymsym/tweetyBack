package com.tweety.userservice.controller;

import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.dto.UserDetailsDto;
import com.tweety.userservice.dto.UserInListDto;
import com.tweety.userservice.model.User;
import com.tweety.userservice.service.FollowService;
import com.tweety.userservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/users")
@Slf4j
@CrossOrigin("*")
public class UserController {

    private UserService userService;
    private FollowService followService;

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
    public ResponseEntity<List<UserInListDto>> getAllUser() {
        //if (users.size()==0) return  new ResponseEntity<List<User>>(userService.getAllUsers(), HttpStatus.NO_CONTENT);
        return  new ResponseEntity<>(
                userService.getAllUsers(),
                HttpStatus.OK
        );
    }


    @GetMapping("/detail/{userID}")
    public Map<String,List<UserInListDto>> GetDetailed(@PathVariable String userID)
    {
        List<UserInListDto> following=followService.getFollowings(userID);
        List<UserInListDto> all= userService.getAllUsers();
        Map<String,List<UserInListDto>> hashMap=new HashMap<>();
        hashMap.put("not",new ArrayList<UserInListDto>());
        hashMap.put("yes",new ArrayList<UserInListDto>());
        Stream<UserInListDto> stream = all.stream();

// Use the filter method to remove the user with the specified ID
        List<UserInListDto> updatedUsers = stream.filter(user -> user.getId().equals(userID)==false)
                .collect(Collectors.toList());

        updatedUsers.removeAll(following);

        for (int i=0;i<updatedUsers.size();i++)
        {
            for (int k =0; k<following.size();k++)
            {
                if(updatedUsers.get(i).getId().equals(following.get(k).getId()))
                {
                    break;
                }
            }


            List<UserInListDto> lis=hashMap.get("not");
            lis.add(updatedUsers.get(i));
            hashMap.put("not",lis);
        }
        for (int i=0;i<following.size();i++)
        {
            List<UserInListDto> lis=hashMap.get("yes");
            lis.add(following.get(i));
            hashMap.put("yes",lis);
        }



        return hashMap;


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
