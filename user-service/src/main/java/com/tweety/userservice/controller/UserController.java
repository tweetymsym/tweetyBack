package com.tweety.userservice.controller;

import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.model.User;
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

@AllArgsConstructor
@RestController
@RequestMapping("api/user")
@Slf4j
@CrossOrigin("*")
public class UserController {

    private UserService userService;
    private com.tweety.userservice.service.followService followService;

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

    @GetMapping("/detail/{userID}")
    public Map<String,List<User>> GetDetailed(@PathVariable String userID)
    {
        List<User> following=followService.getFollowings(userID);
        List<User> all= userService.getAllUsers();
        Map<String,List<User>> hashMap=new HashMap<>();
        hashMap.put("not",new ArrayList<User>());
        hashMap.put("yes",new ArrayList<User>());
        Stream<User> stream = all.stream();

// Use the filter method to remove the user with the specified ID
        List<User> updatedUsers = stream.filter(user -> user.getId().equals(userID)==false)
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


            List<User> lis=hashMap.get("not");
            lis.add(updatedUsers.get(i));
            hashMap.put("not",lis);
        }
        for (int i=0;i<following.size();i++)
        {
            List<User> lis=hashMap.get("yes");
            lis.add(following.get(i));
            hashMap.put("yes",lis);
        }



        return hashMap;


    }

    @DeleteMapping("/{userID}")
    public ResponseEntity<Void> DeleteUser(@PathVariable String userID)  {
            userService.deleteUserById(userID);
        return  new ResponseEntity<>(
            HttpStatus.NO_CONTENT
    );
    }


    @GetMapping("/{userID}")
    public ResponseEntity<User> GetUser(@PathVariable String userID)  {
        Optional<User> user = userService.getUserById(userID);
        return  new ResponseEntity<>(
                user.get(),
                HttpStatus.OK
        );
    }

}
