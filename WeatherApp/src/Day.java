/**
 * @author David Newell
 * 
 * The Day class stores the forecast information for a single day.
 * 
 * Used by the GUI, its instances are typically created by the LongTermForecast class
 * 
 * TODO This class should ONLY return the data, and the GUI should handle display, adding descriptions
 * to the string.  I.E. the getter for temperature should return "23" and the GUI will format to
 * Temperature: 23°C
 * TODO Think about if temp and precipitation fields should be numeric.  I think that the getters in this class
 * return a String, however.
 * TODO I think we should also add the unit conversion logic here. Have a getter for both C and for F? 
 * May be helpful in keeping the code out of the GUI classes.
 */

public class Day {
	
	private String dayOfWeek;
	private String temperature;
	private String skyCondition;
	private String precipitation;
	private String maxTemp;
	private String minTemp;
	
	
	/**
	 * Constructs a Day with all needed fields
	 * 
	 * @param dayOfWeek The day of the week
	 * @param temperature The temperature
	 * @param skyCondition The condition of the sky
	 * @param precipitation Precipitation amount
	 * @param maxTemp Maximum temperature
	 * @param minTemp Minimum temperature
	 */
	public Day(String dayOfWeek, String temperature, String skyCondition,
			String precipitation, String maxTemp, String minTemp) {
	
		this.dayOfWeek = dayOfWeek;
		this.temperature = temperature;
		this.skyCondition = skyCondition;
		this.precipitation = precipitation;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
	}

	/**
	 * no argument constructor creates a dummy day
	 * to ease GUI testing
	 * TODO Delete once parser is integrated
	 */
	public Day() {
		this.dayOfWeek = "Mon Mar 8";
		this.temperature = "2.1°C or 62.3°F";
		this.skyCondition = "Sky is clear";
		this.precipitation = "4.5mm";
		this.maxTemp = "-13";
		this.minTemp = "19";
	}

	/**
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @return the skyCondition
	 */
	public String getSkyCondition() {
		return skyCondition;
	}

	/**
	 * @param skyCondition the skyCondition to set
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}

	/**
	 * @return the maxTemp
	 */
	public String getMaxTemp() {
		return maxTemp;
	}

	/**
	 * @param maxTemp the maxTemp to set
	 */
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

	/**
	 * @return the minTemp
	 */
	public String getMinTemp() {
		return minTemp;
	}

	/**
	 * @param minTemp the minTemp to set
	 */
	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	/**
	 * @return the precipitation
	 */
	public String getPrecipitation() {
		return precipitation;
	}

	/**
	 * @param precipitation the precipitation to set
	 */
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}



}