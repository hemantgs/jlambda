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
                                         .role("arn:aws:iam::498341694494:role/lambda-basic-execution").build();
            CreateFunctionResponse createFunctionResponse = lambdaClient.createFunction(createFunctionRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

