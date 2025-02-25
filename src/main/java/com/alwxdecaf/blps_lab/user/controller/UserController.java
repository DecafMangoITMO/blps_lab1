package com.alwxdecaf.blps_lab.user.controller;

import com.alwxdecaf.blps_lab.user.dto.RegisterUserDto;
import com.alwxdecaf.blps_lab.user.dto.UserDto;
import com.alwxdecaf.blps_lab.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> getAll() {
        log.info("Get all users");
        return userService.getAll();
    }

    @PostMapping
    public UserDto register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        log.info("Register new user {}", registerUserDto);
        return userService.register(registerUserDto);
    }

    @PatchMapping("/{user_id}")
    public UserDto setBalance(@PathVariable("user_id") long userId, @RequestParam("balance") double balance) {
        log.info("Set balance {} to user with id: {}", balance, userId);
        return userService.setBalance(userId, balance);
    }

}
