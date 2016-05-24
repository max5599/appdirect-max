package com.mct.appdirect;

import com.mct.appdirect.utils.IntegrationTest;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class HealthTest extends IntegrationTest {

    @Test
    public void shouldReturnOk() {
        ResponseEntity<String> response = template.getForEntity(urlForPath("/health"), String.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

}