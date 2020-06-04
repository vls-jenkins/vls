package com.cgi.next.monitoring.vls.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity @Data
public class Customer {
    @GeneratedValue
    @Id
    private long id;
    private String name;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore //avoid infinite JSON recursion
    private Set<Shipment> shipments;



}
