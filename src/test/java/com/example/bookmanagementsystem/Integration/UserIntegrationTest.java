package com.example.bookmanagementsystem.Integration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.bookmanagementsystem.controller.dto.UserCreateRequestDto;
import com.example.bookmanagementsystem.entity.Address;
import com.example.bookmanagementsystem.entity.User;
import com.example.bookmanagementsystem.repository.UserRepository;
import com.example.bookmanagementsystem.service.UserService;

@SpringBootTest
public class UserIntegrationTest {

	@Autowired
	UserService userService;

	@Autowired
	UserRepository userRepository;

	@Test
	@DisplayName("사용자를 생성한다.")
	void testCreate() {
		//given
		Address givenAddress = new Address("서울", "마표대로", "231-2", "220202동");
		UserCreateRequestDto requestDto = new UserCreateRequestDto("John", givenAddress,
				"whyWhale@naver.com", "010-111-2222");
		// when
		userService.create(requestDto);
		User user = userRepository.findAll().get(0);
		//then
		Assertions.assertThat(user.getName()).isEqualTo(requestDto.name());
		Assertions.assertThat(user.getAddress().city()).isEqualTo(requestDto.address().city());
		Assertions.assertThat(user.getAddress().street()).isEqualTo(requestDto.address().street());
		Assertions.assertThat(user.getAddress().zipCode()).isEqualTo(requestDto.address().zipCode());
		Assertions.assertThat(user.getAddress().detail()).isEqualTo(requestDto.address().detail());
		Assertions.assertThat(user.getEmail()).isEqualTo(requestDto.email());
		Assertions.assertThat(user.getPhoneNumber()).isEqualTo(requestDto.phoneNumber());
	}

}
