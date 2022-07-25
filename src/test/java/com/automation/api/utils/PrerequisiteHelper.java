package com.automation.api.utils;

import io.restassured.response.Response;
import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PrerequisiteHelper {
    public static void initializePrerequisiteData(String preReq) {

        switch(preReq){
            case "Get Module Guide":
                String randomValidModuleGuid = getRandomModuleGuid(); //FYI: impactId = moduleId
                System.setProperty("moduleGuid", randomValidModuleGuid);
                break;
        }

        switch(preReq){
            case "Get valid userId from Activity API":
                String randomValidUserId = getRandomUserIdFromActivityApi();
                System.setProperty("userId", randomValidUserId);
                break;
        }

        switch(preReq){
            case "Get random equipmentId":
                String randomEquipmentId = getRandomEquipmentIdFromActivityApi();
                System.setProperty("equipmentId", randomEquipmentId);
                break;
        }

        switch(preReq){
            case "Get random equipmentTypeId":
                String randomEquipmentTypeId = getRandomEquipmentTypeIdFromImpactApi();
                System.setProperty("equipmentTypeId", randomEquipmentTypeId);
                break;
        }

        switch(preReq){
            case "Get random locationId":
                String randomLocationId = getRandomLocationIdFromImpactApi();
                System.setProperty("locationId", randomLocationId);
                break;
        }

        switch(preReq){
            case "Get userId, equipment, equipmentTypeId, and locationId":
                getRandomUserEquipmentTypeEquipmentAndLocationId();
                //The method above will set userId, equipmentTypeId, and locationId in SystemProperties
                break;
        }
    }

    private static void getRandomUserEquipmentTypeEquipmentAndLocationId() {
        String authToken = Authorization.getAuthorizationToken(System.getProperty("clientId"), System.getProperty("clientSecret"));

        //Get date range between today and a yr ago
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date = new Date();
        String today = dateFormat.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        String sixMonthsAgo = dateFormat.format(c.getTime());

        //Add auth token to request header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String impactApiUrl = System.getProperty("baseUrl")
                +"/impacts/v1/impacts?startDate="+sixMonthsAgo+"&endDate="+today;

        Response response = Request.makeRequest("GET", impactApiUrl, requestHeaders, "");

        Assert.assertTrue(response.getStatusCode()==200, "The request to get locationId failed!");

        String userId = response.path("userId[0]").toString();
        String equipmentTypeId = response.path("equipmentTypeId[0]").toString();
        String equipmentId = response.path("equipmentId[0]").toString();
        String locationId = response.path("locationId[0]").toString();

        System.setProperty("userId", userId);
        System.setProperty("equipmentTypeId", equipmentTypeId);
        System.setProperty("equipmentId", equipmentId);
        System.setProperty("locationId", locationId);
    }

    private static String getRandomLocationIdFromImpactApi() {
        String authToken = Authorization.getAuthorizationToken(System.getProperty("clientId"), System.getProperty("clientSecret"));

        //Get date range between today and a yr ago
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date = new Date();
        String today = dateFormat.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        String sixMonthsAgo = dateFormat.format(c.getTime());

        //Add auth token to request header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String impactApiUrl = System.getProperty("baseUrl")
                +"/impacts/v1/impacts?startDate="+sixMonthsAgo+"&endDate="+today;

        Response response = Request.makeRequest("GET", impactApiUrl, requestHeaders, "");

        Assert.assertTrue(response.getStatusCode()==200, "The request to get locationId failed!");

        String locationId = response.path("locationId[0]").toString();
        return  locationId;
    }

    private static String getRandomEquipmentTypeIdFromImpactApi() {
        String authToken = Authorization.getAuthorizationToken(System.getProperty("clientId"), System.getProperty("clientSecret"));

        //Get date range between today and a yr ago
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date = new Date();
        String today = dateFormat.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        String sixMonthsAgo = dateFormat.format(c.getTime());

        //Add auth token to request header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String impactApiUrl = System.getProperty("baseUrl")
                +"/impacts/v1/impacts?startDate="+sixMonthsAgo+"&endDate="+today;

        Response response = Request.makeRequest("GET", impactApiUrl, requestHeaders, "");

        Assert.assertTrue(response.getStatusCode()==200, "The request to get equipmentId failed!");

        String randomEquipmentTypeId = response.path("equipmentTypeId[0]").toString();
        return  randomEquipmentTypeId;
    }

    private static String getRandomEquipmentIdFromActivityApi() {
        String authToken = Authorization.getAuthorizationToken(System.getProperty("clientId"), System.getProperty("clientSecret"));

        //Get date range between today and a yr ago
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date = new Date();
        String today = dateFormat.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        String sixMonthsAgo = dateFormat.format(c.getTime());

        //Add auth token to request header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String activityUrl = System.getProperty("baseUrl")
                +"/activity/v1/equipment?startTimestamp="+sixMonthsAgo+"&endTimestamp="+today;
        Response response = Request.makeRequest("GET", activityUrl, requestHeaders, "");

        Assert.assertTrue(response.getStatusCode()==200, "The request to get equipmentTypeId failed!");

        String randomEquipmentId = response.path("equipmentId[0]").toString();
        return  randomEquipmentId;
    }

    public static String getRandomUserIdFromActivityApi(){
        String authToken = Authorization.getAuthorizationToken(System.getProperty("clientId"), System.getProperty("clientSecret"));

        //Get date range between today and a yr ago
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date = new Date();
        String today = dateFormat.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        String sixMonthsAgo = dateFormat.format(c.getTime());

        //Add auth token to request header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String activityUrl = System.getProperty("baseUrl")
                +"/activity/v1/users?startTimestamp="+sixMonthsAgo+"&endTimestamp="+today;
        Response response = Request.makeRequest("GET", activityUrl, requestHeaders, "");

        Assert.assertTrue(response.getStatusCode()==200, "The request to get userId failed!");

        String randomValidUserId = response.path("userId[0]").toString();
        return  randomValidUserId;
    }


    private static String getRandomModuleGuid(){
        //Get auth token to call impact service
        String authToken = Authorization.getAuthorizationToken(System.getProperty("clientId"), System.getProperty("clientSecret"));

        //Get date range between today and a yr ago
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-d");
        Date date = new Date();
        String today = dateFormat.format(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, -6);
        String sixMonthsAgo = dateFormat.format(c.getTime());

        //Add auth token to request header
        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", "Bearer " + authToken);

        String impactApiRequestUrl = System.getProperty("baseUrl") + "impacts/v1/impacts?startDate="+sixMonthsAgo+"&endDate="+today;
        Response response = Request.makeRequest("GET", impactApiRequestUrl, requestHeaders, "");

        Assert.assertTrue(response.getStatusCode()==200, "The request to get moduleGuid failed!");

        String randomValidModuleId = response.path("impactId[0]").toString(); //FYI: impactId = moduleId
        return  randomValidModuleId;
    }
}
