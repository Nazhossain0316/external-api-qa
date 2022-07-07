package com.automation.api.tests;

import com.automation.api.utils.Authorization;
import com.automation.api.utils.ExcelUtil;
import com.automation.api.utils.Properties;
import com.automation.api.utils.Request;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;


public class ImpactTests {


    static String baseUrl;
    static String impactApiUri;
    static String clientId;
    static String clientSecret;

    @BeforeSuite
    public void setUp() {
        Properties.loadPropertiesFile();
        baseUrl = System.getProperty("baseUrl");
        impactApiUri = System.getProperty("impactApiUri");
        clientId = System.getProperty("clientId");
        clientSecret = System.getProperty("clientSecret");
    }


    @DataProvider(name = "impact_meter_data")
    public Object[][] impactMeterData() {
        return ExcelUtil.dataSupplier("C:\\Users\\NazmulHossain\\Projects\\external-api-qa\\src\\test\\resources\\data\\DataSheet.xlsx", "ImpactData");
    }

    @Test(dataProvider = "impact_meter_data")
    public void makeRequest(Map<Object, Object> dataSource) {
        //todo: create method that will assign all excel data to System properties
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        String authToken = Authorization.getAuthorizationToken(clientId, clientSecret);

        String impactApiRequestUrl = baseUrl + dataSource.get("Uri").toString();

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        Response response = Request.makeRequest("GET", impactApiRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");
    }


}
