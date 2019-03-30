package com.wright.urlshortener.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.wright.urlshortener.model.URL;

/**
 * Interface for a URL set and get service.
 * 
 * @author christopherwright
 *
 */
public interface URLService {

    /**
     * Takes a long or original URL and generates a short URL for the original.
     * Calls repository service to store the URL. If a URL for this long URL
     * already exists, that mapping will be returned.
     * 
     * @param longUrl
     *            the original URL.
     * @return a {@link URL} object.
     * @throws UnsupportedEncodingException
     */
    URL saveUrl(String longUrl);

    /**
     * Takes a short URL and returns a {@link URL} object consisting of the
     * original URL and short URL.
     * 
     * @param shortUrl
     *            the shortened URL used to retrieve the long URL.
     * @return a {@link URL} object.
     * @throws UnsupportedEncodingException
     */
    URL getUrl(String shortUrl);

    /**
     * Gets all URL objects from the database.
     * 
     * @return a list of {@link URL} objects.
     */
    List<URL> getAllUrls();
}
