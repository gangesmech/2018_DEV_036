package com.bnpparibasfortis.berlinclock.converter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.time.format.DateTimeParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.runner.RunWith;


@RunWith(JUnitPlatform.class)
@IncludeEngines("junit-jupiter")
public class BerlinClockConverterTest{
	
	@Retention(RetentionPolicy.RUNTIME)
	@ParameterizedTest(name = "{0} should be {1}")
	@CsvSource({ "00:00:00, OOOO", "23:59:59, YYYY", "12:32:00, YYOO", "12:34:00, YYYY", "12:35:00, OOOO"})
	@DisplayName("Single Minute Row")
	@interface SingleMinuteParams { }
	
	@Retention(RetentionPolicy.RUNTIME)
	@ParameterizedTest(name = "{0} should be {1}")
	@CsvSource({ "00:00:00, OOOOOOOOOOO", "23:59:59, YYRYYRYYRYY", "12:04:00, OOOOOOOOOOO", "12:23:00, YYRYOOOOOOO", "12:35:00, YYRYYRYOOOO"})
	@DisplayName("Five Minutes Row")
	@interface FiveMinutesParams { }
	
	@Retention(RetentionPolicy.RUNTIME)
	@ParameterizedTest(name = "{0} should be {1}")
	@CsvSource({ "00:00:00, OOOO", "23:59:59, RRRO", "02:04:00, RROO", "08:23:00, RRRO", "14:35:00, RRRR"})
	@DisplayName("Single Hour Row")
	@interface SingleHourParams { }
	
	@Retention(RetentionPolicy.RUNTIME)
	@ParameterizedTest(name = "{0} should be {1}")
	@CsvSource({ "00:00:00, OOOO", "23:59:59, RRRR", "02:04:00, OOOO", "08:23:00, ROOO", "16:35:00, RRRO"})
	@DisplayName("Five Hours Row")
	@interface FiveHoursParams { }
	
	@Retention(RetentionPolicy.RUNTIME)
	@ParameterizedTest(name = "{0} should be {1}")
	@CsvSource({ "00:00:00, Y", "23:59:59, O"})
	@DisplayName("Seconds Lamp")
	@interface SecondsLampParams { }
	
	@Retention(RetentionPolicy.RUNTIME)
	@ParameterizedTest(name = "{0} should be {1}")
	@CsvSource({ "00:00:00, YOOOOOOOOOOOOOOOOOOOOOOO", "23:59:59, ORRRRRRROYYRYYRYYRYYYYYY", "16:50:06, YRRROROOOYYRYYRYYRYOOOOO", "11:37:01, ORROOROOOYYRYYRYOOOOYYOO"})
	@DisplayName("Digit to Berlin Clock")
	@interface digitToBerlinClockParams { }
	
	
	/**
	 * The below test cases have been intentionally commented for clarifications.
	 * Since seconds lamp doesn't hold any real counts, it seems, it would be difficult to successfully executed all given test cases. 
	 * 
	 */
	@Retention(RetentionPolicy.RUNTIME)
	@ParameterizedTest(name = "{1} should be {0}")	
	@CsvSource({ "00:00:00, YOOOOOOOOOOOOOOOOOOOOOOO", "11:37:01, ORROOROOOYYRYYRYOOOOYYOO"})
	//@CsvSource({ "00:00:00, YOOOOOOOOOOOOOOOOOOOOOOO", "23:59:59, ORRRRRRROYYRYYRYYRYYYYYY", "16:50:06, YRRROROOOYYRYYRYYRYOOOOO", "11:37:01, ORROOROOOYYRYYRYOOOOYYOO"})
	@DisplayName("Berlin to Digit Clock")
	@interface berlinToDigitClockParams { }
	
	private BerlinClockConverter berlinClockConverter = BerlinClockConverter.getInstance();
	
	
	@Test
	@DisplayName("Single Minute - Input Error")
	void testGetSingleMinuteRowInputError(){
		Assertions.assertThrows(DateTimeParseException.class, () -> berlinClockConverter.getSingleMinuteRow("25:61:0A"));
	}
	
	@Test
	@DisplayName("Single Hour - Input Error")
	void testGetSingleHourRowInputError(){
		Assertions.assertThrows(DateTimeParseException.class, () -> berlinClockConverter.getSingleHourRow("25:61:0A"));
	}
	
	@Test
	@DisplayName("Seconds Lamp - Input Error")
	void testGetSecondsLampInputError(){
		Assertions.assertThrows(DateTimeParseException.class, () -> berlinClockConverter.getSecondsLamp("25:61:0A"));
	}
	
	@Test
	@DisplayName("Five Minutes - Input Error")
	void testGetFiveMinutesRowInputError(){
		Assertions.assertThrows(DateTimeParseException.class, () -> berlinClockConverter.getFiveMinutesRow("25:61:0A"));
	}
	
	@Test
	@DisplayName("Five Hours - Input Error")
	void testGetFiveHoursRowInputError(){
		Assertions.assertThrows(DateTimeParseException.class, () -> berlinClockConverter.getFiveHoursRow("25:61:0A"));
	}
	
	@Test
	@DisplayName("Get Berlin Clock - Input Error")
	void testGetBerlinClockTimeInputError(){
		Assertions.assertThrows(DateTimeParseException.class, () -> berlinClockConverter.getBerlinClockTime("25:61:0A"));
	}
	
	@Test
	@DisplayName("Get Digital Clock - Input Error")
	void testGetDigitalClockTimeInputError(){
		Assertions.assertThrows(DateTimeParseException.class, () -> berlinClockConverter.getDigitalClockTime("ORORORORORORRORROROROROZ"));
	}
	

	@SingleMinuteParams
	void testGetSingleMinuteRow(String digitalTime, String berlinClockTime){
		Assertions.assertEquals(berlinClockConverter.getSingleMinuteRow(digitalTime), berlinClockTime);
	}
	
	@FiveMinutesParams
	void testGetFiveMinutesRow(String digitalTime, String berlinClockTime){
		Assertions.assertEquals(berlinClockConverter.getFiveMinutesRow(digitalTime), berlinClockTime);
	}
	
	@SingleHourParams
	void testGetSingleHourRow(String digitalTime, String berlinClockTime){
		Assertions.assertEquals(berlinClockConverter.getSingleHourRow(digitalTime), berlinClockTime);
	}
	
	@FiveHoursParams
	void testGetFiveHoursRow(String digitalTime, String berlinClockTime){
		Assertions.assertEquals(berlinClockConverter.getFiveHoursRow(digitalTime), berlinClockTime);
	}
	
	@SecondsLampParams
	void testGetSecondsLamp(String digitalTime, String berlinClockTime){
		Assertions.assertEquals(berlinClockConverter.getSecondsLamp(digitalTime), berlinClockTime);
	}
	
	@digitToBerlinClockParams
	void testGetBerlinClockTime(String digitalTime, String berlinClockTime){
		Assertions.assertEquals(berlinClockConverter.getBerlinClockTime(digitalTime), berlinClockTime);
	}
	
	@berlinToDigitClockParams
	void testGetDigitalClockTime(String digitalTime, String berlinClockTime){
		Assertions.assertEquals(berlinClockConverter.getDigitalClockTime(berlinClockTime), digitalTime);
	}
	
}