/**
 * @author David Newell
 * 
 * Location class represents a single location,
 * creates and populates the fields instances of the different forecast classes
 * and handles JSON requests.
 * 
 * TODO basically everything.
 *
 */
public class Location {

	private String location;
	private ShortTermForecast shortTermForecast;
	private LongTermForecast longTermForecast;
	private LocalWeather localWeather;
	private MarsDay marsDay;

	

	/**
	 * Constructor.
	 * Creates the 3 forecast child classes for an earth location.
	 * Create the child classes here or in a method?  I think here in the constructor is okay,
	 * but if we change the forecast objects to accept JSON objects as parameters, we will
	 * likely need to add a method(s)
	 * @param location
	 */
	public Location(String location) {
		
		this.location = location;
		/*
		 * MARS case handling.  There are many different ways (and possible places) we could handle this.
		 * 
		 */
		if(location.equalsIgnoreCase("mars")){
			//MARS case
			this.marsDay = new MarsDay();
			this.shortTermForecast = new ShortTermForecast();
			this.longTermForecast = new LongTermForecast();
			this.localWeather = new LocalWeather();	

		} else{
			//Earth case
			this.marsDay = new MarsDay();
			this.shortTermForecast = new ShortTermForecast();
			this.longTermForecast = new LongTermForecast();
			this.localWeather = new LocalWeather();			
		}		
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Do we ever need to change an individual location?  It's set by a parameter on creation.
	 * I think it should be immutable afterwards, and so we should not have a setter for it.  Thoughts? 
	 * @param location the location to set
	 * TODO perhaps delete?
	 */
	public void setLocation(String location) {
		this.location = location;
	}


	
	/**
	 * TODO
	 * Not sure about this method.  Are we handling the http requests here, and then
	 * passing the JSON as a parameter to the child classes?
	 */
	public void readJSON() {
		// does nothing right now.
	}
	
	
	/*
	 * Maybe unnecessary? Constructor creates the forecasts.
	 */
	@SuppressWarnings("unused")
	private void createForecasts() {

		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * @return shortTermForecast the short term forecast for this location
	 */
	public ShortTermForecast getSTF() {
		return shortTermForecast;
	}
	
	/**
	 * @return longTermForecast the long term forecast for this location
	 */
	public LongTermForecast getLTF() {
		return longTermForecast;
	}
	
	/**
	 * Since we don't have a specific class to parse the JSON for Local Weather,
	 * could we do it here, or with helper methods here?
	 * Maybe think about creating a class equivalent to ShortTermForecast for Local weather?
	 * @return localWeather the localWeather for this location
	 */
	public LocalWeather getLW() {
		return localWeather;
	}
	
	/**
	 * Since we don't have a specific class to parse the JSON for MarsDay,
	 * could we do it here, or with helper methods here?
	 * Maybe think about creating a class equivalent to ShortTermForecast for MarsDay?
	 * @return marsDay the MarsDay for this location
	 */
	public MarsDay getMD() {
		return marsDay;
	}


}