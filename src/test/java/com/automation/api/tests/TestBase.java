package com.automation.api.tests;

import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    static String apiKey;
    static String authUrl;
    static String clientId;
    static String clientSecret;

    @BeforeSuite()
    public void loadProperties() throws IOException {
        File src = new File(System.getProperty("user.dir") +
                "/src/test/resources/" + System.getProperty("env") + ".properties");

        FileInputStream fis = new FileInputStream(src);

        Properties p = new Properties();
        p.load(fis);
        for (String name : p.stringPropertyNames()) {
            String value = p.getProperty(name);
            System.setProperty(name, value);
        }

//        apiKey = System.getProperty("apiKey");
//        authUrl = System.getProperty("authUrl");
//        clientId = System.getProperty("clientId");
//        clientSecret = System.getProperty("clientSecret");
    }
}
