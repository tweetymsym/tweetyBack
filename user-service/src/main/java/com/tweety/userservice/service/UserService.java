package com.tweety.userservice.service;


import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.dto.UserDetailsDto;
import com.tweety.userservice.exception.EmailAlreadyExistsException;
import com.tweety.userservice.exception.UserIdNotFoundException;
import com.tweety.userservice.exception.UsernameAlreadyExistsException;
import com.tweety.userservice.mapper.UserMapper;
import com.tweety.userservice.model.User;
import com.tweety.userservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public User createUser(CreateUserDto dto) {
        User user = userMapper.createUserDtoToUserMapper(dto);
        if (userRepository.existsByUserName(dto.getUserName()))
            throw new UsernameAlreadyExistsException(dto.getUserName());
        if (userRepository.existsByEmail(dto.getEmail()))
            throw new EmailAlreadyExistsException(dto.getEmail());
        user.setId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }


    public User updateLastTweet(String userId, Instant date) {
        Optional<User> userOptional = userRepository.findById(userId);
        User user = userOptional.orElseThrow(
                () -> new UserIdNotFoundException(userId)
        );
        user.setLastTweet(date);
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public UserDetailsDto getUserById(String userId) {
        User user =  userRepository.findById(userId).orElseThrow(
                () -> new UserIdNotFoundException(userId)
        );
        return userMapper.userToUserDetailsDtoMapper(user);
    }

    public boolean userExistsById(String userId) {
        return userRepository.existsById(userId);
    }

    public void deleteUserById(String userId) {
        if (!userRepository.existsById(userId))
            throw new UserIdNotFoundException(userId);

        userRepository.deleteById(userId);
    }








}
