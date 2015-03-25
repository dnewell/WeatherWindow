import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.net.URL;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The MarsWeatherPanel class displays the current  Mars weather information
 * 
 * @author David Newell
 * @author David Langford
 */
public class MarsWeatherPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		/**
		 * Constructs the panel
		 * @param loc a requested location (e.g city, planet (Mars)) for weather information
		 * @throws Exception if an error occurs
		 */
		public MarsWeatherPanel(Location loc) throws Exception {	
			initPanel(loc);
		}

		/**
		 * Initializes the panel, and sets its display attributes
		 * @param loc a requested location (e.g city, planet (Mars)) for weather information
		 * @throws Exception if an error occurs
		 */
		private void initPanel(Location loc) throws Exception {
			
			// Sets properties for the LocalWeatherPanel
			this.setLayout(null);
			this.setOpaque(false);
			this.setBackground(new Color(255,255,255,100));
	    	this.setSize(new Dimension(750,200));
    	
	    	// Access the MarsWeather weather information
	    	MarsWeather mw = loc.getMD();
	   
		    // Create the JLabels necessary to display weather information	
	    	JLabel timeInfoLabel = new JLabel();

			JLabel currenttemperatureInfoLabel = new JLabel("", JLabel.CENTER);
			JLabel skyconditionInfoLabel = new JLabel("", JLabel.CENTER);

			JLabel humidityTitleLabel = new JLabel();
			JLabel airpressureInfoLabel = new JLabel();
			JLabel windInfoLabel = new JLabel();


			
			// Set the weather for Mars
				addLabel(timeInfoLabel, mw.getdate(), 25, 0, 280, 20, "Light", 15);	    	
				//addLabel(lastupdateInfoLabel, mw.getUpdateTime(), 205, 0, 225, 20, "Light", 15);	
				if (loc.units == 0)
					addLabel(currenttemperatureInfoLabel, mw.getTemperature(), -45, 20, 400, 150, "Light", 145);	
				else 
					addLabel(currenttemperatureInfoLabel, mw.getFtemp(), -45, 20, 400, 150, "Light", 145);
				addLabel(skyconditionInfoLabel, mw.getSkyCondition(), 25, 173, 250, 25, "Light", 20);			
				//addLabel(mintempInfoLabel, mw.getMinTemp(), 35, 170, 125, 25, "Light", 20);			
				//addLabel(maxtempInfoLabel, mw.getMaxTemp(), 170, 170, 125, 25, "Light", 20);			
				//addLabel(sunriseInfoLabel, mw.getSunrise(), 535, 35, 200, 25, "Light", 18);
				//addLabel(sunsetTitleLabel, mw.getSunset(), 535, 70, 200, 25, "Light", 18);			
				addLabel(humidityTitleLabel, mw.getHumidity(), 335, 105, 350, 25, "Light", 18);			
				addLabel(airpressureInfoLabel, mw.getPressure(), 335, 140, 350, 25, "Light", 18);			
				addLabel(windInfoLabel, mw.getWindSpeed()+" "+mw.getWindDirection(), 335, 174, 350, 25, "Light", 18);
		
		}
		
		/**
		 * Adds a label to the panel with current mars weather information 
		 * @param text the weather information for the label
		 */
		private void addLabel(JLabel label, String text, int x, int y, int width, int height, String style, int fontSize){
			Font newFont = new MakeFont(style).getFont();
			label.setText(text);
			label.setFont(newFont.deriveFont((float)fontSize));
			label.setForeground(Color.WHITE);
			label.setBounds(x,y,width,height);
			this.add(label);
			this.validate();
			this.repaint();
		
		}	
}