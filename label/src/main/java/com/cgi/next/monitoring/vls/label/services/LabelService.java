package com.cgi.next.monitoring.vls.label.services;

import com.cgi.next.monitoring.vls.core.domain.Address;
import com.cgi.next.monitoring.vls.core.domain.Label;

/**
 * Interface for creatin and downloading of PDF labels
 */
public interface LabelService {

    /**
     * Creates and stores a PDF label with the given {@code recipientAddress} and {@code shipmentNumber}
     * @param recipientAddress the recipientAddress that is supposed to be printed on the label
     * @param shipmentNumber the shipmentNumber that is supposed to be printed on the label
     * @return a Label that wraps the PDF label
     */
    Label createLabel(Address recipientAddress, String shipmentNumber);

    /**
     * Return a Label with the given id
     * @param id the id of the label
     * @return a Label that wrpas the PDF label
     * @throws com.cgi.next.monitoring.vls.core.exceptions.ResourceNotFoundException raised if the label with
     * the given id could not be found
     */
    Label getLabelById(long id);
}
