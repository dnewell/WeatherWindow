import org.json.*;

/**
 * @author David Newell
 * 
 * Class LongTermForecast parses the JSON for the long term forecast,
 * initializes all of the Day objects for that data,
 * and stores them in an array.
 * 
 * I think that the createDayArray() method in the UML is redundant,
 * the constructor will handle creating the array, and other methods will fill it!
 *
 */


public class LongTermForecast {
	
private Day[] dayArray;
	
	/**
	 * Constructor
	 * @throws JSONException 
	 */
	public LongTermForecast(JSONObject info) throws JSONException {
		// creates array which will contain the ThreeHourPeriod objects
		this.dayArray = new Day[5];
		// TODO this method may contain parser code, for now it just creates dummy objects
		parseJSONforLTF(info);
	}

	
	// TODO Parser code might live here
	// now creates dummy objects
	public void parseJSONforLTF(JSONObject info) throws JSONException {
	
		// this loop creates 5 dummy Day objects by calling Day()'s no arg constructor
		// TODO Fit parser code in this class, and then delete this nonsense!
		Location.cal.setTime(Location.now);
		for(int i = 0; i < 5; i++){
		dayArray[i] = new Day(info, i);
		}
		
	}


	/*
	 * @return array containing all the ThreeHourPeriod objects
	 */
	public Day[] getDayArray() {
		return dayArray;
	}


}