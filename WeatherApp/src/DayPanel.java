import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

/**
 * The DayPanel class displays the forecast information for a single day.
 * 
 * Used by the GUI, its instances are typically created by the LongTermPanel class
 * 
 * @author David Newell
 *
 */
@SuppressWarnings("serial")
public class DayPanel extends JPanel {

	private Day day;

	public DayPanel(Day day) throws Exception{
		this.day = day;
		initPanel();
	}
	
	private void initPanel() {
		// single vertical column layout
		GridLayout layout = new GridLayout(0,1);
		this.setLayout(layout);

		this.setOpaque(true);
		
		this.setBackground(new Color(0,255,0,20));
		this.setSize(new Dimension(148,240));
		
		addLabel(day.getDayOfWeek());
		addLabel(day.getTemperature());
		addLabel(day.getSkyCondition());
		addLabel(day.getPrecipitation());
		addLabel(day.getMaxTemp());
		addLabel(day.getMinTemp());
		
		ResizableImage button = new ResizableImage("rain20.png", 30, 30);
    	this.add(button);
    	this.validate();
    	this.repaint();
		
	}
		/**
		 * Adds the label to the JPanel
		 */
		private void addLabel(String text) {
			Font newFont = GUI.font.deriveFont(22f);

			JLabel label = new JLabel(text);	
	    	label.setFont(newFont);
	    	label.setForeground(Color.WHITE);
	   	
	    	this.add(label);
	    	this.validate();
	    	this.repaint();
		}	
		
}
