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
		// single vertical column layout
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.setOpaque(false);
		this.setSize(new Dimension(91,270));
		
		addLabel(day.getDayOfWeek(),15,0,10, "Medium");
		addLabel(day.getTemperature(),30,0,15, "Medium");
		
		ResizableImage weatherIcon = new ResizableImage("Sunny_128x128.png", 70, 70);
    	weatherIcon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(weatherIcon);
		
    	addLabel(day.getSkyCondition(),12,0,0, "Light");
		addLabel(day.getPrecipitation(),20,0,15, "Medium");
		addLabel(day.getMaxTemp(),12,0,15, "Medium");
		addLabel(day.getMinTemp(),12,0,15, "Medium");
		
		
    	this.validate();
    	this.repaint();
		
	}
	/**
	 * Adds a label to the JPanel, and sets its display attributes
	 * @param text the weather information to add to the label
	 */
	private void addLabel(String text, int size, int gapX,  int gapY, String style) {
		JLabel label = new JLabel(text);	
		MakeFont createFont = new MakeFont(style);
		Font newFont = createFont.create().deriveFont((float)size);
    	label.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		label.setFont(newFont);
    	label.setForeground(Color.WHITE);
    	this.add((Box.createRigidArea(new Dimension(gapX,gapY))));
    	this.add(label);
    	this.validate();
    	this.repaint();
	}	
		
}
