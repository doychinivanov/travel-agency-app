package com.travelapp.service;

import com.travelapp.models.dto.TripCardDTO;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {

    List<TripCardDTO> getUserBookings(long userId);

    boolean userHasBookedTrip(long userId, long tripId) throws Exception;

    void addNewlySpentMoneyToTotalAmount(String userEmail, BigDecimal newAmount);
}
