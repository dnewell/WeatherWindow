import java.io.*;

/**
 * Deserializes the data.ser file to load user presets
 * 
 * @author Team 22
 */
public class Deserialize implements Serializable {
	private static SavedData s;

	/**
	 * Constructor
	 */
	public Deserialize() {
		{
			
			s = new SavedData();
			try {
				
				// Find the data.ser file and start loading information
				FileInputStream fileIn = new FileInputStream("data.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);

				s = (SavedData) in.readObject();
				in.close();
				fileIn.close();
				
			} catch (IOException i) {
				
				if (WeatherApp.CONSOLE_OUTPUT) {
					System.out.println("No Data file found. Loading default information");
					}
				s.location = "London ON";
				s.fieldFontSize = 45f;
				
			} catch (ClassNotFoundException c) {
				
				if (WeatherApp.CONSOLE_OUTPUT) {
					System.out.println("SavedData class not found");
				c.printStackTrace();
				}
				return;
				
			}

			// Sets what the unit preset is from the data.ser
			String units_text = "Metric";
			if (s.units == 1)
				units_text = "Imperial";

			// Prints what information is being pulled from the data.ser to the
			// console
			if (WeatherApp.CONSOLE_OUTPUT){
			System.out.println("Deserialized Data...");
			System.out.println("Units: " + units_text);
			System.out.println("Location: " + s.location);
			System.out.println("Font size: " + s.fieldFontSize);
			}
		}
	}

	/**
	 * Gets the information from the data.ser file
	 * @return Information from the data.ser file
	 */
	public SavedData getData() {
		return s;
	}
}