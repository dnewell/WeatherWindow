import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
* The ThreeHourPanel class displays the forecast information for a three hour period.
* 
* Used by the GUI, its instances are typically created by the ShortTermPanel class
* 
* @author David Newell
* @author David Langford
*/

public class ThreeHourPanel extends JPanel {
	
	private static final long serialVersionUID = -1641461367018339795L;
	private ThreeHourPeriod period;
	private BoxLayout layout;
	
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
		
		this.setLayout(layout = new BoxLayout(this,BoxLayout.PAGE_AXIS));
		this.setOpaque(true);
	
    	this.setOpaque(false);
		this.setSize(new Dimension(91,270));
    	
		addLabel(period.getTime(),20,0,10, "Medium");
		addLabel(period.getTemperature(),30,0,15, "Medium");
		
		ResizableImage weatherIcon = new ResizableImage("Sunny_Cloud_128x128.png",70,70);
    	weatherIcon.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		this.add(weatherIcon);
		
		addLabel(period.getSkyCondition(),12,0,0, "Light");
		addLabel(period.getPrecipitation() + "mm",20,0,15, "Medium");
    	

    	this.validate();
    	this.repaint();
		
	}
	
	/**
	 * Adds a label to the JPanel, and sets its display attributes
	 * @param text desired label text
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
