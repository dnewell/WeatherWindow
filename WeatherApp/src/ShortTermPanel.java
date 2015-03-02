

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.json.*;


/**
 * This class creates/contains ThreeHourPanels
 * @author David Newell
 *
 */
public class ShortTermPanel extends JPanel {

	private static final long serialVersionUID = -597607470838407704L;

	public ShortTermPanel(Location loc) throws Exception {		
		initPanel(loc);
	}

	private void initPanel(Location loc) throws Exception {
		
		this.setBackground(new Color(0,0,255,20));
		this.setLayout(null);
    	this.setSize(new Dimension(400,700));
    	
		addPeriods(loc);

	}


/**
 * For testing - just populating the panel with ThreeHourPanels objects, created with that classes no arg constructor.
 * 
 * Once parser code is in, populate from LOCATION.getSTF().getThreeHourArray()[0 - 8].getWHATEVER());
 * @throws Exception 
 */
	private void addPeriods(Location loc) throws Exception {

		ThreeHourPanel[] panels = new ThreeHourPanel[8];
		Location.cal.setTime(Location.now);
		
		for(int i = 0; i < 8; i++){
	    	panels[i] = new ThreeHourPanel(loc.getSTF().getThreeHourArray()[i]);//LOCATION..getWHATEVER())
		}
		
		loc.NewDay = false;
		
		int vertPosition = 5;
		for(int i = 0; i < 8; i++){
			// Sets spacing, uses a 5 pixel wide border on all sides of each ThreeHourPanel.
	    	panels[i].setLocation(5, (5+(((vertPosition+80)*i))));
	    	this.add(panels[i]);	
		}
	}
	

}