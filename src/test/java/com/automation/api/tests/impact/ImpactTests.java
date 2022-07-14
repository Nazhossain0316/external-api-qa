package com.automation.api.tests.impact;

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


public class ImpactTests extends TestBase {

    @DataProvider(name = "get_impacts_v1_impacts")
    public Object[][] impactMeterData_1() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Impact.xlsx", "GET_impacts_v1_impacts");
    }

    @DataProvider(name = "ImpactData_2")
    public Object[][] impactMeterData_2() {
        return ExcelUtil.dataSupplier(System.getProperty("user.dir") +
                "/src/test/resources/data/Impact.xlsx", "ImpactData_2");
    }

    @Test(dataProvider = "get_impacts_v1_impacts")
    public void get_impacts_v1_impacts(Map<Object, Object> dataSource) {
        //Create System Properties using the values in the excel datasheet , Example "System.getProperty("ExpectedStatusCode")
        ExcelUtil.createSystemPropertiesFromDataSource(dataSource);

        //Get Authorization Token
        String authToken = Authorization.getAuthorizationToken(clientId, clientSecret);

        //Set Auth Token as Header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String impactApiRequestUrl = baseUrl + dataSource.get("Uri").toString();

        Response response = Request.makeRequest("GET", impactApiRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");
    }


    //@Test(dataProvider = "ImpactData_2")
    public void makeGetRequest_2(Map<Object, Object> dataSource) {
        //ToDo: Test Case work still remians. This test case require DB integration (which is currently pending)
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

        //Set request URL
        String impactApiRequestUrl = baseUrl + dataSource.get("Uri").toString();

        //If ModuleGuid was created in the prerequisite step, it can be retrieved using System.getProperty("moduleGuid")
        String moduleGuid = System.getProperty("moduleGuid");
        impactApiRequestUrl = impactApiRequestUrl.contains("{moduleGuid}") ? impactApiRequestUrl.replace("{moduleGuid}", moduleGuid) : impactApiRequestUrl;

        Response response = Request.makeRequest("GET", impactApiRequestUrl, requestHeaders, "");
        String expectedStatusCode = dataSource.get("ExpectedStatusCode").toString();

        assertEquals(Integer.toString(response.getStatusCode()), expectedStatusCode, "The response code did not match expected");
    }
}
