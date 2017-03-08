package com.github.lynxchina.babypi.test;

import com.github.lynxchina.babypi.BabyPiWebApp;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;


/**
 * @author chris
 * @version 1/5/16-3:23 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(BabyPiWebApp.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class ArgusMobiWebAppTest {
    @Value("${local.server.port}")
    private int port;

    @Test
    public void testHome() throws Exception {
        ResponseEntity<String> entity =
                new TestRestTemplate().getForEntity("http://localhost:" + this.port, String.class);
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        Assert.assertEquals("Hello World", entity.getBody());
    }

    @Test
    public void testCompression() throws Exception {
        System.out.println("chris:" + port);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept-Encoding", "gzip");
        HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);
        RestTemplate restTemplate = new TestRestTemplate();
        ResponseEntity<byte[]> entity =
                restTemplate.exchange("https://localhost:" + this.port, HttpMethod.GET, requestEntity, byte[].class);
        Assert.assertEquals(HttpStatus.OK, entity.getStatusCode());
        GZIPInputStream inflater = new GZIPInputStream(new ByteArrayInputStream(entity.getBody()));
        try {
            Assert.assertEquals("Hello World", StreamUtils.copyToString(inflater, Charset.forName("UTF-8")));
        } finally {
            inflater.close();
        }
    }

}
