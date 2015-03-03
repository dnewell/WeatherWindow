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
	private String skyCondition;
	private String windSpeed;
	private String windDirection;
	private String pressure;
	private String humidity;
	
	
	/**
	 *  Constructor for the MarsWeather class
	 *  
	 * @param date
	 * @param temperature
	 * @param skyCondition
	 * @param windSpeed
	 * @param windDirection
	 * @param pressure
	 * @param humidity
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
		   String pressure = pressureNum + " (" + pressureString + ")";
		   
		   //HUMIDITY + CONDITION
		   Object humidity = data.get("abs_humidity"); 
		   String skyCondition = data.getString("atmo_opacity");
		   
		
		this.date = String.format("Date: " + totalDate);
		this.temperature = temp.format(avgTemp) + "°C";
		
		if (!windDirection.equals("--"))
			this.windDirection = ("Wind Direction: " + windDirection);
		   else System.out.println("No wind direction information available.");
		   
		   if (!windSpeed.equals(null))
			   this.windDirection = ("Wind speed " + windSpeed);
		   else this.windSpeed = ("No wind speed information available.");
		   
		   if (!humidity.equals(null))
			   this.humidity = ("Humidity "+ humidity);	
		   else this.humidity = ("No humidity information available.");	   
		
		this.skyCondition = "Sky Conditon: " + skyCondition;
		this.pressure = temp.format(pressure) + "KpA";
		
	}
	
	/**
	 * @return the date
	 */
	public String getdate() {
		return date;
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
	 * @param date 
	 * @param date the date to set
	 */
	public void setdate(String date) {
		this.date = date;
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
}