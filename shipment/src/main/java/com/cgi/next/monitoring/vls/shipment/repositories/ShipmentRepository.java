package com.cgi.next.monitoring.vls.shipment.repositories;

import com.cgi.next.monitoring.vls.core.domain.Shipment;
import org.springframework.data.repository.CrudRepository;

/**
 * Data access to for persisted shipments
 */
public interface ShipmentRepository extends CrudRepository<Shipment, Long> {
    Shipment findByShipmentNumber(String shipmentNumber);
}
