package com.bnpparibasfortis.berlinclock.cli;

import java.time.format.DateTimeParseException;
import java.util.logging.Logger;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.StringUtils;
import com.bnpparibasfortis.berlinclock.converter.BerlinClockConverter;

/**
 * This program provide some utility functions for accessing API through command line interface
 * 
 */
public class BerlinClockCli {

	public static final String BERLIN_CLOCK_CLI_NAME = "Berlin Clock";
	public static final String OPTION_TIME_INPUT = "time-input";
	public static final String OPTION_REVERSE_CONVERSION = "reverse-conversion";
	public static final String INPUT_FORMAT_ERROR = "INPUT FORMAT ERROR";
	private static final Logger LOG = Logger.getLogger(BerlinClockCli.class.getSimpleName());

	private BerlinClockCli() {
	}

	private static Options creatOptions() {
		Options options = new Options();
		options.addOption("r", OPTION_REVERSE_CONVERSION, false,
				"set this for Berlin clock time to Digital time conversion");
		options.addOption("i", OPTION_TIME_INPUT, true,
				"provide the time for conversion (by default, it will convert to Berlin clock time)");
		return options;

	}

	private static void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp(BERLIN_CLOCK_CLI_NAME, options);
	}

	public static String process(BerlinClockConverter berlinClockConverter, String[] args) {
		CommandLineParser parser = new DefaultParser();
		Options options = creatOptions();
		try {
			CommandLine line = parser.parse(options, args);
			if (line.hasOption(OPTION_TIME_INPUT)) {
				if (line.hasOption(OPTION_REVERSE_CONVERSION)) {
					return berlinClockConverter.getDigitalClockTime(line.getOptionValue(OPTION_TIME_INPUT));
				} else {
					return berlinClockConverter.getBerlinClockTime(line.getOptionValue(OPTION_TIME_INPUT));
				}
			} else {
				printHelp(options);
			}

		} catch (DateTimeParseException dtpe) {
			LOG.warning("Input Format:" + dtpe.getMessage());
			return INPUT_FORMAT_ERROR;
		} catch (Exception exp) {
			LOG.warning("Unexpected exception:" + exp.getMessage());
			return INPUT_FORMAT_ERROR;
		}
		return StringUtils.EMPTY;
	}

}
