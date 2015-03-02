import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

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
	 * @param i 
	 * @param info 
	 * @throws JSONException 
	 */
	public Day(JSONObject info, int index) throws JSONException {
		
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd");
		DecimalFormat temp = new DecimalFormat("#.#");
		
		Double precip = 0.0;
		//get needed JSON Objects and timestamp
		JSONObject list = info.getJSONArray("list").getJSONObject(index);
		JSONObject Temp = list.getJSONObject("temp");
		JSONObject Weather = list.getJSONArray("weather").getJSONObject(0);
		Location.GetTime(list.getInt("dt"));
		
		//Capitalize first letter of cloud contition
		String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
		
		//Get precipitation levels if any
		if (list.has("snow"))
	    	precip += list.getDouble("snow");
	    if (list.has("rain"))
	    	precip += list.getDouble("rain");
		    
	    this.dayOfWeek = format.format(Location.cal.getTime());
	    Location.cal.add(Calendar.DATE, 1);
		this.temperature = temp.format(Temp.getDouble("day")) + "°C";
		this.skyCondition = weather;
		this.precipitation = temp.format(precip) + "mm";
		this.maxTemp = "High: " + temp.format(Temp.getDouble("max")) + "°C";
		this.minTemp = "Low: " + temp.format(Temp.getDouble("min")) + "°C";
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