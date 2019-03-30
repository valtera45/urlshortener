package com.wright.urlshortener;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wright.urlshortener.model.URL;
import com.wright.urlshortener.service.URLService;

/**
 * 
 * @author christopherwright
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class URLIntegrationTest {

    @Autowired
    private URLService urlService;

    @Test
    public void testAddMany() throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder("http://www.test.com");
        List<URL> urls = new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            sb.replace(19, sb.length(), String.valueOf(i));
            urls.add(urlService.saveUrl(sb.toString()));
        }
        assertEquals(100, urls.size());
        
        List<URL> retrievedUrls = urlService.getAllUrls();
        assertEquals(100, retrievedUrls.size());
        
        assertEquals("000000", retrievedUrls.get(0).getShortUrl());
        assertEquals("100000", retrievedUrls.get(1).getShortUrl());
    }
    
    @Test
    public void testFindByShortURL() throws UnsupportedEncodingException {
        URL url = urlService.saveUrl("http://www.test.com");
        URL foundUrl = urlService.getUrl(url.getShortUrl());
        assertEquals(url.getOriginalUrl(), foundUrl.getOriginalUrl());
    }
}
