package com.wright.urlshortener;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.wright.urlshortener.model.URL;

/**
 * 
 * @author christopherwright
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Transactional
public class URLControllerWebIntegrationTest {
    
    private TestRestTemplate restTemplate = new TestRestTemplate();
    private static final String ENDPOINT = "http://localhost:8080/api/v1/urls";
    
    @Test
    public void testListAll() throws IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(ENDPOINT, String.class); 
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    
    /**
     * Tests all endpoints.
     */
    @Test
    public void testCreateAndGet() {
        // First create a new URL
        ResponseEntity<URL> response = restTemplate.postForEntity(ENDPOINT, "http://www.dubdubdub.com", URL.class);
        URL newUrl = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("000000", newUrl.getShortUrl());
        
        // Then fetch the response and verify that it is the first created URL
        response = restTemplate.getForEntity(ENDPOINT + "/000000", URL.class);
        URL retrievedUrl = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("http://www.dubdubdub.com", retrievedUrl.getOriginalUrl());
        
        // Then fetch the list end point to retrieve all URLs and verify the
        // first one is correct.
        ResponseEntity<List<URL>> responseList = restTemplate.exchange(
                ENDPOINT,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<URL>>(){});
        
        List<URL> retrievedUrls = responseList.getBody();
        
        assertEquals(HttpStatus.OK, responseList.getStatusCode());
        assertEquals("000000", retrievedUrls.get(0).getShortUrl());
        assertEquals("http://www.dubdubdub.com", retrievedUrls.get(0).getOriginalUrl());
    }
    
    @Test
    public void test404() {
        ResponseEntity<URL> response = restTemplate.getForEntity(ENDPOINT + "/abcdef", URL.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    public void testBadRequest() {
        ResponseEntity<URL> response = restTemplate.postForEntity(ENDPOINT, "!@##$%$^%&*", URL.class);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
