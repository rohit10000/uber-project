package com.uber.uberapi.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
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

    @OneToMany(mappedBy = "paymentGateway")
    private Set<PaymentReceipt> receipts = new HashSet<>();
}
