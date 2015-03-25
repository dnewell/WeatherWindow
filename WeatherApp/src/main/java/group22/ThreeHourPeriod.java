import java.text.DecimalFormat;
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

	private String time;
	private String temperature;
	private String skyCondition;
	private String precipitation;
	private int weatherID;

	
	/**
	 * Constructs a ThreeHourPeriod
	 * @param  res the JSON data object
	 * @throws JSONException if an error occurs
	 */
	public ThreeHourPeriod(JSONObject res, int index) throws JSONException {
				
		DecimalFormat temp = new DecimalFormat("#");
		DecimalFormat prep = new DecimalFormat("#.#");
		
		Double precip = 0.0;
		   
		String measurement = "inches";
		double prepMultiplier = 0.0393701;
		
		if(Location.getUnits() == 0){
			measurement = "mm";
			prepMultiplier = 1.0;
		}
		
		try{
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
		    		precip += list.getJSONObject("snow").getDouble("3h")*prepMultiplier;
		    	else 
		    		precip += list.getJSONObject("snow").getDouble("1h")*prepMultiplier;
		    if (list.has("rain"))
		    	if (list.getJSONObject("rain").has("3h"))
			    		precip += list.getJSONObject("rain").getDouble("3h")*prepMultiplier;
			    	else 
			    		precip += list.getJSONObject("rain").getDouble("1h")*prepMultiplier;
		    
		    //Capitalize first letter of cloud condition
		    String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
		 
		    this.time = Location.ihours + ":" + (Location.iminutes < 10 ? "0" : "") + Location.iminutes + " " + Location.daytime;
		
		    //Increase day by one
		    if (Location.NewDay == true)
		    	Location.cal.add(Calendar.DATE, 1);
		
		    //this.time += format.format(Location.cal.getTime());
		    this.temperature = temp.format(Main.getDouble("temp")) + "\u00b0";
		    this.skyCondition = weather;
		    this.precipitation = prep.format(precip)+" "+measurement;
		    this.weatherID = Weather.getInt("id");
		
		// If OpenWeatherMap does not generate enough short weather information, fill it in with the below info
		} catch (JSONException e){
			
			this.temperature = "N/A";
			this.skyCondition = "N/A";
			this.precipitation = "N/A";
			this.weatherID = 9999;
			
		}
		
		
	}
	

	/**
	 * Get the current day of the week
	 * @return A string representing the name of the day
	 */
	public String getTime() {
		return time;
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
	 * Getter for the weatherIcon
	 * @return a string representing a code for the current weather
	 */
	public int getWeatherID(){
		return weatherID;
	}
}