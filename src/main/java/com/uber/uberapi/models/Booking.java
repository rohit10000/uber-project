package com.uber.uberapi.models;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.criteria.Predicate.BooleanOperator;

import com.uber.uberapi.exceptions.InvalidActionForBookingStateException;
import com.uber.uberapi.exceptions.InvalidOTPException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="booking", indexes = {                      //look for many to one relationship
    @Index(columnList = "passenger_id"),
    @Index(columnList = "driver_id"),
})

public class Booking extends Auditable{

    @ManyToOne
    private Passenger passenger;

    @ManyToOne
    private Driver driver;

    @Enumerated(value = EnumType.STRING)
    private BookingType bookingType;

    @OneToOne
    private Review reviewByPassenger;

    @OneToOne
    private Review reviewByDriver;

    @OneToOne
    private PaymentReceipt paymentReceipt;

    private BookingStatus bookingStatus;

    @OneToMany
    @JoinTable(
        name="booking_route",
        joinColumns = @JoinColumn(name="booking_id"),
        inverseJoinColumns = @JoinColumn(name="exact_location_id"),
        indexes = {
            @Index(columnList = "booking_id")
        }
    )
    @OrderColumn(name = "location_index")
    private List<ExactLocation> route = new ArrayList<>();

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToOne
    private OTP rideStartOTP;

    private long totalDistanceMeters;

    public void startRide(OTP otp) {
        if (!bookingStatus.equals(BookingStatus.CAB_ARRIVED)) {
            throw new InvalidActionForBookingStateException("Cannot start the ride before the driver arrives");
        }

        if (!rideStartOTP.validateEnteredOTP(otp, DBConstant.RIDE_START_OTP_EXPIRY_MINUTES))
            throw new InvalidOTPException();

        bookingStatus = BookingStatus.IN_RIDE;

    }

    public void endRide() {
        if (!bookingStatus.equals(BookingStatus.IN_RIDE)) {
            throw new InvalidActionForBookingStateException("Cannot end as the ride has not started yet");
        }

        bookingStatus = BookingStatus.COMPLETED;
    }

}
