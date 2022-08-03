package com.travelapp.service;

import com.travelapp.models.dto.TripCardDTO;
import com.travelapp.models.dto.UserTableDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface UserService {

    List<TripCardDTO> getUserBookings(long userId);

    boolean userHasBookedTrip(long userId, long tripId) throws Exception;

    void addNewlySpentMoneyToTotalAmount(String userEmail, BigDecimal newAmount);

    BigDecimal getTotalAmountUserSpentOnThePlatform(long userid);

    Set<UserTableDTO> getAllUsers();
}
