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

public class UtilizationTests extends TestBase {

    @Test(dataProvider = "equipmentData")
    public void activity_v1_equipment(Map<Object, Object> dataSource){
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
        activityRequestUrl = activityRequestUrl.contains("{userId}") ? activityRequestUrl.replace("{userId}", userId) : activityRequestUrl;

        //If equipmentId was created in the prerequisite step, it can be retrieved using System.getProperty("equipmentId")
        String equipmentId = System.getProperty("equipmentId");
        activityRequestUrl = activityRequestUrl.contains("{equipmentId}") ? activityRequestUrl.replace("{equipmentId}", equipmentId) : activityRequestUrl;

        //If locationId was created in the prerequisite step, it can be retrieved using System.getProperty("equipmentId")
        String locationId = System.getProperty("locationId");
        activityRequestUrl = activityRequestUrl.contains("{locationId}") ? activityRequestUrl.replace("{locationId}", locationId) : activityRequestUrl;

        //If equipmentTypeId was created in the prerequisite step, it can be retrieved using System.getProperty("equipmentId")
        String equipmentTypeId = System.getProperty("equipmentTypeId");
        activityRequestUrl = activityRequestUrl.contains("{equipmentTypeId}") ? activityRequestUrl.replace("{equipmentTypeId}", equipmentTypeId) : activityRequestUrl;


        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");

        //Validate Schema if the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_utilization");
        }
    }

    @Test(dataProvider = "lastLoginData")
    public void activity_v1_equipment_equipmentId_lastLogins(Map<Object, Object> dataSource){
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

        //If equipmentId was created in the prerequisite step, it can be retrieved using System.getProperty("equipmentId")
        String equipmentId = System.getProperty("equipmentId");
        activityRequestUrl = activityRequestUrl.contains("{equipmentId}") ? activityRequestUrl.replace("{equipmentId}", equipmentId) : activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");

        //Validate Schema if the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response,"activity_utilization");
        }
    }

    @Test(dataProvider = "equipmentSessionData")
    public void activity_v1_equipment_equipmentId_sessions(Map<Object, Object> dataSource){
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

        //If equipmentId was created in the prerequisite step, it can be retrieved using System.getProperty("equipmentId")
        String equipmentId = System.getProperty("equipmentId");
        activityRequestUrl = activityRequestUrl.contains("{equipmentId}") ? activityRequestUrl.replace("{equipmentId}", equipmentId) : activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");

        //Validate Schema if the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_utilization");
        }
    }

    @Test(dataProvider = "equipmentSummaryData")
    public void activity_v1_equipment_equipmentId_summary(Map<Object, Object> dataSource){
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

        //If equipmentId was created in the prerequisite step, it can be retrieved using System.getProperty("equipmentId")
        String equipmentId = System.getProperty("equipmentId");
        activityRequestUrl = activityRequestUrl.contains("{equipmentId}") ? activityRequestUrl.replace("{equipmentId}", equipmentId) : activityRequestUrl;

        Response response = Request.makeRequest("GET", activityRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");

        //Validate Schema if the schema column has a file Name
        if (schemaValidationFileInExcel()) {
            doSchemaValidation(response, "activity_utilization");
        }
    }

    @DataProvider(name = "equipmentData")
    public Object[][] getEquipmentData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Utilization.xlsx", "GET_equipment");
    }

    @DataProvider(name = "lastLoginData")
    public Object[][] getLastLoginData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Utilization.xlsx", "GET_last_login");
    }

    @DataProvider(name = "equipmentSessionData")
    public Object[][] getEquipmentSessionData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Utilization.xlsx", "GET_equipment_session");
    }

    @DataProvider(name = "equipmentSummaryData")
    public Object[][] getEquipmentSummaryData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Utilization.xlsx", "GET_equipment_summary");
    }
}
