package com.automation.api.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Authorization {


    public static String getAuthorizationToken (String clientId, String clientSecret){
        Map<String, String> authHeaders = new HashMap<>();

        String apiKey = System.getProperty("apiKey");
        String authUrl = System.getProperty("authUrl");

        authHeaders.put("Api-Key", apiKey);
        authHeaders.put("Content-Type", "application/x-www-form-urlencoded");

        Response response = RestAssured
                .given()
                .headers(authHeaders)
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .post(authUrl);

        return response.getBody().path("access_token");
    }
}
