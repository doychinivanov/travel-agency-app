package com.travelapp.service;

import com.travelapp.models.dto.RegisterDto;

public interface AuthService {

    boolean signup(RegisterDto registerDto);
}
