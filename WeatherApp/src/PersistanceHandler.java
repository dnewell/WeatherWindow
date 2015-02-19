/**
 * @author David Newell
 * 
 * This class creates MyLocations,
 * and will... handle persistence. 
 */
public class PersistanceHandler {
	
	// Perhaps this could be a boolean flag, something like Celsius = false? What's the best way to handle this?
	// Use a global variable? I know, I know...
	private String temperatureUnits;
	private MyLocations myLocations;
	
	/**
	 * Constructor. Sets the Units to Celsius by default.  
	 * Creates MyLocations object.
	 * 
	 * TODO will this work with object serialization/persistence? Any side effects?
	 * 
	 * @param temperatureUnits
	 */
	public PersistanceHandler() {
		
		myLocations = new MyLocations();
		temperatureUnits = "c";
	}

	/**
	 * @return temperatureUnits
	 */
	public String getTemperatureUnits() {
		return temperatureUnits;
	}

	/**
	 * @param temperatureUnits
	 */
	public void setTemperatureUnits(String temperatureUnits) {
		this.temperatureUnits = temperatureUnits;
	}

	/**
	 * @return myLocations
	 */
	public MyLocations getMyLocations() {
		return myLocations;
	}

	/**
	 * @param myLocations
	 */
	public void setMyLocations(MyLocations myLocations) {
		this.myLocations = myLocations;
	}

}