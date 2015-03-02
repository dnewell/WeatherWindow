
	import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

	public class Header extends JPanel {

		private static final long serialVersionUID = 1L;


		public Header(Location loc) {	
			initPanel(loc.getLocation());		
		}

		private void initPanel(String loc) {		
			
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.setOpaque(false);
			
	    	this.setSize(new Dimension(500,300));
	    	
			addLabel(loc.substring(0, 1).toUpperCase() + loc.substring(1));


		}

		/**
		 * Adds the label to the JPanel
		 */
		private void addLabel(String text) {
			JLabel label = new JLabel(text);	
			Font newFont = GUI.font.deriveFont(44f);
	    	label.setFont(newFont);
	    	label.setForeground(Color.WHITE);   	
	    	this.add(label);
	    	this.validate();
	    	this.repaint();
		}	
		
		
	}
	
	
	