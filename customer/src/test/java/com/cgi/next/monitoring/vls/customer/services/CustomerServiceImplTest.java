package com.cgi.next.monitoring.vls.customer.services;

import com.cgi.next.monitoring.vls.core.domain.Address;
import com.cgi.next.monitoring.vls.core.domain.Customer;
import com.cgi.next.monitoring.vls.core.domain.Shipment;
import com.cgi.next.monitoring.vls.customer.repositories.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class CustomerServiceImplTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    private CustomerService customerService;
    @BeforeAll
    void setUp(){
        customerService = new CustomerServiceImpl(customerRepository);
    }
    @Before
    void deleteFromDb(){
        Query q = entityManager.getEntityManager().createQuery("DELETE FROM customer");

        q.executeUpdate();
    }
    @Test
    void createCustomer() {
        Shipment shipment = new Shipment();
        shipment.setShipmentNumber("number");

        Address address = new Address();
        address.setLine1("Line 1");
        shipment.setRecipientAddress(new Address());

        Customer cust = new Customer();
        shipment.setCustomer(cust);
        cust.setName("TestCust");
        Set<Shipment> set = new HashSet<>();
        set.add(shipment);
        cust.setShipments(set);

        Customer createdCust = customerService.createCustomer(cust);

        Customer found = entityManager.find(Customer.class,createdCust.getId());

        Assert.assertEquals(found, createdCust);
    }

    @Test
    void getAllCustomers() {
    }


    @Test
    void getCustomer() {
    }
}