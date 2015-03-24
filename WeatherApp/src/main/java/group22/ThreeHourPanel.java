import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
* The ThreeHourPanel class displays the forecast information for a three hour period.
* 
* Used by the GUI, its instances are typically created by the ShortTermPanel class
* 
* @author David Newell
* @author David Langford
* @author Karsten Babin
*/

public class ThreeHourPanel extends JPanel {
	
	private static final long serialVersionUID = -1641461367018339795L;
	private ThreeHourPeriod period;
	
	/**
	 * Constructs the panel
	 * @param period the ThreeHourPeriod
	 */
	public ThreeHourPanel(ThreeHourPeriod period){
		this.period = period;
		initPanel();
	}
	
	/**
	 * Initializes the panel and sets its display attributes.
	 */
	private void initPanel() {
				
		// Create a WeatherIcon object to form the weather icons
		WeatherIcon wI = new WeatherIcon(period.getWeatherID());
		
		// Set the layout to a BoxLayout to have items stack on top
		// of one another
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
    	
		// Make the background of the panels transparent
		this.setOpaque(false);
		
		// Set dimensions of each unique panel
		this.setSize(new Dimension(91,270));
    	
		// Add a time Label
		addLabel(period.getTime(),20,0,10, "Medium");
		
		// Add a average temperature label
		addLabel(period.getTemperature(),30,0,20, "Medium");    	
    	
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
    	
    	// Set the colour of the text to white
    	label.setForeground(Color.WHITE);
    	
    	// Create a spacer between the other elements
    	this.add((Box.createRigidArea(new Dimension(0,15))));
    	
    	// Add the label to the panel
    	this.add(label);
    	this.validate();
    	this.repaint();
		
    	// Add a sky condition label
		addLabel(period.getSkyCondition(), 12, 0, 5,"Light");
		
		// Add a precipitation label
    	addLabel(period.getPrecipitation(),12,0,20, "Medium");

    	
    	this.validate();
    	this.repaint();
		
	}
	
	/**
	 * Adds a label to the JPanel, and sets its display attributes
	 * @param text desired label text
	 */
	private void addLabel(String text, int size, int gapX,  int gapY, String style) {
		
		// Create a new JLabel object to apply properties to
		JLabel label = new JLabel(text);	
		
		MakeFont createFont = new MakeFont(style);
		
		// Set the font according to the parameter 'style'
		Font newFont = createFont.getFont().deriveFont((float)size);
    	label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setFont(newFont);
		
		// Set the color of the text to white
    	label.setForeground(Color.WHITE);
    	
    	// Add a invisible box object to act as a top spacer between JLabels
    	this.add((Box.createRigidArea(new Dimension(gapX,gapY))));
    	
    	// Add the JLabel to the panel
    	this.add(label);
    	this.validate();
    	this.repaint();
	}
	

}
