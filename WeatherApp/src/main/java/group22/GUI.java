import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Cursor;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * The GUI class creates and maintains the graphic interface through which the
 * user interacts with the program
 * 
 * @author David Newell
 * @author David Langford
 * @author Karsten Babin
 * @author Robert Goldfarb
 */
public class GUI implements ActionListener {

	// Set the application name
	private static final String APPLICATION_NAME = "Weather Window";
	
	// Data from data.ser file
	private static String CURRENT_LOCATION = "London ON";
	public int CURRENT_UNITS = 1;
	
	// Main program window
	private JFrame mainWindow;
	
	// Panels that reside within the main frame
	private JPanel shadowPanel;
	private LongTermPanel ltPanel;
	private ShortTermPanel stPanel;
	private LocalWeatherPanel lwPanel;
	
	// Swing elements added to the main frame
	private JTextField field;
	private JButton shorttermButton, longtermButton, celsiusButton, fahrenheitButton, refreshButton;
	private JLabel backgroundImageLabel, greyLineLabelTop,  greyLineLabelBottom;
	
	// Other elements needed for running/managing the GUI
	private Location loc;
	private SavedData savedData;
	private boolean shorttermState = true, refresh = false;

	/**
	 * Constructs JFrame which contains the GUI, and sets the default location
	 */
	public GUI() {

		// Deserialize data and set location to London
		Deserialize de_ser = new Deserialize();
		savedData = de_ser.getData();
		if (!savedData.location.equals("null")) {
			CURRENT_LOCATION = savedData.location;
			CURRENT_UNITS = savedData.units;
		}

		// Create the main window for the GUI, and apply it's name
		mainWindow = new JFrame(APPLICATION_NAME);
		try {
			// Get initial location based on info from the data.ser
			loc = new Location(CURRENT_UNITS, CURRENT_LOCATION);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialize the GUI
		this.initGUI();

	}

	/**
	 * Initializes the GUI on the event dispatch thread.
	 */
	private void initGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// Build the elements of the GUI
					buildHierarchy();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Formats and makes visible the main program JFrame, after adding all child
	 * components to its hierarchy
	 * @throws Exception if an error occurs
	 */
	public void buildHierarchy() throws Exception {

		// Set standard properties for the JFrame of the app
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(750, 630);
		mainWindow.setResizable(false);
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setLayout(null);

		// Apply a background image to the JFrame
		setBackgroundImage(loc);

		// Add a search field to the top of the application
		addLocationField();

		// While there is a valid location, create the GUI 
		if (loc != null) {

			if (!loc.getLocation().toLowerCase().equals("mars")) {
				// Add the long-term forecast to the frame
				addLTF(loc);

				// Add the short-term forecast to the frame
				addSTF(loc);
			}

			// Add the current forecast to the frame
			addLW(loc);

			// Add a refresh button that updates the weather information for
			// the local, short-term, and long-term views
			addRefreshButton();

			// Add the Celsius and Fahrenheit buttons to the frame
			addCelsiusButton();
			addFahrenheitButton();

			// Set the default measurements to either Celsius or Fahrenheit
			if (CURRENT_UNITS == 1)
				stateFahrenheit();
			else
				stateCelsius();

			// Add the Short and Long-term forecast buttons to the frame
			addShortTermButton();
			addLongTermButton();

			// Set the default state between short and long-term forecast
			if (!loc.getLocation().toLowerCase().equals("mars"))
				if (shorttermState == true)
					changeToShortTerm();
				else
					changeToLongTerm();

			// Add grey divisional lines to the bottom of the GUI 
			addGreyLines();
			
			// Add a ShadowPanel to the background
			addShadowPanel();

			// Check if Mars is the searched location
			if (loc.getLocation().toLowerCase().equals("mars")) {
				// If so hide the short-term and long-term panels
				shorttermButton.setVisible(false);
				longtermButton.setVisible(false);
			}

		}

		// Make the JFrame visible
		mainWindow.setVisible(true);
	}

	/**
	 * Adds a JButton to the GUI that updates the weather information
	 */
	private void addRefreshButton() {

		// Remove the weather refresh button if it already exists
		if (refreshButton != null)
			mainWindow.remove(refreshButton);

		// Create and apply the properties for the refresh button
		refreshButton = new JButton();
		refreshButton.setFont(new MakeFont("WeatherIcons").getFont().deriveFont(22f));
		refreshButton.setText(new WeatherIcon("f03e").getWeatherIcon());
		refreshButton.setForeground(Color.WHITE);
		refreshButton.setHorizontalAlignment(JButton.CENTER);
		refreshButton.setVerticalAlignment(JButton.BOTTOM);
		refreshButton.setBounds(520, 18, 95, 65);
		refreshButton.setOpaque(false);
		refreshButton.setContentAreaFilled(false);
		refreshButton.setBorderPainted(false);

		// Change the cursor of the mouse to a hand to represent a clickable button
		refreshButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Define the actions of the button
		refreshButton.addActionListener(new ActionListener() {

			// Set the action code when the refresh button is pressed
			public void actionPerformed(ActionEvent e) {

				try {
					refresh = true;
					refresh();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});

		// Add the refresh button to the GUI
		mainWindow.getContentPane().add(refreshButton);
		refreshGUI();

	}

	/**
	 * Validates and refreshGUIes the main JFrame hierarchy
	 * @throws Exception
	 */
	private void refresh() throws Exception {
		updateGUI(CURRENT_UNITS, CURRENT_LOCATION);
	}
	
	/**
	 * Creates a JButton to convert the weather information to Celsius
	 */
	private void addCelsiusButton() {

		// Remove the Celsius button if it already exists
		if (celsiusButton != null)
			mainWindow.remove(celsiusButton);
		
		// Define the text for the Celsius button
		celsiusButton = new JButton("C" + "\u00b0");

		// Make a new font for the button
		MakeFont makenewFont = new MakeFont("Bold");
		celsiusButton.setFont(makenewFont.getFont().deriveFont(40f));
		
		// Change the cursor of the mouse to a hand to represent a clickable button
		celsiusButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Define the actions of the button
		celsiusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateCelsius();
			}
		});

		// Set other properties of the button and its text
		celsiusButton.setVerticalAlignment(SwingConstants.BOTTOM);
		celsiusButton.setForeground(Color.WHITE);
		celsiusButton.setBounds(600, 35, 90, 50);
		celsiusButton.setOpaque(false);
		celsiusButton.setContentAreaFilled(false);
		celsiusButton.setBorderPainted(false);
		mainWindow.getContentPane().add(celsiusButton);
		refreshGUI();
	}

