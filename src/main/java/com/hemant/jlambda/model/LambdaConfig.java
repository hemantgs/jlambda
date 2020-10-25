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

package com.hemant.jlambda.model;

public class LambdaConfig {
    private String name;
    private String executionRole;
    private String awsAccessKey;
    private String awsAccessSecret;
    private String profile;
    private String handler;
    private String region;
    private String memory;
    private String timeout = "60";
    private String runTimeout = "3";
    private String description;
    private String version;
    private String vpcSubnets;
    private String vpcSecurityGroups;
    private String tracingConfig;
    private String logRetentionDays;
    private String layers;

    public String getLayers() {
        return layers;
    }

    public void setLayers(String layers) {
        this.layers = layers;
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout;
    }

    public String getRunTimeout() {
        return runTimeout;
    }

    public void setRunTimeout(String runTimeout) {
        this.runTimeout = runTimeout;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVpcSubnets() {
        return vpcSubnets;
    }

    public void setVpcSubnets(String vpcSubnets) {
        this.vpcSubnets = vpcSubnets;
    }

    public String getVpcSecurityGroups() {
        return vpcSecurityGroups;
    }

    public void setVpcSecurityGroups(String vpcSecurityGroups) {
        this.vpcSecurityGroups = vpcSecurityGroups;
    }

    public String getTracingConfig() {
        return tracingConfig;
    }

    public void setTracingConfig(String tracingConfig) {
        this.tracingConfig = tracingConfig;
    }

    public String getLogRetentionDays() {
        return logRetentionDays;
    }

    public void setLogRetentionDays(String logRetentionDays) {
        this.logRetentionDays = logRetentionDays;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getAwsAccessKey() {
        return awsAccessKey;
    }

    public void setAwsAccessKey(String awsAccessKey) {
        this.awsAccessKey = awsAccessKey;
    }

    public String getAwsAccessSecret() {
        return awsAccessSecret;
    }

    public void setAwsAccessSecret(String awsAccessSecret) {
        this.awsAccessSecret = awsAccessSecret;
    }

    public LambdaConfig(String name) {
        this.name = name;
    }

    public LambdaConfig() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecutionRole() {
        return executionRole;
    }

    public void setExecutionRole(String executionRole) {
        this.executionRole = executionRole;
    }
}
