package no.yr.weather;

public class Wind {
	
	/**
	 * directions in deg
	 */
	protected float direction;
	
	/**
	 * power in meter per sec
	 */
	protected float speed ;
	

	
	public float getDirection() {
		return direction;
	}
	public Wind setDirection(float direction) {
		this.direction = direction;
		return this;
	}
	public float getSpeed() {
		return speed;
	}
	public Wind setSpeed(float power) {
		this.speed = power;
		return this;
	}
	
	
	
}
