package com.uber.uberapi.services;

import com.uber.uberapi.models.Booking;
import com.uber.uberapi.models.Driver;

public interface DriverMatchingService {

    void assignDriver(Booking booking);
    // figure out what drivers are nearby
    // send notifications to them
    // 

}
