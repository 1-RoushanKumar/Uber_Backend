package com.rOushAn.cabcore.service;

import com.rOushAn.cabcore.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserFromId(Long userId);
}