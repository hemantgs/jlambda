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

import com.hemant.jlambda.events.Event;
import com.hemant.jlambda.events.Generate;
import com.hemant.jlambda.model.LambdaConfig;
import com.hemant.jlambda.runner.EventsRunner;
import com.hemant.jlambda.runner.ParsedIntent;
import com.hemant.jlambda.runner.PropertyLoader;
import picocli.CommandLine;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.IOException;

@Command(name = "jlambda")
public class App implements Runnable {

    @Option(names = {"-h", "--help"}, usageHelp = true)
    private boolean help;

    @ArgGroup(exclusive = true, multiplicity = "1")
    private ParsedIntent intent;

    public static void main(String[] args) throws IOException {
        int exitCode =  new CommandLine(new App()).setCaseInsensitiveEnumValuesAllowed(true).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {

        EventsRunner.build()
                    .withEnv("")
                    .withIntent(intent)
                    .run();
    }
}
