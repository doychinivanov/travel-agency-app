package com.travelapp.service.impl;

import com.travelapp.models.UserEntity;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.repositories.UserRepository;
import com.travelapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TripCardDTO> getUserBookings(long userId) {
        UserEntity currUser = this.userRepository.findById(userId).get();

        return currUser
                .getBookings()
                .stream()
                .map(booking -> this.modelMapper.map(booking.getTrip(), TripCardDTO.class)).toList();
    }
}
