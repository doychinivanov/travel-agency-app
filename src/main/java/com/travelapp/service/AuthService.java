package com.travelapp.service;

import com.travelapp.models.dto.RegisterDto;

public interface AuthService {

    void signup(RegisterDto registerDto) throws Exception;
}
