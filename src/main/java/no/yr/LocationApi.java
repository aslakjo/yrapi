package no.yr;


import org.jdom.JDOMException;

import no.yr.exceptions.YrException;
import no.yr.weather.TimeSeries;
import no.yr.xml.parser.Weatherdata;

public class LocationApi extends Api {

	public LocationApi() {
		super("http://api.yr.no/weatherapi/locationforecast/1.5/");
	}
	
	public void setLongitude(float longitude)
	{
		super.setParameter("lon", String.valueOf(longitude));
	}
	
	public void setLatitude(float latitude)
	{
		super.setParameter("lat", String.valueOf(latitude));
	}
	
	public void setMoh(int height)
	{
		super.setParameter("msl", String.valueOf(height));
	}
	
	public TimeSeries fetchTimeseries()
		throws YrException
	{
		try {
			return new Weatherdata(super.fetch()).getTimeSeries();
		} catch (JDOMException e) {
			throw new YrException("Could not parse xml", e);
		} catch (YrException e) {
			throw new YrException("Problem giving result from yr", e);
		}
	}
	
}