	/**
	 * Changes the metric for the weather information to Celsius
	 */
	private void stateCelsius() {

		// Updates the app with weather information in Celsius
		// by reloading new weather information in Celsius
		try {
			if(CURRENT_UNITS != 0)
				updateGUI(0, field.getText());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Set the fahrenheitButton text to grey
		fahrenheitButton.setForeground(new Color(215, 215, 215, 255));

		// Set the celsiusButton text to white
		celsiusButton.setForeground(Color.WHITE);

		// Shrink the size of the fahrenheitButton
		MakeFont makenewFahrenheitFont = new MakeFont("Bold");
		fahrenheitButton.setFont(makenewFahrenheitFont.getFont()
				.deriveFont(38f));

		// Change the celsiusButton font back to normal
		MakeFont makenewCelsiusFont = new MakeFont("Bold");
		celsiusButton.setFont(makenewCelsiusFont.getFont().deriveFont(40f));

	}

	/**
	 * Creates a JButton to convert the weather information to Fahrenheit
	 */
	private void addFahrenheitButton() {

		// Remove the Fahrenheit button if it already exists
		if (fahrenheitButton != null)
			mainWindow.remove(fahrenheitButton);
		
		// Define the text for the Fahrenheit button
		fahrenheitButton = new JButton("F" + "\u00b0");

		// Make a new font for the button
		MakeFont makenewFont = new MakeFont("Bold");
		fahrenheitButton.setFont(makenewFont.getFont().deriveFont(40f));
		
		// Change the cursor of the mouse to a hand to represent a clickable button
		fahrenheitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Define the actions of the button
		fahrenheitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateFahrenheit();
			}
		});

		// Set other properties of the button and its text
		fahrenheitButton.setVerticalAlignment(SwingConstants.BOTTOM);
		fahrenheitButton.setForeground(Color.WHITE);
		fahrenheitButton.setBounds(660, 34, 90, 50);
		fahrenheitButton.setOpaque(false);
		fahrenheitButton.setContentAreaFilled(false);
		fahrenheitButton.setBorderPainted(false);
		mainWindow.getContentPane().add(fahrenheitButton);
		refreshGUI();
	}

	/**
	 * Changes the metric for the weather information to Fahrenheit
	 */
	private void stateFahrenheit() {
		
		// Updates the app with weather information in Fahrenheit
		// by reloading new weather information in Fahrenheit
		try {
			if(CURRENT_UNITS != 1)
				updateGUI(1, field.getText());
		} catch (Exception e1) {
			e1.printStackTrace();
		}

		// Set the fahrenheitButton text to white
		fahrenheitButton.setForeground(Color.WHITE);

		// Set the celsiusButton text to grey
		celsiusButton.setForeground(new Color(215, 215, 215, 255));

		// Change the fahrenheitButton font back to normal
		MakeFont makenewFahrenheitFont = new MakeFont("Bold");
		fahrenheitButton.setFont(makenewFahrenheitFont.getFont()
				.deriveFont(40f));

		// Shrink the size of the celsiusButton
		MakeFont makenewCelsiusFont = new MakeFont("Bold");
		celsiusButton.setFont(makenewCelsiusFont.getFont().deriveFont(38f));

	}

	/**
	 * Creates a JButton that changes to the Short-term weather panel
	 */
	private void addShortTermButton() {

		// Remove the Short-term button if it already exists
		if (shorttermButton != null)
			mainWindow.remove(shorttermButton);
		
		// Create a new JButton
		shorttermButton = new JButton("Short-term");
		
		// Apply the properties of the new button
		MakeFont makenewFont = new MakeFont("Light");
		shorttermButton.setFont(makenewFont.getFont().deriveFont(18f));
		shorttermButton.setVerticalAlignment(SwingConstants.BOTTOM);
		shorttermButton.setForeground(Color.WHITE);
		shorttermButton.setBounds(15, 300, 150, 30);
		shorttermButton.setBackground(Color.BLACK);
		shorttermButton.setOpaque(false);
		shorttermButton.setContentAreaFilled(false);
		shorttermButton.setBorderPainted(false);
		
		// Change the cursor of the mouse to a hand to represent a clickable button
		shorttermButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		// Adds the button the GUI
		mainWindow.getContentPane().add(shorttermButton);
		
		// Logic code that changes to the short-term panel when pressed
		shorttermButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeToShortTerm();
			}
		});
		
		// Refresh the GUI with the button added
		refreshGUI();

	}

	/**
	 * Applies changes to the formating of the Short-term and Long-term
	 * JButton text when the Short-term button is pressed
	 */
	private void changeToShortTerm() {

		longtermButton.setForeground(new Color(215, 215, 215, 255));
		shorttermButton.setForeground(Color.WHITE);

		MakeFont makenewLongFont = new MakeFont("Medium");
		shorttermButton.setFont(makenewLongFont.getFont().deriveFont(20f));

		MakeFont makenewShortFont = new MakeFont("Medium");
		longtermButton.setFont(makenewShortFont.getFont().deriveFont(18f));

		ltPanel.setVisible(false);
		stPanel.setVisible(true);

		// Refresh the GUI
		refreshGUI();

	}

	/**
	 * Creates a JButton that changes to the Long-term weather panel
	 */
	private void addLongTermButton() {

		// Remove the Long-term button if it already exists
		if (longtermButton != null)
			mainWindow.remove(longtermButton);
		
		// Create a new JButton
		longtermButton = new JButton("Long-term");
		
		// Apply the properties of the new button
		MakeFont makenewFont = new MakeFont("Light");
		longtermButton.setFont(makenewFont.getFont().deriveFont(18f));
		longtermButton.setVerticalAlignment(SwingConstants.BOTTOM);
		longtermButton.setForeground(Color.WHITE);
		longtermButton.setBackground(Color.BLACK);
		longtermButton.setOpaque(false);
		longtermButton.setContentAreaFilled(false);
		longtermButton.setBorderPainted(false);
		longtermButton.setBounds(130, 300, 150, 30);
		
		// Change the cursor of the mouse to a hand to represent a clickable button
		longtermButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
		// Adds the button the GUI
		mainWindow.getContentPane().add(longtermButton);
		
		// Logic code that changes to the short-term panel when pressed
		longtermButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				changeToLongTerm();

			}
		});
		
		// Refresh the GUI with the button added
		refreshGUI();

	}

	/**
	 * Applies changes to the formating of the Long-term and Short-term
	 * JButton text when the Long-term button is pressed
	 */
	private void changeToLongTerm() {

		shorttermButton.setForeground(new Color(215, 215, 215, 255));
		longtermButton.setForeground(Color.WHITE);

		MakeFont makenewLongFont = new MakeFont("Medium");
		longtermButton.setFont(makenewLongFont.getFont().deriveFont(20f));

		MakeFont makenewShortFont = new MakeFont("Medium");
		shorttermButton.setFont(makenewShortFont.getFont().deriveFont(18f));

		stPanel.setVisible(false);
		ltPanel.setVisible(true);
		
		// Refresh the GUI
		refreshGUI();

	}

	/**
	 * Changes the background image to suit the weather of that location
	 * @param loc the location (i.e city or Mars)
	 */
	private void setBackgroundImage(Location loc) {

		if (backgroundImageLabel != null)
			mainWindow.remove(backgroundImageLabel);
		
		String imageName, skyCondition = "";
		
		// Set a background image for the Mars JPanel
		if (!loc.getLocation().toLowerCase().equals("mars"))
			skyCondition = loc.getLW().getSkyCondition().toLowerCase();
		else
			imageName = "mars.png";

		LocalWeather lw = loc.getLW();
		WeatherIcon wI = new WeatherIcon(lw.getWeatherID());

		// Get the picture
		URL url = getClass().getResource(wI.getWeatherBackground());

		// Set the size of the JLabel and apply the image
		backgroundImageLabel = new JLabel();
		backgroundImageLabel.setSize(750, 630);
		backgroundImageLabel.setIcon(new ImageIcon(url));
		mainWindow.setContentPane(backgroundImageLabel);
		
		// Refresh the GUI
		refreshGUI();

	}

	/**
	 * Adds a text field through which the user can change locations
	 */
	private void addLocationField() {
		
		// Remove the text if it already exists in the GUI
		if (field != null)
			mainWindow.remove(field);

		// Create a new font based on the needs for that element
		MakeFont makeNewFont = new MakeFont("UltraLight");
		Font locationfieldFont = makeNewFont.getFont().deriveFont(45f);

		// Create a new text field
		field = new JTextField(15);

		// Set properties for the text field
		field.setText(loc.getLocation().substring(0, 1).toUpperCase()+ loc.getLocation().substring(1));
		field.addActionListener(this);
		field.setLocation(30, 30);
		field.setSize(500, 50);
		field.setFont(locationfieldFont);
		field.setOpaque(false);
		field.setBackground(new Color(0, 0, 255, 40));
		field.setForeground(Color.WHITE);

		// Set the properties for the text field border
		Border border = BorderFactory.createLineBorder(new Color(255, 255, 255, 40));
		field.setBorder(border);
		field.setCaretColor(new Color(255, 255, 255, 140));
		field.setVisible(true);

		// Add the text field to the JFrame
		mainWindow.add(field);
		refreshGUI();
	}

	/**
	 * Validates and refreshGUIes the main JFrame hierarchy
	 */
	private void refreshGUI() {
		mainWindow.validate();
		mainWindow.repaint();
	}


	/**
	 * Adds two JLabels that form grey divisional lines at the bottom of the GUI
	 */
	private void addGreyLines() {

		// Create a new JLabel
		greyLineLabelTop = new JLabel();
		
		// Check if the top grey label exists; if so remove it
		if (greyLineLabelTop != null)
			mainWindow.remove(greyLineLabelTop);

		// Set properties for the top grey line
		greyLineLabelTop.setBackground(new Color(245, 245, 245, 125));
		greyLineLabelTop.setOpaque(true);
		greyLineLabelTop.setBounds(15, 332, 720, 2);
		greyLineLabelTop.setVisible(true);
		
		// Add the top grey line to the GUI
		mainWindow.getContentPane().add(greyLineLabelTop);

		// Create a new JLabel
		greyLineLabelBottom = new JLabel();
		
		// Check if the bottom grey label exists; if so remove it
		if (greyLineLabelBottom != null)
			mainWindow.remove(greyLineLabelBottom);

		// Set properties for the bottom grey line
		greyLineLabelBottom.setBackground(new Color(245, 245, 245, 125));
		greyLineLabelBottom.setOpaque(true);
		greyLineLabelBottom.setBounds(15, 590, 720, 2);
		greyLineLabelBottom.setVisible(true);
		
		// Add the bottom grey line to the GUI
		mainWindow.getContentPane().add(greyLineLabelBottom);

		// Refresh the GUI
		refreshGUI();

	}

	// Creates a transparent shadow box behind weather info text so
	// it is easier to read
	private void addShadowPanel() {

		// Remove the old shadowPanel when updating to a new location
		if (shadowPanel != null) {
			mainWindow.remove(shadowPanel);
		}

		// Create a new shadowPanel to the JFrame
		shadowPanel = new JPanel();

		// shadowPanel properties
		shadowPanel.setLocation(10, 10);
		shadowPanel.setOpaque(true);
		shadowPanel.setSize(730, 590);
		shadowPanel.setBackground(new Color(0, 0, 0, 125));
		shadowPanel.setVisible(true);

		// Add the shadowPanel to the JFrame
		mainWindow.getContentPane().add(shadowPanel);
		refreshGUI();
	}

	/**
	 * Adds the local weather panel to the main JFrame hierarchy
	 * @param loc the location
	 * @throws Exception if an error occurs
	 */
	private void addLW(Location loc) throws Exception {

		// Remove the old LocalWeatherPanel when updating to a new location
		if (lwPanel != null) {
			mainWindow.remove(lwPanel);
		}

		// Create a new LocationWeatherPanel to the JFrame
		lwPanel = new LocalWeatherPanel(loc);
		lwPanel.setLocation(10, 85);

		// Add the LocalWeatherPanel to the JFrame
		mainWindow.getContentPane().add(lwPanel);
		refreshGUI();
	}

	/**
	 * Adds the short term weather panel to the main JFrame hierarchy
	 * @param loc the location
	 * @throws Exception if an error occurs
	 */
	private void addSTF(Location loc) throws Exception {

		// Remove the old ShortTermForecastWeatherPanel when updating to a new
		// location
		if (stPanel != null) {
			// panel exists
			mainWindow.remove(stPanel);
		}

		// Create a new ShortTermPanel to the JFrame
		stPanel = new ShortTermPanel(loc);
		stPanel.setLocation(10, 335);

		// Add the ShortTermPanel to the JFrame
		mainWindow.getContentPane().add(stPanel);
		refreshGUI();
	}

	/**
	 * Adds the DayPanel (which organizes 5 DayPanel objects) to the main JFrame
	 * hierarchy
	 * @param loc the location
	 * @throws Exception if an error occurs
	 */
	private void addLTF(Location loc) throws Exception {

		// Remove the old LongTermForecastWeatherPanel when updating to a new
		// location
		if (ltPanel != null) {
			// panel exists
			mainWindow.remove(ltPanel);
		}

		// Create the LongTermPanel to the JFrame
		ltPanel = new LongTermPanel(loc);
		ltPanel.setLocation(10, 335);

		// Add the LongTermPanel to the JFrame
		mainWindow.getContentPane().add(ltPanel);
		refreshGUI();

	}

	/**
	 * Handles the ActionEvents for the location JTextField field object
	 * @Override
	 */
	public void actionPerformed(ActionEvent e) {
		String locationText = field.getText();
		try {
			// TODO add a button. Calls on newline atm.
			if (loc != null) {
				updateGUI(CURRENT_UNITS, locationText);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Handles GUI update on location change
	 * @param locationText the location (i.e a city or Mars)
	 * @throws Exception invalid location
	 */
	private void updateGUI(int units, String locationText) throws Exception {

		// Sets the current weather metric and location
		CURRENT_UNITS = units;
		CURRENT_LOCATION = locationText;
		
		// Saves the new location and current units as default presets next time software loads
		Serialize newSavedData = new Serialize(CURRENT_LOCATION, CURRENT_UNITS);
		
		// Applies the current location
		Location oldLoc = loc;

		// Conditions for updating the GUI
		if ((!loc.getLocation().toLowerCase().equals(locationText.toLowerCase()) || loc.getUnits() != units)|| refresh == true){
			try {

				try {
					// Show a spinning circle to tell the user the program is doing something
					mainWindow.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

					// Get the location
					Location userLoc = new Location(units, locationText);

					// Set a new background image
					setBackgroundImage(userLoc);
					
					// Re-add the search field
					addLocationField();
					
					// Re-add the Celsius and Fahrenheit Buttons
					addCelsiusButton();
					addFahrenheitButton();
					
					if (CURRENT_UNITS == 1)
						stateFahrenheit();
					else
						stateCelsius();
					
					// Re-add the Short-term and Long-ter Buttons
					addShortTermButton();
					addLongTermButton();
					
					// Update Long-term panel
					addLTF(userLoc);
					
					// Update the Short-term panel
					addSTF(userLoc);
					
					// Update the Local weather panel
					addLW(userLoc);
					
					// Set the new location
					userLoc.setLocation(userLoc.getLW().getCity() + ", " + userLoc.getLW().getCountry());
					
					// Set the text in the text field to the returned location by the API call
					field.setText(userLoc.getLocation());

					// Change back to the Short-term panel
					if (shorttermState == true)
						changeToShortTerm();
					else
						changeToLongTerm();

					// Re-add the refresh button
					addRefreshButton();
					
					// Re-add the grey lines
					addGreyLines();
					
					// Re-add the shadow panel
					addShadowPanel();

					// Set the visibility if the location is mars
					if (locationText.toLowerCase().equals("mars")) {
						shorttermButton.setVisible(false);
						longtermButton.setVisible(false);
					} else {
						shorttermButton.setVisible(true);
						longtermButton.setVisible(true);
					}
					
					loc = userLoc;
					
					
				} finally {
					// End the animation of the loading circle on the mouse cursor
					mainWindow.setCursor(Cursor.getDefaultCursor());
				}
				
			// Set the text in the text field back to the old location if the new location input is invalid
			} catch (NullPointerException e) {
				field.setText(oldLoc.getLocation().substring(0, 1).toUpperCase() + oldLoc.getLocation().substring(1));
			}
			
			// Set refresh back to false
			refresh = false;
		}
	}
}
