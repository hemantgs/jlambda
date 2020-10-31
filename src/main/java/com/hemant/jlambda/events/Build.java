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
import java.util.List;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;
import org.gradle.tooling.model.GradleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Build implements Event {
    GradleConnector gradleConnector;
    private static final Logger logger = LoggerFactory.getLogger(Build.class);

    public Build(String path) {
        gradleConnector = GradleConnector.newConnector();
        gradleConnector.forProjectDirectory(new File(String.format("%s/basic-lambda/", path)));
    }

    @Override
    public void execute() {
        logger.info("Building deployment package.");
        ProjectConnection connection = gradleConnector.connect();
        BuildLauncher launcher = connection.newBuild();
        GradleProject project = connection.getModel(GradleProject.class);
        List<? extends GradleTask> tasks = project.getTasks().getAll();
        try {
            launcher.forTasks(":buildZip").run();
        } catch (Throwable e) {
            logger.error("Error while building deployment package.",e);
        } finally {
            connection.close();
        }
    }
}
