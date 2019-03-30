package com.wright.urlshortener.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 500 Internal Server Exception.
 * 
 * @author christopherwright
 *
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class URLServerErrorException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 5089336850976218587L;

    public URLServerErrorException(String message) {
        super(message);
    }

}
