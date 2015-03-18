import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * The GUI class creates and maintains the graphic interface
 * through which the user interacts with the program
 * 
 * @author David Newell
 * @author David Langford
 * @author Karsten Babin
 * @author Robert Goldfarb
 */
public class GUI implements ActionListener{
	
	
	private static final String APPLICATION_NAME = "WeatherApp";
	private static String CURRENT_LOCATION = "London, On";
	public int CURRENT_UNITS = 1;
	private JFrame mainWindow;
	private JTextField field;

	private LongTermPanel ltPanel;
	private ShortTermPanel stPanel;
	private LocalWeatherPanel lwPanel;
	private Location loc;
	public static Font font;
	private SavedData s;

	private boolean shorttermState = true;
	private JButton shorttermButton, longtermButton, celsiusButton, fahrenheitButton;
	private JPanel shadowPanel;
	private JLabel backgroundImageLabel = new JLabel();
	
	/**
	 * Constructs JFrame which contains the GUI,
	 * and sets the default location.
	 */
	public GUI() {	
		
		//Deserialize data and set location to London
		Deserialize de_ser = new Deserialize();
		s = de_ser.getData();
		CURRENT_LOCATION = s.location;
		CURRENT_UNITS = s.units;
		
		mainWindow = new JFrame(APPLICATION_NAME);
		try {
			loc = new Location(CURRENT_UNITS, CURRENT_LOCATION);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		setFont();
		this.initGUI();	
		
	
	}
	
	/**
	 *  Initializes the GUI on the event dispatch thread.
	 */
	private void initGUI() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	try {
					buildHierarchy();
				} catch (Exception e) {
					e.printStackTrace();
				}
		    }
		});
	}

	/**
	 *  Formats and makes visible the main program JFrame,
	 *  after adding all child components to its hierarchy
	 *  @throws Exception if an error occurs
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
		mainWindow.setContentPane(backgroundImageLabel);
	
		// Add a search field to the top of the application
		addLocationField();

		if (loc != null) 
		{		

			// Add the long-term forecast to the frame
			addLTF(loc);
			
			// Add the short-term forecast to the frame
			addSTF(loc);
				
			// Add the current forecast to the frame
			addLW(loc);
			
			// Add the Celsius and Fahrenheit buttons to the frame
			addCelsiusButton();
			addFahrenheitButton();
			
			// Set the default measurements to either Celsius or Fahrenheit
			if(CURRENT_UNITS == 1)
				stateFahrenheit();
			else
				stateCelsius();
			
			// Add the Short and Long-term forecast buttons to the frame
			addShortTermButton();
			addLongTermButton();
			
			// Set the default state between short and long-term forecast
			if(shorttermState == true)
				changeToShortTerm();
			else
				changeToLongTerm();
			
			// Add the ShadowPanel to the backgroud
			addShadowPanel();
		}
    	
		// Make the JFrame visible
    	mainWindow.setVisible(true);
	}

	/**
	 * Creates a button to convert the weather information to Celsius
	 */
	private void addCelsiusButton(){
		
		// Define the text for the Celsius button
		celsiusButton = new JButton("C"+"\u00b0");
		
		// Make a new font for the button
		MakeFont makenewFont = new MakeFont("Bold");
		celsiusButton.setFont(makenewFont.create().deriveFont(40f));
		
		// Define the actions of the button
		celsiusButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateCelsius();
			}
		});

		// Set other properties of the button and its text
		celsiusButton.setVerticalAlignment(SwingConstants.BOTTOM);
		celsiusButton.setForeground(Color.WHITE);
		celsiusButton.setBounds(565, 35, 90, 50);
		celsiusButton.setBackground(Color.BLACK);
		celsiusButton.setOpaque(false);
		celsiusButton.setContentAreaFilled(false);
		celsiusButton.setBorderPainted(false);
		mainWindow.getContentPane().add(celsiusButton);
		
	}
	
	/**
	 * Changes the metric for the weather information to Celsius
	 */
	private void stateCelsius(){
		
		// Updates the app with weather information in Celsius
		// by reloading new weather information in Celsius
		try {
			updateGUI(0,field.getText());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
			
		// Set the fahrenheitButton text to grey
		fahrenheitButton.setForeground(new Color(150,150,150,200));
		
		// Set the celsiusButton text to white
		celsiusButton.setForeground(Color.WHITE);

		// Shrink the size of the fahrenheitButton
		MakeFont makenewFahrenheitFont = new MakeFont("Bold");
		fahrenheitButton.setFont(makenewFahrenheitFont.create().deriveFont(38f));
		
		// Change the celsiusButton font back to normal
		MakeFont makenewCelsiusFont = new MakeFont("Bold");
		celsiusButton.setFont(makenewCelsiusFont.create().deriveFont(40f));
		
	}
	
	/**
	 * Creates a button to convert the weather information to Fahrenheit
	 */
	private void addFahrenheitButton(){
		
		// Define the text for the Fahrenheit button
		fahrenheitButton = new JButton("F"+"\u00b0");
		
		// Make a new font for the button
		MakeFont makenewFont = new MakeFont("Bold");
		fahrenheitButton.setFont(makenewFont.create().deriveFont(40f));
		
		// Define the actions of the button
		fahrenheitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stateFahrenheit();					
			}
		});

		// Set other properties of the button and its text
		fahrenheitButton.setVerticalAlignment(SwingConstants.BOTTOM);
		fahrenheitButton.setForeground(Color.WHITE);
		fahrenheitButton.setBounds(625, 35, 85, 50);
		fahrenheitButton.setBackground(Color.BLACK);
		fahrenheitButton.setOpaque(false);
		fahrenheitButton.setContentAreaFilled(false);
		fahrenheitButton.setBorderPainted(false);
		mainWindow.getContentPane().add(fahrenheitButton);
		
	}
	
	/**
	 * Changes the metric for the weather information to Fahrenheit
	 */
	private void stateFahrenheit(){
		// Updates the app with weather information in Fahrenheit
		// by reloading new weather information in Fahrenheit
		try {
			updateGUI(1,field.getText());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
				
		// Set the fahrenheitButton text to white
		fahrenheitButton.setForeground(Color.WHITE);
		
		// Set the celsiusButton text to grey
		celsiusButton.setForeground(new Color(150,150,150,200));
		
		// Change the fahrenheitButton font back to normal
		MakeFont makenewFahrenheitFont = new MakeFont("Bold");
		fahrenheitButton.setFont(makenewFahrenheitFont.create().deriveFont(40f));
		
		// Shrink the size of the celsiusButton
		MakeFont makenewCelsiusFont = new MakeFont("Bold");
		celsiusButton.setFont(makenewCelsiusFont.create().deriveFont(38f));
		
		
	}
	
	private void addShortTermButton(){
	
		shorttermButton = new JButton("Short-term");
		MakeFont makenewFont = new MakeFont("Light");
		shorttermButton.setFont(makenewFont.create().deriveFont(18f));
		shorttermButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					changeToShortTerm();
				
			}
		});

		shorttermButton.setVerticalAlignment(SwingConstants.BOTTOM);
		shorttermButton.setForeground(Color.WHITE);
	
		shorttermButton.setBounds(15, 300, 150, 30);
		shorttermButton.setBackground(Color.BLACK);
		shorttermButton.setOpaque(false);
		shorttermButton.setContentAreaFilled(false);
		shorttermButton.setBorderPainted(false);
		mainWindow.getContentPane().add(shorttermButton);
		
	
	}
	
	private void changeToShortTerm(){
		
		longtermButton.setForeground(new Color(150,150,150,200));
		shorttermButton.setForeground(Color.WHITE);
		
		MakeFont makenewLongFont = new MakeFont("Medium");
		shorttermButton.setFont(makenewLongFont.create().deriveFont(20f));
		
		MakeFont makenewShortFont = new MakeFont("Medium");
		longtermButton.setFont(makenewShortFont.create().deriveFont(18f));
		
		ltPanel.setVisible(false);
		stPanel.setVisible(true);
		
		 refreshGUI();
		
	}
	
	private void addLongTermButton() {
		
		longtermButton = new JButton("Long-term");
		MakeFont makenewFont = new MakeFont("Light");
		longtermButton.setFont(makenewFont.create().deriveFont(18f));
		
		longtermButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				changeToLongTerm();
				
			}
		});
		
		longtermButton.setVerticalAlignment(SwingConstants.BOTTOM);
		longtermButton.setForeground(Color.WHITE);
		longtermButton.setBackground(Color.BLACK);
		longtermButton.setOpaque(false);
		longtermButton.setContentAreaFilled(false);
		longtermButton.setBorderPainted(false);
		longtermButton.setBounds(130, 300, 150, 30);
		mainWindow.getContentPane().add(longtermButton);
		
	}
	
	private void changeToLongTerm(){
		
		shorttermButton.setForeground(new Color(150,150,150,200));
		longtermButton.setForeground(Color.WHITE);
		
		MakeFont makenewLongFont = new MakeFont("Medium");
		longtermButton.setFont(makenewLongFont.create().deriveFont(20f));
		
		MakeFont makenewShortFont = new MakeFont("Medium");
		shorttermButton.setFont(makenewShortFont.create().deriveFont(18f));
		
		stPanel.setVisible(false);
		ltPanel.setVisible(true);
		 
		refreshGUI();
		
	}
	
	/**
	 * Changes the background image to suit the weather of that location
	 * @param loc the location (i.e city or Mars)
	 */
	private void setBackgroundImage(Location loc){
			
		String imageName = "";
		String skyCondition = loc.getLW().getSkyCondition().toLowerCase();
		
		// Set the imageName to a picture that is congruent to the
		// weather conditions of the current location
		if(skyCondition.equals("sky is clear"))
    		imageName = "Clear_Sky_750x630.png";
    	else if(skyCondition.equals("few clouds"))
    		imageName = "Sunny_750x630.png";
    	else if(skyCondition.equals("scattered clouds"))
    		imageName = "Sunny_750x630.png";
    	else if(skyCondition.equals("broken clouds"))
    		imageName = "Sunny_Clouds_750x630.png";
    	else if(skyCondition.equals("shower rain"))
    		imageName = "Rain_750x630.png";
    	else if(skyCondition.equals("light rain"))
    		imageName = "Rain_750x630.png";
    	else if(skyCondition.equals("thunderstorm"))
    		imageName = "Rain_750x630.png";
    	else if(skyCondition.equals("snow"))
    		imageName = "Sunny_Clouds_750x630.png";
    	else if(skyCondition.equals("mist"))
    		imageName = "Rain_750x630.png";
    	else
    		mainWindow.setBackground(Color.DARK_GRAY);
		
		// Get the picture
		URL url = getClass().getResource(imageName);
		
		// Set the size of the JLabel and apply the image
		backgroundImageLabel.setSize(750, 630);
		backgroundImageLabel.setIcon(new ImageIcon(url));
	
	}
	
	/**
	 * Adds a text field through which the user can change locations
	 */
	private void addLocationField() {
		//Font newFont = GUI.font.deriveFont(45f);
		
		// Create a new font based on the needs for that element
		MakeFont makeNewFont = new MakeFont("UltraLight");
		Font locationfieldFont = makeNewFont.create().deriveFont(45f);
		
		// Create a new text field
		field = new JTextField(15);
		
		// Set properties for the text field
		field.setText(loc.getLocation().substring(0, 1).toUpperCase() + loc.getLocation().substring(1));
		field.addActionListener(this);
		field.setLocation(30,30);
		field.setSize(500, 50);
    	field.setFont(locationfieldFont);
    	field.setOpaque(false);
    	field.setBackground(new Color(0,0,255,40));
    	field.setForeground(Color.WHITE);
    	
    	// Set the properties for the text field border
    	Border border = BorderFactory.createLineBorder(new Color(255,255,255,40));
    	field.setBorder(border);
    	field.setCaretColor(new Color(255,255,255,140));
		field.setVisible(true);
		
		// Add the text field to the JFrame
		mainWindow.add(field);	
	}

	
	
	/**
	 * Validates and refreshGUIes the main JFrame hierarchy
	 */
	private void refreshGUI() {
       	mainWindow.validate();
    	mainWindow.repaint();		
	}
	
	
	/**
	 * Validates and refreshGUIes the main JFrame hierarchy
	 * @throws Exception 
	 */
	private void refresh() throws Exception {
		updateGUI(CURRENT_UNITS, CURRENT_LOCATION);
	}
	

	 //Creates a transparent shadow box behind weather info text so
	 //it is easier to read
	private void addShadowPanel() {
		
		// Remove the old shadowPanel when updating to a new location
		if (shadowPanel != null){
			mainWindow.remove(shadowPanel);	
		}
		
		// Create a new shadowPanel to the JFrame
		shadowPanel = new JPanel();
		
		// shadowPanel properties
		shadowPanel.setLocation(10,10);
		shadowPanel.setOpaque(true);
		shadowPanel.setSize(730,590);
		shadowPanel.setBackground(new Color(0,0,0,100));
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
		if (lwPanel != null){
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
	 * @throws Exception 
	 */
	private void addSTF(Location loc) throws Exception {
		
		// Remove the old ShortTermForecastWeatherPanel when updating to a new location
		if (stPanel != null){
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
	 * Adds the DayPanel (which organizes 5 DayPanel objects) to the main JFrame hierarchy
	 * @param loc the location
	 * @throws Exception 
	 */
	private void addLTF(Location loc) throws Exception {
		
		// Remove the old LongTermForecastWeatherPanel when updating to a new location
		if (ltPanel != null){
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
	 * Sets the GUI.font global variable.  
	 * TODO it may be better to register the font with the Graphics Environment
	 */
	private void setFont(){
		//File fontFile;
		InputStream is = this.getClass().getResourceAsStream("HelveticaNeue-UltraLight.otf");
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, is);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
//		try {
//			
//			fontFile = new File("Roboto-Thin.ttf");
//			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
//			font = font.deriveFont(44f);
//		} 
//		catch (Exception e) 
//		{
//			System.out.println("Uh oh, you have font problems.");
//		}
			
	}
		
	/**
	 * Handles the ActionEvents for the location JTextField field object
	 * 	@Override
	 */
	public void actionPerformed(ActionEvent e) {
		String locationText = field.getText();
		try {
			// TODO add a button.  Calls on newline atm.
			if (loc != null)
			updateGUI(CURRENT_UNITS, locationText);
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
		
		CURRENT_UNITS = units;
		CURRENT_LOCATION = locationText;
		Serialize s = new Serialize(locationText, units); //Serialize the new location
		Location oldLoc = loc;
		
		try {		

			Location userLoc = new Location(units, locationText);	

			setBackgroundImage(userLoc);
			addLTF(userLoc);
			addSTF(userLoc);
			addLW(userLoc);  
			
			if(shorttermState == true)
				changeToShortTerm();
			else
				changeToLongTerm();
			
			addShadowPanel();

		
		} 
		catch (NullPointerException e) {			
			field.setText(oldLoc.getLocation().substring(0, 1).toUpperCase() + oldLoc.getLocation().substring(1));	
		}
	}
}
