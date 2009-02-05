package no.yr.weather;

import no.yr.util.Possition;
import no.yr.util.Validity;

public class Weather {

	protected Wind wind;
	protected Pressure pressure;
	protected Temperatur tempratur;
	protected Clouds clouds;
	protected Rain rain;
	
	protected Possition possition;
	
	protected Validity valid;

	public Wind getWind() {
		return wind;
	}

	public void setWind(Wind wind) {
		this.wind = wind;
	}

	public Pressure getPressure() {
		return pressure;
	}

	public void setPressure(Pressure pressure) {
		this.pressure = pressure;
	}

	public Temperatur getTempratur() {
		return tempratur;
	}

	public void setTempratur(Temperatur tempratur) {
		this.tempratur = tempratur;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public Weather setClouds(Clouds clouds) {
		this.clouds = clouds;
		return this;
	}

	public Possition getPossition() {
		return possition;
	}

	public void setPossition(Possition possition) {
		this.possition = possition;
	}

	public Validity getValid() {
		return valid;
	}

	public void setValid(Validity valid) {
		this.valid = valid;
	}

	public void setRain(Rain rain) {
		this.rain = rain;
	}
	
	public Rain getRain()
	{
		return rain;
	}

	
	
	
	
}
