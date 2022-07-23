package com.travelapp.service;

import com.travelapp.models.dto.RegisterDTO;

public interface AuthService {

    void signup(RegisterDTO registerDto) throws Exception;
}
