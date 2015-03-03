import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.json.*;
/**
 * The LocalWeatherPanel class displays the current forecast
 * 
 * @author David Newell
 * @author David Langford
 */
public class LocalWeatherPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		/**
		 * Constructs the panel
		 * @param loc the location
		 * @throws Exception
		 */
		public LocalWeatherPanel(Location loc) throws Exception {	
			initPanel(loc);
		}

		/**
		 * Initializes the panel, and sets its display attributes
		 * @param loc the location
		 * @throws Exception
		 */
		private void initPanel(Location loc) throws Exception {
			this.setLayout(new GridLayout(0,2));
			
			this.setOpaque(true);
			this.setBackground(new Color(255,255,255,0));
			
	    	this.setSize(new Dimension(500,300));
	    	LocalWeather lw = loc.getLW();
			addLabel("Now: " + lw.getTemperature());
			addLabel("Sky is: " + lw.getSkyCondition());
			addLabel(lw.getMinTemp() + " / " + lw.getMaxTemp());
			addLabel(lw.getPrecipatation() + "mm precipitation");
			addLabel("Humidity: " + lw.getHumidity() + "%");
			addLabel("Wind: " + lw.getWindSpeed() + " from " + lw.getWindDirection());
			addLabel(lw.getSunrise());
			addLabel("Pressure: " + lw.getPressure());
			addLabel(lw.getSunset());
		}
		
		private void addLabel(String text) {
			JLabel label = new JLabel(text);	
			Font newFont = GUI.font.deriveFont(20f);
	    	label.setFont(newFont);
	    	label.setForeground(Color.WHITE);
	   	
	    	this.add(label);
	    	this.validate();
	    	this.repaint();
		}	
		
}