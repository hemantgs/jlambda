package com.hemant.jlambda.events;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.hemant.jlambda.model.LambdaConfig;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;

public class Generate implements Event {

    private String path;

    public Generate(String path) {
        this.path = path;
    }

    @Override
    public void execute(LambdaConfig config) {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream("bundles/basic-lambda.zip");
            File tempFile = File.createTempFile("temp", "zip");
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            ZipFile zipFile = new ZipFile(tempFile);
            zipFile.extractAll(path);
            tempFile.deleteOnExit();
        } catch (ZipException | IOException e) {
            e.printStackTrace();
        }
    }
}
