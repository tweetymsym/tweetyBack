package com.tweety.userservice.mapper;
import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User createUserDtoToUser(CreateUserDto dto);
}
