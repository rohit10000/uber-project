package com.uber.uberapi.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account")

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Account extends Auditable{

    private String username;
    private String password;

    @ManyToMany
    private List<Role> roles = new ArrayList<>();
}
