package com.tweety.userservice.service;


import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.dto.FollowRequest;
import com.tweety.userservice.followership.followService;
import com.tweety.userservice.mapper.UserMapper;
import com.tweety.userservice.model.User;
import com.tweety.userservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;



    public User createUser(CreateUserDto dto)
    {
        User user = userMapper.createUserDtoToUser(dto);
        return userRepository.save(user);
    }



    public User UpdateLastTweet(Long userId, ZonedDateTime date)
    {

        Optional<User> user = userRepository.findById(userId);
        user.get().setLastTweet(date);
        return userRepository.save(user.get());
    }


    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }


    public  Optional<User> getUserById(Long UserId)
    {


        return userRepository.findById(UserId);
    }

    public void deleteUserById(Long UserId)
            throws Exception
    {
         userRepository.deleteById(UserId);
    }








}
