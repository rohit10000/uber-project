package com.uber.uberapi.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.uber.uberapi.exceptions.InvalidOTPException;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="otp")
public class OTP extends Auditable{
    private String code;
    private String sentToNumber;

    public boolean validateEnteredOTP(OTP otp, Long expiryMinutes) {
        if (!code.equals(otp.getCode())) {
            throw new InvalidOTPException();
        }

        // if the createAt + expiryMinutes > currentTime, then it is valid
        //other not

        return true;
    }


}