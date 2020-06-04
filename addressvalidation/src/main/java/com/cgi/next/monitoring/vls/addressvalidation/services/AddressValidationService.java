package com.cgi.next.monitoring.vls.addressvalidation.services;

import com.cgi.next.monitoring.vls.core.domain.Address;

import java.util.Set;

/**
 * Interface with services concerning the detection of flaws in postal adresses
 */
public interface AddressValidationService {
    /**
     * Analyzes a given postal {@code address} for flaws
     * @param address the address to analyze
     * @return a set of validation messages
     */
    Set<String> getValidationErrors(Address address);
}
