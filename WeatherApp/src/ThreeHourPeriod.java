import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * The ThreeHourPeriod class stores the forecast information for a three hour period.
 * 
 * Used by the GUI, its instances are typically created by the ShortTermForecast class
 * 
 * @author David Newell
 * @author David Langford
 */
public class ThreeHourPeriod {

	private String dayOfWeek;
	private String temperature;
	private String skyCondition;
	private String precipitation;

	
	/**
	 * Constructs a ThreeHourPeriod
	 * 
	 * @param  res the JSON data object
	 * @throws JSONException 
	 */
	public ThreeHourPeriod(JSONObject res, int index) throws JSONException {
				
		SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd");
		DecimalFormat temp = new DecimalFormat("#.#");
		
		Double precip = 0.0;
		   
		//get needed JSON Objects and timestamp
		JSONObject list = res.getJSONArray("list").getJSONObject(index);
		JSONObject Main = list.getJSONObject("main");
		JSONObject Weather = list.getJSONArray("weather").getJSONObject(0);
		@SuppressWarnings("unused")
		JSONObject Clouds = list.getJSONObject("clouds");
		Location.GetTime(list.getInt("dt"));
		
		//Get precipitation levels if any
		 if (list.has("snow"))
		    	if (list.getJSONObject("snow").has("3h"))
		    	precip += list.getJSONObject("snow").getDouble("3h");
		    	else precip += list.getJSONObject("snow").getDouble("1h");
		    if (list.has("rain"))
		    	if (list.getJSONObject("rain").has("3h"))
			    	precip += list.getJSONObject("rain").getDouble("3h");
			    	else precip += list.getJSONObject("rain").getDouble("1h");
		    
		//Capitalize first letter of cloud condition
		String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
		 
		this.dayOfWeek = Location.ihours + ":" + (Location.iminutes < 10 ? "0" : "") + Location.iminutes + " " + Location.daytime + ", ";
		
		//Increase day by one
	    if (Location.NewDay == true)
		    Location.cal.add(Calendar.DATE, 1);
		
		this.dayOfWeek += format.format(Location.cal.getTime());
		this.temperature = temp.format(Main.getDouble("temp")) + "\u00b0C";
		this.skyCondition = weather;
		this.precipitation = temp.format(precip) + "";

	}
	

	/**
	 * Get the current day of the week
	 * @return A string representing the name of the day
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	/**
	 * Get the current temperature
	 * @return A string representing the current temperature
	 */
	public String getTemperature() {
		return temperature;
	}

	/**
	 * Get the current sky condition
	 * @return A string representing the current condition of the sky
	 */
	public String getSkyCondition() {
		return skyCondition;
	}

	/**
	 * Get the current amount of precipitation
	 * @return A string representing the current amount of precipitation
	 */
	public String getPrecipitation() {
		return precipitation;
	}

	/**
	 * Set an updated title for the day of the week
	 * @param dayOfWeek the updated title to set for the current day
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	/**
	 * Set an updated temperature value
	 * @param temperature the updated temperature information to set
	 */
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	/**
	 * Set an updated sky condition value
	 * @param skyCondition the updated sky condition information to set
	 */
	public void setSkyCondition(String skyCondition) {
		this.skyCondition = skyCondition;
	}

	/**
	 * Set an updated precipitation value
	 * @param precipitation the updated precipitation value to set
	 */
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}



}