package com.bnpparibasfortis.berlinclock.cli;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.runner.RunWith;
import com.bnpparibasfortis.berlinclock.cli.BerlinClockCli;
import com.bnpparibasfortis.berlinclock.converter.BerlinClockConverter;

@RunWith(JUnitPlatform.class)
@IncludeEngines("junit-jupiter")
public class BerlinClockCliTest{

	@Test
	@DisplayName("CLI Berlin Clock - Wrong Input time")
	void testProcessWithWrongInputTime(){
		Assertions.assertEquals(BerlinClockCli.INPUT_FORMAT_ERROR, BerlinClockCli.process(BerlinClockConverter.getInstance(), new String[]{"-i", "25:62:61"}));
	}
	
	@Test
	@DisplayName("CLI Berlin Clock - Wrong Option")
	void testProcessWithWrongOptions(){
		Assertions.assertEquals(BerlinClockCli.INPUT_FORMAT_ERROR, BerlinClockCli.process(BerlinClockConverter.getInstance(), new String[]{"-t", "25:62:61"}));
	}
	
	@Test
	@DisplayName("CLI Berlin Clock - Empty Input")
	void testProcessWithEmptyInput(){
		Assertions.assertEquals(StringUtils.EMPTY, BerlinClockCli.process(BerlinClockConverter.getInstance(), null));
	}
	
	@Test
	@DisplayName("CLI Digital to Berlin Clock - Valid time")
	void testProcessDigitalToBerlinClock(){
		Assertions.assertEquals("ORRRRRRROYYRYYRYYRYYYYYY", BerlinClockCli.process(BerlinClockConverter.getInstance(), new String[]{"-i", "23:59:59"}));
	}
	
	@Test
	@DisplayName("CLI Berlin to Digital Clock - Valid time")
	void testProcessBerlinToDigitalClock(){
		Assertions.assertEquals("11:37:01", BerlinClockCli.process(BerlinClockConverter.getInstance(), new String[]{"-r", "-i", "ORROOROOOYYRYYRYOOOOYYOO"}));
	}
}
