package com.rOushAn.cabcore.service.implementations;

import com.rOushAn.cabcore.dtos.LoginRequestDto;
import com.rOushAn.cabcore.dtos.LoginResponseDto;
import com.rOushAn.cabcore.dtos.SignupDto;
import com.rOushAn.cabcore.dtos.UserDto;
import com.rOushAn.cabcore.entities.User;
import com.rOushAn.cabcore.security.JwtService;
import com.rOushAn.cabcore.service.AuthService;
import com.rOushAn.cabcore.service.UserService;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtService jwtService, UserService userService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String[] login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String accessToken = jwtService.getAccessJwtToken(user);
        String refreshToken = jwtService.getRefreshJwtToken(user);

        return new String[]{accessToken, refreshToken};
    }

    @Transactional
    @Override
    public UserDto signup(SignupDto signupDto) {
        return null;
    }


    @Override
    public LoginResponseDto refreshToken(String refreshToken) {

        Long userId = jwtService.getUserId(refreshToken);
        User user = userService.getUserFromId(userId);
        if (user == null) throw new AuthenticationCredentialsNotFoundException("User not found");

        String accessToken = jwtService.getAccessJwtToken(user);
        return new LoginResponseDto(accessToken);
    }
}