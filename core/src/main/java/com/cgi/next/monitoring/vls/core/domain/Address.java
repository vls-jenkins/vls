package com.cgi.next.monitoring.vls.core.domain;


import lombok.Data;


import javax.persistence.Embeddable;

// @Embeddable lets addresses to be persisted within other database tables.
@Embeddable @Data
public class Address {
    private String line1;
    private String line2;
    private String line3;


}
