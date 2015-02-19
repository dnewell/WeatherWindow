/**
 * @author David Newell
 * A bit of ugly code to demonstrate that things work.
 */
public class TestHarness {

	public static void main(String[] args) {
		
		
		PersistanceHandler ph = new PersistanceHandler();
		
		// create a test location
		Location testLocation = new Location("FakesVille");
		
		//add the location to the location list
		System.out.println("Location added: " + ph.getMyLocations().getLocationList().add(testLocation));
		
		//retrieve the test Location object from the list, and print its name
		System.out.println("Location Name: " + ph.getMyLocations().getLocationList().get(0).getLocation());
		
		//checks that the right number of days are contained in the DayArray
		System.out.println("Number of Days contained in the Long term forcast: " + 
				ph.getMyLocations().getLocationList().get(0).getLTF().getDayArray().length);
		
		// Print some values from random days
		System.out.println("Day of the week: " + 
				ph.getMyLocations().getLocationList().get(0).getLTF().getDayArray()[3].getDayOfWeek());
		System.out.println("Sky Condition: " + 
				ph.getMyLocations().getLocationList().get(0).getLTF().getDayArray()[2].getSkyCondition());
		System.out.println("Precipitation: " + 
				ph.getMyLocations().getLocationList().get(0).getLTF().getDayArray()[4].getPrecipitation());
		System.out.println("Temp: " + 
				ph.getMyLocations().getLocationList().get(0).getLTF().getDayArray()[1].getTemperature());
		System.out.println("MaxTemp " + 
				ph.getMyLocations().getLocationList().get(0).getLTF().getDayArray()[3].getMaxTemp());
		
		//checks that the right number of days are contained in the ThreeHourPeriod array
		System.out.println("\nNumber of periods contained in the Short Term Forecast: " + 
				ph.getMyLocations().getLocationList().get(0).getSTF().getThreeHourArray().length);
		
		// Print some values from random days
		System.out.println("Day of the week: " + 
				ph.getMyLocations().getLocationList().get(0).getSTF().getThreeHourArray()[3].getDayOfWeek());
		System.out.println("Sky Condition: " + 
				ph.getMyLocations().getLocationList().get(0).getSTF().getThreeHourArray()[2].getSkyCondition());
		System.out.println("Precipitation: " + 
				ph.getMyLocations().getLocationList().get(0).getSTF().getThreeHourArray()[4].getPrecipitation());
		System.out.println("Temp: " + 
				ph.getMyLocations().getLocationList().get(0).getSTF().getThreeHourArray()[1].getTemperature());
	}

}