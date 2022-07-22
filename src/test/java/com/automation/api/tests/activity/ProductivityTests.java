package com.automation.api.tests.activity;

import com.automation.api.tests.TestBase;
import com.automation.api.utils.Authorization;
import com.automation.api.utils.ExcelUtil;
import com.automation.api.utils.PrerequisiteHelper;
import com.automation.api.utils.Request;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ProductivityTests extends TestBase {

    @Test(dataProvider = "surveyActivityData")
    public void get_activity_v1_users(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //set preRequisite properties if required by the datasheet
        if (!System.getProperty("preRequisite").equals("")) {
            PrerequisiteHelper.initializePrerequisiteData(System.getProperty("preRequisite"));
        }

        //Get Authorization Token
        String authToken = Authorization.getAuthorizationToken(clientId, clientSecret);

        //Set Auth Token as Header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        //Set Request URL
        String activityApiUrl = baseUrl + dataSource.get("Uri").toString();

        //Make Request
        Response response = Request.makeRequest("GET", activityApiUrl, requestHeaders, "");

        //Validate expected vs. actual Response Code
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();
        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");

        //Validate Schema is the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_productivity");
        }
    }

    @Test(dataProvider = "lastLoginData")
    public void get_activity_v1_users_userId_lastLogin(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //check if preReq step exist in datasource
        if (!System.getProperty("preRequisite").equals("")) {
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
        activityRequestUrl = activityRequestUrl.contains("{userId}") ? activityRequestUrl.replace("{userId}", userId) : activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");

        //Validate Schema is the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_productivity");
        }
    }

    @Test(dataProvider = "userSessionsData")
    public void get_activity_v1_users_userId_sessions(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //check if preReq step exist in datasource
        if (!System.getProperty("preRequisite").equals("")) {
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
        activityRequestUrl = activityRequestUrl.contains("{userId}") ? activityRequestUrl.replace("{userId}", userId) : activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");

        //Validate Schema is the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_productivity");
        }
    }

    @Test(dataProvider = "simultaneousUserData")
    public void get_activity_v1_users_userId_simultaneous(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //check if preReq step exist in datasource
        if (!System.getProperty("preRequisite").equals("")) {
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
        activityRequestUrl = activityRequestUrl.contains("{userId}") ? activityRequestUrl.replace("{userId}", userId) : activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");

        //Validate Schema is the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_productivity");
        }
    }

    @Test(dataProvider = "simultaneousUserLoginData")
    public void get_activity_v1_users_userId_simultaneous_logins(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //check if preReq step exist in datasource
        if (!System.getProperty("preRequisite").equals("")) {
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
        activityRequestUrl = activityRequestUrl.contains("{userId}") ? activityRequestUrl.replace("{userId}", userId) : activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");

        //Validate Schema is the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_productivity");
        }
    }

    @Test(dataProvider = "userSummaryData")
    public void get_activity_v1_users_userId_summary(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //check if preReq step exist in datasource
        if (!System.getProperty("preRequisite").equals("")) {
            PrerequisiteHelper.initializePrerequisiteData(System.getProperty("preRequisite"));
        }

        //Get Authorization Token
        String authToken = Authorization.getAuthorizationToken(clientId, clientSecret);

        //Set Auth Token as Header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        //Build Request URL
        String activityRequestUrl = baseUrl + dataSource.get("Uri").toString();
        //If userId was created in the prerequisite step, it can be retrieved using System.getProperty("userId")
        String userId = System.getProperty("userId");
        activityRequestUrl = activityRequestUrl.contains("{userId}") ? activityRequestUrl.replace("{userId}", userId) : activityRequestUrl;

        //Make Request
        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        //Assertions
        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");

        //Validate Schema is the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_productivity");
        }
    }

    @DataProvider(name = "surveyActivityData")
    public Object[][] getSurveyActivityData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Productivity.xlsx", "GET_activity_v1_users");
    }

    @DataProvider(name = "lastLoginData")
    public Object[][] getLastLoginData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Productivity.xlsx", "Get_last_login");
    }

    @DataProvider(name = "userSessionsData")
    public Object[][] getUserSessionsData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Productivity.xlsx", "GET_user_sessions");
    }

    @DataProvider(name = "simultaneousUserData")
    public Object[][] getSimultaneousUSerData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Productivity.xlsx", "GET_user_simultaneous");
    }

    @DataProvider(name = "simultaneousUserLoginData")
    public Object[][] getSimultaneousUserLoginData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Productivity.xlsx", "GET_user_simultaneous_logins");
    }

    @DataProvider(name = "userSummaryData")
    public Object[][] getUserSummaryData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Productivity.xlsx", "GET_user_summary");
    }
}
