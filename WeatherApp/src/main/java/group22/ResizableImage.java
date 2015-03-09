import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * ImageButton class uses JLabel to implement buttons with dynamically sized images
 * It is transparent, and border free.
 * 
 * Created to ease the process of resizing images, eliminates the need to
 * manually size each image using an image editor
 * 
 * Created for CS2212 WeatherApp project
 * @author David Newell
 *
 */
public class ResizableImage extends JLabel {
	
	private static final long serialVersionUID = 1040314673698166571L;
	private ImageIcon icon;
	private ImageIcon resizedIcon;
	private int _desiredWidth;
	private int _desiredHeight;
	private int _imageWidthOnDisk;
	private int _imageHeightOnDisk;
	private String _fileName;


	/**
	 * Constructor sets size of button and resizes image to fit
	 * @param fileName Image file name
	 * @param width Desired button width
	 * @param height Desired button height
	 */
	public ResizableImage(String fileName, int w, int h) {
		this._fileName = fileName;
		
		setDimensionsFromDisk(_fileName);

    	this._desiredWidth = w;
    	this._desiredHeight = h;
    	
		icon = new ImageIcon(getClass().getResource(fileName));
 	
    	
   // maintain aspect ratio
    	// checks if we need to adjust dimensions
    	int newWidth, newHeight;
    	// checks if width needs to be scales
    	if (_imageWidthOnDisk > _desiredWidth){
    		newWidth = _desiredWidth;
    		// scale height to maintain proportions
    		newHeight = (int)(_imageHeightOnDisk * newWidth) / _imageWidthOnDisk; 				
    	} else{
    		System.out.println("no resize necessary");
    		newWidth = _desiredWidth;
    		newHeight = _desiredHeight;
    	}
    	
    	// checks if we still need to scale
    	if (newHeight > _desiredHeight){
    		newHeight = _desiredHeight;
    		// scale width to maintain proportions
    		newWidth = (int)(_imageWidthOnDisk * newHeight) / _imageHeightOnDisk; 				
    	} 
    	
    	
    	Image image = icon.getImage();
    	Image resizedImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
    	resizedIcon = new ImageIcon(resizedImage);
    	
    	this.setSize(_imageWidthOnDisk, _imageHeightOnDisk);
    	this.setIcon(resizedIcon);
       	this.validate();
    	this.repaint();
    	
    	
/*		
 * 	Spent about an hour trying to get this resizing method to work,
 *  since Image.getScaledInstance() method is very old, slow, low quality, and mostly deprecated.
 *  Turns out Graphics2D.drawImage() is BROKEN IN JAVA 8!!!
 *  Implemented it with Image.getScaledInstance() in about 30 seconds.  Time for a break.
 */
    	
//  	Image i = icon.getImage();
//		BufferedImage buffImage = new BufferedImage(i.getWidth(null),i.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//    	Graphics2D grObj = buffImage.createGraphics();
//    	grObj.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//    	grObj.drawImage(buffImage, 0, 0, newWidth, newHeight, null);  		
	}
		

/**
 * Constructor helper method sets
 * the dimension fields
 * @param name File Name
 */
	private void setDimensionsFromDisk(String name) {
		getClass().getResource("rain20.png");
		_imageWidthOnDisk = new ImageIcon(getClass().getResource(name)).getIconWidth();
		_imageHeightOnDisk = new ImageIcon(getClass().getResource(name)).getIconHeight();
	}


	/**
	 * Constructor, sets size of button to size of image on disk
	 * @param fileName Image file name
	 */
	public ResizableImage(String fileName) {
		icon = new ImageIcon(fileName);
		this.setSize(icon.getIconWidth(), icon.getIconHeight());
    	this.setIcon(icon);

	}

}
