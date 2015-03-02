import org.json.*;

/**
 * The MarsDay class stores the current weather information for Mars.
 * 
 * @author David Newell
 * @author David Langford
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
	 *  Constructor
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