package com.cgi.next.monitoring.vls.shipment;

import com.cgi.next.monitoring.vls.customer.repositories.CustomerRepository;
import com.cgi.next.monitoring.vls.label.repositories.LabelRepository;
import com.cgi.next.monitoring.vls.shipment.repositories.ShipmentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.cgi.next.monitoring.vls")
@EntityScan(basePackages = "com.cgi.next.monitoring.vls.core.domain")
@EnableJpaRepositories(basePackageClasses = {LabelRepository.class, ShipmentRepository.class, CustomerRepository.class})
public class VLSApplication {

    public static void main(String args[]){
        SpringApplication.run(VLSApplication.class, args);
    }
}
