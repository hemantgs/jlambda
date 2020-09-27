package com.hemant.jlambda.events;

import java.io.File;
import java.io.IOException;

import com.hemant.jlambda.model.LambdaConfig;
import org.apache.commons.io.FileUtils;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.model.CreateFunctionRequest;
import software.amazon.awssdk.services.lambda.model.CreateFunctionResponse;
import software.amazon.awssdk.services.lambda.model.FunctionCode;
import software.amazon.awssdk.services.lambda.model.Runtime;

public class Publish implements Event {

    private String pathToZip;

    public Publish(String pathToZip) {
        this.pathToZip = pathToZip;
    }

    @Override
    public void execute(LambdaConfig config) {
        LambdaClient lambdaClient = LambdaClient.builder().region(Region.US_WEST_2).build();
        try {
            SdkBytes bytes = SdkBytes.fromByteArray(FileUtils.readFileToByteArray(new File(pathToZip)));
            CreateFunctionRequest createFunctionRequest =
                    CreateFunctionRequest.builder()
                                         .functionName(config.getName())
                                         .code(code -> code.zipFile(bytes).build())
                                         .handler(String.format("com.lambda.basic.Handler"))
                                         .runtime(Runtime.JAVA11)
                                         .role("arn:aws:iam::498341694494:role/lambda-basic-execution").build();
            CreateFunctionResponse createFunctionResponse = lambdaClient.createFunction(createFunctionRequest);
            System.out.println("Created Function" + createFunctionResponse.functionName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
