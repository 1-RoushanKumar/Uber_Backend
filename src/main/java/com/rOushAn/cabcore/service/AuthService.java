package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.dtos.*;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {
    String[] login(LoginRequestDto loginRequestDto);

    @Transactional
    UserDto signup(SignupDto signupDto);

    @Transactional
    DriverDto onboardNewDriver(Long userId, OnboardDriverDto onboardDriverDto);

    LoginResponseDto refreshToken(String refreshToken);
}
