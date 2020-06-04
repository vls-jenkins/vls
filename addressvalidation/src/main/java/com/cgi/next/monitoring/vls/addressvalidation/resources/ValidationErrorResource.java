package com.cgi.next.monitoring.vls.addressvalidation.resources;


import org.springframework.hateoas.RepresentationModel;


public class ValidationErrorResource  extends RepresentationModel<ValidationErrorResource> {
    private final String validationError;

    public ValidationErrorResource(String validationError){
        this.validationError = validationError;
    }

    public String getValidationError(){
        return validationError;
    }
}
