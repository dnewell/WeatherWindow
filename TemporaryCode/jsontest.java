// Written by David Langford

import java.io.*;
import java.net.*;
import java.text.*;
import java.util.*;
import org.json.*;

public class JsonTest {
	
	//Declare number formats
	public static DecimalFormat temp = new DecimalFormat("#.#");
	public static DecimalFormat place = new DecimalFormat("#.####");
	
	//Determines am or pm
	public static String daytime;
	public static int ihours, iminutes;
	
	//used to check for short term if it is a new date or not
	public static Boolean NewDay = false;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		JSONObject res = null;
		//substituting user input of forecast type desired
		String length = "mars";
		//Get city user desires		
		do 
		{
			System.out.println("Enter a city and its country or province: ");
			String s = br.readLine();			
			res = JSONReader(s, length);		
		} while (res == null);
		
		//Gets the correct weather forecast
		if (length == "current")
			CurrentWeather(res);
		else if (length == "short term")
			ShortTerm(res);
		else if (length == "long term")
			LongTerm(res);
		else if (length == "mars")
			Mars(res);
	}
	
	//  All of this is for a test with JSON import data on a specific location, in this case my apt.
	   public static JSONObject JSONReader(String addr, String s) throws Exception
	{	
	       // build a URL
		   String api = null;
		   
		   //NOTE - this will be controlled by a radio button or something, not the user entering a string
		   if (s == "current")
		   {
			   api = "http://api.openweathermap.org/data/2.5/weather?q=";
			   api += URLEncoder.encode(addr, "UTF-8");
		   }
		   else if (s == "short term")
		   {
			   api = "http://api.openweathermap.org/data/2.5/forecast?q=";
			   api += URLEncoder.encode(addr, "UTF-8");
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
	   
	   //Gets the current weather conditions for the selected location
	   public static void CurrentWeather (JSONObject res) throws Exception
	   {	
		    Double precip=0.00;
		   
		    //Declare Json Objects for use
		    JSONObject loc = res.getJSONObject("coord");
		    JSONObject Weather = res.getJSONArray("weather").getJSONObject(0);
		    JSONObject info = res.getJSONObject("sys");
		    JSONObject Main = res.getJSONObject("main");
		    JSONObject Wind = res.getJSONObject("wind");
		    JSONObject Clouds = res.getJSONObject("clouds");
		    
		    //get current time
	 	    Date now = new Date();
	 	    
		    //Capitalize first letter of cloud contition
		    String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
		    
		    // Print the results
		    System.out.println(res.getString("name") + ", " + info.getString("country"));
		    System.out.println("Current temperature: " + temp.format(Celcius(Main.getDouble("temp"))) + "\u00B0" + "C or " + temp.format(Fahrenheight(Celcius(Main.getDouble("temp")))) + "\u00B0" + "F");
		    System.out.println((int)Main.getDouble("humidity") + "% Humidity");
		    System.out.println(weather + ", " + (int)Clouds.getDouble("all") + "% cloud coverage.");
		    
		    //Get precipitation levels if any
		    if (res.has("snow"))
		    	precip += res.getJSONObject("snow").getDouble("3h");
		    if (res.has("rain"))
		    	precip += res.getJSONObject("rain").getDouble("3h");		    
		    if (precip !=0)
		    	System.out.println(temp.format(precip) + "mm of precipitation expected.");
		    
		    System.out.println("Wind speed: " + Wind.getDouble("speed") + "Km/s " + Direction(Wind.getDouble("deg")) + ". Air pressure: " + temp.format(Main.getDouble("pressure")) + " hpa");
		    
		    //Get sunrise and sunset times
		    GetTime(info.getInt("sunrise"));
		    System.out.print("Sunrise " + ihours + ":" + (iminutes < 10 ? "0" : "") + iminutes + " " + daytime);
		    GetTime(info.getInt("sunset"));
		    System.out.println("  Sunset " + ihours + ":" + (iminutes < 10 ? "0" : "") + iminutes + " " + daytime);
		    
		    System.out.println("Geo coords: [" + place.format(loc.getDouble("lat")) + ", " + place.format(loc.getDouble("lon")) + "]");
		    
		    //Get refresh time of information
		    GetTime(res.getInt("dt"));	    		    
		    System.out.format("Information last updated at " + ihours + ":" + (iminutes < 10 ? "0" : "") + iminutes + " " + daytime);
		    
		    System.out.println();
		    System.out.format("Retrieved at: %tl:%tM %tp%n", now, now, now);
	   }

	   //Gets the short term weather conditions for the selected location
	   public static void ShortTerm (JSONObject res) throws Exception
	   {		 
		   //get city info
		   JSONObject info = res.getJSONObject("city");
		   System.out.println(info.getString("name") + ", " + info.getString("country"));		   

		   //Get current day of the week
		   Calendar cal = Calendar.getInstance();
		   Date now = new Date();
		   cal.setTime(now);
		   SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd");
		   
		   //To catch if there is less than 24 hours worth of forecast data available
		   try{
		   
			   //do for each 3 hour period
			   for (int index=0;index <= 8;index++)
			   {
				   Double precip = 0.0;
				   
				   //get needed JSON Objects and timestamp
				   JSONObject list = res.getJSONArray("list").getJSONObject(index);
				   JSONObject Main = list.getJSONObject("main");
				   JSONObject Weather = list.getJSONArray("weather").getJSONObject(0);
				   JSONObject Clouds = list.getJSONObject("clouds");
				   GetTime(list.getInt("dt"));
				    
				    //Capitalize first letter of cloud contition
				    String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
				   
				    //Output data with a space after each output
				    System.out.format("Weather conditions for " + ihours + ":" + (iminutes < 10 ? "0" : "") + iminutes + " " + daytime + ", ");
				    //Increase day by one
				    if (NewDay == true)
					    cal.add(Calendar.DATE, 1);   	
				    System.out.println(format.format(cal.getTime()));
				    System.out.println("Current temperature: " + temp.format(Celcius(Main.getDouble("temp"))) + "\u00B0" + "C or " + temp.format(Fahrenheight(Celcius(Main.getDouble("temp")))) + "\u00B0" + "F");
				    System.out.println(weather + ", " + (int)Clouds.getDouble("all") + "% cloud coverage.");
				  
				    //Get precipitation levels if any
				    if (list.has("snow"))
				    	precip += list.getJSONObject("snow").getDouble("3h");
				    if (list.has("rain"))
				    	precip += list.getJSONObject("rain").getDouble("3h");		    
				    if (precip !=0)
				    	System.out.println(temp.format(precip) + "mm of precipitation expected.");
				    
				    System.out.println();
				    
			   }
		   }
		   //Outputs ststement for no more data availble
		   catch (JSONException e) 
		   {
			   System.out.println( e + "\nSorry, there is no more forecast data available.");
		   }
		   
	   }
	   
	   //Gets the long term weather conditions for the selected location
	   public static void LongTerm (JSONObject res) throws Exception
	   {
		 //get city info
		   JSONObject info = res.getJSONObject("city");
		   System.out.println(info.getString("name") + ", " + info.getString("country"));
		   
		   //Get current day of the week
		   Calendar cal = Calendar.getInstance();
		   Date now = new Date();
		   cal.setTime(now);
		   SimpleDateFormat format = new SimpleDateFormat("EEE MMM dd");
		   
		   //To catch if there is less than 5 days worth of forecast data available
		   try
		   {		   
			   //do for each day period
			   for (int index=0;index <= 4;index++)
			   {
				   Double precip = 0.0;
				   //get needed JSON Objects and timestamp
				   JSONObject list = res.getJSONArray("list").getJSONObject(index);
				   JSONObject Temp = list.getJSONObject("temp");
				   JSONObject Weather = list.getJSONArray("weather").getJSONObject(0);
				   GetTime(list.getInt("dt"));
				    
				    //Capitalize first letter of cloud contition
				    String weather = Weather.getString("description").substring(0, 1).toUpperCase() + Weather.getString("description").substring(1);
				   
				    //Output data with a space after each output
				    System.out.format("Weather conditions for ");
				    System.out.println(format.format(cal.getTime()));
				    //Increase day by one
				    cal.add(Calendar.DATE, 1);
				    System.out.println("Current temperature: " + temp.format(Temp.getDouble("day")) + "\u00B0" + "C or " + temp.format(Fahrenheight(Temp.getDouble("day"))) + "\u00B0" + "F");
	
				    System.out.println("Min: " + temp.format(Temp.getDouble("min")) + "\u00B0" + "C. Max: " + temp.format(Temp.getDouble("max")) + "\u00B0" + "C");
				    System.out.println(weather + ", " + (int)list.getDouble("clouds") + "% cloud coverage.");
				    
				    //Get precipitation levels if any
				    if (list.has("snow"))
				    	precip += list.getDouble("snow");
				    if (list.has("rain"))
				    	precip += list.getDouble("rain");
				    //remove the if statement if you always want to show precip levels, even if 0
				    if (precip !=0)
				    	System.out.println(temp.format(precip) + "mm of precipitation expected.");
			   
				    System.out.println();				    
				  }
			   
		   //Outputs ststement for no more data availble
		   } 
		   catch (JSONException e) 
		   {
			   System.out.println( e + "\nSorry, there is no more forecast data available.");
		   }
	   }
	   

	   //Gets the current weather conditions for the selected location
	   public static void Mars (JSONObject res) throws Exception
	   {	
		   Double precip=0.00;
		   
		   //Declare Json Objects for use
		   JSONObject data = res.getJSONObject("report");
		   
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
		   
		   //PRINT
		   System.out.println("Average Temp: " + avgTemp + "\u00B0" + "C");
		   System.out.println("Date: " + totalDate);
		   
		   if (!windDirection.equals("--"))
			   System.out.println("Wind Direction: " + windDirection);
		   else System.out.println("No wind direction information available.");
		   
		   if (!windSpeed.equals(null))
			   System.out.println("Wind speed " + windSpeed);
		   else System.out.println("No wind speed information available.");
		   
		   if (!humidity.equals(null))
			   System.out.println("Humidity "+ humidity);	
		   else System.out.println("No humidity information available.");	   
		   
		   System.out.println("Pressure: " + pressure);
		   System.out.println("Sky Conditon: " + skyCondition);
		   
		   	}
	   
	   //Gets the current time from the Epoch timestamp. Modifies ihours and iminutes, as wel as 
	   //NewDay, which checks to see it it is the start of a new date.
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
	   
	   //Get the wind direction from degree reading
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
	
	   //Used to convert temperature
	   public static Double Celcius (Double temp) {	return temp - 273.15;}	   
	   public static Double Fahrenheight (Double temp) { return temp*1.8000 + 32.00;}
}