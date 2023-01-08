package com.tweety.userservice.mapper;
import com.tweety.userservice.dto.CreateUserDto;
import com.tweety.userservice.dto.UserDetailsDto;
import com.tweety.userservice.dto.UserInListDto;
import com.tweety.userservice.model.User;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface UserMapper {
    User createUserDtoToUserMapper(CreateUserDto dto);
    UserInListDto userToUserInListDtoMapper(User user);
    UserDetailsDto userToUserDetailsDtoMapper(User user);
}
