package com.hemant.jlambda.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hemant.jlambda.model.LambdaConfig;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;

public class PropertyLoader {

    public static LambdaConfig loadProperties(String path) {
        try {
            InputStream inputStream = new FileInputStream(String.format("%s/basic-lambda/default.properties", path));
            Properties config = new Properties();
            config.load(inputStream);
            LambdaConfig lambdaConfig = new LambdaConfig();
            lambdaConfig.setName((String) config.getOrDefault("jlambda.name", "default_jlamda"));
            return lambdaConfig;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LambdaConfig();
    }
}
