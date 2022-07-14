package com.automation.api.tests.activity;

import com.automation.api.tests.TestBase;
import com.automation.api.utils.Authorization;
import com.automation.api.utils.ExcelUtil;
import com.automation.api.utils.PrerequisiteHelper;
import com.automation.api.utils.Request;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ProductivityTests extends TestBase {

    @DataProvider(name = "getSurveyActivityData")
    public Object[][] getSurveyActivityData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity.xlsx", "GET_activity_v1_users");
    }

    @DataProvider(name = "lastLoginData")
    public Object[][] getLastLoginData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity.xlsx", "Get_last_login");
    }

    //@Test(dataProvider = "getSurveyActivityData")
    public void get_activity_v1_users(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //check if preReq step exist in datasource
        if(!System.getProperty("preRequisite").equals("")){
            PrerequisiteHelper.initializePrerequisiteData(System.getProperty("preRequisite"));
        }

        //Get Authorization Token
        String authToken = Authorization.getAuthorizationToken(clientId, clientSecret);

        //Set Auth Token as Header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String impactApiRequestUrl = baseUrl + dataSource.get("Uri").toString();

        Response response = Request.makeRequest("GET", impactApiRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");
    }


    @Test(dataProvider = "lastLoginData")
    public void get_activity_v1_users_userId_lastLogin(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //check if preReq step exist in datasource
        if(!System.getProperty("preRequisite").equals("")){
            PrerequisiteHelper.initializePrerequisiteData(System.getProperty("preRequisite"));
        }

        //Get Authorization Token
        String authToken = Authorization.getAuthorizationToken(clientId, clientSecret);

        //Set Auth Token as Header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String activityRequestUrl = baseUrl + dataSource.get("Uri").toString();
        //If userId was created in the prerequisite step, it can be retrieved using System.getProperty("userId")
        String userId = System.getProperty("userId");
        activityRequestUrl =activityRequestUrl.contains("{userId}") ? activityRequestUrl.replace("{userId}", userId): activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");
    }


}
