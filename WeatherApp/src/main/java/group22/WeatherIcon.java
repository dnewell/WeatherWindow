
/**
 * Takes an id code integer from OWM and looks up the corresponding 
 * hex number to form a new unicode value.  That unicode value is
 * associated with a weather image from the owfont-regular.otf font pack.
 * 
 * @author Karsten Babin
 *
 */

public class WeatherIcon {

	private String weatherBackground;
	private String content;

	/**
	 * Constructs a WeatherIcon object
	 * @param weatherCode a integer id value from OWM of the current sky condition
	 */
	public WeatherIcon(int weatherCode) {
	
		switch (weatherCode) {

		// THUNDERSTORM
		// Thunderstorm with Light Rain
		case 200:
			content = "EB28";
			weatherBackground = "LightThunderstormDay.png";
			break;

		// Thunderstorm with Rain
		case 201:
			content = "EB29";
			weatherBackground = "ThunderstormDay.png";
			break;

		// Thunderstorm with Heavy Rain
		case 202:
			content = "EB2A";
			weatherBackground = "HeavyThunderstormDay.png";
			break;

		// Light Thunderstorm
		case 210:
			content = "EB32";
			weatherBackground = "LightThunderstormDay.png";
			break;

		// Thunderstorm
		case 211:
			content = "EB33";
			weatherBackground = "ThunderstormDay.png";
			break;

		// Heavy Thunderstorm
		case 212:
			content = "EB34";
			weatherBackground = "HeavyThunderstormDay.png";
			break;

		// Ragged Thunderstorm
		case 221:
			content = "EB3D";
			weatherBackground = "ThunderstormDay.png";
			break;

		// Thunderstorm with Light Drizzle
		case 230:
			content = "EB46";
			weatherBackground = "LightThunderstormDay.png";
			break;

		// Thunderstorm with Drizzle
		case 231:
			content = "EB47";
			weatherBackground = "ThunderstormDay.png";
			break;

		// Thunderstorm with Heavy Drizzle
		case 232:
			content = "EB48";
			weatherBackground = "HeavyThunderstormDay.png";
			break;

		// DRIZZLE

		// Light Intensity Drizzle
		case 300:
			content = "EB8C";
			weatherBackground = "LightRainDay.png";
			break;

		// Drizzle
		case 301:
			content = "EB8D";
			weatherBackground = "RainDay.png";
			break;

		// Heavy Intensity Drizzle
		case 302:
			content = "EB8E";
			weatherBackground = "HeavyRainDay.png";
			break;

		// Light Intensity Drizzle Rain
		case 310:
			content = "EB96";
			weatherBackground = "LightRainDay.png";
			break;

		// Drizzle Rain
		case 311:
			content = "EB97";
			weatherBackground = "RainDay.png";
			break;

		// Heavy Intensity Drizzle Rain
		case 312:
			content = "EB98";
			weatherBackground = "HeavyRainDay.png";
			break;

		// Shower Rain and Drizzle
		case 313:
			content = "EB99";
			weatherBackground = "RainDay.png";
			break;

		// Heavy Shower Rain and Drizzle
		case 314:
			content = "EB9A";
			weatherBackground = "HeavyRainDay.png";
			break;

		// Shower Drizzle
		case 321:
			content = "EBA1";
			weatherBackground = "LightRainDay.png";
			break;

		// RAIN

		// Light Rain
		case 500:
			content = "EC54";
			weatherBackground = "LightRainDay.png";
			break;

		// Moderate Rain
		case 501:
			content = "EC55";
			weatherBackground = "RainDay.png";
			break;

		// Heavy Intensity Rain
		case 502:
			content = "EC56";
			weatherBackground = "HeavyRainDay.png";
			break;

		// Very Heavy Rain
		case 503:
			content = "EC57";
			weatherBackground = "HeavyRainDay.png";
			break;

		// Extreme Rain
		case 504:
			content = "EC58";
			weatherBackground = "HeavyRainDay.png";
			break;

		// Freezing Rain
		case 511:
			content = "EC5F";
			weatherBackground = "FreezingRainDay.png";
			break;

		// Light Intensity Shower Rain
		case 520:
			content = "EC68";
			weatherBackground = "LightRainDay.png";
			break;

		// Shower Rain
		case 521:
			content = "EC69";
			weatherBackground = "RainDay.png";
			break;

		// Heavy Intensity Shower Rain
		case 522:
			content = "EC6A";
			weatherBackground = "HeavyRainDay.png";
			break;

		// Ragged Shower Rain
		case 531:
			content = "EC73";
			weatherBackground = "RainDay.png";
			break;

		// SNOW

		// Light Snow
		case 600:
			content = "ECB8";
			weatherBackground = "LightSnowDay.png";
			break;

		// Snow
		case 601:
			content = "ECB9";
			weatherBackground = "SnowDay.png";
			break;

		// Heavy Snow
		case 602:
			content = "ECBA";
			weatherBackground = "HeavySnowDay.png";
			break;

		// Sleet
		case 611:
			content = "ECC3";
			weatherBackground = "Sleet.png";
			break;

		// Shower Sleet
		case 612:
			content = "ECC4";
			weatherBackground = "Sleet.png";
			break;

		// Light Rain and Snow
		case 615:
			content = "ECC7";
			weatherBackground = "Sleet.png";
			break;

		// Rain and Snow
		case 616:
			content = "ECC8";
			weatherBackground = "Sleet.png";
			break;

		// Light Shower Snow
		case 620:
			content = "ECCC";
			weatherBackground = "Sleet.png";
			break;

		// Shower Snow
		case 621:
			content = "ECCD";
			weatherBackground = "SnowDay.png";
			break;

		// Heavy Shower Snow
		case 622:
			content = "ECCE";
			weatherBackground = "HeavySnowDay.png";
			break;

		// Atmosphere

		// Mist
		case 701:
			content = "ED1D";
			weatherBackground = "MistDay.png";
			break;

		// Smoke
		case 711:
			content = "ED27";
			weatherBackground = "Smoke.png";
			break;

		// Haze
		case 721:
			content = "ED31";
			weatherBackground = "Haze.png";
			break;

		// Sand/Dust Whirls
		case 731:
			content = "ED3B";
			weatherBackground = "DustWhirlwind.png";
			break;

		// Fog
		case 741:
			content = "ED45";
			weatherBackground = "Fog.png";
			break;

		// Sand
		case 751:
			content = "ED4F";
			weatherBackground = "Sandstorm.png";
			break;

		// Dust
		case 761:
			content = "ED59";
			weatherBackground = "Dust.png";
			break;

		// Volcanic Ash
		case 762:
			content = "ED5A";
			weatherBackground = "VolcanicAsh.png";
			break;

		// Squalls
		case 771:
			content = "ED63";
			weatherBackground = "WindSquallDay.png";
			break;

		// Tornado
		case 781:
			content = "ED6D";
			weatherBackground = "Tornado.png";
			break;

		// CLOUDS

		// Sky is clear -DAY
		case 800:
			content = "ED80";
			weatherBackground = "ClearSkyDay.png";
			break;

		// Calm
		case 951:
			content = "ED80";
			weatherBackground = "ClearSkyDay.png";
			break;

		// Few Clouds
		case 801:
			content = "ED81";
			weatherBackground = "FewCloudsDay.png";
			break;

		// Scattered Clouds
		case 802:
			content = "ED82";
			weatherBackground = "ScatteredCloudsDay.png";
			break;

		// Broken Clouds
		case 803:
			content = "ED83";
			weatherBackground = "BrokenCloudsDay.png";
			break;

		// Overcast Clouds
		case 804:
			content = "ED84";
			weatherBackground = "OvercastDay.png";
			break;

		// EXTREME

		// Tornado
		case 900:
			content = "EDE4";
			weatherBackground = "Tornado.png";
			break;

		// Tropical Storm
		case 901:
			content = "EDE5";
			weatherBackground = "TropicalStormDay.png";
			break;

		// Hurricane
		case 902:
			content = "EDE6";
			weatherBackground = "HurricaneDay.png";
			break;

		// Cold
		case 903:
			content = "EDE7";
			weatherBackground = "ColdDay.png";
			break;

		// Hot
		case 904:
			content = "EDE8";
			weatherBackground = "HotDay.png";
			break;

		// Windy
		case 905:
			content = "EDE9";
			weatherBackground = "WindyDay.png";
			break;

		// Hail
		case 906:
			content = "EDEA";
			weatherBackground = "HailDay.png";
			break;

		// ADDITIONAL

		// Setting
		case 950:
			content = "EE16";
			break;

		// Light Breeze
		case 952:
			content = "EE18";
			break;

		// Gentle Breeze
		case 953:
			content = "EE19";
			break;

		// Moderate breeze
		case 954:
			content = "EE1A";
			break;

		// Fresh Breeze
		case 955:
			content = "EE1B";
			break;

		// Strong Breeze
		case 956:
			content = "EE1C";
			break;

		// High Wind, near Gale
		case 957:
			content = "EE1D";
			break;

		// Gale
		case 958:
			content = "EE1E";
			break;

		// Severe Gale
		case 959:
			content = "EE1F";
			break;

		// Storm
		case 960:
			content = "EE20";
			break;

		// Violent Storm
		case 961:
			content = "EE21";
			break;

		// Hurricane
		case 962:
			content = "EE22";
			break;
		
		}
		
	}
	
	/**
	 * Get a content string as a parameter to form a unicode
	 * @param weatherCode A string representing a content code 
	 */
	public WeatherIcon (String weatherCode){
		content = weatherCode;
	}

	/**
	 * A getter method that takes content string and forms
	 * a useful unicode value to get a weather icon image
	 * @return A string representing a unicode value 
	 */
	public String getWeatherIcon() {

		// Create a custom unicode for the sky icon using the content value
		content = "\\u" + content;
		int i = 0; 
		int contentLength = content.length();
		char c;
		StringBuffer sb = new StringBuffer(contentLength);
		while (i < contentLength) {
			c = content.charAt(i++);
			if (c == '\\') {
				if (i < contentLength) {
					c = content.charAt(i++);
					if (c == 'u') {
						c = (char) Integer.parseInt(
								content.substring(i, i + 4), 16);
						i += 4;
					}
				}
			}
			sb.append(c);
		}
		
		// Return the brand new unicode
		return sb.toString();

	}
	
	public String getWeatherBackground(){
		return weatherBackground;
	}

}
