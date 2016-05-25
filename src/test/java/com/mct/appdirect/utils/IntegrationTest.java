package com.mct.appdirect.utils;

import com.mct.appdirect.Application;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
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

    @Value("${app.security.consumerKey}")
    private String consumerKey;

    @Value("${app.security.secret}")
    private String securitySecret;

    private final RestTemplate template = new TestRestTemplate();

    protected <T> T securedGet(String path, Class<T> responseType, Object... params) throws Exception {
        OAuthRestTemplate oAuthRestTemplate = createOAuthRestTemplate(consumerKey, securitySecret);
        return oAuthRestTemplate.getForEntity(urlForPath(path), responseType, params).getBody();
    }

    protected <T> ResponseEntity<T> unsecuredGet(String path, Class<T> responseType, Object... params) {
        return template.getForEntity(urlForPath(path), responseType, params);
    }

    private String urlForPath(String path) {
        return "http://localhost:" + port + path;
    }

    private OAuthRestTemplate createOAuthRestTemplate(String consumerKey, String securitySecret) {
        BaseProtectedResourceDetails protectedResourceDetails = new BaseProtectedResourceDetails();
        protectedResourceDetails.setConsumerKey(consumerKey);
        protectedResourceDetails.setSharedSecret(new SharedConsumerSecretImpl(securitySecret));

        return new OAuthRestTemplate(protectedResourceDetails);
    }
}
