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
	private String city;
	private String country;
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
	private int weatherID;

	// Just making minor comment change to test to make
	// first push to rep
	
	/**
	 * Constructs the LocalWeather
	 * @param info the weather information 
	 * @throws Exception
	 */
	public LocalWeather(JSONObject info) throws Exception {
	
		DecimalFormat temp = new DecimalFormat("#");
		Date now = new Date();
		
		//Declare Json Objects for use
	    JSONObject Weather = info.getJSONArray("weather").getJSONObject(0);
	    JSONObject sys = info.getJSONObject("sys");
	    JSONObject Main = info.getJSONObject("main");
	    JSONObject Wind = info.getJSONObject("wind");
    
	    Double precip = 0.00;

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
	    
	    String windSpeedText = "";
	    
	    // Gets the current units being used, and then sets the appropriate text
		if(Location.getUnits() == 0)
			windSpeedText = "km/h";
		else
			windSpeedText = "mph";
	    
		this.updateTime = "Last updated: " + Location.ihours + ":" + (Location.iminutes < 10 ? "0" : "") + Location.iminutes + " " + Location.daytime;
		this.userTime = String.format("Current Time: %tl:%tM %tp%n", now, now, now);
		this.temperature = temp.format((int)Main.getDouble("temp")) + "\u00b0";
		this.skyCondition = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
		this.precipatation = "Precipitation: "+temp.format(precip) + "%";
		this.windSpeed = "Wind: "+temp.format(Wind.getDouble("speed") * 3.6) +windSpeedText;
		this.windDirection = Location.Direction(Wind.getDouble("deg"));
		this.pressure = "Air Pressure: "+temp.format(Main.getDouble("pressure")) + "hPa";
		this.humidity = "Humidity: "+(int)Main.getDouble("humidity") + "%";
		this.minTemp = "Low: " + temp.format(Main.getDouble("temp_min")) + "\u00b0";
		this.maxTemp = "High: " + temp.format(Main.getDouble("temp_max")) + "\u00b0";
		this.weatherID =  Weather.getInt("id");
		this.city = info.getString("name");
		this.country = sys.getString("country");
		
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
	 * Getter for the weatherIcon
	 * @return a string representing a code for the current weather
	 */
	public int getWeatherID(){
		return weatherID;
	}

	/**
	 * Gets the current country
	 * @return a string representing the current country
	 */
	public String getCountry() {
		return this.country;
	}

	/**
	 * Gets the current city
	 * @return a string representing the current city
	 */
	public String getCity() {		
		return this.city;
	}	
}