package com.automation.api.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Properties {

    public static void loadPropertiesFile() {
        File src = new File(System.getProperty("user.dir") +
                "/src/test/resources/" + System.getProperty("env") + ".properties");

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(src);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        java.util.Properties p = new java.util.Properties();
        try {
            p.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (String name : p.stringPropertyNames()) {
            String value = p.getProperty(name);
            System.setProperty(name, value);
        }
    }
}
