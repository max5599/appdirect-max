package com.mct.appdirect.utils;

import com.mct.appdirect.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest({"server.port=0"})
public abstract class IntegrationTest {

    @Value("${local.server.port}")
    private int port;

    @Autowired
    private OAuthRestTemplate oAuthRestTemplate;

    private final RestTemplate template = new TestRestTemplate();

    protected <T> ResponseEntity<T> securedGet(String path, Class<T> responseType, Object... params) {
        return oAuthRestTemplate.getForEntity(urlForPath(path), responseType, params);
    }

    protected <T> ResponseEntity<T> unsecuredGet(String path, Class<T> responseType, Object... params) {
        return template.getForEntity(urlForPath(path), responseType, params);
    }

    private String urlForPath(String path) {
        return "http://localhost:" + port + path;
    }
}
