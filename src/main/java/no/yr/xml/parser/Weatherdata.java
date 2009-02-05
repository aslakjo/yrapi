package no.yr.xml.parser;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import no.yr.util.Possition;
import no.yr.util.Validity;
import no.yr.weather.Clouds;
import no.yr.weather.Pressure;
import no.yr.weather.Rain;
import no.yr.weather.Temperatur;
import no.yr.weather.TimeSeries;
import no.yr.weather.Weather;
import no.yr.weather.Wind;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public class Weatherdata {

	Document xml;
	
	public Weatherdata(String xml)
		throws JDOMException
	{
			SAXBuilder builder = new SAXBuilder();
			StringReader reader = new StringReader(xml);
				
			try {
				if(!reader.ready())
					throw new JDOMException("Could not read input string");
				
				this.xml = builder.build(reader);
			} catch (JDOMException e) {
				throw e;
			} catch (IOException e) {
				throw new JDOMException("Could not build xml document", e);
			}
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * returning the time series for the xml, enterd 
	 */
	public TimeSeries getTimeSeries()
	{
		TimeSeries series = new TimeSeries();
		
		List<Element> times = xml.getRootElement().getChild("product").getChildren("time");
		
		Iterator<Element> it = times.iterator();
		while(it.hasNext())
		{
			Element time = it.next();
			Weather weather = new Weather();
			
			
			try {
				weather.setValid(new Validity().setFrom(time.getAttributeValue("from")).setValidTo(time.getAttributeValue("to")));				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			Element location = time.getChild("location");
			
			weather.setPossition(
					new Possition().
					setHight(Float.valueOf(location.getAttributeValue("altitude"))).
					setLatitude(Float.valueOf(location.getAttributeValue("latitude"))).
					setLongitude(Float.valueOf(location.getAttributeValue("longitude")))
					);
			
			//if we have found this time before
			if(series.containsTime(weather.getValid()))
			{
				Weather tmp = series.get(weather.getValid());
				weather = tmp;
				
				
			}
			
			if(location.getChild("windDirection") != null)
			{
				weather.setClouds(new Clouds().
						setFog(Float.valueOf(location.getChild("fog").getAttributeValue("percent"))).
						setHigh(Float.valueOf(location.getChild("highClouds").getAttributeValue("percent"))).
						setLow(Float.valueOf(location.getChild("lowClouds").getAttributeValue("percent"))).
						setMedium(Float.valueOf(location.getChild("mediumClouds").getAttributeValue("percent"))).
						setTotal(Float.valueOf(location.getChild("cloudiness").getAttributeValue("percent")))
					);
				
				weather.setPressure(new Pressure().setHpa(Float.valueOf(location.getChild("pressure").getAttributeValue("value"))));
				
				weather.setWind(new Wind().
						setSpeed(Float.valueOf(location.getChild("windSpeed").getAttributeValue("mps"))).
						setDirection(Float.valueOf(location.getChild("windDirection").getAttributeValue("deg"))));
				weather.setTempratur(new Temperatur().setTemperatur(Float.valueOf(location.getChild("temperature").getAttributeValue("value"))));
				
				
			}else if(location.getChild("precipitation") != null)
			{
				weather.setRain(new Rain().setAmount(Float.valueOf(location.getChild("precipitation").getAttributeValue("value"))));
				
			}
			
			series.add(weather.getValid(), weather);
		}
		
		
		
		return series;
	}
	
	
	
}
