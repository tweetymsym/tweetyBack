package com.tweety.userservice.service;


import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.mapper.UserMapper;
import com.tweety.userservice.model.User;
import com.tweety.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public User createUser(CreateUserDto dto)
    {
        User user = userMapper.createUserDtoToUser(dto);
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }



    public User updateLastTweet(String userId, ZonedDateTime date)
    {
        Optional<User> user = userRepository.findById(userId);
        user.get().setLastTweet(date);
        return userRepository.save(user.get());
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public  Optional<User> getUserById(String UserId) {
        return userRepository.findById(UserId);
    }

    public void deleteUserById(String UserId) {
         userRepository.deleteById(UserId);
    }








}
