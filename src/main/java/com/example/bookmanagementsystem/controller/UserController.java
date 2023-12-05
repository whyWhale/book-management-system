package com.example.bookmanagementsystem.controller;

import com.example.bookmanagementsystem.controller.dto.UserCreateRequestDto;
import com.example.bookmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(path = "/api/users")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void create(
            @Valid
            @RequestBody
            UserCreateRequestDto requestDto) {
        userService.create(requestDto);
    }
}
