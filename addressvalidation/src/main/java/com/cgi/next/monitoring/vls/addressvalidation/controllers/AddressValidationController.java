package com.cgi.next.monitoring.vls.addressvalidation.controllers;

import com.cgi.next.monitoring.vls.addressvalidation.resources.ValidationErrorResource;
import com.cgi.next.monitoring.vls.addressvalidation.services.AddressValidationService;
import com.cgi.next.monitoring.vls.core.domain.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(produces = "application/hal+json")
public class AddressValidationController {

    private final AddressValidationService addressValidationService;

    @Autowired
    public AddressValidationController(final AddressValidationService addressValidationService){
        this.addressValidationService = addressValidationService;
    }
    @RequestMapping(value = "/addressValidator", method = RequestMethod.POST)
    public ResponseEntity<CollectionModel<ValidationErrorResource>> validateAddress(@RequestBody Address address){
        Set<String> errors = addressValidationService.getValidationErrors(address);

        Link link = linkTo(methodOn(AddressValidationController.class).validateAddress(address)).withSelfRel();
        List<ValidationErrorResource> validationErrors = errors.stream()
                .map(ValidationErrorResource::new).collect(Collectors.toList());
        return ResponseEntity.ok(new CollectionModel<>(validationErrors, link));

    }


}
