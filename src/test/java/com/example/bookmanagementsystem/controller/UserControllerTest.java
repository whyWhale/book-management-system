package com.example.bookmanagementsystem.controller;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.example.bookmanagementsystem.controller.dto.UserCreateRequestDto;
import com.example.bookmanagementsystem.entity.Address;
import com.example.bookmanagementsystem.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(UserController.class)
class UserControllerTest {

	final String URI_PREFIX = "/api/users";

	@MockBean
	UserService userService;

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@DisplayName("회원 가입을 한다.")
	@Test
	void testCreate() throws Exception {
		/// given
		Address givenAddress = new Address("서울", "마표대로", "231-2", "220202동");
		UserCreateRequestDto requestDto = new UserCreateRequestDto("John", givenAddress,
				"whyWhale@naver.com", "010-111-2222");
		// when
		ResultActions perform = mockMvc.perform(post(URI_PREFIX)
				.content(objectMapper.writeValueAsString(requestDto))
				.contentType(MediaType.APPLICATION_JSON));
		// then
		perform.andExpect(status().isOk());
		verify(userService, times(1)).create(any(UserCreateRequestDto.class));
	}

	@DisplayName("[입력 유효성 검증] - 회원가입 할 때, ")
	@Nested
	class UserValidationTest {
		static Stream<Arguments> getBlankField() {
			String givenName = "John";
			String givenCity = "서울";
			String givenStreet = "마표대로";
			String givenZipCode = "231-2";
			String givenDetail = "220202동";
			Address givenAddress = new Address(givenCity, givenStreet, givenZipCode, givenDetail);
			String givenEmail = "whyWhale@naver.com";
			String givenPhoneNumber = "010-111-2222";

			return Stream.of(
					Arguments.arguments(new UserCreateRequestDto("", givenAddress, givenEmail, givenPhoneNumber)),
					Arguments.arguments(new UserCreateRequestDto(null, givenAddress, givenEmail, givenPhoneNumber)),
					Arguments.arguments(new UserCreateRequestDto(givenName, null, givenEmail, givenPhoneNumber)),
					Arguments.arguments(new UserCreateRequestDto(givenName, null, "", givenPhoneNumber)),
					Arguments.arguments(new UserCreateRequestDto(givenName, givenAddress, null, givenPhoneNumber)),
					Arguments.arguments(new UserCreateRequestDto(givenName, givenAddress, givenEmail, null)),
					Arguments.arguments(new UserCreateRequestDto(givenName, givenAddress, givenEmail, "")),
					Arguments.arguments(new UserCreateRequestDto(
							givenName,
							new Address("", givenStreet, givenZipCode, givenDetail),
							givenEmail,
							givenPhoneNumber)),
					Arguments.arguments(
							new UserCreateRequestDto(givenName,
									new Address(null, givenStreet, givenZipCode, givenDetail),
									givenEmail,
									givenPhoneNumber))
			);
		}

		@DisplayName("입력하지 않는 문구가 존재하면 예외가 발생한다.")
		@ParameterizedTest(name = "{index}:  userCreateRequestDto {0}")
		@MethodSource("getBlankField")
		void failNotBlankWord(UserCreateRequestDto requestDto) throws Exception {
			//given
			//when
			ResultActions perform = getPerform(requestDto);
			//then
			perform.andExpect(status().isBadRequest());
		}

		@DisplayName("잘못된 이메일 형식은 예외가 발생한다.")
		@ParameterizedTest(name = "{index}:  email : {0}")
		@ValueSource(strings = {"whywhale@", "whyWhale", "whyWhale@naver.", "whyWhale@naver."})
		void failInvalidEmail(String invalidEmail) throws Exception {
			//given
			Address givenAddress = new Address("서울", "마표대로", "231-2", "220202동");
			UserCreateRequestDto requestDto = new UserCreateRequestDto("John", givenAddress,
					invalidEmail, "010-111-2222");
			//when
			ResultActions perform = getPerform(requestDto);
			//then
			perform.andExpect(status().isBadRequest());
		}

		@DisplayName("잘못된 휴대폰 전화번호 형식은 예외가 발생한다.")
		@ParameterizedTest(name = "{index}:  email : {0}")
		@ValueSource(strings = {"010-111111@", "1-1-1", "d01-020-0200", "0d1-123-1234", "001-000,2023"})
		void failInvalidPhoneNumber(String invalidPhoneNumber) throws Exception {
			//given
			Address givenAddress = new Address("서울", "마표대로", "231-2", "220202동");
			UserCreateRequestDto requestDto = new UserCreateRequestDto("John", givenAddress,
					"whyWhale@naver.com", invalidPhoneNumber);
			//when
			ResultActions perform = getPerform(requestDto);
			//then
			perform.andExpect(status().isBadRequest());
		}

		ResultActions getPerform(UserCreateRequestDto requestDto) throws Exception {
			return mockMvc.perform(post(URI_PREFIX)
					.content(objectMapper.writeValueAsString(requestDto))
					.contentType(MediaType.APPLICATION_JSON));
		}

	}
}