package com.alwxdecaf.blps_lab.user.service;

import com.alwxdecaf.blps_lab.user.dao.UserRepository;
import com.alwxdecaf.blps_lab.user.dto.RegisterUserDto;
import com.alwxdecaf.blps_lab.user.dto.UserDto;
import com.alwxdecaf.blps_lab.user.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(UserMapper::toDto)
                .toList();
    }

    public UserDto register(RegisterUserDto registerUserDto) {
        return UserMapper.toDto(userRepository.save(UserMapper.toEntity(registerUserDto)));
    }

}
