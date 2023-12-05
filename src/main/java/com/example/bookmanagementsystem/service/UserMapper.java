package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.controller.dto.UserCreateRequestDto;
import com.example.bookmanagementsystem.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserCreateRequestDto userCreateRequestDto) {
        return new User(
                userCreateRequestDto.name(),
                userCreateRequestDto.email(),
                userCreateRequestDto.phoneNumber(),
                userCreateRequestDto.address()
        );
    }
}
