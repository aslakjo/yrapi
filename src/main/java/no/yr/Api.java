package no.yr;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;
import java.util.Properties;

import no.yr.exceptions.YrException;

public class Api {
	protected URL searchBase = null;
	protected Properties parameter = null;
	
	public Api(URL searchBase)
	{
		this.searchBase = searchBase;
		parameter = new Properties();
	}
	
	public Api(String urlSearchBase)
		
	{
		try {
			this.searchBase = new URL(urlSearchBase);
		} catch (MalformedURLException e) {
			;
		}
		parameter = new Properties();
	}
	
	public void setParameter(String name, String value)
	{
		parameter.setProperty(name, value);
	}
	
	
	/**
	 * fetches the query
	 * @return the result at the query
	 */
	public String fetch()
		throws YrException
	{
		StringBuilder xml = new StringBuilder();
		
		URL query = createSearchUrl();
		InputStream stream = null;
		try {
			URLConnection conn = query.openConnection();
			
			
			stream = conn.getInputStream();
		} catch (IOException e) {
			throw new YrException("Could not get weather result from url "+ query.toExternalForm(), e);
		}
		BufferedInputStream bufferdStream = new BufferedInputStream(stream);
		byte[] buffer = null;
		try {
			int read = 2;
			int bufferSize = 500000;
			buffer = new byte[bufferSize];
			while(read > 0)
			{
				
				read = bufferdStream.read(buffer);
				if(read > 0 )
				{
					String tmpString = new String(buffer,0, read);
					xml.append(tmpString);
				}
			}
		} catch (IOException e) {
			throw new YrException("Could not get content of url resonse", e);
		}
		return xml.toString();
	}

	public URL createSearchUrl()
		throws YrException
	{
		String parameters = "";
		Enumeration enums = parameter.propertyNames();
		while(enums.hasMoreElements())
		{
			String key = (String)enums.nextElement();
			if(parameters.length() != 0)
				parameters +=";";
				
			parameters +=  (String)key + "=" + (String)parameter.getProperty(key);
		}
		
		URL query;
		try {
			query = new URL(searchBase + "?" + parameters);
		} catch (MalformedURLException e1) {
			throw new YrException("could not construct search url", e1);
		}
		return query;
	}
	
}
