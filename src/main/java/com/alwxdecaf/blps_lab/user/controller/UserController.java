package com.alwxdecaf.blps_lab.user.controller;

import com.alwxdecaf.blps_lab.user.dto.RegisterUserDto;
import com.alwxdecaf.blps_lab.user.dto.UserDto;
import com.alwxdecaf.blps_lab.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDto> findAll() {
        return userService.getAll();
    }

    @PostMapping
    public UserDto register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        return userService.register(registerUserDto);
    }

    @PatchMapping("/{user_id}")
    public UserDto setBalance(@PathVariable("user_id") long userId, @RequestParam("balance") double balance) {
        return userService.setBalance(userId, balance);
    }

}
