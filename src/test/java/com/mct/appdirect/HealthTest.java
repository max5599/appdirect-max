package com.mct.appdirect;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class HealthTest {

    @Value("${local.server.port}")
    private int port;

    private final RestTemplate template = new TestRestTemplate();

    @Test
    public void shouldReturnOk() {
        ResponseEntity<String> response = template.getForEntity("http://localhost:" + port + "/health", String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}