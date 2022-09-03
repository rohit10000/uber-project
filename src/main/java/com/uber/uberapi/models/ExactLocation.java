package com.uber.uberapi.models;

import javax.persistence.*;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="exactLocation")
public class ExactLocation extends Auditable{

    private String latitude;
    private String longitude;

}


//multiple entries in the table with the same lat, longitude.
