package no.yr;


import java.io.File;
import java.io.FileWriter;

import org.jdom.output.XMLOutputter;

import no.yr.LocationApi;
import no.yr.svg.TimeSeriesRendrer;
import no.yr.weather.TimeSeries;
import junit.framework.TestCase;

public class LocationApiTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFetchTimeseries()
	throws Exception{
		LocationApi api = new LocationApi();
		api.setMoh(70);
		api.setLongitude((float)9.58);
		api.setLatitude((float)60.10);
		
		assertTrue(api.createSearchUrl().toExternalForm().contains("lat=60.1"));
		assertTrue(api.createSearchUrl().toExternalForm().contains("lon=9.58"));
		assertTrue(api.createSearchUrl().toExternalForm().contains("msl=70"));
				
		//"http://api.yr.no/weatherapi/locationforecast/1.4/?lat=60.10;lon=9.58;msl=70"
		
		
		TimeSeries series = api.fetchTimeseries();
		assertNotNull(series);
		
		
	}
	
	public void testRenderBlindernSvg()
		throws Exception
	{
		LocationApi api = new LocationApi();
		//location for met.no at blindern oslo norway
		api.setMoh(70);
		api.setLongitude((float)10.7211);
		api.setLatitude((float)59.9428);
		
		TimeSeries series = api.fetchTimeseriesForHours(6);
		TimeSeriesRendrer rendrer = new TimeSeriesRendrer(series);
		
		
	
		XMLOutputter out = new XMLOutputter();
		
		File newFile = new File("timeseries-actual.svg");
			
		FileWriter file = new FileWriter(newFile);
		out.output(rendrer.getSvg(), file);
		
		System.out.flush();
	}

}
