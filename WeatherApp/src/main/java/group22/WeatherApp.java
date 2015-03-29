/**
 * WeatherApp is the main class for Team 22's weather viewing program. It allows
 * a user to see the current weather as well as forecasts for any earth city,
 * as well as the conditions on Mars as reported by the Curiosity Rover
 * 
 * @author David Newell
 * @author David Langford
 */
public class WeatherApp {
	
	// Flag to enable console output
	public static boolean CONSOLE_OUTPUT = false;
	
	/**
	 * Main method creates the GUI
	 * @param args command line args
	 */
	public static void main(String[] args) {
		if(args.length == 0 || (args == null)){
			System.out.println("Use -V to enable console output.");
		}
		// Checks and handles command line arguments
		for (String s: args){
				if (s.equals("-v") || s.equals("-V")) {
					System.out.println("Verbose console output enabled. \n");
					CONSOLE_OUTPUT = true;
				} else {
					System.out.println(s + " is not a valid argument. Use -V to enable console output.");
					}	
		}
		@SuppressWarnings("unused")
		GUI swingGUI = new GUI();
	}
}