
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;


/**
 * This class displays the Short Term Forecast, by creating/containing an array of ThreeHourPanel objects
 * 
 * @author David Newell
 * @author David Langford
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
 * Populates the panel with ThreeHourPanels objects
 * 
 * @throws Exception 
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