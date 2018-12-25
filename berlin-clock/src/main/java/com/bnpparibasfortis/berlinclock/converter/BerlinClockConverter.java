package com.bnpparibasfortis.berlinclock.converter;

import org.apache.commons.lang3.StringUtils;

/**
 * This program simply converts Berlin clock time to Digital clock time and vice versa 
 *
 */
public class BerlinClockConverter {

	private static BerlinClockConverter berlinClockConverter;

	private BerlinClockConverter(){}
	
	public static BerlinClockConverter getInstance(){
		if(berlinClockConverter != null){
			return berlinClockConverter;
		}
		return 	new BerlinClockConverter();
	}
	
	public String getSingleMinuteRow(String digitalTime) {
		return StringUtils.EMPTY;
	}

	public String getFiveMinutesRow(String digitalTime) {
		return StringUtils.EMPTY;
	}

	public String getSingleHourRow(String digitalTime) {
		return StringUtils.EMPTY;
	}

	public String getFiveHoursRow(String digitalTime) {
		return StringUtils.EMPTY;
	}

	public String getSecondsLamp(String digitalTime) {
		return StringUtils.EMPTY;
	}

	public String getBerlinClockTime(String digitalTime) {
		return StringUtils.EMPTY;
	}

	public String getDigitalClockTime(String berlinTime) {
		return StringUtils.EMPTY;
	}
}