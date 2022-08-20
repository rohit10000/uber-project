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
@Table(name="paymentGateway")
public class PaymentGateway extends Auditable{
    private String name;
}
