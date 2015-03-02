/**
 * Location class represents a single location,
 * creates and populates the fields instances of the different forecast classes
 * and handles JSON requests.
 * 
 * @author David Newell
 * @author David Langford
 */

import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

import org.json.*;

/**
 * @author David
 *
 */
public class Location {

	private String location;
	private ShortTermForecast shortTermForecast;
	private LongTermForecast longTermForecast;
	private LocalWeather localWeather;
	private MarsDay marsDay;

	//Determines am or pm
	public static String daytime;
	public static int ihours, iminutes;
	
	//used to check for short term if it is a new date or not
	public static Boolean NewDay = false;
	//Get current day of the week
	public static Calendar cal = Calendar.getInstance();
	public static Date now = new Date();

	

	/**
	 * Constructor.
	 * @param location
	 */
	public Location(String location) throws Exception {
		
		this.location = location;
		/*
		 * MARS case handling.  There are many different ways (and possible places) we could handle this.
		 * 
		 */		
			this.marsDay = new MarsDay(readJSON("", "mars"));
			this.shortTermForecast = new ShortTermForecast(readJSON(location, "short term"));
			this.longTermForecast = new LongTermForecast(readJSON(location, "long term"));
			this.localWeather = new LocalWeather(readJSON(location, "current"));				
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Do we ever need to change an individual location?  It's set by a parameter on creation.
	 * I think it should be immutable afterwards, and so we should not have a setter for it.  Thoughts? 
	 * @param location the location to set
	 * TODO perhaps delete?
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	

	/**
	 * @param addr
	 * @param s
	 * @return
	 * @throws Exception
	 */
	public JSONObject readJSON(String addr, String s) throws Exception
	{
		String api = null;
		   
		   //NOTE - this will be controlled by a radio button or something, not the user entering a string
		   if (s == "current")
		   {
			   api = "http://api.openweathermap.org/data/2.5/weather?q=";
			   api += URLEncoder.encode(addr, "UTF-8");
			   api +="&units=metric";
		   }
		   else if (s == "short term")
		   {
			   api = "http://api.openweathermap.org/data/2.5/forecast?q=";
			   api += URLEncoder.encode(addr, "UTF-8");
			   api +="&units=metric";
		   }
		   else if (s == "long term")
		   {
			   api = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
			   api += URLEncoder.encode(addr, "UTF-8");
			   api += "&units=metric&cnt=5";
		   }
		   else if (s == "mars")
		   {
			   api = "http://marsweather.ingenology.com/v1/latest/?format=json";
		   }
	    URL url = new URL(api);
	    System.out.println("API address used: " + url);
	 
	    // read from the URL, then close the scanner
	    Scanner scan = new Scanner(url.openStream());
	    String str = new String();
	    while (scan.hasNext())
	        str += scan.nextLine();
	    scan.close();
	 
	    // build JSON objects
	    JSONObject res = new JSONObject(str);
	    
	    // check to make sure it pulls a valid location
	    if (res.has("cod"))
	    	if (res.getInt("cod") == 404)
	    	{
	    		System.out.print("That is not a valid location. ");
	    		return null;
	    	}
	    
	    	    
	    return res;	    
	}
	
	public static String Direction (double deg)
	   {		   
		   if (deg <=22.5 || deg >=337.5)
			   return "North";
		   else if (deg <=67.5 )
			   return "Northeast";
		   else if (deg <=112.5)
			   return "East";
		   else if (deg <=157.5)
			   return "Southeast";
		   else if (deg <=202.5)
			   return "South";
		   else if (deg <=247.5 )
			   return "Southwest";
		   else if (deg <=292.5)
			   return "West";
		   else return "Northwest";
	   }
	
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
	 * @return shortTermForecast the short term forecast for this location
	 */
	public ShortTermForecast getSTF() {
		return shortTermForecast;
	}
	
	/**
	 * @return longTermForecast the long term forecast for this location
	 */
	public LongTermForecast getLTF() {
		return longTermForecast;
	}
	
	/**
	 * @return localWeather the localWeather for this location
	 */
	public LocalWeather getLW() {
		return localWeather;
	}
	
	/**
	 * @return marsDay the MarsDay for this location
	 */
	public MarsDay getMD() {
		return marsDay;
	}
	
	/**
	 * @return location Name of the location
	 */
	public String toString(){
		return location;
	}



}