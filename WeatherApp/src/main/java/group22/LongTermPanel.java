import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

/**
 * This class displays the Long Term Forecast, by creating/containing an array of DayPanel objects
 * 
 * @author David Newell
 */
public class LongTermPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public LongTermPanel(Location loc) throws Exception {
		
		initPanel(loc);		
	}

	/**
	 * Initializes and sets display attributes for the panel
	 * @param loc the location e.g city or planet
	 * @throws Exception when an error occurs
	 */
	private void initPanel(Location loc) throws Exception {
	
		GridLayout layout = new GridLayout(1,0);
		this.setOpaque(false);
		this.setLayout(layout);
		this.setSize(new Dimension(730,270));
		
    	addDayPanels(loc);

	}

	/**
	 * Adds Day objects to the day panel
	 * @param loc a location e.g city or planet
	 * @throws Exception when an error occurs
	 */
	private void addDayPanels(Location loc) throws Exception {
		if (!loc.getLocation().toLowerCase().equals("mars"))
		{		
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
	
}
