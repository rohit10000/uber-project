package com.uber.uberapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uber.uberapi.exceptions.InvalidBookingException;
import com.uber.uberapi.exceptions.InvalidPassengerException;
import com.uber.uberapi.models.Booking;
import com.uber.uberapi.models.BookingStatus;
import com.uber.uberapi.models.Passenger;
import com.uber.uberapi.models.OTP;
import com.uber.uberapi.models.Review;
import com.uber.uberapi.repositories.BookingRepository;
import com.uber.uberapi.repositories.PassengerRepository;
import com.uber.uberapi.repositories.ReviewRepository;
import com.uber.uberapi.services.BookingService;
import com.uber.uberapi.services.DriverMatchingService;

@RestController
@RequestMapping("/Passenger")
public class PassengerController {

    //all endpoints that the Passenger can use
    //session/jwt based authentication

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DriverMatchingService driverMatchingService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookingService bookingService;

    public Passenger getPassengerFromId(Long PassengerId) {
        Optional<Passenger> passenger = passengerRepository.findById(PassengerId);
        if (passenger.isEmpty()) {
            throw new InvalidPassengerException("No Passenger with id" + PassengerId);
        }

        return passenger.get();
    }

    public Booking getPassengerBookingFromId(Long bookingId, Passenger Passenger) {

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) {
            throw new InvalidBookingException("No booking with id " + bookingId);
        }

        Booking booking = optionalBooking.get();

        if (!booking.getPassenger().equals(Passenger)) {
            throw new InvalidBookingException("Passenger " + Passenger.getId() + " has no such booking " + bookingId);
        }

        return booking;
    }

    @GetMapping("/{PassengerId}")
    public Passenger getPassenger(@RequestParam(name="PassengerId") Long PassengerId) {
        //make sure that the Passenger is authenticated and has the same Passenger id as accepted.

        return getPassengerFromId(PassengerId);
    }

    @GetMapping("{PassengerId}/bookings")
    public List<Booking> getAllBookings(@RequestParam(name="PassengerId") Long passengerId) {
        Passenger passenger = getPassengerFromId(passengerId);
        return passenger.getBookings();
    }

    @GetMapping("{PassengerId}/bookings/{bookingId}")
    public Booking getBooking(@RequestParam(name="PassengerId") Long passengerId, @RequestParam(name="bookingId") Long bookingId) {

        Passenger passenger = getPassengerFromId(passengerId);
        Booking booking = getPassengerBookingFromId(bookingId, passenger);

        if (!booking.getPassenger().equals(passenger)) {
            throw new InvalidBookingException("Passenger " + passengerId + " has no such booking " + bookingId);
        }

        return booking;
    }

    @PostMapping("{PassengerId}/bookings")
    public void requestBooking(@RequestParam(name="PassengerId") Long PassengerId, @RequestBody Booking data) {
        Passenger passenger = getPassengerFromId(PassengerId);

        Booking booking = Booking.builder().build();
        bookingService.createBooking(booking);

        bookingRepository.save(booking);
        passengerRepository.save(passenger);

        //todo

    }

    @DeleteMapping("{PassengerId}/bookings/{bookingId}")
    public void cancelBooking(@RequestParam(name="PassengerId") Long passengerId, @RequestParam(name="bookingId") Long bookingId) {

        Passenger passenger = getPassengerFromId(passengerId);
        Booking booking = getPassengerBookingFromId(bookingId, passenger);

        bookingService.cancelByPassenger(passenger, booking);
    }

    @PatchMapping("{PassengerId}/bookings/{bookingId}/rate")
    public void rateRide(@RequestParam(name="PassengerId") Long passengerId, @RequestParam(name="bookingId") Long bookingId, @RequestBody Review data) { // reflection for the data is used.

        Passenger passenger = getPassengerFromId(passengerId);
        Booking booking = getPassengerBookingFromId(bookingId, passenger);

        Review review = Review.builder()
                            .note(data.getNote())
                            .ratingOutOfFive(data.getRatingOutOfFive())
                            .build();

        booking.setReviewByPassenger(review);
        reviewRepository.save(review);
        bookingRepository.save(booking);
    }
    
}

