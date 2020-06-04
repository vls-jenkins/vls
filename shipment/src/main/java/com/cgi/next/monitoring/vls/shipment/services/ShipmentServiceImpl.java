package com.cgi.next.monitoring.vls.shipment.services;

import com.cgi.next.monitoring.vls.addressvalidation.services.AddressValidationService;
import com.cgi.next.monitoring.vls.core.domain.Shipment;
import com.cgi.next.monitoring.vls.customer.services.CustomerService;
import com.cgi.next.monitoring.vls.label.services.LabelService;
import com.cgi.next.monitoring.vls.shipment.repositories.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShipmentServiceImpl implements ShipmentService{

    private final ShipmentRepository shipmentRepository;
    private final CustomerService customerService;
    private final AddressValidationService addressValidationService;
    private final LabelService labelService;

    @Autowired
    public ShipmentServiceImpl(final ShipmentRepository shipmentRepository,
                           final CustomerService customerService,
                           final AddressValidationService addressValidationService,
                           final LabelService labelService){
        this.shipmentRepository = shipmentRepository;
        this.customerService = customerService;
        this.addressValidationService = addressValidationService;
        this.labelService = labelService;
    }

    @Override
    public Shipment createShipment(Shipment shipment, long customerId) {
        return null;
    }

    @Override
    public Set<Shipment> getAllShipmentsOfCustomer(long id) {
        return null;
    }

    @Override
    public Shipment getShipmentByShipmentNumber(String shipmentNumber) {
        return null;
    }
}
