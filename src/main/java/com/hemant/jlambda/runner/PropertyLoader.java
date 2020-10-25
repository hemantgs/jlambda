/*
 * Copyright 2020 hemantgs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hemant.jlambda.runner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.hemant.jlambda.model.LambdaConfig;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;

public class PropertyLoader {

    public static LambdaConfig loadProperties(String path, String env) {
        try {
            InputStream inputStream = getFileStream(path, env);
            Properties config = new Properties();
            config.load(inputStream);
            LambdaConfig lambdaConfig = new LambdaConfig();
            lambdaConfig.setName(config.getProperty("jlambda.aws.name", "default_jlamda"));
            lambdaConfig.setExecutionRole(config.getProperty("jlambda.aws.role"));
            lambdaConfig.setAwsAccessKey(config.getProperty("jlambda.aws.access_key_id"));
            lambdaConfig.setAwsAccessSecret(config.getProperty("jlambda.aws.access_key_secret"));
            lambdaConfig.setProfile(config.getProperty("jlambda.aws.profile"));
            lambdaConfig.setHandler(config.getProperty("jlambda.aws.handler"));
            lambdaConfig.setRegion(config.getProperty("jlambda.aws.region"));
            lambdaConfig.setMemory(config.getProperty("jlambda.aws.mem", "512"));
            lambdaConfig.setTracingConfig(config.getProperty("jlambda.aws.tracing_config"));
            lambdaConfig.setVpcSecurityGroups(config.getProperty("jlambda.aws.security_groups"));
            lambdaConfig.setVpcSubnets(config.getProperty("lambda.aws.subnets"));
            lambdaConfig.setDescription(config.getProperty("jlambda.aws.description"));
            lambdaConfig.setTimeout(config.getProperty("jlambda.aws.timeout"));
            return lambdaConfig;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new LambdaConfig();
    }

    private static InputStream getFileStream(String path, String env) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(String.format("%s/basic-lambda/%s.properties", path, env));
        if (inputStream == null) {
            inputStream = new FileInputStream(String.format("%s/basic-lambda/%default.properties.properties", path));
        }
        return inputStream;
    }
}
