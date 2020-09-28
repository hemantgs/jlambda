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

package com.hemant.jlambda.events;

import java.io.File;
import java.io.IOException;

import com.hemant.jlambda.model.LambdaConfig;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.CreateFunctionRequest;
import software.amazon.awssdk.services.lambda.model.CreateFunctionResponse;
import software.amazon.awssdk.services.lambda.model.FunctionCode;
import software.amazon.awssdk.services.lambda.model.Runtime;

public class Publish implements Event {

    private String pathToZip;
    private static LambdaConfig lambdaConfig;

    Logger logger = LoggerFactory.getLogger(Publish.class);

    public Publish(String pathToZip) {
        this.pathToZip = String.format("%s/basic-lambda/build/distributions/basic-lambda.zip", pathToZip);
    }

    public static void setLambdaConfig(LambdaConfig lambdaConfig) {
        Publish.lambdaConfig = lambdaConfig;
    }

    @Override
    public void execute() {
        logger.info("Publishing Lambda now");
        LambdaClient lambdaClient = LambdaClient.builder().region(Region.US_WEST_2).build();
        try {
            SdkBytes bytes = SdkBytes.fromByteArray(FileUtils.readFileToByteArray(new File(pathToZip)));
            CreateFunctionRequest createFunctionRequest =
                    CreateFunctionRequest.builder()
                                         .functionName(lambdaConfig.getName())
                                         .code(code -> code.zipFile(bytes).build())
                                         .handler(String.format("com.lambda.basic.Handler"))
                                         .runtime(Runtime.JAVA11)
                                         .role(lambdaConfig.getExecutionRole()).build();
            CreateFunctionResponse createFunctionResponse = lambdaClient.createFunction(createFunctionRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

