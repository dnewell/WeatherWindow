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
	    	precip += list.getJSONObject("snow").getDouble("3h");
	    if (list.has("rain"))
	    	precip += list.getJSONObject("rain").getDouble("3h");
		    
		//Capitalize first letter of cloud condition
		String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
		 
		this.dayOfWeek = Location.ihours + ":" + (Location.iminutes < 10 ? "0" : "") + Location.iminutes + " " + Location.daytime + ", ";
		
		//Increase day by one
	    if (Location.NewDay == true)
		    Location.cal.add(Calendar.DATE, 1);
		
		this.dayOfWeek += format.format(Location.cal.getTime());
		this.temperature = temp.format(Main.getDouble("temp")) + "°C";
		this.skyCondition = weather;
		this.precipitation = temp.format(precip) + "";

	}
	

	/**
	 * @return the dayOfWeek
	 */
	public String getDayOfWeek() {
		return dayOfWeek;
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
	 * @return the precipitation
	 */
	public String getPrecipitation() {
		return precipitation;
	}

	/**
	 * @param dayOfWeek the dayOfWeek to set
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
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
	 * @param precipitation the precipitation to set
	 */
	public void setPrecipitation(String precipitation) {
		this.precipitation = precipitation;
	}



}