package no.yr.weather;

/**
 * cloud coverage represented in precent
 * @author aslak
 *
 */
public class Clouds {

	/**
	 * highly located clouds
	 */
	protected float high;
	
	/**
	 * medium high located clouds
	 */
	protected float medium;
	
	/**
	 * low located clouds
	 */
	protected float low;
	
	
	protected float fog;
	
	/**
	 * total clod coverage
	 */
	protected float total;

	public float getHigh() {
		return high;
	}

	public Clouds setHigh(float high) {
		this.high = high;
		return this;
	}

	public float getMedium() {
		return medium;
	}

	public Clouds setMedium(float medium) {
		this.medium = medium;
		return this;
	}

	public float getLow() {
		return low;
	}

	public Clouds setLow(float low) {
		this.low = low;
		return this;
	}

	public float getFog() {
		return fog;
	}

	public Clouds setFog(float fog) {
		this.fog = fog;
		return this;
	}

	public float getTotal() {
		return total;
	}

	public Clouds setTotal(float total) {
		this.total = total;
		return this;
	}
	
}
