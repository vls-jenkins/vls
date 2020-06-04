package com.cgi.next.monitoring.vls.customer.controllers;

import com.cgi.next.monitoring.vls.core.domain.Customer;
import com.cgi.next.monitoring.vls.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Class that handles incoming restful calls, which concern the resource {@code customer}
 */
@RestController
@RequestMapping(produces = "application/hal+json")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(final CustomerService customerService){
        this.customerService = customerService;
    }

    /**
     * returns a list of all customers
     *
     * Example call with {@code curl}:
     * <pre class="code">
     *     curl -s http://localhost:8080/customers
     * </pre>
     * @return all customers
     */
    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> getCustomers(){
        List<EntityModel<Customer>> customers = StreamSupport.stream(customerService.getAllCustomers().spliterator(), false)
                .map(cust -> new EntityModel<Customer>(cust,
                        linkTo(methodOn(CustomerController.class).getCustomer(cust.getId())).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).getCustomers()).withRel("customers")))
                .collect(Collectors.toList());
        return ResponseEntity.ok(new CollectionModel<>(customers, linkTo(methodOn(CustomerController.class).getCustomers()).withSelfRel()));
    }

    /**
     * Returns a singe customer of the given id
     *
     * Example call with {@code curl}:
     * <pre class="code">
     *     curl -s http://localhost:8080/customer/1
     * </pre>
     * @param id the unique customer id
     * @return the customer if existent
     */
    @RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
    public ResponseEntity<EntityModel<Customer>> getCustomer(@PathVariable long id){
        return ResponseEntity.ok(new EntityModel<Customer>(customerService.getCustomer(id),
                linkTo(methodOn(CustomerController.class).getCustomer(id)).withSelfRel(),
                linkTo(methodOn(CustomerController.class).getCustomers()).withRel("customers")));

    }

    /**
     * Creates a new customer and assings a unique id to him/her
     * Example call with {@code curl}
     *
     * <pre class="code">
     *   curl -sSL -D - -H "Content-Type: application/json" -X POST -d '{"name":"CGI"}' http://localhost:8080/customers
     * </pre>
     * @param customer the data of the new customer
     * @return a URI that identifies the new customer
     */
    @RequestMapping(value = "/customers", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer){
        try{
            Customer savedCust = customerService.createCustomer(customer);
            EntityModel<Customer> customerResource = new EntityModel<>(savedCust,
                    linkTo(methodOn(CustomerController.class).getCustomer(savedCust.getId())).withSelfRel());
            return ResponseEntity.created(new URI(customerResource.getRequiredLink(IanaLinkRelations.SELF)
                    .getHref())).body(customerResource);
        }catch (URISyntaxException e){
            return ResponseEntity.badRequest().body("Unable to create " + customer);
        }
    }
}
