package com.travelapp.service;

import com.travelapp.models.dto.BookingCreateDTO;
import com.travelapp.models.dto.BookingPaymentMetadataDTO;

import java.math.BigDecimal;

public interface BookingService {

    long bookTrip(BookingCreateDTO bookingCreateDTO, long tripId, long userId) throws Exception;

    BigDecimal getPriceForBooking(long bookingId) throws Exception;

    String getFeatureRequestForBooking(long bookingId) throws Exception;

    String getPhoneForBooking(long bookingId) throws Exception;

    BookingPaymentMetadataDTO getBookingMetadata(long bookingId) throws Exception;

    void completePayment(long bookingId) throws Exception;
}
