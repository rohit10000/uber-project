package com.uber.uberapi.models;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="booking")
public class DBConstant extends Auditable{
    private String name;
    private String value;

    public Long getAsLong() {
        return Long.parseLong(value);
    }




    private static Long MINUTES_PER_DAY = 24 * 60L;
    public static Long RIDE_START_OTP_EXPIRY_MINUTES = 72 * MINUTES_PER_DAY; 
}
