import java.awt.Font;
import java.io.InputStream;

public class MakeFont {

	private String style;
	private InputStream fontIS;
	
	public MakeFont(String style){
		this.style = style;
	}
	
	public Font create(){
		
		Font newFont = null;
		
		if (style == "Light")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-Light.otf");
		else if (style == "Medium")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-Medium.otf");
		else if (style == "Bold")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-Bold.otf");
		else if (style == "UltraLight")
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-UltraLight.otf");
		else
			fontIS = this.getClass().getResourceAsStream("HelveticaNeue-UltraLight.otf");
		
		try {
			newFont = Font.createFont(Font.TRUETYPE_FONT, fontIS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
						
		return newFont;
	}
	
}
