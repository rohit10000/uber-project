package com.uber.uberapi.models;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="namedLocation")
public class NamedLocation extends Auditable{

    @OneToOne
    private ExactLocation exactLocation;
    private String name;
    private String zipCode;
    private String city;
    private String country;
    private String state;
    
}
