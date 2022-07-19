package com.automation.api.utils;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.response.Response;
import org.testng.Reporter;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Request {

    public static Response makeRequest(String requestMethod, String requestUrl, Map<String, String> requestHeaders, String requestBody) {
        Response resp = null;

        switch (requestMethod.toUpperCase()) {
            case "GET":
                resp = given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .get(requestUrl);
                break;
            case "POST":
                resp = given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .post(requestUrl);
                break;
            case "PUST":
                resp = given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .put(requestUrl);
                break;
            case "DELETE":
                resp = given()
                        .baseUri(requestUrl)
                        .headers(requestHeaders)
                        .delete(requestUrl);
                break;
        }

        String currentTestCase = System.getProperty("Description");
        Reporter.log("\nTestCase:\n" + currentTestCase);
        Reporter.log(" ");
        Reporter.log("\nREQUEST Method:\n" +requestMethod);
        Reporter.log(" ");
        Reporter.log("\nREQUEST url:\n" +requestUrl);
        Reporter.log(" ");
        Reporter.log("\nREQUEST Headers:\n" + requestHeaders.toString());
        Reporter.log(" ");
        Reporter.log("\nREQUEST Body:\n" + requestBody);
        Reporter.log(" ");
        Reporter.log("\nRESPONSE code:\n" + resp.getStatusCode());
        Reporter.log(" ");
        Reporter.log("\nRESPONSE body:\n" + resp.getBody().prettyPrint());
        Reporter.log(" ");
        Reporter.log("\nRESPONSE time (ms):\n" + resp.getTime());
        Reporter.log(" ");
        Reporter.log(" ---------------------------------- ");
        return resp;
    }
}
