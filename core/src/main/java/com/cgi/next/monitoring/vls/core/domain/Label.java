package com.cgi.next.monitoring.vls.core.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Data
public class Label {
    @Id
    @GeneratedValue
    private long id;
    @Column(length = 1048576)
    private String base64EncodedLabel;


}
