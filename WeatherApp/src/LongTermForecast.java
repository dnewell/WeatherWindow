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
	 */
	public LongTermForecast() {
		// creates array which will contain the ThreeHourPeriod objects
		this.dayArray = new Day[5];
		// TODO this method may contain parser code, for now it just creates dummy objects
		parseJSONforLTF();
	}

	
	// TODO Parser code might live here
	// now creates dummy objects
	public void parseJSONforLTF() {
	
		// this loop creates 9 dummy ThreeHourPeriod objects by calling ThreeHourPeriod()'s no arg constructor
		// TODO Fit parser code in this class, and then delete this nonsense!
		for(int i = 0; i < 5; i++){
		dayArray[i] = new Day();
		}
		
	}


	/*
	 * @return array containing all the ThreeHourPeriod objects
	 */
	public Day[] getDayArray() {
		return dayArray;
	}


}