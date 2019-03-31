package com.wright.urlshortener.service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.commons.validator.routines.UrlValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wright.urlshortener.exceptions.URLBadRequestException;
import com.wright.urlshortener.model.URL;
import com.wright.urlshortener.repository.URLRepository;
import com.wright.urlshortener.utils.Base62UrlEncoder;

/**
 * 
 * @author christopherwright
 *
 */
@Service("urlService")
@Transactional
public class URLServiceImpl implements URLService {

    private static AtomicLong counter = new AtomicLong(0L);

    private static final int URL_LENGTH = 6;

    private static final Logger log = Logger
            .getLogger(URLService.class.getName());

    @Autowired
    private URLRepository urlRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<URL> getAllUrls() {
        log.debug("Retrieving all urls.");
        return urlRepository.findAll();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getUrl(String shortUrl) {
        if (shortUrl.length() < URL_LENGTH || shortUrl.length() > URL_LENGTH) {
            throw new URLBadRequestException(
                    "The URL to retrieve must be 6 characters.");
        }
        log.debug("Retrieving short url: " + shortUrl);
        return urlRepository.findOneByShortUrl(shortUrl);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL saveUrl(String longUrl) {
        validateUrl(longUrl);

        log.debug("Saving long url: " + longUrl);
        // First check if there exists an entry for this longUrl
        // if so return it.
        URL url = urlRepository.findOneByLongUrl(longUrl);
        if (url != null && url.getId() != null) {
            return url;
        }

        // atomically set the value of the counter to the number of rows in the
        // urls table. Essentially after each restart the counter will be set
        // to the current value in the database.
        counter.compareAndSet(0L, urlRepository.count());
        long count = counter.getAndIncrement();
        log.debug("The current counter is: " + count);

        String encodedUrl = Base62UrlEncoder.encode(count);

        url = new URL();
        url.setCreatedDate(new Date());
        url.setOriginalUrl(longUrl);
        url.setShortUrl(encodedUrl);
        log.debug("The new URL is: " + url);
        return urlRepository.saveAndFlush(url);
    }

    public void validateUrl(String url) {
        String[] schemes = { "http", "https" };
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (!urlValidator.isValid(url)) {
            throw new URLBadRequestException("The url: " + url
                    + " is not in a valid format.  Please try again.");
        }
    }

}
