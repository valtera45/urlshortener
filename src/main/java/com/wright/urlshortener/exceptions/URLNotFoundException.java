package com.wright.urlshortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 404 Not Found Exception.
 * 
 * @author christopherwright
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class URLNotFoundException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -711649333572451298L;

    public URLNotFoundException(String exception) {
        super(exception);
    }

}
