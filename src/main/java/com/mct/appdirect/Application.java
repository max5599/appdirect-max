package com.mct.appdirect;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.consumer.BaseProtectedResourceDetails;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OAuthRestTemplate oAuthRestTemplate(@Value("${app.security.consumerKey}") String consumerKey,
                                               @Value("${app.security.secret}") String securitySecret,
                                               @Value("${app.server.timeout}") int timeout) {
        BaseProtectedResourceDetails protectedResourceDetails = new BaseProtectedResourceDetails();
        protectedResourceDetails.setConsumerKey(consumerKey);
        protectedResourceDetails.setSharedSecret(new SharedConsumerSecretImpl(securitySecret));

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(timeout);
        requestFactory.setConnectTimeout(timeout);

        return new OAuthRestTemplate(requestFactory, protectedResourceDetails);
    }
}
