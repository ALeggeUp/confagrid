/*
 * App.java
 *
 * Copyright (C) 2017 [ A Legge Up ] <stephen@aleggeup.com>
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 */

package com.aleggeup.confagrid;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Hello world!
 *
 */
public class App {

    private final static Option help = Option.builder("h").longOpt("help").desc("print this message").build();

    public static void main(final String[] args) {
        final Options options = new Options();
        options.addOption(help);

        final CommandLineParser parser = new DefaultParser();

        try {
            final CommandLine cmdLine = parser.parse(options, args);
            if (cmdLine.hasOption("help")) {
                final HelpFormatter helpFormatter = new HelpFormatter();
                helpFormatter.printHelp("confagrid", options, true);
            }

        } catch (final ParseException e) {
            System.err.println("Parsing failed.  Reason: " + e.getMessage());
        }
    }
}
