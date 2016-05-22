package com.mct.appdirect;

import com.mct.appdirect.utils.IntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class HealthTest extends IntegrationTest {

    @Value("${local.server.port}")
    private int port;

    private final RestTemplate template = new TestRestTemplate();

    @Test
    public void shouldReturnOk() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port + "/health", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}