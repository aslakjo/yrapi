package no.yr.util;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;


public class Validity implements Comparable<Validity> {

	/**
	 * from time this is valid
	 */
	protected Calendar from;
	/**
	 * valid to
	 */
	protected Calendar valid;
	
	
	public Calendar getFrom() {
		return from;
	}
	/**
	 * handles only strings from the met.no xml on the used iso format
	 * @param from
	 * @return
	 */
	public Validity setFrom(String from)
		throws Exception{
		
		this.from = stringToCalendar(from);
		return this;
	}
	
	protected Calendar stringToCalendar(String time)
		throws Exception
	{
		
		String[] dateSequece;
		String[] timeSequence;
		try {
			Pattern pattern = Pattern.compile("T");
			String[] dateAndTime  = pattern.split(time);
			
					
			Pattern date = Pattern.compile("-");
			dateSequece = date.split(dateAndTime[0]);
			
			Pattern clockTime = Pattern.compile(":");
			timeSequence = clockTime.split(dateAndTime[1]);
			timeSequence[2] = new String(timeSequence[2].subSequence(0, 2).toString()); // removeing the z
		} catch (IndexOutOfBoundsException e) {
			throw new Exception("error in format " + time);
		}
		
		
		return new GregorianCalendar(
					Integer.valueOf(dateSequece[0]),
					Integer.valueOf(dateSequece[1]),
					Integer.valueOf(dateSequece[2]),
					Integer.valueOf(timeSequence[0]),
					Integer.valueOf(timeSequence[1]),
					Integer.valueOf(timeSequence[2]));
		
		
	}
	
	public Calendar getValidTo() {
		return valid;
	}
	public Validity setValidTo(String validTo)
		throws Exception{
		this.valid = stringToCalendar(validTo);
		 
		return this;
	}
	
	
	public int compareTo(Validity o) {
		return from.compareTo(o.getFrom());
	}
	
	public boolean equals(Object o)
	{
		return from.equals(o);
	}
	
}
