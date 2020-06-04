package com.cgi.next.monitoring.vls.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Thrown in cases where and invalid postal address is processed.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAddressException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public InvalidAddressException(String details){
        super("invalid address: " + details);
    }
}
