import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The MarsWeatherPanel class displays the current  Mars weather information
 * 
 * @author David Newell
 * @author David Langford
 * @author Karsten Babin
 */
public class MarsWeatherPanel extends JPanel {

		private static final long serialVersionUID = 1L;
		JLabel humidityIconLabel = new JLabel();
		JLabel airpressureIconLabel = new JLabel();
		JLabel windIconLabel = new JLabel();
		
		
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
			
			addLabel(humidityIconLabel, new WeatherIcon("f04e").getWeatherIcon(), 500, 50, 25, 25, "WeatherIcons", 14);
			humidityIconLabel.setHorizontalAlignment(JLabel.CENTER);
			humidityIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(humidityTitleLabel, mw.getHumidity(), 435, 40, 300, 25, "Light", 18);			
			
			addLabel(airpressureIconLabel, new WeatherIcon("f053").getWeatherIcon(), 500, 100, 25, 25, "WeatherIcons", 10);
			airpressureIconLabel.setHorizontalAlignment(JLabel.CENTER);
			airpressureIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(airpressureInfoLabel, mw.getPressure(), 435, 78, 200, 25, "Light", 18);			
			
			addLabel(windIconLabel, new WeatherIcon("f050").getWeatherIcon(), 500, 150, 25, 25, "WeatherIcons", 10);
			windIconLabel.setHorizontalAlignment(JLabel.CENTER);
			windIconLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(windInfoLabel, mw.getWindSpeed()+" "+mw.getWindDirection(), 435, 114, 375, 25, "Light", 18);
	    	   	
	    	// Access the WeatherIcon information
	    	WeatherIcon wI = new WeatherIcon(mw.getWeatherID());

			
			// Set the weather for Mars
				addLabel(timeInfoLabel, mw.getdate(), 25, 0, 280, 20, "Light", 15);	    	
				//addLabel(lastupdateInfoLabel, mw.getUpdateTime(), 205, 0, 225, 20, "Light", 15);	
				if (loc.units == 0)
					addLabel(currenttemperatureInfoLabel, mw.getTemperature(), -45, 20, 400, 150, "Light", 145);	
				else 
					addLabel(currenttemperatureInfoLabel, mw.getFtemp(), -45, 20, 400, 150, "Light", 145);
								
				JLabel skyconditionImageLabel = new JLabel();
				skyconditionImageLabel.setHorizontalAlignment(JLabel.CENTER);
				skyconditionImageLabel.setVerticalAlignment(JLabel.BOTTOM);
				addLabel(skyconditionImageLabel, wI.getWeatherIcon(), 295, 35, 150, 150, "Owfont", 60);
				addLabel(skyconditionInfoLabel, mw.getSkyCondition(), 248, 173, 250, 25, "Light", 15);			
				//addLabel(mintempInfoLabel, mw.getMinTemp(), 35, 170, 125, 25, "Light", 20);			
				//addLabel(maxtempInfoLabel, mw.getMaxTemp(), 170, 170, 125, 25, "Light", 20);			
				//addLabel(sunriseInfoLabel, mw.getSunrise(), 535, 35, 200, 25, "Light", 18);
				//addLabel(sunsetTitleLabel, mw.getSunset(), 535, 70, 200, 25, "Light", 18);			
				addLabel(humidityTitleLabel, mw.getHumidity(), 535, 47, 350, 25, "Light", 14);			
				addLabel(airpressureInfoLabel, mw.getPressure(), 535, 102, 350, 25, "Light", 14);			
				addLabel(windInfoLabel, mw.getWindSpeed()+" "+mw.getWindDirection(), 535, 153, 350, 25, "Light", 14);
	

		
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