package no.yr.util;

public class Possition {

	protected float latitude;
	protected float longitude;
	
	/**
	 * in meters
	 */
	protected float hight;

	public float getLatitude() {
		return latitude;
	}

	public Possition setLatitude(float latitude) {
		this.latitude = latitude;
		return this;
	}

	public float getLongitude() {
		return longitude;
	}

	public Possition setLongitude(float longitude) {
		this.longitude = longitude;
		return this;
	}

	public float getHight() {
		return hight;
	}

	public Possition setHight(float hight) {
		this.hight = hight;
		return this;
	}
}
