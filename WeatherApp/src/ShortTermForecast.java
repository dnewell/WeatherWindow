import org.json.*;

/**
 * Class ShortTermForecast parses the JSON for the short term forecast,
 * initializes all of the ThreeHourPeriod objects for that data,
 * and stores them in an array.
 *  
 * @author David Newell
 * @author David Langford
 */

public class ShortTermForecast {
	
	private ThreeHourPeriod[] threeHourArray;
	
	/**
	 * Constructor
	 * @param threeHourArray
	 * @throws JSONException 
	 */
	public ShortTermForecast(JSONObject info) throws JSONException {
		// creates array which will contain the ThreeHourPeriod objects
		this.threeHourArray = new ThreeHourPeriod[8];
		// TODO this method may contain parser code, for now it just creates dummy objects
		parseJSONforSTF(info);
	}
	/**
	 * Parses the JSON 
	 * @param info the JSON to parse
	 * @throws JSONException
	 */
	public void parseJSONforSTF(JSONObject info) throws JSONException {
		Location.cal.setTime(Location.now);
		for(int i = 0; i < 8; i++){
		threeHourArray[i] = new ThreeHourPeriod(info, i+1);
		}
		
	}

	/**
	 * Getter for the ThreeHourArray
	 * @return array containing all the ThreeHourPeriod objects 
	 */
	public ThreeHourPeriod[] getThreeHourArray() {
		return threeHourArray;
	}


}
