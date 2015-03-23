import java.awt.Font;
import java.io.InputStream;

public class MakeFont {

	private Font newFont;
	
	public MakeFont(String style){
		
		InputStream fontIS;
		
		// Check what 'style' is set and choose the appropriate font 
		if (style == "Light")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-Light.otf");
		else if (style == "Medium")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-Medium.otf");
		else if (style == "Bold")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-Bold.otf");
		else if (style == "UltraLight")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-UltraLight.otf");
		else if (style == "Owfont")
			fontIS = this.getClass().getResourceAsStream("owfont-regular.otf");
		else if (style == "WeatherIcons")
			fontIS = this.getClass().getResourceAsStream("WeatherIcons-Regular.otf");
		else if (style == "FontAwesome")
			fontIS = this.getClass().getResourceAsStream("FontAwesome.otf");
		else 
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-UltraLight.otf");
		
		try {
			newFont = Font.createFont(Font.TRUETYPE_FONT, fontIS);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * Creates a new font using the global variable 'style'
	 * @return a new font 
	 */
	public Font getFont(){
		return newFont;
	}
	
}
