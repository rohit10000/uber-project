package com.uber.uberapi.services;

import org.springframework.stereotype.Service;

import com.uber.uberapi.models.Booking;

@Service
public class SchedulingServiceImpl implements SchedulingService{

    BookingService bookingService;

    @Override
    public void schedule(Booking booking) {
        // if it is time to activate this booking
        bookingService.acceptBooking(booking.getDriver(), booking);
    }

    public static void main(String[] args) {
        //consumer
    }
    
}
