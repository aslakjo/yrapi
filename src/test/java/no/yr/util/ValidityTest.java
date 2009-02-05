package no.yr.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

import junit.framework.TestCase;

public class ValidityTest extends TestCase {


	public void testStringToCalendar() 
	throws Exception{
		String from ="2008-01-30T21:00:00Z";
		Calendar cal = new GregorianCalendar(2008,01,30,21,00,00);
		
		Validity valid = new Validity();
		valid.setFrom(from);
		assertEquals(cal, valid.getFrom());
	}

}
