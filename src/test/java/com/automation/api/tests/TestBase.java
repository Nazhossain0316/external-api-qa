package com.automation.api.tests;

import com.automation.api.utils.PropertiesUtil;
import com.automation.api.utils.Request;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static String baseUrl;
    public static String clientId;
    public static String clientSecret;

    @BeforeSuite
    public static void setUp() {
        PropertiesUtil.loadPropertiesFile();
        baseUrl = System.getProperty("baseUrl");
        clientId = System.getProperty("clientId");
        clientSecret = System.getProperty("clientSecret");
    }



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
    }
}
