import org.json.*;

/**
 * Class LongTermForecast parses the JSON for the long term forecast,
 * initializes all of the Day objects for that data,
 * and stores them in an array.
 *
 * @author David Newell
 * @author David Langford
 */


public class LongTermForecast {
	
private Day[] dayArray;
	
	/**
	 * Constructs a LongTermForecast
	 * @throws JSONException when an error occurs
	 */
	public LongTermForecast(JSONObject info) throws JSONException {
		// creates array which will contain the ThreeHourPeriod objects
		this.dayArray = new Day[5];
		// TODO this method may contain parser code, for now it just creates dummy objects
		parseJSONforLTF(info);
	}

	
	/**
	 * Parses the JSON for the Long-term weather forecast
	 * @param info the JSON to parse
	 * @throws JSONException when an error occurs
	 */
	public void parseJSONforLTF(JSONObject info) throws JSONException {
	
		// this loop creates 5 Day objects
		Location.cal.setTime(Location.now);
		for(int i = 0; i < 5; i++){
		dayArray[i] = new Day(info, i);
		}
		
	}

	/**
	 * Gets the day array
	 * @return array containing all the ThreeHourPeriod objects
	 */
	public Day[] getDayArray() {
		return dayArray;
	}


}