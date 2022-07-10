package com.travelapp.service.impl;

import com.travelapp.models.UserEntity;
import com.travelapp.models.dto.RegisterDto;
import com.travelapp.repositories.UserRepository;
import com.travelapp.service.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean signup(RegisterDto registerDto) {
        Optional<UserEntity> existingUser = this.userRepository.findByEmail(registerDto.getEmail());

        if (existingUser.isPresent()) {
            return false;
        }

        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        UserEntity newUser = this.modelMapper.map(registerDto, UserEntity.class);
        this.userRepository.save(newUser);
        login(newUser);
        return true;
    }

    private void login(UserEntity userEntity) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userEntity.getEmail());

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }
}
