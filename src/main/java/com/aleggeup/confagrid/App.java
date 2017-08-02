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

    private final static Option help = Option.builder("h").longOpt("help").desc("print this message").build();

    private final static Option input = Option.builder("i")
            .longOpt("input").desc("phrase input file")
            .hasArg().argName("FILE")
            .build();

    public static void main(final String[] args) {
        final Options options = new Options();
        options.addOption(help);
        options.addOption(input);

        final CommandLineParser parser = new DefaultParser();

        try {
            final CommandLine cmdLine = parser.parse(options, args);
            if (cmdLine.hasOption("help")) {
                final HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("confagrid", options, true);
            } else if (cmdLine.hasOption("input")) {
                final String filename = cmdLine.getOptionValue("input");

                try (final Stream<String> stream = Files.lines(Paths.get(filename))) {
                    stream.forEach(App::writePhrase);
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (final ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
        }
    }

    public static void writePhrase(final String line) {
        final Phrase phrase = new Phrase(line);
        System.out.println(phrase.toString());
    }
}
