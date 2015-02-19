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
public class LocalWeather {

	private String updateTime;
	private String userTime;
	private String temperature;
	private String skyCondition;
	private String precipatation;
	private String windSpeed;
	private String windDirection;
	private String pressure;
	private String humidity;
	private String minTemp;
	private String maxTemp;
	private String sunrise;
	private String sunset;
	
	/**
	 *  Constructs LocalWeather with all needed fields
	 * @param updateTime
	 * @param userTime
	 * @param temperature
	 * @param skyCondition
	 * @param precipatation
	 * @param windSpeed
	 * @param windDirection
	 * @param pressure
	 * @param humidity
	 * @param mStringemp
	 * @param maxTemp
	 * @param sunrise
	 * @param sunset
	 */
	public LocalWeather(String updateTime, String userTime, String temperature,
			String skyCondition, String precipatation, String windSpeed,
			String windDirection, String pressure, String humidity, String minTemp,
			String maxTemp, String sunrise, String sunset) {
		this.updateTime = updateTime;
		this.userTime = userTime;
		this.temperature = temperature;
		this.skyCondition = skyCondition;
		this.precipatation = precipatation;
		this.windSpeed = windSpeed;
		this.windDirection = windDirection;
		this.pressure = pressure;
		this.humidity = humidity;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.sunrise = sunrise;
		this.sunset = sunset;
	}
	
	/**
	 *  Constructs LocalWeather with dummy values.  Used for testing GUI.
	 *  TODO Delete once parser is integrated
	 * @param updateTime
	 * @param userTime
	 * @param temperature
	 * @param skyCondition
	 * @param precipatation
	 * @param windSpeed
	 * @param windDirection
	 * @param pressure
	 * @param humidity
	 * @param minTemp
	 * @param maxTemp
	 * @param sunrise
	 * @param sunset
	 */
	public LocalWeather() {
		this.updateTime = "Last data update: 10:23pm";
		this.userTime = "Current Time: 10:32";
		this.temperature = "Current Temp is: 15.7°C";
		this.skyCondition = "Sky is clear";
		this.precipatation = "0mm precipitation";
		this.windSpeed = "24km/h";
		this.windDirection = "from NW";
		this.pressure = "101.2KpA";
		this.humidity = "12% relative humidity";
		this.minTemp = "low: 5.2°C";
		this.maxTemp = "high: 15.7°C";
		this.sunrise = "Sunrise: 5:49am";
		this.sunset = "Sunset: 8:54pm";
	}
	
	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	/**
	 * @return the userTime
	 */
	public String getUserTime() {
		return userTime;
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
	 * @return the precipatation
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
	 * @return the pressure
	 */
	public String getPressure() {
		return pressure;
	}
	/**
	 * @return the humidity
	 */
	public String getHumidity() {
		return humidity;
	}
	/**
	 * @return the mStringemp
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
	 * @return the sunrise
	 */
	public String getSunrise() {
		return sunrise;
	}
	/**
	 * @return the sunset
	 */
	public String getSunset() {
		return sunset;
	}
	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * @param userTime the userTime to set
	 */
	public void setUserTime(String userTime) {
		this.userTime = userTime;
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
	 * @param pressure the pressure to set
	 */
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	/**
	 * @param humidity the humidity to set
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	/**
	 * @param mStringemp the mStringemp to set
	 */
	public void setMinTemp(String mStringemp) {
		this.minTemp = mStringemp;
	}
	/**
	 * @param maxTemp the maxTemp to set
	 */
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}
	/**
	 * @param sunrise the sunrise to set
	 */
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	/**
	 * @param sunset the sunset to set
	 */
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	
}