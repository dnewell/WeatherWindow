import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * The GUI class creates and maintains the interface

 * @author David Newell
 * @author David Langford
 */
public class GUI implements ActionListener{
	
	private static final String APPLICATION_NAME = "WeatherApp";
	private static final String DEFAULT_LOCATION = "London, On";
	private JFrame mainWindow;
	private JTextField field;

	private LongTermPanel ltPanel;
	private ShortTermPanel stPanel;
	private LocalWeatherPanel lwPanel;
	private Location loc;
	public static Font font;
	
	// Constructor
	public GUI() {	
		mainWindow = new JFrame(APPLICATION_NAME);
		try {
			loc = new Location(DEFAULT_LOCATION);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setFont();
		this.initGUI();	
	}
	
	/**
	 *  Initializes the GUI on the event dispatch thread
	 */
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
		
		// centers window
		mainWindow.setLocationRelativeTo(null);	

		addLocationField();

		addLTF(loc);
		
    	addSTF(loc);
    
    	addLW(loc);
    	
    	mainWindow.setVisible(true);
	}
	
	
	private void addLocationField() {
		Font newFont = GUI.font.deriveFont(64f);
		field = new JTextField(15);		
		field.setText(loc.getLocation().substring(0, 1).toUpperCase() + loc.getLocation().substring(1));
		field.addActionListener(this);
		field.setLocation(30,30);
		field.setSize(500, 70);
    	field.setFont(newFont);
    	field.setOpaque(false);
    	field.setBackground(new Color(0,0,255,40));
    	field.setForeground(Color.WHITE);
    	Border border = BorderFactory.createLineBorder(new Color(255,255,255,40));
    	field.setBorder(border);
    	field.setCaretColor(new Color(255,255,255,140));
		field.setVisible(true);
		mainWindow.add(field);
		
	}

	/**
	 * Validates and refreshes the main JFrame hierarchy
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
	private void addLW(Location loc) throws Exception {
		if (lwPanel != null){
			// panel exists
			mainWindow.remove(lwPanel);	
		}
	   	lwPanel = new LocalWeatherPanel(loc);
	   	lwPanel.setLocation(80, 110);
    	mainWindow.getContentPane().add(lwPanel);
    	refresh();	
	}

	/**
	 * Adds the short term weather panel to the main JFrame hierarchy 
	 * @param loc 
	 * @throws Exception 
	 */
	private void addSTF(Location loc) throws Exception {
		if (stPanel != null){
			// panel exists
			mainWindow.remove(stPanel);	
		}
    	stPanel = new ShortTermPanel(loc);
    	stPanel.setLocation(780, 5);
    	mainWindow.getContentPane().add(stPanel);
    	refresh();
	}


	/**
	 * Adds the DayPanel (which organizes 5 DayPanel objects) to the main JFrame hierarchy
	 * @param loc 
	 * @throws Exception 
	 */
	private void addLTF(Location loc) throws Exception {
		if (ltPanel != null){
			// panel exists
			mainWindow.remove(ltPanel);	
		}
		ltPanel = new LongTermPanel(loc);
		ltPanel.setLocation(5, 458);
		mainWindow.getContentPane().add(ltPanel);
		
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

	@Override
	public void actionPerformed(ActionEvent e) {
		String locationText = field.getText();
		try {
			// TODO add a button.  Calls on newline atm.
			updateGUI(locationText);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * Handles GUI update on location change 
	 * @param locationText
	 * @throws Exception
	 */
	private void updateGUI(String locationText) throws Exception {
		Location userLoc = new Location(locationText);	
		addLTF(userLoc);
    	addSTF(userLoc);
    	addLW(userLoc);    	
		
	}
}
