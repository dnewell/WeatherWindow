import java.awt.Font;

/**
 * Takes an id code integer from OWM and looks up the corresponding 
 * hex number to form a new unicode value.  That unicode value is
 * associated with a weather image from the owfont-regular.otf font pack.
 * 
 * @author Karsten Babin
 *
 */

public class WeatherIcon {

	private int weatherCode;

	/**
	 * Constructs a WeatherIcon object
	 * @param weatherCode a integer id value from OWM of the current sky condition
	 */
	public WeatherIcon(int weatherCode) {
		this.weatherCode = weatherCode;
	}

	/**
	 * A getter method that takes content string and forms
	 * a useful unicode value to get a weather icon image
	 * @return A string representing a unicode value 
	 */
	public String getWeatherIcon() {

		String content = "";

		switch (weatherCode) {

		// THUNDERSTORM
		// Thunderstorm with Light Rain
		case 200:
			content = "EB28";
			break;

		// Thunderstorm with Rain
		case 201:
			content = "EB29";
			break;

		// Thunderstorm with Heavy Rain
		case 202:
			content = "EB2A";
			break;

		// Light Thunderstorm
		case 210:
			content = "EB32";
			break;

		// Thunderstorm
		case 211:
			content = "EB33";
			break;

		// Heavy Thunderstorm
		case 212:
			content = "EB34";
			break;

		// Ragged Thunderstorm
		case 221:
			content = "EB3D";
			break;

		// Thunderstorm with Light Drizzle
		case 230:
			content = "EB46";
			break;

		// Thunderstorm with Drizzle
		case 231:
			content = "EB47";
			break;

		// Thunderstorm with Heavy Drizzle
		case 232:
			content = "EB48";
			break;

		// DRIZZLE

		// Light Intensity Drizzle
		case 300:
			content = "EB8C";
			break;

		// Drizzle
		case 301:
			content = "EB8D";
			break;

		// Heavy Intensity Drizzle
		case 302:
			content = "EB8E";
			break;

		// Light Intensity Drizzle Rain
		case 310:
			content = "EB96";
			break;

		// Drizzle Rain
		case 311:
			content = "EB97";
			break;

		// Heavy Intensity Drizzle Rain
		case 312:
			content = "EB98";
			break;

		// Shower Rain and Drizzle
		case 313:
			content = "EB99";
			break;

		// Heavy Shower Rain and Drizzle
		case 314:
			content = "EB9A";
			break;

		// Shower Drizzle
		case 321:
			content = "EBA1";
			break;

		// RAIN

		// Light Rain
		case 500:
			content = "EC54";
			break;

		// Moderate Rain
		case 501:
			content = "EC55";
			break;

		// Heavy Intensity Rain
		case 502:
			content = "EC56";
			break;

		// Very Heavy Rain
		case 503:
			content = "EC57";
			break;

		// Extreme Rain
		case 504:
			content = "EC58";
			break;

		// Freezing Rain
		case 511:
			content = "EC5F";
			break;

		// Light Intensity Shower Rain
		case 520:
			content = "EC68";
			break;

		// Shower Rain
		case 521:
			content = "EC69";
			break;

		// Heavy Intensity Shower Rain
		case 522:
			content = "EC6A";
			break;

		// Ragged Shower Rain
		case 531:
			content = "EC73";
			break;

		// SNOW

		// Light Snow
		case 600:
			content = "ECB8";
			break;

		// Snow
		case 601:
			content = "ECB9";
			break;

		// Heavy Snow
		case 602:
			content = "ECBA";
			break;

		// Sleet
		case 611:
			content = "ECC3";
			break;

		// Shower Sleet
		case 612:
			content = "ECC4";
			break;

		// Light Rain and Snow
		case 615:
			content = "ECC7";
			break;

		// Rain and Snow
		case 616:
			content = "ECC8";
			break;

		// Light Shower Snow
		case 620:
			content = "ECCC";
			break;

		// Shower Snow
		case 621:
			content = "ECCD";
			break;

		// Heavy Shower Snow
		case 622:
			content = "ECCE";
			break;

		// Atmosphere

		// Mist
		case 701:
			content = "ED1D";
			break;

		// Smoke
		case 711:
			content = "ED27";
			break;

		// Haze
		case 721:
			content = "ED31";
			break;

		// Sand/Dust Whirls
		case 731:
			content = "ED3B";
			break;

		// Fog
		case 741:
			content = "ED45";
			break;

		// Sand
		case 751:
			content = "ED4F";
			break;

		// Dust
		case 761:
			content = "ED59";
			break;

		// Volcanic Ash
		case 762:
			content = "ED5A";
			break;

		// Squalls
		case 771:
			content = "ED63";
			break;

		// Tornado
		case 781:
			content = "ED6D";
			break;

		// CLOUDS

		// Sky is clear -DAY
		case 800:
			content = "ED80";
			break;

		// Calm
		case 951:
			content = "ED80";
			break;

		// Few Clouds
		case 801:
			content = "ED81";
			break;

		// Scattered Clouds
		case 802:
			content = "ED82";
			break;

		// Broken Clouds
		case 803:
			content = "ED83";
			break;

		// Overcast Clouds
		case 804:
			content = "ED84";
			break;

		// EXTREME

		// Tornado
		case 900:
			content = "EDE4";
			break;

		// Tropical Storm
		case 901:
			content = "EDE5";
			break;

		// Hurricane
		case 902:
			content = "EDE6";
			break;

		// Cold
		case 903:
			content = "EDE7";
			break;

		// Hot
		case 904:
			content = "EDE8";
			break;

		// Windy
		case 905:
			content = "EDE9";
			break;

		// Hail
		case 906:
			content = "EDEA";
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

}
