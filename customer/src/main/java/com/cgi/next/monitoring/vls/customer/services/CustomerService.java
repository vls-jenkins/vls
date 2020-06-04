package com.cgi.next.monitoring.vls.customer.services;

import com.cgi.next.monitoring.vls.core.domain.Customer;

/**
 * Interface with services for customer entities
 */
public interface CustomerService {
    /**
     * Creates a new customer and assigns a unique id to him/her
     * @param customer an object that carries customer relatet attributes
     * @return the passed customer object with an additional id
     */
    Customer createCustomer(Customer customer);

    /**
     * A set of known customers
     * @return an iterable set of all known customers
     */
    Iterable<Customer> getAllCustomers();

    /**
     * Returns a customer with the given id
     * @param id the id of the searched customer
     * @return a Customer with the give {@code id} if existent
     * @throws com.cgi.next.monitoring.vls.core.exceptions.ResourceNotFoundException
     * raised if the customer with the given id could not be found
     */
    Customer getCustomer(long id);
}
