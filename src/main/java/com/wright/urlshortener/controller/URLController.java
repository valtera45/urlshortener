package com.wright.urlshortener.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wright.urlshortener.exceptions.URLNotFoundException;
import com.wright.urlshortener.exceptions.URLServerErrorException;
import com.wright.urlshortener.model.URL;
import com.wright.urlshortener.service.URLService;

/**
 * Rest API to create and retrieve short and long URLs, respectively.
 * 
 * @author christopherwright
 *
 */
@RestController
@RequestMapping("api/v1/urls")
public class URLController {

    @Autowired
    private URLService urlService;

    /**
     * Retrieve all URLs.
     * 
     * @return a list of {@link URL} objects.
     */
    @GetMapping()
    public List<URL> getAllUrls() {
        return urlService.getAllUrls();
    }

    /**
     * Retrieves a URL based on its short URL.
     * 
     * @param shortUrl
     *            the short URL used to look up the original URL.
     * @return a {@link URL} object based on the lookup.
     */
    @GetMapping(value = "{shortUrl}")
    public URL getUrl(@PathVariable String shortUrl) {
        URL url = urlService.getUrl(shortUrl);
        if (url == null) {
            throw new URLNotFoundException(
                    "The url " + shortUrl + " could not be found");
        }
        return url;
    }

    /**
     * Creates a new short URL based on the long URL input.
     * 
     * @param longUrl
     *            the URL to create a short URL for.
     * @return a {@link URL} object with the newly created URL entry or an
     *         existing URL if it exists.
     */
    @PostMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    public URL create(@RequestBody String longUrl) {
        URL newUrl = null;
        newUrl = urlService.saveUrl(longUrl);
        if (newUrl == null) {
            throw new URLServerErrorException(
                    "Not able to create a new URL: " + longUrl);
        }
        return newUrl;
    }
}
