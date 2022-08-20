package com.uber.uberapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="role")
public class Role extends Auditable{

    @Column(unique = true, nullable = false)
    private String name;

    private String description;
}

//Role base authentication
//Permission based authentication
