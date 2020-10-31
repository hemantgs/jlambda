/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.hemant.jlambda;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import picocli.CommandLine;

class AppTest {
    App app;
    CommandLine commandLine;
    StringWriter stringWriter;
    StringWriter errWriter;

    @BeforeEach
    public void setup() {
        app = new App();
        commandLine = new CommandLine(app);
        stringWriter = new StringWriter();
        errWriter = new StringWriter();
        commandLine.setOut(new PrintWriter(stringWriter));
        commandLine.setErr(new PrintWriter(errWriter));
    }

    @Test
    void shouldDisplayCorrectHelp() {
        app = new App("-h");
        commandLine = new CommandLine(app);
        commandLine.setOut(new PrintWriter(stringWriter));
        commandLine.setErr(new PrintWriter(errWriter));

        int code = commandLine.execute("-h");
        assertThat(code).isNotNegative();
        assertThat(stringWriter.toString()).contains("Usage: jlambda [-h] (-g=<path to generate lambda> | -b=<path to build package\n" +
                "               to> | [-p=<publish> [-e=<env>]])\n" +
                "  -b, --build=<path to build package to>\n" +
                "\n" +
                "  -e, --env=<env>\n" +
                "  -g, --generate=<path to generate lambda>\n" +
                "\n" +
                "  -h, --help\n" +
                "  -p, --publish=<publish>");
    }

    @Test
    void shouldThrowMissingArgumentErrorWithoutAnyCommand() {
        int code = commandLine.execute();
        assertThat(code).isEqualTo(2);
        assertThat(stringWriter.toString()).isEmpty();
        assertThat(errWriter.toString()).contains("Error: Missing required argument");
    }
}
