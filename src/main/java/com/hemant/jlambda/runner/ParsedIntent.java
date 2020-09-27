package com.hemant.jlambda.runner;

import picocli.CommandLine;

public class ParsedIntent {
    @CommandLine.Option(names = {"-g", "--generate"}, paramLabel = "<path to generate lambda>")
    String generate;

    @CommandLine.Option(names = {"-b", "--build"}, paramLabel = "<path to build package to>")
    String build;
}
