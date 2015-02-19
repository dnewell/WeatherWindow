import java.util.ArrayList;

/**
 * @author David Newell
 * MyLocations class is created by PersistanceHandler,
 * and will interact with the GUI. 
 * 
 * TODO this class will likely be instrumental in the logic of the program and GUI.
 * 
 * TODO this class need work and discussion.  What creates what, and when. 
 */
public class MyLocations {
	
	private String currentLocation;
	// the arraylist is used to contain Location objects as they are created. 
	// Needs to be a list, not an array, since number of Locations is dynamic
	private ArrayList<Location> locationList;;
	
	/**
	 * Constructor.
	 * 
     * We need to prompt the user to pick a location at first run.
	 * 
	 * Actually, I'm not certain what the best way to do this is...
	 * The GUI location picker must interact with this class with
	 * ActionEvents.
	 */
	public MyLocations(){
		// actionEvents here. GUI link.
		
		locationList = new ArrayList<Location>();
	}

	/**
	 * addLocation method
	 * @param locationName the name of the location to add
	 */
	public void addLocation(Location location) {
		
		locationList.add(location);
		
	}

	/**
	 * @return the currentLocation
	 */
	public String getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * @return the locationList
	 */
	public ArrayList<Location> getLocationList() {
		return locationList;
	}

	/**
	 * @param locationList the locationList to set
	 */
	public void setLocationList(ArrayList<Location> locationList) {
		this.locationList = locationList;
	}
	

}
