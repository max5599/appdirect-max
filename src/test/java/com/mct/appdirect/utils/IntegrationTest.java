package com.mct.appdirect.utils;

import com.mct.appdirect.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest({"server.port=0"})
public abstract class IntegrationTest {

    @Value("${local.server.port}")
    private int port;

    private final RestTemplate template = new TestRestTemplate();

    protected <T> ResponseEntity<T> securedGet(String path, Class<T> responseType, Object... params) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTHORIZATION, "oauth_consumer_key=Dummy");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        return template.exchange(urlForPath(path), HttpMethod.GET, entity, responseType, params);
    }

    protected <T> ResponseEntity<T> unsecuredGet(String path, Class<T> responseType, Object... params) {
        return template.getForEntity(urlForPath(path), responseType, params);
    }

    private String urlForPath(String path) {
        return "http://localhost:" + port + path;
    }
}
