package no.yr.weather;

import java.io.FileReader;
import java.util.Calendar;
import java.util.Iterator;
import junit.framework.TestCase;
import no.yr.xml.parser.Weatherdata;

public class TimeSeriesTest extends TestCase {

    Weatherdata weatherData = null;

	protected void setUp() throws Exception {
		super.setUp();

        FileReader reader;
		reader = new FileReader("1.5-yrexample.xml");
        int bufferSize = 900000;
		char[] buf = new char[bufferSize];

		int sizeRead = reader.read(buf);
        if(sizeRead == bufferSize)
        {
            throw new Exception("To small a buffer to read the entire xml.");
        }

		String xml = new String(buf);
        xml = xml.trim();

        

		weatherData = new Weatherdata(xml);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testGetSeriesForHours()
	{
		TimeSeries series = weatherData.getTimeSeriesForHours(10);
        assertEquals(10, series.getSize());
	}
}
