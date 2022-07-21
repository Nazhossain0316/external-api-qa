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

    //@Test(dataProvider = "equipmentData")
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

        String activityApiUrl = baseUrl + dataSource.get("Uri").toString();

        Response response = Request.makeRequest("GET", activityApiUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()),expectedStatusCode, "The response code did not match expected");
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
    }

    @DataProvider(name = "lastLoginData")
    public Object[][] getLastLoginData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Utilization.xlsx", "GET_equipment");
    }
    @DataProvider(name = "equipmentData")
    public Object[][] getEquipmentData() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Activity_Utilization.xlsx", "GET_equipment");
    }
}
