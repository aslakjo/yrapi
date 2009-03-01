package no.yr.weather;

import java.util.Iterator;
import java.util.TreeMap;

import no.yr.util.Validity;

public class TimeSeries {

	TreeMap<Validity, Weather> timeseries = new TreeMap<Validity, Weather>();
	
	public void add(Validity valid, Weather weather)
	{
		timeseries.put(valid, weather);
	}
	
	public Weather get(Validity valid)
	{
		return timeseries.get(valid);
	}

	public Iterator<Weather> getIterator() {
		return timeseries.values().iterator();
	}
	
	public int getSize()
	{
		return timeseries.size();
	}
	
	public boolean containsTime(Validity valid)
	{
		return timeseries.containsKey(valid);
	}

    public TimeSeries seriesForHours(int i) {

        TimeSeries truncatedSeries = new TimeSeries();
        Iterator<Weather> allHours = getIterator();

        while(allHours.hasNext() && i > 0)
        {
            Weather hour = allHours.next();
            truncatedSeries.add(hour.getValid(), hour);

            i--;
        }

        return truncatedSeries;
    }


}
