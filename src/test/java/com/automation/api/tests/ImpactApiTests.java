package com.automation.api.tests;

import com.automation.api.utils.ExcelUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ImpactApiTests extends TestBase {

    static String authToken;
    static String baseUrl;
    static String impactApiUri;


    @DataProvider(name = "impact_meter_data")
    public Object[][] impactMeterData() {
        return ExcelUtil.dataSupplier("C:\\Users\\NazmulHossain\\Projects\\external-api-qa\\src\\test\\resources\\data\\DataSheet.xlsx", "ImpactData");
    }


    @Test(dataProvider = "impact_meter_data")
    public void makeRequest(Map<Object, Object> map) {

        String impactApiRequestUrl = baseUrl + impactApiUri + "?startDate=2020-5-1&endDate=2022-5-1";

        Map<String, String> authHeaders = new HashMap<>();
        authHeaders.put("Authorization", "Bearer " + authToken);

        Response response = RestAssured
                .given()
                .headers(authHeaders)
                .get(impactApiRequestUrl);

        System.out.println("");

        Reporter.log(response.getBody().prettyPrint());
        //reporter should capture the url, request body, header
        //reporter should capture the response body, code, time

    }


    @BeforeClass
    public void setUrls() {
        baseUrl = System.getProperty("baseUrl");
        impactApiUri = System.getProperty("impactApiUri");
    }

    @BeforeMethod
    public void createAccessToken() {
        Map<String, String> authHeaders = new HashMap<>();
        authHeaders.put("Api-Key", apiKey);
        authHeaders.put("Content-Type", "application/x-www-form-urlencoded");

        Response response = RestAssured
                .given()
                .headers(authHeaders)
                .formParam("grant_type", "client_credentials")
                .formParam("client_id", clientId)
                .formParam("client_secret", clientSecret)
                .post(authUrl);

        authToken = response.getBody().path("access_token");
    }

    //** Customers want to view all equipmentthat has had a high impact since X date.
    //•Customers want to knowthetop X operatorsthat have had high impacts since X date.
    //•Customers want to know whichtruck type has had thehighestnumber of impacts.
    //•Customers want to know whichlocation has had the most impacts.
    //•Customers want to know what equipment had an impact and why.
    //•Customers want to know the speed my equipment was going at the time of impact.
    //•Customers want to know the cause of the impact.•Customers want to know where in the facility the impact occurred.


}
