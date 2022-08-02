package com.travelapp.service.impl;

import com.travelapp.models.UserEntity;
import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.repositories.UserRepository;
import com.travelapp.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @Override
    public boolean userHasBookedTrip(long userId, long tripId) throws Exception {
        Optional<UserEntity> user = this.userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new Exception("No such user");
        }

        return user
                .get()
                .getBookings()
                .stream()
                .filter(booking -> booking.getTrip().getId() == tripId)
                .toList()
                .size() > 0;
    }

    @Override
    public void addNewlySpentMoneyToTotalAmount(String userEmail, BigDecimal newAmount) {
        UserEntity user = this.userRepository.findByEmail(userEmail).get();

        BigDecimal totalAmountOfMoneySpent = user.getTotalAmountOfMoneySpent() != null ? user.getTotalAmountOfMoneySpent() : BigDecimal.ZERO;
        user.setTotalAmountOfMoneySpent(totalAmountOfMoneySpent.add(newAmount));
        this.userRepository.save(user);
    }

    @Override
    public BigDecimal getTotalAmountUserSpentOnThePlatform(long userid) {
        UserEntity user = this.userRepository.findById(userid).get();
        return user.getTotalAmountOfMoneySpent();
    }
}
