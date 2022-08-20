package com.uber.uberapi.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="car")
public class Car extends Auditable{

    private String plateNumber;
    private String brandAndModel;

    @Enumerated(value = EnumType.STRING)
    private CarType carType;

    @ManyToOne
    private Color color;
    
    @OneToOne
    private Driver driver;


}
