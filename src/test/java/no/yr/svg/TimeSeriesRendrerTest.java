package no.yr.svg;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import junit.framework.TestCase;
import no.yr.xml.parser.Weatherdata;

import org.jdom.output.XMLOutputter;

public class TimeSeriesRendrerTest extends TestCase {

	public void testTimeSeriesRenderer() throws Exception
	{
		FileReader reader;  
		reader = new FileReader("yrexample.xml");
		char[] buf = new char[100000];
		reader.read(buf);
				
		String xml = new String(buf);
		xml = xml.trim();
		
		Weatherdata data = new Weatherdata(xml);
		
		TimeSeriesRendrer renderer = new TimeSeriesRendrer(data.getTimeSeries());
		
		XMLOutputter out = new XMLOutputter();
		
		File newFile = new File("timeseries.svg");
			
		FileWriter file = new FileWriter(newFile);
		out.output(renderer.getSvg(), file);
		
		System.out.flush();
		
		
	}

}
