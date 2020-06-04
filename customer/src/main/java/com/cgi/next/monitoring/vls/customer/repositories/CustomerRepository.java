package com.cgi.next.monitoring.vls.customer.repositories;

import com.cgi.next.monitoring.vls.core.domain.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * Data access for persisted customers
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
