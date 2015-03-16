
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;


/**
 * This class displays the Short Term Forecast, by creating/containing 
 * a collection of ThreeHourPanel objects
 * 
 * @author David Newell
 * @author David Langford
 */
public class ShortTermPanel extends JPanel {

	private static final long serialVersionUID = -597607470838407704L;

	/**
	 * Constructs the panel
	 * @param loc the location
	 * @throws Exception when an error occurs
	 */
	public ShortTermPanel(Location loc) throws Exception {		
		initPanel(loc);
	}
	/**
	 * Initializes the panel and sets its display attributes.
	 * @param loc a location such as a city or planet
	 * @throws Exception when an error occurs
	 */
	private void initPanel(Location loc) throws Exception {
		
		GridLayout layout = new GridLayout(1,0);
		this.setOpaque(false);
		this.setLayout(layout);
		this.setSize(new Dimension(730,270));
    	
    	//adds ThreeHourPanel objects with a helper method
		addPeriods(loc);

	}


	/**
	 * Populates the panel with ThreeHourPanels objects
	 * @param loc the location such as a city or planet
	 * @throws Exception when an error occurs
	 */
	@SuppressWarnings("static-access")
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