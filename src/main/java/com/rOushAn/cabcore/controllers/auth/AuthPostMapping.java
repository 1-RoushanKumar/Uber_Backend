package com.rOushAn.cabcore.controllers.auth;

import com.rOushAn.cabcore.dtos.*;
import com.rOushAn.cabcore.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication & Authorization", description = "Endpoints for user signup, login, and token refresh.")
// Tag for the controller
public class AuthPostMapping {

    @Value("${deploy.env}")
    private String deployment;
    private final AuthService authService;

    public AuthPostMapping(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(path = "/signup")
    @Operation(summary = "Register a new user",
            description = "Creates a new user account (rider by default) with the provided credentials.")
    public ResponseEntity<UserDto> signup(@RequestBody SignupDto signupDto) {
        UserDto user = authService.signup(signupDto);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping(path = "/login")
    @Operation(summary = "User login",
            description = "Authenticates a user and returns an access token, setting a refresh token in a secure HTTP-only cookie.")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse httpServletResponse) {
        String[] tokens = authService.login(loginRequestDto);
        Cookie cookie = new Cookie("refreshToken", tokens[1]);
        cookie.setHttpOnly(true);
        cookie.setSecure(deployment.equals("production"));
        cookie.setPath("/auth/refresh");
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(new LoginResponseDto(tokens[0]));
    }

    @PostMapping(path = "/refresh")
    @Operation(summary = "Refresh access token",
            description = "Uses the refresh token (from cookie) to obtain a new access token without re-logging in.")
    public ResponseEntity<LoginResponseDto> refreshToken(HttpServletRequest httpServletRequest) {
        String refreshToken = Arrays
                .stream(httpServletRequest
                        .getCookies())
                .filter(cookie -> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("Refresh token not found"));
        return ResponseEntity.ok(authService.refreshToken(refreshToken));
    }
}