package com.travelapp.service.impl;

import com.travelapp.models.RoleEntity;
import com.travelapp.models.UserEntity;
import com.travelapp.models.dto.RegisterDTO;
import com.travelapp.models.enums.UserRoleEnum;
import com.travelapp.repositories.RolesRepository;
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

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    private RolesRepository rolesRepository;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, RolesRepository rolesRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public void signup(RegisterDTO registerDto) throws Exception {
        Optional<UserEntity> existingUser = this.userRepository.findByEmail(registerDto.getEmail());

        if (existingUser.isPresent()) {
            throw new Exception("A user with that email already exists.");
        }

        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        UserEntity newUser = this.modelMapper.map(registerDto, UserEntity.class);
        RoleEntity role = this.rolesRepository.getUserRole(UserRoleEnum.STANDARD);
        newUser.setRoles(new HashSet<>(List.of(role)));
        this.userRepository.save(newUser);
        login(newUser);
    }

    private void login(UserEntity userEntity) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEntity.getEmail());

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
