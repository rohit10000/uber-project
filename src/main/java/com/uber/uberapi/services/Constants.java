package com.uber.uberapi.services;

import com.uber.uberapi.repositories.DBConstantRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class Constants {
    private static final int TEN_MINUTES = 60 * 10 * 1000;
    final DBConstantRepository dbConstantRepository;
    private final Map<String, String> constants = new HashMap<>();

    public Constants(DBConstantRepository dbConstantRepository) {
        this.dbConstantRepository = dbConstantRepository;
        loadConstantsFromDB();
    }

    @Scheduled(fixedRate = TEN_MINUTES)
    private void loadConstantsFromDB() {
        dbConstantRepository.findAll().forEach(dbconstant -> {
            constants.put(dbconstant.getName(), dbconstant.getValue());
        });
    }

    public Integer getRideStartOTPExpiryMinutes() {
        return Integer.parseInt(constants.getOrDefault("rideStartOTPExpiryMinutes", "3600000"));
    }

    public String getSchedulingTopicName() {
        return constants.getOrDefault("schedulingTopicName", "schedulingServiceTopic");
    }

    public String getDriverMatchingTopicName() {
        return constants.getOrDefault("driverMatchingTopicName", "driverMatchingTopic");
    }

    public int getMaxWaitTimeForPreviousRide() {
        return Integer.parseInt(constants.getOrDefault("maxWaitTimeForPreviousRide", "900000"));
    }

    public Integer getBookingProcessBeforeTime() {
        return Integer.parseInt(constants.getOrDefault("bookingProcessBeforeTime", "900000"));
    }

    public String getLocationTrackingTopicName() {
        return constants.getOrDefault("locationTrackingTopicName", "locationTrackingTopic");
    }

    public double getMaxDistanceKmForDriverMatching() {
        return Double.parseDouble(constants.getOrDefault("maxDistanceKmForDriverMatching", "2"));
    }

    public int getMaxDriverETAMinutes() {
        return Integer.parseInt(constants.getOrDefault("maxDriverETAMinutes", "15"));
    }

    public boolean getIsETABasedFilterEnabled() {
        return Boolean.parseBoolean(constants.getOrDefault("isETABasedFilterEnabled", "true"));
    }

    public boolean getIsGenderFilterEnabled() {
        return Boolean.parseBoolean(constants.getOrDefault("isGenderFilterEnabled", "true"));
    }

    public double getDefaultETASpeedKmph() {
        return Double.parseDouble(constants.getOrDefault("defaultETASpeedKmph", "30.0"));
    }
}