package com.hemant.jlambda.runner;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import com.hemant.jlambda.model.LambdaConfig;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.lambda.LambdaClient;
import software.amazon.awssdk.services.lambda.LambdaClientBuilder;

public class AWSCredentialsHandler {
    static LambdaClient client;

    public static Optional<LambdaClient> getClient(LambdaConfig lambdaConfig) {
        return creds(lambdaConfig)
                .map(awsCredentialsProvider -> LambdaClient.builder().credentialsProvider(awsCredentialsProvider).build());
    }

    static Optional<AwsCredentialsProvider> creds(LambdaConfig lambdaConfig) {
        if (Objects.nonNull(lambdaConfig.getAwsAccessKey()) && Objects.nonNull(lambdaConfig.getAwsAccessSecret())) {
            AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials.create(lambdaConfig.getAwsAccessKey(), lambdaConfig.getAwsAccessSecret());
            return Optional.of(StaticCredentialsProvider.create(awsBasicCredentials));
        } else if (Objects.nonNull(lambdaConfig.getProfile())) {
            return Optional.of(ProfileCredentialsProvider.create(lambdaConfig.getProfile()));
        } else {
            return Optional.of(DefaultCredentialsProvider.create());
        }
    }
}
