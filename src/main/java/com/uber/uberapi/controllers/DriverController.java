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
import com.uber.uberapi.exceptions.InvalidDriverException;
import com.uber.uberapi.models.Booking;
import com.uber.uberapi.models.Driver;
import com.uber.uberapi.models.OTP;
import com.uber.uberapi.models.Review;
import com.uber.uberapi.repositories.BookingRepository;
import com.uber.uberapi.repositories.DriverRepository;
import com.uber.uberapi.repositories.ReviewRepository;
import com.uber.uberapi.services.BookingService;
import com.uber.uberapi.services.DriverMatchingService;
import com.uber.uberapi.services.Constants;


@RestController
@RequestMapping("/driver")
public class DriverController {

    //all endpoints that the driver can use
    //session/jwt based authentication

    @Autowired
    DriverRepository driverRepository;

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    DriverMatchingService driverMatchingService;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BookingService bookingService;

    @Autowired
    Constants constants;

    public Driver getDriverFromId(Long driverId) {
        Optional<Driver> driver = driverRepository.findById(driverId);
        if (driver.isEmpty()) {
            throw new InvalidDriverException("No driver with id" + driverId);
        }

        return driver.get();
    }

    public Booking getDriverBookingFromId(Long bookingId, Driver driver) {

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingId);

        if (optionalBooking.isEmpty()) {
            throw new InvalidBookingException("No booking with id " + bookingId);
        }

        Booking booking = optionalBooking.get();

        if (!booking.getDriver().equals(driver)) {
            throw new InvalidBookingException("Driver " + driver.getId() + " has no such booking " + bookingId);
        }

        return booking;
    }

    @GetMapping("/{driverId}")
    public Driver getDriver(@RequestParam(name="driverId") Long driverId) {
        //make sure that the driver is authenticated and has the same driver id as accepted.

        return getDriverFromId(driverId);
    }

    //rest api
    //uri, request -> mark the driver as unavailable?

    @PutMapping("/{driverId}")
    public void changeAvailability(@RequestParam(name="driverId") Long driverId, @RequestBody Boolean available) {
        
        Driver driver = getDriverFromId(driverId);
        driver.setAvailable(available);
        driverRepository.save(driver);

    }

    @GetMapping("{driverId}/bookings")
    public List<Booking> getAllBookings(@RequestParam(name="driverId") Long driverId) {
        Driver driver = getDriverFromId(driverId);
        return driver.getBookings();
    }

    @GetMapping("{driverId}/bookings/{bookingId}")
    public Booking getBooking(@RequestParam(name="driverId") Long driverId, @RequestParam(name="bookingId") Long bookingId) {

        Driver driver = getDriverFromId(driverId);
        Booking booking = getDriverBookingFromId(bookingId, driver);

        if (!booking.getDriver().equals(driver)) {
            throw new InvalidBookingException("Driver " + driverId + " has no such booking " + bookingId);
        }

        return booking;
    }

    @PostMapping("{driverId}/bookings/{bookingId}")
    public void acceptBooking(@RequestParam(name="driverId") Long driverId, @RequestParam(name="bookingId") Long bookingId) {
        Driver driver = getDriverFromId(driverId);
        Booking booking = getDriverBookingFromId(bookingId, driver);

        bookingService.acceptBooking(driver, booking);
    }

    @DeleteMapping("{driverId}/bookings/{bookingId}")
    public void cancelBooking(@RequestParam(name="driverId") Long driverId, @RequestParam(name="bookingId") Long bookingId) {

        Driver driver = getDriverFromId(driverId);
        Booking booking = getDriverBookingFromId(bookingId, driver);

        //push this to the task queue - producer.
        bookingService.cancelByDriver(driver, booking);
    }

    //rate the booking
    // start the ride
    // end the ride


    @PatchMapping("{driverId}/bookings/{bookingId}/start")
    public void startRide(@RequestParam(name="driverId") Long driverId, @RequestParam(name="bookingId") Long bookingId, @RequestBody OTP otp) {

        Driver driver = getDriverFromId(driverId);
        Booking booking = getDriverBookingFromId(bookingId, driver);

        //confirm the OTP
        // the ride is currently in the correct ride

        booking.startRide(otp, constants.getRideStartOTPExpiryMinutes());
        bookingRepository.save(booking);
    }

    @PatchMapping("{driverId}/bookings/{bookingId}/end")
    public void endRide(@RequestParam(name="driverId") Long driverId, @RequestParam(name="bookingId") Long bookingId) {

        Driver driver = getDriverFromId(driverId);
        Booking booking = getDriverBookingFromId(bookingId, driver);
        booking.endRide();
        driverRepository.save(driver);
        bookingRepository.save(booking);
    }

    @PatchMapping("{driverId}/bookings/{bookingId}/rate")
    public void rateRide(@RequestParam(name="driverId") Long driverId, @RequestParam(name="bookingId") Long bookingId, @RequestBody Review data) { // reflection for the data is used.

        Driver driver = getDriverFromId(driverId);
        Booking booking = getDriverBookingFromId(bookingId, driver);

        Review review = Review.builder()
                            .note(data.getNote())
                            .ratingOutOfFive(data.getRatingOutOfFive())
                            .build();

        booking.setReviewByDriver(review);
        reviewRepository.save(review);
        bookingRepository.save(booking);
    }
    
}

