package com.cgi.next.monitoring.vls.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity @NoArgsConstructor @Getter @Setter @ToString(exclude = "customer") @EqualsAndHashCode(exclude = "customer")
public class Shipment {
    @GeneratedValue
    @Id
    private long id;
    private String shipmentNumber;

    @Embedded
    private Address recipientAddress;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @JsonIgnore
    @OneToOne
    private Label label;

}
