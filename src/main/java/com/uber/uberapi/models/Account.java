package com.uber.uberapi.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
// import javax.persistence.FetchType;
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

    //(fetch = FetchType.EAGER)

    @ManyToMany
    private List<Role> roles = new ArrayList<>();

}


// types of inheritance are available for databases
// OOP <-/-> databases
// single-table inheritance - all the columns from all the subclasses are present in 1 table
//                          - disadvantage: sparse table
// per-table-inheritance - each subclass has its own table with a copy of the parent classes columns
//                       - lose our OOP - references cannot be made to the superclass
// composition-based-inheritance - each subclass has a foreign key to the superclass object
//                       - one of the best ways
//                       - disadvantage - we have to use table joins - expensive
// mapped-super-class - the super class is abstract - no table for the superclass

