package com.automation.api.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class Request {

    public static Response makeRequest(String requestMethod, String requestUrl, Map<String, String> requestHeaders, String requestBody){
        Response resp = null;

        switch(requestMethod.toUpperCase()) {
            case "GET":
              resp =   given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .get(requestUrl);
                break;
            case "POST":
                resp =   given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .post(requestUrl);
                break;
            case "PUST":
                resp =   given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .put(requestUrl);
                break;
            case "DELETE":
                resp =   given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .delete(requestUrl);
                break;
        }

        return resp;

    }
}
