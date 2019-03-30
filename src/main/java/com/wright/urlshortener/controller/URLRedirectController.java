package com.wright.urlshortener.controller;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.wright.urlshortener.exceptions.URLNotFoundException;
import com.wright.urlshortener.exceptions.URLServerErrorException;
import com.wright.urlshortener.model.URL;
import com.wright.urlshortener.service.URLService;

/**
 * Controller used to handle forwarding requests to the correct end point.
 * 
 * @author christopherwright
 *
 */
@Controller
public class URLRedirectController {

    @Autowired
    private URLService urlService;

    private static final Logger log = Logger
            .getLogger(URLRedirectController.class.getName());

    /**
     * Handles any request a-zA-Z0-9 and calls the redirect end point
     * {@link #redirect(String)} if it is a match.
     */
    @RequestMapping("/{path:[A-Za-z0-9-]{6}}/**")
    public String forward(HttpServletRequest request) {
        log.debug(request.getRequestURI());
        log.debug("Handling forward with request URI: "
                + request.getRequestURI());
        return "forward:/redirect" + request.getRequestURI();
    }

    /**
     * End point responsible for handling a request which maps to 6 characters.
     * Makes a lookup in the database to see if the short URL exists. If it
     * does, issue a redirect to the found long URL.
     * 
     * @param shortUrl
     *            the short URL for which to issue a redirect. Should be passed
     *            in the request URI.
     * @return a redirect to the original URL.
     */
    @GetMapping(value = "redirect/{shortUrl}")
    public RedirectView redirect(@PathVariable String shortUrl) {
        log.debug("Caught redirect URL: " + shortUrl);
        RedirectView rv = new RedirectView();
        try {
            URL retUrl = urlService.getUrl(shortUrl);
            if (retUrl == null) {
                log.error("404 not found: " + shortUrl);
                throw new URLNotFoundException(
                        "The requested URL is not found: " + shortUrl);
            }
            rv.setUrl(retUrl.getOriginalUrl());
        } catch (Exception e) {
            log.error("Error retrieving the URL: " + shortUrl, e);
            throw new URLServerErrorException(
                    "Error retrieving the URL: " + shortUrl);
        }
        return rv;
    }

}
