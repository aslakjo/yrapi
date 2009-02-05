package no.yr;

import java.net.URL;

import no.yr.Api;
import no.yr.weather.TimeSeries;

import junit.framework.TestCase;

public class ApiTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	
	public void testFetch()
		throws Exception{
		Api api = new Api(new URL("http://api.yr.no/weatherapi/locationforecast/1.5/"));
		api.setParameter("lat", "60.1");
		api.setParameter("lon", "10.1");
		
		String series = api.fetch();
		assertNotNull(series); 
		assertTrue(!series.isEmpty());
		
	}

	public void testCreateSearchUrl()
		throws Exception{
		Api api = new Api(new URL("http://api.yr.no/weatherapi/locationforecast/1.4/"));
		api.setParameter("lat", "60.1");
		api.setParameter("lon", "10.1");
		
		assertEquals(new URL("http://api.yr.no/weatherapi/locationforecast/1.4/?lat=60.1;lon=10.1"), api.createSearchUrl());
		
	}

}
