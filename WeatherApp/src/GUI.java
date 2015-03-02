import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.Component;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class GUI implements ActionListener{
	
	private static final String APPLICATION_NAME = "WeatherApp";
	private JFrame mainWindow;
	private JButton displayLPButton;
	private gLocationPicker lp;
	public static Font font;
	
	// Constructor
	public GUI() {	
		mainWindow = new JFrame(APPLICATION_NAME);
		
		setFont();
		this.initGUI();	
	}
	
	// Initializes the GUI on the event dispatch thread
	private void initGUI() {
		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	try {
					buildHierarchy();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		});
	}

	/**
	 *  Formats and makes visible the main program JFrame,
	 *  after adding all child components to its hierarchy
	 *  
	 *  	There are a few ways to handle location changes, best might be
	 *  	to call this method from MyLocations.setCurrentLocation() 
	 *     	and have each element of the GUI pull all information from the currentLocation,
	 *     	the GUI updates itself as the location changes.
	 *     	Syntax like:
	 *     	PersistanceHandler.getMyLocations().getLocationList().get(0).getLTF().getDayArray()[3].getDayOfWeek()
	 *     	(Yikes, that's wordy.)
	 *     
	 *     	Note: if we need to rebuild the hierarchy from scratch, i.e. for a full refresh,
	 *      make a call to mainWindow.removeAll(), then to this method.  
	 * @throws Exception 
	 */
	public void buildHierarchy() throws Exception {
		mainWindow.setResizable(false);
		//Sets background
		mainWindow.setContentPane(new JLabel(new ImageIcon("LONDONNIGHT.jpg")));
		
		//defines frame closing behavior
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(1190, 742);
		
		// null layout enables absolute positioning
		mainWindow.setLayout(null);
		mainWindow.setVisible(true);
		
		// centers window
		mainWindow.setLocationRelativeTo(null);
		
		refresh();
		
		Location loc = new Location("london, Canada");
		
		//ALL THESE METHODS SHOULD BE RENAMED TO addBLAH().
		displayLTF(loc);
		
		
		displayHeader(loc);
		
    	displaySTF(loc);
    
    	displayLW(loc);
    	
    	addLocationPicker();
    	
    	// add the unit conversion button (this should be handled elsewhere)
    	ResizableImage button = new ResizableImage("unitIcon.png", 70, 200);
    	button.setLocation(350,20);
    	mainWindow.getContentPane().add(button);
    	
    	refresh();
	}
	
	/**
	 * ActionPerfomed() overrides the eponymous method of ActionListener,
	 * adding functions to the buttons.
	 * @param e ActionEvent
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == displayLPButton) {

			if (lp.isShowing() == true) {
				lp.setVisible(false);
				refresh();
			} else {
				lp.setVisible(true);
				refresh();
			}
		}
	}
	
	/**
	 * Validates and refreshes the main JFrame
	 */
	private void refresh() {
       	mainWindow.validate();
    	mainWindow.repaint();		
	}

	/**
	 * Adds the local weather panel to the main JFrame hierarchy 
	 * @param loc 
	 * @throws Exception 
	 */
	private void displayLW(Location loc) throws Exception {
	   	LocalWeatherPanel lwp = new LocalWeatherPanel(loc);
    	lwp.setLocation(80, 110);
    	mainWindow.getContentPane().add(lwp);
    	refresh();	
	}

	/**
	 * Adds the short term weather panel to the main JFrame hierarchy 
	 * @param loc 
	 * @throws Exception 
	 */
	private void displaySTF(Location loc) throws Exception {
    	ShortTermPanel panel6 = new ShortTermPanel(loc);
    	panel6.setLocation(780, 5);
    	mainWindow.getContentPane().add(panel6);
    	refresh();
	}

	/**
	 * Adds the header/title panel to the main JFrame hierarchy 
	 * @param loc 
	 */
	private void displayHeader(Location loc) {
		Header header = new Header(loc);
    	header.setLocation(30, 30);
    	mainWindow.getContentPane().add(header);
    	refresh();	
	}

	/**
	 * Adds the DayPanel (which organizes 5 DayPanel objects) to the main JFrame hierarchy
	 * @param loc 
	 * @throws Exception 
	 */
	private void displayLTF(Location loc) throws Exception {
		
		LongTermPanel ltPanel = new LongTermPanel(loc);
		ltPanel.setLocation(5, 458);
		mainWindow.getContentPane().add(ltPanel);
		
    	refresh();
	}
	
	/**
	 * Adds the locationPicker to the main JFrame hierarchy
	 */
	private void addLocationPicker() {
		
		// adds a location picker		
		lp = new gLocationPicker();
		
		// adds the location picker to the main window hierarchy, but does not paint it.
		lp.setVisible(false);
		addToMain(lp, 360, 75);	

		
		// button to toggle the visibility of the location picker
		displayLPButton = new JButton("Add / Switch location");
		displayLPButton.addActionListener(this);
		displayLPButton.setSize(160,25);
		displayLPButton.setOpaque(true);
		displayLPButton.setBorderPainted(false);
		displayLPButton.setFocusPainted(false);
    	
		addToMain(displayLPButton, 460, 50);
		refresh();		
	}
	
	/**
	 * Helper to add a Swing component to the main window.
	 * Reduces code duplication and eases readability.
	 * @param comp Component to add
	 * @param x X coordinate of the upper left corner
	 * @param y Y coordinate of the upper left corner
	 */
	private void addToMain(Component comp, int x, int y) {
		comp.setLocation(x, y);
		mainWindow.add(comp);
		refresh();
	}
	
	/**
	 * Sets the GUI.font global variable.  Can also register the font with the Graphics Environment,
	 * but its a TODO
	 */
	private void setFont() {
		try {
			File fontFile = new File("Roboto-Thin.TTF");
			font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
			font = font.deriveFont(44f);
		} 
		catch (IOException|FontFormatException e) 
		{
			System.out.println("Uh oh, you have font problems.");
		}
}
}
