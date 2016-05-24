package com.mct.appdirect.security;

import org.junit.Test;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class OAuthFieldsParserImplTest {

    private final OAuthFieldsParserImpl oAuthFieldsParser = new OAuthFieldsParserImpl();

    @Test
    public void shouldReturnEmptyIfAtLeastOneFieldIsMissing() throws Exception {
        String oauthWithOneFieldMissing = "OAuth realm=\"\"," +
                "oauth_nonce=\"72250409\"," +
                "oauth_timestamp=\"1294966759\"," +
                "oauth_consumer_key=\"Dummy\"," +
                "oauth_version=\"1.0\"," +
                "oauth_signature=\"IBlWhOm3PuDwaSdxE/Qu4RKPtVE=\"";

        assertThat(oAuthFieldsParser.parse(oauthWithOneFieldMissing), is(empty()));
    }

    @Test
    public void shouldReturnOAuthFieldsIfAllFieldsArePresents() throws Exception {
        String oauthWithOneFieldMissing = "OAuth realm=\"\"," +
                "oauth_nonce=\"72250409\"," +
                "oauth_timestamp=\"1294966759\"," +
                "oauth_consumer_key=\"Dummy\"," +
                "oauth_signature_method=\"HMAC\"," +
                "oauth_version=\"1.0\"," +
                "oauth_signature=\"IBlWhOm3PuDwaSdxE/Qu4RKPtVE=\"";

        assertThat(oAuthFieldsParser.parse(oauthWithOneFieldMissing), equalTo(of(new OAuthFields("Dummy"))));
    }


}