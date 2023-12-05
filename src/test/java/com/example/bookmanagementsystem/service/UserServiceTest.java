package com.example.bookmanagementsystem.service;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.SpyBean;

import com.example.bookmanagementsystem.controller.dto.UserCreateRequestDto;
import com.example.bookmanagementsystem.entity.Address;
import com.example.bookmanagementsystem.entity.User;
import com.example.bookmanagementsystem.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Spy
	UserMapper userMapper;

	@Test
	@DisplayName("사용자를 생성한다.")
	void testCreate(){
	    //given
		Address givenAddress = new Address("서울", "마표대로", "231-2", "220202동");
		UserCreateRequestDto requestDto = new UserCreateRequestDto("John", givenAddress,
				"whyWhale@naver.com", "010-111-2222");
		// when
		userService.create(requestDto);
	    //then
		verify(userRepository, times(1)).save(any(User.class));
	}

}