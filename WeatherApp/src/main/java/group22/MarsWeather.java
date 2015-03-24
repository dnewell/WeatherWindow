import java.text.DecimalFormat;
import java.util.Date;

import org.json.*;

/**
 * The MarsWeather class stores the current forecast information.
 * 
 * @author David Langford
 * @author David Newell 
 */
public class MarsWeather {

	private String date;
	private String temperature;
	private String Ftemp;
	private String skyCondition;
	private String windSpeed;
	private String windDirection;
	private String pressure;
	private String humidity;
	
	
	/**
	 *  Constructor for the MarsWeather class
	 *  @param info the Mars weather information
	 */
	public MarsWeather(JSONObject info) throws Exception {

		DecimalFormat temp = new DecimalFormat("#.#");
		Double precip=0.00;
		   
		   //Declare Json Objects for use
		   JSONObject data = info.getJSONObject("report");
		   
		   //TIME
		   Date now = new Date();
		   
		   //TEMPERATURE
		   int maxTemp = data.getInt("max_temp");
		   int minTemp = data.getInt("min_temp");
		   int avgTemp = (maxTemp+minTemp)/2;
		   
		   //TEMPERATURE
		   int maxFTemp = data.getInt("max_temp_fahrenheit");
		   int minFTemp = data.getInt("min_temp_fahrenheit");
		   int avgFTemp = (maxFTemp+minFTemp)/2;
		   
		   //DATE
		   String date = data.getString("terrestrial_date"); //Earth Date
		   String season = data.getString("season"); //Martian Month
		   String totalDate = date + " (" + season + " on mars)";
		   
		   //WIND
		   String windDirection = data.getString("wind_direction");
		   Object windSpeed = data.get("wind_speed");
		   
		   //PRESSURE
		   int pressureNum = data.getInt("pressure");
		   String pressureString = data.getString("pressure_string");
		   String pressure = temp.format(pressureNum) + " (" + pressureString + ")";
		   
		   //HUMIDITY + CONDITION
		   Object humidity = data.get("abs_humidity"); 
		   String skyCondition = data.getString("atmo_opacity");
		   
		
		this.date = String.format("Date: " + totalDate);
		this.temperature = temp.format(avgTemp) + "\u00b0";
		this.Ftemp = temp.format(avgFTemp) + "\u00b0";
		
		if (!windDirection.equals("--"))
			this.windDirection = ("Wind Direction: " + windDirection);
		   else this.windDirection = ("");
		   
		   if (!windSpeed.equals(null))
			   this.windDirection = ("Wind speed " + windSpeed);
		   else this.windSpeed = ("No wind speed information available.");
		   
		   if (!humidity.equals(null))
			   this.humidity = ("Humidity "+ humidity);	
		   else this.humidity = ("No humidity information available.");	   
		
		this.skyCondition = "Sky Conditon: " + skyCondition;
		this.pressure = pressure + "KpA";
		
	}
	
	/**
	 * Get the date the weather information was accessed
	 * @return A string representing the date the information was accessed
	 */
	public String getdate() {
		return date;
	}
	
	/**
	 * Get the current temperature
	 * @return A string representing the current temperature 
	 */
	public String getTemperature() {
		return temperature;
	}
	
	/**
	 * Get the current temperature in Fahrenheight
	 * @return A string representing the current temperature in Fahrenheight
	 */
	public String getFtemp() {
		return Ftemp;
	}
	
	/**
	 * Get the current condition of the sky
	 * @return A string representing the sky condition 
	 */
	public String getSkyCondition() {
		return skyCondition;
	}
	
	/**
	 * Get the current wind speed
	 * @return A string representing the wind speed
	 */
	public String getWindSpeed() {
		return windSpeed;
	}
	
	/**
	 * Get the current wind direction
	 * @return A string representing the wind direction
	 */
	public String getWindDirection() {
		return windDirection;
	}
	
	/**
	 * Get the current atmospheric pressure
	 * @return A string representing the atmospheric pressure
	 */
	public String getPressure() {
		return pressure;
	}
	
	/**
	 * Get the current humidity
	 * @return A string representing the humidity
	 */
	public String getHumidity() {
		return humidity;
	}
	
	/**
	 * Set an updated date for the current date
	 * @param date the updated date information to set
	 */
	public void setdate(String date) {
		this.date = date;
	}
	
	/**
	 * Set an updated temperature value
	 * @param temperature the updated temperature information to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	/**
	 * Set an updated temperature value in Fahrenheight
	 * @param temperature the updated temperature information to set
	 */
	public void setFtemp(String Ftemp) {
		this.Ftemp = Ftemp;
	}
	
	/**
	 * Set an updated sky condition
	 * @param skyCondition the updated skycondition information to set
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}
	
	/**
	 * Set an updated wind speed value
	 * @param windSpeed the updated wind speed information to set
	 */
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	
	/**
	 * Set an updated wind direction value
	 * @param windDirection the updated wind direction information to set
	 */
	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}
	
	/**
	 * Set an updated atmospheric pressure value
	 * @param pressure the updated pressure value to set
	 */
	public void setPressure(String pressure) {
		this.pressure = pressure;
	}
	
	/**
	 * Set an updated humidity value
	 * @param humidity the updated humidity information to set
	 */
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
}