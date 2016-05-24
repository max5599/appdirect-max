package com.mct.appdirect.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static <T> T get(String strUrl, Class<T> responseType) throws Exception {
        URL url = new URL(strUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        OAuthConsumer consumer = new DefaultOAuthConsumer("Dummy", "secret");
        consumer.sign(conn);

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        ObjectMapper mapper = new ObjectMapper();
        T content = mapper.readValue(conn.getInputStream(), responseType);

        conn.disconnect();

        return content;
    }


}
