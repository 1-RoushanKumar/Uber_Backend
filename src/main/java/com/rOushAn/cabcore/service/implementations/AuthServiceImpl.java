package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.dtos.LoginRequestDto;
import com.rOushAn.cabcore.dtos.SignupDto;
import com.rOushAn.cabcore.dtos.UserDto;
import com.rOushAn.cabcore.service.AuthService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public String[] login(LoginRequestDto loginRequestDto) {
        return null;
    }

    @Transactional
    @Override
    public UserDto signup(SignupDto signupDto) {
        return null;
    }

}