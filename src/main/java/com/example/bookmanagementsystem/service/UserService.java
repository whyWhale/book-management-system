package com.example.bookmanagementsystem.service;

import com.example.bookmanagementsystem.controller.dto.UserCreateRequestDto;
import com.example.bookmanagementsystem.entity.User;
import com.example.bookmanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public void create(UserCreateRequestDto requestDto) {
        User user = userMapper.toUser(requestDto);
        userRepository.save(user);
    }
}
