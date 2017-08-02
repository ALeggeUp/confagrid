/*
 * App.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.aleggeup.confagrid.words.Phrase;

/**
 * App entry point.
 *
 * @author Stephen Legge <stephen@aleggeup.com>
 */
public class App {

    private static final String APP_NAME = "confagrid";

    private static final String OPTION_LONG_INPUT = "input";
    private static final String OPTION_LONG_HELP = "help";

    private static final String OPTION_SHORT_INPUT = "i";
    private static final String OPTION_SHORT_HELP = "h";

    private static final Option help = Option.builder(OPTION_SHORT_HELP)
            .longOpt(OPTION_LONG_HELP).desc("print this message").build();

    private static final Option input = Option.builder(OPTION_SHORT_INPUT)
            .longOpt(OPTION_LONG_INPUT).desc("phrase input file")
            .hasArg().argName("FILE")
            .build();

    public static void main(final String[] args) {
        final Options options = new Options();
        options.addOption(help);
        options.addOption(input);

        final CommandLineParser parser = new DefaultParser();

        try {
            final CommandLine cmdLine = parser.parse(options, args);
            if (cmdLine.hasOption(OPTION_LONG_HELP)) {
                printHelp(options);
            } else if (cmdLine.hasOption(OPTION_LONG_INPUT)) {
                final String filename = cmdLine.getOptionValue(OPTION_LONG_INPUT);

                try (final Stream<String> stream = Files.lines(Paths.get(filename))) {
                    stream.forEach(App::writePhrase);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (final ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
            printHelp(options);
        }
    }

    public static void writePhrase(final String line) {
        final Phrase phrase = new Phrase(line);
        System.out.println(phrase.toString());
    }

    public static void printHelp(final Options options) {
        final HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp(APP_NAME, options, true);
    }
}
