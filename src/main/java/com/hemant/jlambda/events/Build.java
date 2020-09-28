package com.hemant.jlambda.events;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.hemant.jlambda.model.LambdaConfig;
import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;
import org.gradle.tooling.model.GradleProject;
import org.gradle.tooling.model.GradleTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Build implements Event {
    GradleConnector gradleConnector;
    Logger logger = LoggerFactory.getLogger(Build.class);

    public Build(String path) {
        gradleConnector = GradleConnector.newConnector();
        gradleConnector.forProjectDirectory(new File(String.format("%s/basic-lambda/", path)));
    }

    @Override
    public void execute() {
        logger.info("Building deployment package");
        ProjectConnection connection = gradleConnector.connect();
        BuildLauncher launcher = connection.newBuild();
        GradleProject project = connection.getModel(GradleProject.class);
        List<? extends GradleTask> tasks = project.getTasks().getAll();
        try {
            launcher.forTasks(":buildZip").run();
        } catch (Throwable e) {
            System.out.println(e.getCause());
        } finally {
            connection.close();
        }
    }
}
