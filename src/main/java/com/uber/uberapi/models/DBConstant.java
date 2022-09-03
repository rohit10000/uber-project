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
@Table(name="dbconstant")
public class DBConstant extends Auditable{

    @Column(unique = true, nullable = false)
    private String name;
    private String value;

    public Long getAsLong() {
        return Long.parseLong(value);
    }
}
