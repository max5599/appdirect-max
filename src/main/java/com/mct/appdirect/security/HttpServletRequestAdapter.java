package com.mct.appdirect.security;

import oauth.signpost.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class HttpServletRequestAdapter implements HttpRequest {

    private final HttpServletRequest request;

    public HttpServletRequestAdapter(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getMethod() {
        return request.getMethod();
    }

    @Override
    public String getRequestUrl() {
        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    @Override
    public void setRequestUrl(String url) {
    }

    @Override
    public void setHeader(String name, String value) {
    }

    @Override
    public String getHeader(String name) {
        return request.getHeader(name);
    }

    @Override
    public Map<String, String> getAllHeaders() {
        return Collections.list(request.getHeaderNames()).stream()
                .map(name -> new HashMap.SimpleEntry<>(name, request.getHeader(name)))
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public InputStream getMessagePayload() throws IOException {
        return null;
    }

    @Override
    public String getContentType() {
        return request.getContentType();
    }

    @Override
    public HttpServletRequest unwrap() {
        return request;
    }
}
