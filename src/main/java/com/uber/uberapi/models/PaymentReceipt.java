package com.uber.uberapi.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="paymentReceipt")
public class PaymentReceipt extends Auditable{
     private Double amount;

     @ManyToOne
     private PaymentGateway paymentGateway;
}
