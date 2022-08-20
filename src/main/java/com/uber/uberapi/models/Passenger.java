package com.uber.uberapi.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="passenger")
public class Passenger extends Auditable{

    @OneToOne(cascade = CascadeType.ALL)
    private Account account;

    private String name;

    @Enumerated(value=EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "passenger")
    private List<Booking> bookings = new ArrayList<>();

    @Temporal(value = TemporalType.DATE)
    private Date dob;

    @OneToOne
    private ExactLocation home;

    @OneToOne
    private ExactLocation work;
    
    @OneToOne
    private ExactLocation lastKnownLocation;


}


// varchar(size)
//text
//json
//blob
//int
//