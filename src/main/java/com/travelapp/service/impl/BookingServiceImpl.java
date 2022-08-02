package com.travelapp.service.impl;

import com.travelapp.models.Booking;
import com.travelapp.models.Trip;
import com.travelapp.models.UserEntity;
import com.travelapp.models.dto.BookingCreateDTO;
import com.travelapp.models.dto.BookingPaymentMetadataDTO;
import com.travelapp.repositories.BookingRepository;
import com.travelapp.repositories.TripRepository;
import com.travelapp.repositories.UserRepository;
import com.travelapp.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
    private ModelMapper modelMapper;

    private BookingRepository bookingRepository;

    private TripRepository tripRepository;

    private UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(ModelMapper modelMapper, BookingRepository bookingRepository, TripRepository tripRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.bookingRepository = bookingRepository;
        this.tripRepository = tripRepository;
        this.userRepository = userRepository;
    }


    @Override
    public long bookTrip(BookingCreateDTO bookingCreateDTO, long tripId, long userId) throws Exception {
        Booking newBooking = this.modelMapper.map(bookingCreateDTO, Booking.class);
        Optional<Trip> existingTrip = this.tripRepository.findById(tripId);
        Optional<UserEntity> currentUser = this.userRepository.findById(userId);

        if (existingTrip.isEmpty() || currentUser.isEmpty()) {
            throw new Exception("No such trip");
        }

        newBooking.setTrip(existingTrip.get());
        newBooking.setUser(currentUser.get());

        Booking savedBooking = this.bookingRepository.save(newBooking);
        existingTrip.get().getBookings().add(savedBooking);
        this.tripRepository.save(existingTrip.get());
        return savedBooking.getId();
    }

    @Override
    public BigDecimal getPriceForBooking(long bookingId) throws Exception {
        Optional<Booking> optionalBooking = this.bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) throw new Exception("No such booking");

        return  optionalBooking.get().getTrip().getPrice();
    }

    @Override
    public String getFeatureRequestForBooking(long bookingId) throws Exception {
        Optional<Booking> optionalBooking = this.bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) throw new Exception("No such booking");

        return optionalBooking.get().getComment();
    }

    @Override
    public String getPhoneForBooking(long bookingId) throws Exception {
        Optional<Booking> optionalBooking = this.bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) throw new Exception("No such booking");

        return optionalBooking.get().getPhoneNumber();
    }

    @Override
    public BookingPaymentMetadataDTO getBookingMetadata(long bookingId) throws Exception {
        Optional<Booking> optionalBooking = this.bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) throw new Exception("No such booking");

        return this.modelMapper.map(optionalBooking.get(), BookingPaymentMetadataDTO.class);
    }

    @Override
    public void completePayment(long bookingId) throws Exception {
        Booking booking = this.bookingRepository.findById(bookingId).get();
        booking.setPayed(true);
    }
}
