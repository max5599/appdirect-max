package com.mct.appdirect.utils;

import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class URLValidator implements Validator<String> {

    public boolean isInvalid(String url) {
        try {
            new URL(url);
        } catch (Exception e) {
            return true;
        }
        return false;
    }
}
