package com.wright.urlshortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author christopherwright
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class URLBadRequestException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -6694943746020352016L;

    public URLBadRequestException(String exception) {
        super(exception);
    }

}
