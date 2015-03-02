import org.json.*;

/**
 * @author David Newell
 * 
 * Class ShortTermForecast parses the JSON for the short term forecast,
 * initializes all of the ThreeHourPeriod objects for that data,
 * and stores them in an array.
 * 
 *  I think that the createThreeHourArray() method in the UML is redundant,
 *  the constructor will handle creating the array, and other methods will populate it!
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

	
	// TODO Parser code might live here
	// now creates dummy objects
	public void parseJSONforSTF(JSONObject info) throws JSONException {
	
		// this loop creates 9 dummy ThreeHourPeriod objects for testing
		// calls ThreeHourPeriod()'s no arg constructor
		// TODO Fit parser code in this class, and then delete this nonsense!
		Location.cal.setTime(Location.now);
		for(int i = 0; i < 8; i++){
		threeHourArray[i] = new ThreeHourPeriod(info, i+1);
		}
		
	}

	/*
	 * @return array containing all the ThreeHourPeriod objects 
	 */
	public ThreeHourPeriod[] getThreeHourArray() {
		return threeHourArray;
	}


}
