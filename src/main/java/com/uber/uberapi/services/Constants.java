package com.uber.uberapi.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.uber.uberapi.repositories.DBConstantRepository;

@Service
public class Constants {
    private static final int TEN_MINUTES = 60 * 10 * 1000;

    private final DBConstantRepository dbConstantRepository;

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

    public Long getRideStartOTPExpiryMinutes() {
        return Long.parseLong(constants.getOrDefault("rideStartOTPExpiryMinutes", "3600000"));
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
    

}
