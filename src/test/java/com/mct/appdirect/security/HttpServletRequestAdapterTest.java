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
    public void shouldAdaptGetMethod() throws Exception {
        assertThat(httpRequest.getMethod(), equalTo("GET"));
    }

    @Test
    public void shouldAdaptGetRequestUrl() throws Exception {
        assertThat(httpRequest.getRequestUrl(), equalTo("https://www.appdirect.com/api/billing/v1/orders"));
    }

    @Test
    public void shouldAdaptGetHeader() throws Exception {
        assertThat(httpRequest.getHeader("header1"), equalTo("header1Value"));
    }

    @Test
    public void shouldAdaptGetAllHeaders() throws Exception {
        Map<String, String> allHeaders = httpRequest.getAllHeaders();
        assertThat(allHeaders, hasEntry("header1","header1Value"));
        assertThat(allHeaders, hasEntry("header2","header2Value"));
    }

    @Test
    public void shouldReturnNullForGetMessagePayload() throws Exception {
        assertThat(httpRequest.getMessagePayload(), is(nullValue()));
    }

    @Test
    public void shouldAdaptGetContentType() throws Exception {
        assertThat(httpRequest.getContentType(), equalTo("application/json"));
    }

    @Test
    public void shouldUnwrap() throws Exception {
        assertThat(httpRequest.unwrap(), equalTo(mockHttpRequest));
    }

    private MockHttpServletRequest createMockRequest() {
        MockHttpServletRequest request = new MockHttpServletRequest("GET", "https://www.appdirect.com/api/billing/v1/orders");
        request.addHeader("header1", "header1Value");
        request.addHeader("header2", "header2Value");
        request.setContentType("application/json");
        return request;
    }
}