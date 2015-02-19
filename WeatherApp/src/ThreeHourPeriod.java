/**
 * @author David Newell
 * The ThreeHourPeriod class stores the forecast information for a three hour period.
 * 
 * Used by the GUI, its instances are typically created by the ShortTermForecast class
 * 
 * TODO This class should ONLY return the data, and the GUI should handle display, adding descriptions
 * to the string.  I.E. the getter for temperature should return "23" and the GUI will format to
 * Temperature: 23°C
 * TODO Think about if temp and precipitation fields should be numeric.  I think that the getters in this class
 * return a String, however.
 * TODO I think we should also add the unit conversion logic here. Have a getter for both C and for F? 
 * May be helpful in keeping the code out of the GUI classes.
 */
public class ThreeHourPeriod {

	private String dayOfWeek;
	private String temperature;
	private String skyCondition;
	private String precipitation;


	/**
	 * Constructs a ThreeHourPeriod with all needed fields
	 * 
	 * @param dayOfWeek The day of the week
	 * @param temperature The temperature
	 * @param skyCondition The sky condition
	 * @param precipitation The precipitation amount
	 */
	public ThreeHourPeriod(String dayOfWeek, String temperature,
			String skyCondition, String precipitation) {
		this.dayOfWeek = dayOfWeek;
		this.temperature = temperature;
		this.skyCondition = skyCondition;
		this.precipitation = precipitation;
	}
	
	/**
	 * TODO Delete once parser is integrated
	 * No argument constructor creates a dummy day,
	 * to ease GUI testing
	 */
	public ThreeHourPeriod() {
		this.dayOfWeek = "Tue Feb 17";
		this.temperature = "15.7°C or 42.3°F";
		this.skyCondition = "Sky is cloudy";
		this.precipitation = "1.5mm rain";

	}
	

	/**
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * @return the skyCondition
	 */
	public String getSkyCondition() {
		return skyCondition;
	}

	/**
	 * @return the precipitation
	 */
	public String getPrecipitation() {
		return precipitation;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * @param skyCondition the skyCondition to set
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}

	/**
	 * @param precipitation the precipitation to set
	 */
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}



}