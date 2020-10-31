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
import java.io.InputStream;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Generate implements Event {

    private String path;
    private static final Logger logger = LoggerFactory.getLogger(Generate.class);

    public Generate(String path) {
        this.path = path;
    }

    @Override
    public void execute() {
        ClassLoader classLoader = getClass().getClassLoader();
        try {
            InputStream inputStream = classLoader.getResourceAsStream("bundles/basic-lambda.zip");
            File tempFile = File.createTempFile("temp", "zip");
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            ZipFile zipFile = new ZipFile(tempFile);
            zipFile.extractAll(path);
            tempFile.deleteOnExit();
        } catch (ZipException | IOException e) {
            logger.error("Error while generating lambda",e);
        }
    }
}
