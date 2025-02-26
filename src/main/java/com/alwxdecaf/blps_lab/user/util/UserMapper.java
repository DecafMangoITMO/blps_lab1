package com.alwxdecaf.blps_lab.user.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.alwxdecaf.blps_lab.user.dto.RegisterUserDto;
import com.alwxdecaf.blps_lab.user.dto.UserDto;
import com.alwxdecaf.blps_lab.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterUserDto createUserDto);

    public User toEntity(UserDto userDto);

    public UserDto toDto(User user);

}
