import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.*;

/**
 * The DayPanel class displays the forecast information for a single day.
 * Organizes a collection of Day objects.
 * 
 * Used by the GUI, its instances are typically created by the LongTermPanel class
 * 
 * @author David Newell
 * @author Karsten Babin
 *
 */
@SuppressWarnings("serial")
public class DayPanel extends JPanel {

	private Day day;

	/**
	 * Constructs a DayPanel
	 * @param day to access weather information
	 * @throws Exception when an error occurs
	 */
	public DayPanel(Day day) throws Exception{
		this.day = day;
		initPanel();
	}
	
	/**
	 * Initializes and adds all the display components
	 * to the panel
	 */
	private void initPanel() {
	
		// Create a WeatherIcon object to form the weather icons
		WeatherIcon wI = new WeatherIcon(day.getWeatherID());
		
		// Set the layout to a BoxLayout to have items stack on top
		// of one another
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		// Make the background of the panels transparent
		this.setOpaque(false);
		
		// Set dimensions of each unique panel
		this.setSize(new Dimension(91,270));
		
		// Add a day of the week label
		addLabel(day.getDayOfWeek(),15,0,10, "Medium");
		
		// Add a temperature label
		addLabel(day.getTemperature(),30,0,15, "Medium");
		
		// Add the label for the weather icon
		JLabel label = new JLabel();	
		
		MakeFont createFont;
		
		if(wI.getWeatherCode() == 9999){
			createFont = new MakeFont("FontAwesome");
		}
		else{
			createFont = new MakeFont("Owfont");
		}
		
		Font newFont = createFont.getFont().deriveFont((float)30);
		label.setFont(newFont);
		label.setMaximumSize(new Dimension(70,70));
		label.setText(wI.getWeatherIcon());
		
		// Aligns the icon in the center of the panel
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		// Set the icon in the center and bottom of the label
		label.setHorizontalAlignment(JLabel.CENTER);
    	label.setVerticalAlignment(JLabel.BOTTOM);
    	
    	// Color the image white
    	label.setForeground(Color.WHITE);
    	
    	// Create a spacer between the other elements
    	this.add((Box.createRigidArea(new Dimension(0,10))));
 
    	// Add the label to the panel
    	this.add(label);
    	this.validate();
    	this.repaint();
    	
    	// Add a sky condition label
    	addLabel(day.getSkyCondition(),12,0,0, "Light");
    	
    	// Add a precipitation label
		addLabel(day.getPrecipitation(),12,0,15, "Medium");
		
		// Add a maximum temperature label
		addLabel(day.getMaxTemp(),12,0,15, "Medium");
		
		// Add a minimum temperature label
		addLabel(day.getMinTemp(),12,0,15, "Medium");
		
	}
	/**
	 * Adds a label to the JPanel, and sets its display attributes
	 * @param text the weather information to add to the label
	 */
	private void addLabel(String text, int size, int gapX, int gapY, String style) {
		
		// Create a new JLabel object to apply properties to
		JLabel label = new JLabel(text);	
		
		// Set the font according to the parameter 'style'
		MakeFont createFont = new MakeFont(style);
		Font newFont = createFont.getFont().deriveFont((float)size);
		label.setFont(newFont);
		
		// Set the color of the text to white
		label.setForeground(Color.WHITE);
		
		// Center the JLabel in the middle of the panel
		label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		// Add a invisible box object to act as a top spacer between JLabels
    	this.add((Box.createRigidArea(new Dimension(gapX,gapY))));
    	
    	// Add the JLabel to the panel
    	this.add(label);
    	this.validate();
    	this.repaint();
	}	
		
}
