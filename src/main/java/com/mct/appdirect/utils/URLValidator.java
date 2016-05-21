package com.mct.appdirect.utils;

import org.springframework.stereotype.Service;

@Service
public class URLValidator implements Validator<String> {

    public boolean isValid(String url) {
        return false;
    }
}
