package com.hemant.jlambda.runner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hemant.jlambda.model.LambdaConfig;

public class PropertyLoader {

    public static LambdaConfig loadProperties(String name) {
        InputStream inputStream = PropertyLoader.class.getResourceAsStream(String.format("%s.properties",name));
        Properties config = new Properties();
        try {
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
