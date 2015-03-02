import javax.swing.DefaultListModel;

/**
 * CHANGED TO USE DEFAULT LIST MODEL INTEAD OF ARRAYLIST
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
	// the DefaultListModel is used to contain Location objects as they are created. 
	// Needs to be a list, not an array, since number of Locations is dynamic
	// and it is by FAR easiest/best choice to use a DefaultListModel if we're using 
	//a JList to pick locations in the GUI.
	private DefaultListModel<Location> locationList;
	
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
		
		locationList = new DefaultListModel<Location>();
	}

	/**
	 * addLocation method
	 * 
	 * Validate locations here!!!
	 * 
	 * return true if location was valid, and successfully added to the list.
	 * @param locationName the name of the location to add
	 * @throws Exception 
	 */
	public Boolean addLocation(String locationName) throws Exception {
		
		if (isValidLocation(locationName) == true){
			Location validatedLocation = new Location(locationName);			
			locationList.addElement(validatedLocation);			
			return true;
		}
		
		// if not a valid location
		return false;
	}

	/**
	 * This method will use the OpenWeatherMaps API to check if the location the user
	 * entered is valid
	 * TODO validation code
	 * @param locationName The name of the location to check
	 * @return boolean True if the location is valid, false otherwise
	 */
	private boolean isValidLocation(String locationName) {
		// validate with OWM API here.
		
		
		return true;
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
	public DefaultListModel<Location> getLocationList() {
		return locationList;
	}

	/**
	 * @param locationList the locationList to set
	 */
	public void setLocationList(DefaultListModel<Location> locationList) {
		this.locationList = locationList;
	}
	

}
