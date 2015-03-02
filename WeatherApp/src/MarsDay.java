import org.json.*;

/**
 * @author David Newell
 * 
 * The LocalWeather class stores the current forecast information.
 * 
 * Used by the GUI, its instances are typically created by the Location class
 * TODO This class should ONLY return the data, and the GUI should handle display, adding descriptions
 * to the string.  I.E. the getter for temperature should return "23" and the GUI will format to
 * Temperature: 23°C
 * TODO Think about if temp and precipitation fields should be numeric.  I think that the getters in this class
 * return a String, however.
 * TODO I think we should also add the unit conversion logic here. Have a getter for both C and for F? 
 * May be helpful in keeping the code out of the GUI classes.
 */
public class MarsDay {


	private String marsDate;
	private String averageTemp;
	private String skyCondition;
	private String precipatation;
	private String windSpeed;
	private String windDirection;
	private String minTemp;
	private String maxTemp;
	
	/**
	 *  Constructs MarsDay with all needed fields
	 * @param marsDate
	 * @param averageTemp
	 * @param skyCondition
	 * @param precipatation
	 * @param windSpeed
	 * @param windDirection
	 * @param minTemp
	 * @param maxTemp
	 */
	public MarsDay(String marsDate, String averageTemp,
			String skyCondition, String precipatation, String windSpeed,
			String windDirection, String minTemp,
			String maxTemp) {
		
		this.marsDate = marsDate;
		this.averageTemp = averageTemp;
		this.skyCondition = skyCondition;
		this.precipatation = precipatation;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
	}
	
	/**
	 *  Constructs MarsDay with dummy values.  Used for testing GUI.
	 *  TODO Delete once parser is integrated
	 * @param marsDate
	 * @param averageTemp
	 * @param skyCondition
	 * @param precipatation
	 * @param windSpeed
	 * @param windDirection
	 * @param minTemp
	 * @param maxTemp
	 */
	public MarsDay(JSONObject info) {
		this.marsDate = "Current Time: 10:32";
		this.averageTemp = "Current Temp is: 15.7°C";
		this.skyCondition = "Sky is clear";
		this.precipatation = "0mm precipitation";
		this.windSpeed = "24km/h";
		this.windDirection = "from NW";
		this.minTemp = "low: 5.2°C";
		this.maxTemp = "high: 15.7°C";
	}
	

	/**
	 * @return the marsDate
	 */
	public String getMarsDate() {
		return marsDate;
	}
	/**
	 * @return the averageTemp
	 */
	public String averageTemp() {
		return averageTemp;
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
	public String getPrecipatation() {
		return precipatation;
	}
	/**
	 * @return the windSpeed
	 */
	public String getWindSpeed() {
		return windSpeed;
	}
	/**
	 * @return the windDirection
	 */
	public String getWindDirection() {
		return windDirection;
	}

	/**
	 * @return the minTemp
	 */
	public String getMinTemp() {
		return minTemp;
	}
	/**
	 * @return the maxTemp
	 */
	public String getMaxTemp() {
		return maxTemp;
	}

	/**
	 * @param marsDate the marsDate to set
	 */
	public void setMarsDate(String marsDate) {
		this.marsDate = marsDate;
	}
	/**
	 * @param averageTemp the averageTemp to set
	 */
	public void setAverageTemp(String averageTemp) {
		this.averageTemp = averageTemp;
	}
	/**
	 * @param skyCondition the skyCondition to set
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}
	/**
	 * @param precipatation the precipatation to set
	 */
	public void setPrecipatation(String precipatation) {
		this.precipatation = precipatation;
	}
	/**
	 * @param windSpeed the windSpeed to set
	 */
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	/**
	 * @param windDirection the windDirection to set
	 */
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	/**
	 * @param mStringemp the minTemp to set
	 */
	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}
	/**
	 * @param maxTemp the maxTemp to set
	 */
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}


	
}