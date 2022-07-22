package com.automation.api.tests;

import com.automation.api.utils.PropertiesUtil;
import io.restassured.response.Response;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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

    public boolean schemaValidationFileInExcel() {
        //If the schemaValidationFile coulmn in the excel sheet is not empty, return true; else false
        return !System.getProperty("schemaValidationFile").equals("");
    }

    public void doSchemaValidation(Response response, String nameOfApi) {

        String schemaFileLocation = System.getProperty("user.dir")  + "\\src\\test\\resources\\schemaValidations\\"
                + nameOfApi + "\\"
                + System.getProperty("schemaValidationFile");

        File file = new File(System.getProperty("user.dir")
                + "/src/test/resources/schemaValidations/"
                + nameOfApi + "/"
                + System.getProperty("schemaValidationFile"));

        response.then()
                .assertThat().body(matchesJsonSchema(file));
    }
}
