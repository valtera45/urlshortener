package com.wright.urlshortener;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.UnsupportedEncodingException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.wright.urlshortener.model.URL;
import com.wright.urlshortener.repository.URLRepository;
import com.wright.urlshortener.service.URLServiceImpl;
import com.wright.urlshortener.utils.Base62UrlEncoder;

/**
 * 
 * @author christopherwright
 *
 */
public class URLServiceTest {
    
    @InjectMocks
    private URLServiceImpl urlService;
    
    @Mock
    private URLRepository urlRepository;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testUrlGet() throws UnsupportedEncodingException {
        URL url = new URL();
        url.setId(1L);
        url.setShortUrl("100000");
        url.setOriginalUrl("http://www.test.com");
        when(urlRepository.findOneByShortUrl("100000")).thenReturn(url);
        
        URL returnedUrl = urlService.getUrl("100000");
        
        assertEquals(new Long(1L), returnedUrl.getId());
        assertEquals("100000", returnedUrl.getShortUrl());
    }
    
    @Test
    public void testUrlCreate() throws UnsupportedEncodingException {
        URL url = new URL();
        url.setId(1L);
        url.setShortUrl("100000");
        url.setOriginalUrl("http://www.test.com");
        when(urlRepository.saveAndFlush(Mockito.any(URL.class))).thenReturn(url);
        
        URL returnedUrl = urlService.saveUrl("http://www.test.com");
        
        assertEquals(new Long(1L), returnedUrl.getId());
        assertEquals("100000", returnedUrl.getShortUrl());
    }
    
    @Test
    public void testEncode() throws UnsupportedEncodingException {
        String encodedStr = Base62UrlEncoder.encode(1L);
        assertEquals("100000", encodedStr);
        encodedStr = Base62UrlEncoder.encode(2L);
        assertEquals("200000", encodedStr);
    }

}
