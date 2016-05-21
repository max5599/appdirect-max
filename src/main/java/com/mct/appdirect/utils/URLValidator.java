package com.mct.appdirect.utils;

import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class URLValidator implements Validator<String> {

    public boolean isValid(String url) {
        try {
            new URL(url);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
