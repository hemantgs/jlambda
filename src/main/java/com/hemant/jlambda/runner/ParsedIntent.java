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

package com.hemant.jlambda.runner;

import picocli.CommandLine;

public class ParsedIntent {
    @CommandLine.Option(names = {"-g", "--generate"}, paramLabel = "<path to generate lambda>")
    String generate;

    @CommandLine.Option(names = {"-b", "--build"}, paramLabel = "<path to build package to>")
    String build;

    @CommandLine.Option(names = {"-p", "--publish"}, paramLabel = "<path to packaged zip>")
    String publish;
}
