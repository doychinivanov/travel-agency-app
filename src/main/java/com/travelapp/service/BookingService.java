package com.travelapp.service;

import com.travelapp.models.dto.BookingCreateDTO;

import java.math.BigDecimal;

public interface BookingService {

    long bookTrip(BookingCreateDTO bookingCreateDTO, long tripId, long userId) throws Exception;

    BigDecimal getPriceForBooking(long bookingId) throws Exception;
}
