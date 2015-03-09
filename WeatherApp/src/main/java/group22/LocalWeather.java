import java.text.DecimalFormat;
import java.util.Date;

import org.json.*;

/**
 * The LocalWeather class stores the current forecast information.
 * 
 * Used by the GUI, its instances are created by the Location class
 * @author David Newell
 * @author David Langford
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

	
	// Just making minor comment change to test to make
	// first push to rep
	
	/**
	 * Constructs the LocalWeather
	 * @param info the weather information 
	 * @throws Exception
	 */
	public LocalWeather(JSONObject info) throws Exception {

		DecimalFormat temp = new DecimalFormat("#.#");
		Date now = new Date();
		
		//Declare Json Objects for use
	    JSONObject loc = info.getJSONObject("coord");
	    JSONObject Weather = info.getJSONArray("weather").getJSONObject(0);
	    JSONObject sys = info.getJSONObject("sys");
	    JSONObject Main = info.getJSONObject("main");
	    JSONObject Wind = info.getJSONObject("wind");
	    JSONObject Clouds = info.getJSONObject("clouds");
	    
	    Double precip=0.00;

	    Location.GetTime(info.getInt("dt"));	  
	    
	    //Get precipitation levels if any
	    if (info.has("snow"))
	    	if (info.getJSONObject("snow").has("3h"))
	    	precip += info.getJSONObject("snow").getDouble("3h");
	    	else precip += info.getJSONObject("snow").getDouble("1h");
	    if (info.has("rain"))
	    	if (info.getJSONObject("rain").has("3h"))
		    	precip += info.getJSONObject("rain").getDouble("3h");
		    	else precip += info.getJSONObject("rain").getDouble("1h");	
	    
		
		this.updateTime = "Last data update: " + Location.ihours + ":" + (Location.iminutes < 10 ? "0" : "") + Location.iminutes + " " + Location.daytime;
		this.userTime = String.format("Current Time: %tl:%tM %tp%n", now, now, now);
		this.temperature = temp.format(Main.getDouble("temp")) + "\u00b0C";
		this.skyCondition = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
		this.precipatation = temp.format(precip) + "";
		this.windSpeed = temp.format(Wind.getDouble("speed") * 3.6) + "km/h";
		this.windDirection = Location.Direction(Wind.getDouble("deg"));
		this.pressure = temp.format(Main.getDouble("pressure")) + "hPa";
		this.humidity = (int)Main.getDouble("humidity") + "";
		this.minTemp = "Low: " + temp.format(Main.getDouble("temp_min")) + "\u00b0C";
		this.maxTemp = "High: " + temp.format(Main.getDouble("temp_max")) + "\u00b0C";
		
		Location.GetTime(sys.getInt("sunrise"));
		this.sunrise = "Sunrise: " + Location.ihours + ":" + (Location.iminutes < 10 ? "0" : "") + Location.iminutes + " " + Location.daytime;
		
		Location.GetTime(sys.getInt("sunset"));
		this.sunset = "Sunset: " + Location.ihours + ":" + (Location.iminutes < 10 ? "0" : "") + Location.iminutes + " " + Location.daytime;
		
		Location.NewDay = false;
	}
	
	/**
	 * Gets the time when the current weather was last updated by the weather service
	 * @return A string representing the time of when the current weather was last updated
	 */
	public String getUpdateTime() {
		return updateTime;
	}
	
	/**
	 * Gets the user's current time
	 * @return A string representing the user's current time 
	 */
	public String getUserTime() {
		return userTime;
	}
	
	/**
	 * Gets the current temperature
	 * @return A string representing the current temperature
	 */
	public String getTemperature() {
		return temperature;
	}
	
	/**
	 * Gets the current condition of the sky
	 * @return A string representing the current condition of the sky
	 */
	public String getSkyCondition() {
		return skyCondition;
	}
	
	/**
	 * Gets the current amount of precipitation
	 * @return A string representing the current amount of precipitation
	 */
	public String getPrecipatation() {
		return precipatation;
	}
	
	/**
	 * Gets the current wind speed
	 * @return A string representing the current wind speed
	 */
	public String getWindSpeed() {
		return windSpeed;
	}
	
	/**
	 * Gets the current wind direction
	 * @return A string representing the current wind direction
	 */
	public String getWindDirection() {
		return windDirection;
	}
	
	/**
	 * Gets the current atmospheric pressure
	 * @return A string representing the current atmospheric pressure
	 */
	public String getPressure() {
		return pressure;
	}
	
	/**
	 * Gets the current humidity
	 * @return A string representing that current humidity
	 */
	public String getHumidity() {
		return humidity;
	}
	
	/**
	 * Get the current minimum temperature
	 * @return A string representing the minimum temperature
	 */
	public String getMinTemp() {
		return minTemp;
	}
	
	/**
	 * Get the current maximum temperature
	 * @return A string representing the maximum temperature
	 */
	public String getMaxTemp() {
		return maxTemp;
	}
	
	/**
	 * Gets the time for sunrise for the current day
	 * @return A string representing the time for sunrise
	 */
	public String getSunrise() {
		return sunrise;
	}
	
	/**
	 * Gets the time for sunset for the current day
	 * @return A string representing the time for sunset
	 */
	public String getSunset() {
		return sunset;
	}
	
	/**
	 * Sets the time when the current weather was last updated 
	 * @param updateTime the time to set to the updateTime variable
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	/**
	 * Sets an updated time for the current time
	 * @param userTime the time to set to the userTime variable
	 */
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	
	/**
	 * Sets an updated temperature for the current temperature
	 * @param temperature the temperature to set to the temperature variable
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * Sets an updated sky condition for the current temperature 
	 * @param skyCondition the skyCondition to set to the skyCondition variable
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}
	
	/**
	 * Sets an updated amount of precipitation 
	 * @param precipatation the precipitation amount to set precipitation variable
	 */
	public void setPrecipatation(String precipatation) {
		this.precipatation = precipatation;
	}
	
	/**
	 * Sets an updated wind speed value for the current wind speed
	 * @param windSpeed the wind speed to set the wind speed variable
	 */
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	/**
	 * Sets an updated wind direction value for the current wind direction
	 * @param windDirection the windDirection to set the windDirection variable
	 */
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	
	/**
	 * Sets an updated atmospheric pressure value to the current pressure value
	 * @param pressure the atmospheric pressure to set to the pressure variable
	 */
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	
	/**
	 * Sets an updated humidity value to the current humidity value
	 * @param humidity the humidity to set to the humidity variable
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	
	/**
	 * Sets an updated minimum temperature value to the current minimum temperature
	 * @param minTemp the minimum temperature to set to the minTemp variable
	 */
	public void setMinTemp(String mStringemp) {
		this.minTemp = mStringemp;
	}
	
	/**
	 * Sets an updated maximum temperature value to the current maximum temperature
	 * @param maxTemp the maximum temperature to set to the maxTemp variable
	 */
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}
	
	/**
	 * Sets an updated time for sunrise to the current sunrise value
	 * @param sunrise a time to set to the sunrise variable
	 */
	public void setSunrise(String sunrise) {
		this.sunrise = sunrise;
	}
	
	/**
	 * Sets an updated time for sunset to the current sunset value
	 * @param sunset a time to set the sunset variable
	 */
	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

	
	
}