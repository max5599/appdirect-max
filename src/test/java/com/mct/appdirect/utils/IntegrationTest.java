package com.mct.appdirect.utils;

import com.mct.appdirect.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@org.springframework.boot.test.IntegrationTest({"server.port=0"})
public abstract class IntegrationTest {

    private static final String OAUTH = "OAuth realm=\"\"," +
            "oauth_nonce=\"72250409\"," +
            "oauth_timestamp=\"1294966759\"," +
            "oauth_consumer_key=\"Dummy\"," +
            "oauth_signature_method=\"HMAC\"," +
            "oauth_version=\"1.0\"," +
            "oauth_signature=\"%s\"";

    @Value("${local.server.port}")
    private int port;

    private final RestTemplate template = new TestRestTemplate();

    protected <T> T securedGet(String url, Class<T> responseType) throws Exception {
        return HttpClient.get(url, responseType);
    }

    protected String encodeParamAndCreateUrl(String path, String urlParam) throws Exception {
        return urlForPath(path) + URLEncoder.encode(urlParam, "UTF-8");
    }

    protected <T> ResponseEntity<T> unsecuredGet(String path, Class<T> responseType, Object... params) {
        return template.getForEntity(urlForPath(path), responseType, params);
    }

    private String urlForPath(String path) {
        return "http://localhost:" + port + path;
    }
}
