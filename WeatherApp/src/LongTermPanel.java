/**
 * @author David Newell
 */

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.json.*;

public class LongTermPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public LongTermPanel(Location loc) throws Exception {
		
		initPanel(loc);		
	}

	private void initPanel(Location loc) throws Exception {
	
		this.setLayout(null);
		this.setOpaque(true);
		
		this.setBackground(new Color(0,0,255,30));
    	this.setSize(new Dimension(770,250));
    	JSONObject test = null;
		addDayPanels(loc);

	}

	
	private void addDayPanels(Location loc) throws Exception {
		DayPanel[] panels = new DayPanel[5];
		
		for(int i = 0; i < 5; i++){
	    	panels[i] = new DayPanel(loc.getLTF().getDayArray()[i]);
		}
		
		int horPosition = 5;
		for(int i = 0; i < 5; i++){
			// Sets spacing, uses a 5 pixel wide border on all sides of each ThreeHourPanel.
	    	panels[i].setLocation((5+(((horPosition+148)*i))), 5);
	    	this.add(panels[i]);	
		}
	}
	
}
