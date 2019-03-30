package com.wright.urlshortener.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * Model class which contains the original URL and the encoded short URL
 * equivalent. Also maintains the creation date of the URL.
 * 
 * <ul>
 * <li>originalUrl</li>
 * <li>shortUrl</li>
 * <li>createdDate</li>
 * <li>id</li>
 * </ul>
 * 
 * @author christopherwright
 *
 */
@Entity
@Table(name = "urls", indexes = {
        @Index(columnList = "originalUrl", name = "originalUrl_hidx"),
        @Index(columnList = "shortUrl", name = "shortUrl_hidx")})
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortUrl;
    private Date createdDate;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        URL other = (URL) obj;
        if (createdDate == null) {
            if (other.createdDate != null)
                return false;
        } else if (!createdDate.equals(other.createdDate))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (originalUrl == null) {
            if (other.originalUrl != null)
                return false;
        } else if (!originalUrl.equals(other.originalUrl))
            return false;
        if (shortUrl == null) {
            if (other.shortUrl != null)
                return false;
        } else if (!shortUrl.equals(other.shortUrl))
            return false;
        return true;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Long getId() {
        return id;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createdDate == null) ? 0 : createdDate.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result
                + ((originalUrl == null) ? 0 : originalUrl.hashCode());
        result = prime * result
                + ((shortUrl == null) ? 0 : shortUrl.hashCode());
        return result;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d, Long URL: %s, Short URL: %s, Created Date: %s", id,
                originalUrl, shortUrl, createdDate);
    }

}
