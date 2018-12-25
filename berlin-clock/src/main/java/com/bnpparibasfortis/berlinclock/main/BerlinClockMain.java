package com.bnpparibasfortis.berlinclock.main;

import java.util.logging.Level;
import java.util.logging.Logger;
import com.bnpparibasfortis.berlinclock.cli.BerlinClockCli;
import com.bnpparibasfortis.berlinclock.converter.BerlinClockConverter;

/**
 * This CLI class acts as the main interface for Berlin clock converter
 *
 */
public class BerlinClockMain {

	private static final Logger LOG = Logger.getLogger(BerlinClockMain.class.getSimpleName());
	
	public static void main(String[] args) {
		String message = BerlinClockCli.process(BerlinClockConverter.getInstance(), args);
		LOG.log(Level.INFO, message);		 
	}
	


}
