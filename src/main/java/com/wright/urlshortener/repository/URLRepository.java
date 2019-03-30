package com.wright.urlshortener.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wright.urlshortener.model.URL;

/**
 * Interfaces with Postgres DB.
 * 
 * @author christopherwright
 *
 */
@Repository("urlRepository")
public interface URLRepository extends JpaRepository<URL, Long> {

    @Query("select u from URL u where u.shortUrl = ?1")
    URL findOneByShortUrl(String shortUrl);
    
    @Query("select u from URL u where u.originalUrl = ?1")
    URL findOneByLongUrl(String originalUrl);
    
}
