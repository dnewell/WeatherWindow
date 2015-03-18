/**
 * The Location class represents a single location, creates and populates
 * the fields instances of the different forecast classes and handles JSON requests.
 * 
 * @author David Newell
 * @author David Langford
 */

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.json.*;

public class Location {

	private String location;
	private ShortTermForecast shortTermForecast;
	private LongTermForecast longTermForecast;
	private LocalWeather localWeather;
	private MarsWeather marsWeather;
	private int count = 0;

	//Determines am or pm
	public static String daytime;
	public static int ihours, iminutes, units;
	
	//used to check for short term if it is a new date or not
	public static Boolean NewDay = false;
	//Get current day of the week
	public static Calendar cal = Calendar.getInstance();
	public static Date now = new Date();


	

	/**
	 * Constructs a location.
	 * - Handles the Mars case
	 * @param location a requested city or planet to query weather information for
	 */
	public Location(int units, String location) throws Exception {
		
		this.location = location;
		this.units = units;
		/*
		 * MARS case handling.  There are many different ways (and possible places) we could handle this.
		 * 
		 */		if (getLocation().toLowerCase().equals("mars"))
			this.marsWeather = new MarsWeather(readJSON(units, "", "mars"));
		 else {
			this.shortTermForecast = new ShortTermForecast(readJSON(units, location, "short term"));
			this.longTermForecast = new LongTermForecast(readJSON(units,location, "long term"));
			this.localWeather = new LocalWeather(readJSON(units,location, "current"));
		 }
	}

	/**
	 * Get the current location being queried
	 * @return the location the current location
	 */
	public String getLocation() {
		return location;
	}
		
	/**
	 * Get the current units (metric/imperial) being used
	 * @return a integer representing the current integer
	 */
	public static int getUnits(){
		return units;
	}

	/**
	 * Do we ever need to change an individual location?  It's set by a parameter on creation.
	 * I think it should be immutable afterwards, and so we should not have a setter for it.  Thoughts? 
	 * @param location: the location to set. 
	 * TODO perhaps delete?
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	
	/**
	 * Reads the JSON for a specified forecast type and location
	 * @param addr the location text
	 * @param s the desired type of forecast 
	 * @return A JSONObject
	 * @throws Exception when an error occurs
	 */
	public JSONObject readJSON(int units, String addr, String s) throws Exception
	{
		String api = null;
		String key = "&APPID=e57e5d3a71d17c47936c8513bdd97825";
		String units_text = "&units=metric";
		if(units==1)
			units_text = "&units=imperial";
		   
		//NOTE - this will be controlled by a radio button or something, not the user entering a string
		if (s == "current")
		{
			api = "http://api.openweathermap.org/data/2.5/weather?q=";
			api += URLEncoder.encode(addr, "UTF-8");
			api += units_text;
			//api += key;
		}
		else if (s == "short term")
		{
			api = "http://api.openweathermap.org/data/2.5/forecast?q=";
			api += URLEncoder.encode(addr, "UTF-8");
			api += units_text;
			//api += key;
		}
		else if (s == "long term")
		{
			api = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
			api += URLEncoder.encode(addr, "UTF-8");
			api += units_text;
			api += "&cnt=5";
			//api += key;
		}
		else if (s == "mars")
		{
		   	api = "http://marsweather.ingenology.com/v1/latest/?format=json";
	   	}
		   
	    URL url = new URL(api);
	   
	    Scanner scan = null;
	 
	    // read from the URL, then close the scanner
	    try {
	    	scan = new Scanner(url.openStream());
	    } catch (IOException e)
	    {
	    	count++;
	    	if (count <=10)
	    	{
	    		System.out.println("Retry attempt " + count + " of 10");
	    		return readJSON(units, addr, s);
	    	}
	    	System.out.println("No information available. Check Internet connection.\nOpen Weather Maps API could also be down.\nClosing application.");
	    	System.exit(0);
	    }
	   
	    System.out.println("API address used: " + url);
	    
	    String str = "";
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	 
	    if (str == "")
	    {
    		System.out.println("Sorry, OpenWeatherMaps is loading a blank page.");
    		return null;
    	}
	    
	    // build JSON objects
	    JSONObject res = new JSONObject(str);
	    
	    // check to make sure it pulls a valid location
	    if (res.has("cod"))
	    	if (res.getInt("cod") == 404)
	    	{
	    		System.out.println("That is not a valid location. ");
	    		return null;
	    	}
	    
	    	    
	    return res;	    
	}
	/**
	 * Converts cardinal directions from a numeric representation to text
	 * @param deg direction in degrees
	 * @return a friendly text representation of the direction
	 */
	public static String Direction (double deg)
	   {		   
		   if (deg <=22.5 || deg >=337.5)
			   return "N";
		   else if (deg <=67.5 )
			   return "NE";
		   else if (deg <=112.5)
			   return "E";
		   else if (deg <=157.5)
			   return "SE";
		   else if (deg <=202.5)
			   return "S";
		   else if (deg <=247.5 )
			   return "SW";
		   else if (deg <=292.5)
			   return "W";
		   else return "NW";
	   }
	/**
	 * Converts Epoch time from OpenWeatherMap api to a more
	 * user friendly format
	 * TODO Change to return a value, instead of using global variables
	 * @param time the epoch time
	 */
	 public static void GetTime (int time)
	   {
		 	//Get number of seconds from last possible midnight
		 	do 
		 	{
		 		time -=86400;
		 	} while (time >=104400);
		 
		 	//Get number of hours and minutes from remaining seconds
		 	double hours = (double)time/3600 -5;
		 	ihours = (int) hours;
		 	double minutes = (hours - ihours)*60 ;
		 	iminutes = (int) minutes;
		 	
		 	//convert to 12 hour clock
		 	if (ihours >12)
		 	{
		 		ihours -=12;
		 		daytime = "pm";
		 		NewDay = false;
		 	}
		 	else if (ihours == 12)
		 	{
		 		daytime = "pm";
		 		NewDay = false;
		 	}	
		 	else 
		 	{
		 		String old = daytime;
		 		daytime = "am";
		 		if (old != daytime)
		 			NewDay = true;
		 		else NewDay = false;
		 	}
	   }	
	/*
	 * Maybe unnecessary? Constructor creates the forecasts.
	 */
	@SuppressWarnings("unused")
	private void createForecasts() {

		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * Get the Short-term forecast for the current location
	 * @return shortTermForecast the short term forecast for this location
	 */
	public ShortTermForecast getSTF() {
		return shortTermForecast;
	}
	
	/**
	 * Get the Long-term forecast for the current location
	 * @return longTermForecast the long term forecast for this location
	 */
	public LongTermForecast getLTF() {
		return longTermForecast;
	}
	
	/**
	 * Get the current weather for the current location
	 * @return localWeather the localWeather for this location
	 */
	public LocalWeather getLW() {
		return localWeather;
	}
	
	/**
	 * Get the current weather for a Mars query
	 * @return marsDay the MarsDay for this location
	 */
	public MarsWeather getMD() {
		return marsWeather;
	}
	
	/**
	 * Get the name of the current location
	 * @return location Name of the location
	 */
	public String toString() {
		return location;
	}
}