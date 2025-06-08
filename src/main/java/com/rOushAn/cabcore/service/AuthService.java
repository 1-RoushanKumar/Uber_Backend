package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.dtos.LoginRequestDto;
import com.rOushAn.cabcore.dtos.SignupDto;
import com.rOushAn.cabcore.dtos.UserDto;
import org.springframework.transaction.annotation.Transactional;

public interface AuthService {
    String[] login(LoginRequestDto loginRequestDto);

    @Transactional
    UserDto signup(SignupDto signupDto);
}
