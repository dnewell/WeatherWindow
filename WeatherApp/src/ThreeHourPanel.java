import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
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
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
		this.setOpaque(true);
		
		this.setBackground(new Color(0,255,0,10));
    	this.setSize(new Dimension(390,80));
    	
		addLabel(period.getDayOfWeek() + "   ");
		addLabel("Now: " + period.getTemperature() + "      ");
		addLabel(period.getSkyCondition());
		addLabel("  " + period.getPrecipitation() + "mm of precipitation");
    	ResizableImage button = new ResizableImage("resources/rain20.png", 30, 30);
    	this.add(button);

    	this.validate();
    	this.repaint();
		
	}
	
	/**
	 * Adds a label to the JPanel, and sets its display attributes
	 * @param text desired label text
	 */
	private void addLabel(String text) {
		JLabel label = new JLabel(text);	
		Font newFont = GUI.font.deriveFont(22f);
    	label.setFont(newFont);
    	label.setForeground(Color.WHITE);
   	
    	this.add(label);
    	this.validate();
    	this.repaint();
	}
	

}
