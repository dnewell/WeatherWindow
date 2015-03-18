import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

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
			this.setLayout(null);
			this.setOpaque(false);
			this.setBackground(new Color(255,255,255,100));
	    	this.setSize(new Dimension(750,200));
	  
	    	LocalWeather lw = loc.getLW();
	    	WeatherIcon wI = new WeatherIcon(lw.getWeatherID());
	    	
	    	String imageName = "";
	    	String skyCondition = lw.getSkyCondition().toLowerCase();
	    	
	    	if(skyCondition.equals("sky is clear"))
	    		imageName = "Sunny_128x128.png";
	    	else if(skyCondition.equals("few clouds"))
	    		imageName = "Sunny_Cloud_128x128.png";
	    	else if(skyCondition.equals("scattered clouds"))
	    		imageName = "Cloudy_128x128.png";
	    	else if(skyCondition.equals("broken clouds"))
	    		imageName = "Cloudy_128x128.png";
	    	else if(skyCondition.equals("shower rain"))
	    		imageName = "LightRain_128x128.png";
	    	else if(skyCondition.equals("rain"))
	    		imageName = "LightRain_128x128.png";
	    	else if(skyCondition.equals("thunderstorm"))
	    		imageName = "Thunder_128x128.png";
	    	else if(skyCondition.equals("snow"))
	    		imageName = "Snow_128x128.png";
	    	else if(skyCondition.equals("mist"))
	    		imageName = "Cloudy_128x128.png";
	    	
	    	URL url = getClass().getResource(imageName);
	    	
	    	JLabel timeInfoLabel = new JLabel();
			addLabel(timeInfoLabel, lw.getUserTime(), 25, 0, 180, 20, "Light", 15);
	    	
			JLabel lastupdateInfoLabel = new JLabel();
			addLabel(lastupdateInfoLabel, lw.getUpdateTime(), 205, 0, 225, 20, "Light", 15);
			
			JLabel currenttemperatureInfoLabel = new JLabel("", JLabel.CENTER);
			addLabel(currenttemperatureInfoLabel, lw.getTemperature(), -45, 20, 400, 150, "Light", 145);
			
			JLabel skyconditionInfoLabel = new JLabel("", JLabel.CENTER);
			addLabel(skyconditionInfoLabel, lw.getSkyCondition(), 315, 173, 150, 25, "Light", 20);
			
			JLabel skyconditionImageLabel = new JLabel();
			skyconditionImageLabel.setHorizontalAlignment(JLabel.CENTER);
			skyconditionImageLabel.setVerticalAlignment(JLabel.BOTTOM);
			addLabel(skyconditionImageLabel, wI.getWeatherIcon(), 305, 50, 150, 150, "Sky", 60);
			
			JLabel mintempInfoLabel = new JLabel();
			addLabel(mintempInfoLabel, lw.getMinTemp(), 35, 170, 125, 25, "Light", 20);
			
			JLabel maxtempInfoLabel = new JLabel();
			addLabel(maxtempInfoLabel, lw.getMaxTemp(), 170, 170, 125, 25, "Light", 20);
			
			JLabel sunriseInfoLabel = new JLabel();
			addLabel(sunriseInfoLabel, lw.getSunrise(), 535, 35, 200, 25, "Light", 18);

			JLabel sunsetTitleLabel = new JLabel();
			addLabel(sunsetTitleLabel, lw.getSunset(), 535, 70, 200, 25, "Light", 18);
			
			JLabel humidityTitleLabel = new JLabel();
			addLabel(humidityTitleLabel, lw.getHumidity(), 535, 105, 200, 25, "Light", 18);
			
			JLabel airpressureInfoLabel = new JLabel();
			addLabel(airpressureInfoLabel, lw.getPressure(), 535, 140, 200, 25, "Light", 18);
			
			JLabel windInfoLabel = new JLabel();
			addLabel(windInfoLabel, lw.getWindSpeed()+" "+lw.getWindDirection(), 535, 174, 175, 25, "Light", 18);
			
		}
		
		/**
		 * Adds a label to the panel with current weather information 
		 * @param text the weather information for the label
		 */
		private void addLabel(JLabel label, String text, int x, int y, int width, int height, String style, int fontSize){
			Font newFont = new MakeFont(style).create();
			label.setText(text);
			label.setFont(newFont.deriveFont((float)fontSize));
			label.setForeground(Color.WHITE);
			label.setBounds(x,y,width,height);
			this.add(label);
			this.validate();
			this.repaint();
		
		}	
}