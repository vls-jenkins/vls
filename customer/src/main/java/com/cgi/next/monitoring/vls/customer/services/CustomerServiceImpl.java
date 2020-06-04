package com.cgi.next.monitoring.vls.customer.services;

import com.cgi.next.monitoring.vls.core.domain.Customer;
import com.cgi.next.monitoring.vls.core.exceptions.ResourceNotFoundException;
import com.cgi.next.monitoring.vls.customer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomer(long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent()){
            return customer.get();
        }else{
            throw new ResourceNotFoundException("customer with id " + id + " not found");
        }
    }
}
