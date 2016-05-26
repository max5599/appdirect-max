package com.mct.appdirect.security;

import oauth.signpost.http.HttpRequest;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HttpServletRequestAdapterTest {

    private final MockHttpServletRequest mockHttpRequest = createMockRequest();

    private final HttpRequest httpRequest = new HttpServletRequestAdapter(mockHttpRequest);

    @Test
    public void shouldAdaptGetMethod() {
        assertThat(httpRequest.getMethod(), equalTo("GET"));
    }

    @Test
    public void shouldAdaptGetRequestUrl() {
        assertThat(httpRequest.getRequestUrl(), equalTo("http://localhost:654/api/billing/v1/orders"));
    }

    @Test
    public void shouldAdaptGetHeader() {
        assertThat(httpRequest.getHeader("header1"), equalTo("header1Value"));
    }

    @Test
    public void shouldAdaptGetAllHeaders() {
        Map<String, String> allHeaders = httpRequest.getAllHeaders();
        assertThat(allHeaders, hasEntry("header1","header1Value"));
        assertThat(allHeaders, hasEntry("header2","header2Value"));
    }

    @Test
    public void shouldReturnNullForGetMessagePayload() throws Exception {
        assertThat(httpRequest.getMessagePayload(), is(nullValue()));
    }

    @Test
    public void shouldAdaptGetContentType() {
        assertThat(httpRequest.getContentType(), equalTo("application/json"));
    }

    @Test
    public void shouldUnwrap() {
        assertThat(httpRequest.unwrap(), equalTo(mockHttpRequest));
    }

    @Test
    public void shouldAdaptSetHeader() {
        String newHeader = "newHeader";
        String newHeaderValue = "newHeaderValue";
        httpRequest.setHeader(newHeader, newHeaderValue);

        assertThat(httpRequest.getHeader(newHeader), equalTo(newHeaderValue));
    }

    private MockHttpServletRequest createMockRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "/api/billing/v1/orders");
        request.setServerPort(654);
        request.addHeader("header1", "header1Value");
        request.addHeader("header2", "header2Value");
        request.setContentType("application/json");
        return request;
    }
}