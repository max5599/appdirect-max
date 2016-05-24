package com.mct.appdirect.security;

import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class OAuthFieldsTest {

    private OAuthFields oAuthFields = new OAuthFields("OAuth realm=," +
            "oauth_nonce=72250409," +
            "oauth_timestamp=1294966759," +
            "oauth_consumer_key=Dummy," +
            "oauth_signature_method=HMAC-SHA1," +
            "oauth_version=1.0," +
            "oauth_signature=IBlWhOm3PuDwaSdxE/Qu4RKPtVE=");

    @Test
    public void shouldRetrieveConsumerKey() throws Exception {
        assertThat(oAuthFields.getConsumerKey(), equalTo("Dummy"));
    }
}