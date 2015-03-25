import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The LocalWeatherPanel class displays the current forecast and Mars weather information
 * 
 * @author David Newell
 * @author David Langford
 */
public class LocalWeatherPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		
		/**
		 * Constructs the panel
		 * @param loc a requested location (e.g city, planet (Mars)) for weather information
		 * @throws Exception if an error occurs
		 */
		public LocalWeatherPanel(Location loc) throws Exception {	
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
	  
	    	// Access the LocalWeather weather information
	    	LocalWeather lw = null;
	    	
	    	// Access the MarsWeather weather information
	    	MarsWeather mw = null;
	    	
	    	// Check if the panel will display local or mars weather information
	    	if (!loc.getLocation().toLowerCase().equals("mars"))
	    		lw = loc.getLW();
	    	else 
	    		mw = loc.getMD();
	    	
	    	// Access the WeatherIcon information
	    	WeatherIcon wI = new WeatherIcon(lw.getWeatherID());
	    	
		    // Create the JLabels necessary to display weather information	
	    	JLabel timeInfoLabel = new JLabel();
			JLabel lastupdateInfoLabel = new JLabel();
			JLabel currenttemperatureInfoLabel = new JLabel("", JLabel.CENTER);
			JLabel skyconditionInfoLabel = new JLabel("", JLabel.CENTER);
			JLabel mintempInfoLabel = new JLabel();
			JLabel maxtempInfoLabel = new JLabel();
			JLabel sunriseInfoLabel = new JLabel();
			JLabel sunsetTitleLabel = new JLabel();
			JLabel humidityTitleLabel = new JLabel();
			JLabel airpressureInfoLabel = new JLabel();
			JLabel windInfoLabel = new JLabel();
			JLabel sunriseIconLabel = new JLabel();
			JLabel sunsetIconLabel = new JLabel();
			JLabel humidityIconLabel = new JLabel();
			JLabel airpressureIconLabel = new JLabel();
			JLabel windIconLabel = new JLabel();
			
			// Set the weather for the local view
				
			// Add the text based weather information
			addLabel(timeInfoLabel, lw.getUserTime(), 25, 0, 180, 20, "Light", 15);	    	
			addLabel(lastupdateInfoLabel, lw.getUpdateTime(), 205, 0, 225, 20, "Light", 15);			
			addLabel(currenttemperatureInfoLabel, lw.getTemperature(), -45, 20, 400, 150, "Light", 145);			
			addLabel(skyconditionInfoLabel, lw.getSkyCondition(), 307, 173, 150, 25, "Light", 15);			
			addLabel(mintempInfoLabel, lw.getMinTemp(), 35, 170, 125, 25, "Light", 20);			
			addLabel(maxtempInfoLabel, lw.getMaxTemp(), 170, 170, 125, 25, "Light", 20);			
			
			// Add icons to their matching labels
			addLabel(sunriseIconLabel, new WeatherIcon("f051").getWeatherIcon(), 500, 33, 25, 25, "WeatherIcons", 10);
			sunriseIconLabel.setHorizontalAlignment(JLabel.CENTER);
			sunriseIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(sunriseInfoLabel, lw.getSunrise(), 535, 35, 200, 25, "Light", 18);
			
			addLabel(sunsetIconLabel, new WeatherIcon("f052").getWeatherIcon(), 500, 68, 25, 25, "WeatherIcons", 10);
			sunsetIconLabel.setHorizontalAlignment(JLabel.CENTER);
			sunsetIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(sunsetTitleLabel, lw.getSunset(), 535, 70, 200, 25, "Light", 18);			
			
			addLabel(humidityIconLabel, new WeatherIcon("f04e").getWeatherIcon(), 500, 107, 25, 25, "WeatherIcons", 14);
			humidityIconLabel.setHorizontalAlignment(JLabel.CENTER);
			humidityIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(humidityTitleLabel, lw.getHumidity(), 535, 105, 200, 25, "Light", 18);			
			
			addLabel(airpressureIconLabel, new WeatherIcon("f053").getWeatherIcon(), 500, 138, 25, 25, "WeatherIcons", 10);
			airpressureIconLabel.setHorizontalAlignment(JLabel.CENTER);
			airpressureIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(airpressureInfoLabel, lw.getPressure(), 535, 140, 200, 25, "Light", 18);			
			
			addLabel(windIconLabel, new WeatherIcon("f050").getWeatherIcon(), 500, 172, 25, 25, "WeatherIcons", 10);
			windIconLabel.setHorizontalAlignment(JLabel.CENTER);
			windIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(windInfoLabel, lw.getWindSpeed()+" "+lw.getWindDirection(), 535, 174, 175, 25, "Light", 18);
			
			JLabel skyconditionImageLabel = new JLabel();
			skyconditionImageLabel.setHorizontalAlignment(JLabel.CENTER);
			skyconditionImageLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(skyconditionImageLabel, wI.getWeatherIcon(), 305, 35, 150, 150, "Owfont", 60);


		}
		
		/**
		 * Adds a label to the panel with current weather information 
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