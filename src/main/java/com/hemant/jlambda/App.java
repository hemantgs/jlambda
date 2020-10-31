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

package com.hemant.jlambda;

import com.hemant.jlambda.runner.EventsRunner;
import com.hemant.jlambda.runner.ParsedIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "jlambda")
public class App implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(App.class);
    private final String[] args;

    public App(String... args) {
        this.args = args;
    }

    @Option(names = {"-h", "--help"}, usageHelp = true)
    private boolean help;

    @ArgGroup(exclusive = true, multiplicity = "1")
    private ParsedIntent intent;

    public static void main(String[] args) {
        int exitCode =  new CommandLine(new App(args))
                .setCaseInsensitiveEnumValuesAllowed(true)
                .execute(args);

        logger.debug("Exiting application with the exit code: {}",exitCode);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        logger.debug("Started running the command with parameters: {}", (Object) args);
        EventsRunner.build()
                    .withEnv("")
                    .withIntent(intent)
                    .run();
    }
}
