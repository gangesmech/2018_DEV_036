package com.bnpparibasfortis.berlinclock.converter;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;

/**
 * This program simply converts Berlin clock time to Digital clock time and vice versa 
 *
 */
public class BerlinClockConverter {

	private static final String DIGITAL_CLOCK_PAD_CHAR = "0";
	private static final String LAMP_OFF = "O";
	private static final String LAMP_ON_YELLOW = "Y";
	private static final String LAMP_ON_RED = "R";
	private static final String DIGITAL_CLOCK_DELIMITER = ":";
	private static final int SINGLE_MINUTE_ROW_COUNT = 4;
	private static final int FIVE_MINUTES_ROW_COUNT = 11;
	private static final int HOUR_ROW_COUNT = 4;
	private static final String BERLIN_CLOCK_PATTERN = "^(.{1})(.{4})(.{4})(.{11})(.{4}).*";
	private static final String BERLIN_CLOCK_CHAR_PATTERN = "[OYR]+";
	private static final String INPUT_DATE_FORMAT_ERROR = "ERROR";
	
	private static BerlinClockConverter berlinClockConverter;

	private BerlinClockConverter(){}
	
	public static BerlinClockConverter getInstance(){
		if(berlinClockConverter != null){
			return berlinClockConverter;
		}
		return 	new BerlinClockConverter();
	}
	
	public String getSingleMinuteRow(String digitalTime) {
		LocalTime digitalLocalTime = LocalTime.parse(digitalTime);
		String onLamps = IntStream.range(0, digitalLocalTime.getMinute() % 5).mapToObj(e -> LAMP_ON_YELLOW)
				.collect(Collectors.joining(StringUtils.EMPTY));
		return StringUtils.rightPad(onLamps, SINGLE_MINUTE_ROW_COUNT, LAMP_OFF);
	}

	public String getFiveMinutesRow(String digitalTime) {
		LocalTime digitalLocalTime = LocalTime.parse(digitalTime);
		String onLamps = IntStream.range(0, digitalLocalTime.getMinute() / 5)
				.mapToObj(e -> (e + 1) % 3 == 0 ? LAMP_ON_RED : LAMP_ON_YELLOW)
				.collect(Collectors.joining(StringUtils.EMPTY));
		return StringUtils.rightPad(onLamps, FIVE_MINUTES_ROW_COUNT, LAMP_OFF);
	}

	public String getSingleHourRow(String digitalTime) {
		LocalTime digitalLocalTime = LocalTime.parse(digitalTime);
		String onLamps = IntStream.range(0, digitalLocalTime.getHour() % 5).mapToObj(e -> LAMP_ON_RED)
				.collect(Collectors.joining(StringUtils.EMPTY));
		return StringUtils.rightPad(onLamps, HOUR_ROW_COUNT, LAMP_OFF);
	}

	public String getFiveHoursRow(String digitalTime) {
		LocalTime digitalLocalTime = LocalTime.parse(digitalTime);
		String onLamps = IntStream.range(0, digitalLocalTime.getHour() / 5).mapToObj(e -> LAMP_ON_RED)
				.collect(Collectors.joining(StringUtils.EMPTY));
		return StringUtils.rightPad(onLamps, HOUR_ROW_COUNT, LAMP_OFF);
	}

	public String getSecondsLamp(String digitalTime) {
		LocalTime digitalLocalTime = LocalTime.parse(digitalTime);
		return digitalLocalTime.getSecond() % 2 == 0 ? LAMP_ON_YELLOW : LAMP_OFF;
	}

	public String getBerlinClockTime(String digitalTime) {
		return getSecondsLamp(digitalTime) + getFiveHoursRow(digitalTime) + getSingleHourRow(digitalTime)
				+ getFiveMinutesRow(digitalTime) + getSingleMinuteRow(digitalTime);
	}

	public String getDigitalClockTime(String berlinTime) {
		validateBerlinClockTime(berlinTime);
		String[] berlinClockValues = parseBerlinClockValues(berlinTime);
		int digitalHours = countOnLamps(berlinClockValues[1]) * 5 + countOnLamps(berlinClockValues[2]);
		int digitalMinutes = countOnLamps(berlinClockValues[3]) * 5 + countOnLamps(berlinClockValues[4]);
		int digitalSeconds = countOnLamps(berlinClockValues[0]) == 1 ? 0 : 1;
		return StringUtils.leftPad(String.valueOf(digitalHours), 2, DIGITAL_CLOCK_PAD_CHAR)
					+ DIGITAL_CLOCK_DELIMITER
						+ StringUtils.leftPad(String.valueOf(digitalMinutes), 2, DIGITAL_CLOCK_PAD_CHAR)
							+ DIGITAL_CLOCK_DELIMITER
								+ StringUtils.leftPad(String.valueOf(digitalSeconds), 2, DIGITAL_CLOCK_PAD_CHAR);
	}

	private String[] parseBerlinClockValues(String berlinTime) {
		Pattern pattern = Pattern.compile(BERLIN_CLOCK_PATTERN);
		Matcher matcher = pattern.matcher(berlinTime);
		String[] berlinClockValues = new String[matcher.groupCount()];
		if (matcher.matches()) {
			IntStream.rangeClosed(1, matcher.groupCount()).forEach(e -> berlinClockValues[e - 1] = matcher.group(e));
		}
		return berlinClockValues;
	}

	private void validateBerlinClockTime(String berlinTime) {
		if (berlinTime.length() != 24 || !berlinTime.matches(BERLIN_CLOCK_CHAR_PATTERN)) {
			throw new DateTimeParseException(INPUT_DATE_FORMAT_ERROR, berlinTime, 0);
		}
	}

	private int countOnLamps(String berlinTime) {
		return StringUtils.countMatches(berlinTime, LAMP_ON_RED) + StringUtils.countMatches(berlinTime, LAMP_ON_YELLOW);
	}
}