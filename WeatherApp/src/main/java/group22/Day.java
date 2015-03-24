import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The Day class stores the forecast information for a single day.
 * 
 * Used by the GUI, its instances are typically created by the LongTermForecast class
 * 
 * @author David Newell
 * @author David Langford
 */

public class Day {
	
	// Global Attributes
	private String dayOfWeek;
	private String temperature;
	private String skyCondition;
	private String precipitation;
	private String maxTemp;
	private String minTemp;
	private int weatherID;
	
	
	
	/**
	 * Constructs a new Day
	 * @param info the weather information 
	 * @param index the position in the array
	 * @throws JSONException when an error occurs
	 */
	public Day(JSONObject info, int index) throws JSONException {
		
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd");
		DecimalFormat temp = new DecimalFormat("#");
		DecimalFormat prep = new DecimalFormat("#.#");
		
		String measurement = "inches";
		double prepMultiplier = 0.0393701;
		
		if(Location.getUnits() == 0){
			measurement = "mm";
			prepMultiplier = 1.0;
		}
		
		Double precip = 0.0;
		//get needed JSON Objects and timestamp
		JSONObject list;
		try{
			list = info.getJSONArray("list").getJSONObject(index);
			JSONObject Temp = list.getJSONObject("temp");
			JSONObject Weather = list.getJSONArray("weather").getJSONObject(0);
			Location.GetTime(list.getInt("dt"));
			
			//Capitalize first letter of cloud condition
			String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
			
			//Get precipitation levels if any
			if (list.has("snow"))
		    	precip += list.getDouble("snow")*prepMultiplier;
		    if (list.has("rain"))
		    	precip += list.getDouble("rain")*prepMultiplier;
		
		    this.dayOfWeek = format.format(Location.cal.getTime());
		    Location.cal.add(Calendar.DATE, 1);
			this.temperature = temp.format(Temp.getDouble("day")) + "\u00b0";
			this.skyCondition = weather;
			this.precipitation = prep.format(precip)+" "+measurement;
			this.maxTemp = "High: " + temp.format(Temp.getDouble("max")) + "\u00b0";
			this.minTemp = "Low: " + temp.format(Temp.getDouble("min")) + "\u00b0";
			this.weatherID = Weather.getInt("id");
		
			// If OpenWeatherMap does not have enough weather information apply the below info
		} catch (JSONException e) {
			
			this.dayOfWeek = format.format(Location.cal.getTime());
		    Location.cal.add(Calendar.DATE, 1);
			this.temperature = "N/A";
			this.skyCondition = "N/A";
			this.precipitation = "N/A";
			this.maxTemp = "N/A";
			this.minTemp = "N/A";
			this.weatherID = 9999;
		}
		
	}

	/**
	 * Getter for dayOfWeek
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Setter for dayOfWeek
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Getter for temperature
	 * @return the temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * Getter for the weatherIcon
	 * @return a string representing a code for the current weather
	 */
	public int getWeatherID(){
		return weatherID;
	}
	
	/**
	 * Setter for temperature
	 * @param temperature the temperature to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * Getter for skyCondition
	 * @return the skyCondition
	 */
	public String getSkyCondition() {
		return skyCondition;
	}

	/**
	 * Setter for skyCondition
	 * @param skyCondition the skyCondition to set
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}

	/**
	 * Getter for maxTemp
	 * @return the maxTemp
	 */
	public String getMaxTemp() {
		return maxTemp;
	}

	/**
	 * Setter for maxTemp
	 * @param maxTemp the maxTemp to set
	 */
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}

	/**
	 * Getter for precipitation
	 * @return the minTemp
	 */
	public String getMinTemp() {
		return minTemp;
	}
	
	
	/**
	 * Setter for minTemp
	 * @param minTemp the minTemp to set
	 */
	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}

	/**
	 * Getter for precipitation
	 * @return the precipitation
	 */
	public String getPrecipitation() {
		return precipitation;
	}

	/**
	 * Setter for precipitation
	 * @param precipitation the precipitation to set
	 */
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}



}