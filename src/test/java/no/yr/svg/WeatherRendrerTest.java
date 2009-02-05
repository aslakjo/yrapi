package no.yr.svg;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import junit.framework.TestCase;
import no.yr.xml.parser.Weatherdata;

import org.jdom.output.XMLOutputter;

public class WeatherRendrerTest extends TestCase {

	public void testSvgRender() throws Exception
	{
		String xml ;
		FileReader reader;  
		reader = new FileReader("yrexample.xml");
		char[] buf = new char[100000];
		reader.read(buf);
		
		xml = new String(buf);
		xml = xml.trim();
		
		Weatherdata data = new Weatherdata(xml);
		
		WeatherRendrer rendrer = new WeatherRendrer(data.getTimeSeries().getIterator().next());
		
		XMLOutputter out = new XMLOutputter();
		
		File newFile = new File("tmp.svg");
		
		
		FileWriter file = new FileWriter(newFile);
		out.output(rendrer.getSvg(), file);
		
		System.out.flush();
		
		
		
	}
	
}
