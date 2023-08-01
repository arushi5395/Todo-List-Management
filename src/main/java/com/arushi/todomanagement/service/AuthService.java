package com.arushi.todomanagement.service;

import com.arushi.todomanagement.dto.LoginDto;
import com.arushi.todomanagement.dto.RegisterDto;

public interface AuthService {
    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
