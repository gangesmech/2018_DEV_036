package com.bnpparibasfortis.berlinclock.converter;

import java.time.LocalTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;

/**
 * This program simply converts Berlin clock time to Digital clock time and vice versa 
 *
 */
public class BerlinClockConverter {

	private static final String LAMP_OFF = "O";
	private static final String LAMP_ON_YELLOW = "Y";
	private static final String LAMP_ON_RED = "R";
	private static final int SINGLE_MINUTE_ROW_COUNT = 4;
	private static final int FIVE_MINUTES_ROW_COUNT = 11;
	private static final int HOUR_ROW_COUNT = 4;
	
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
		return StringUtils.EMPTY;
	}
	
}