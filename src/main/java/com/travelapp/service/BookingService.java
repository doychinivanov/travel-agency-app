package com.travelapp.service;

import com.travelapp.models.dto.BookingCreateDTO;

public interface BookingService {

    void bookTrip(BookingCreateDTO bookingCreateDTO, long tripId, long userId) throws Exception;
}
