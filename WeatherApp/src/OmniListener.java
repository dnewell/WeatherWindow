import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This class implements ActionListener.
 * 
 * NOTE TO ALL: I created this very early on, while I still thought that trying to 
 * make swing use a MVC pattern was a good idea. It's not, that sucked,
 * and the code in this class will get moved into other classes.
 *
 * @author David Newell
 *
 */

public class OmniListener implements ActionListener, ListSelectionListener{
	
	private Object object;
	private String op;
	
	/**
	 * Constructor.
	 * 	Usage:		OmniListener ol = new OmniListener(this);
	 * @param obj
	 * @param operation A string, either add, remove, or list
	 */
	public OmniListener(Object obj, String operation){
		this.object = obj;
		this.op = operation;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		gLocationPicker lp = (gLocationPicker) object;
		
		if (op.equalsIgnoreCase("add") == true){
			try {
				lp.addLocationToList();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {
			lp.removeLocationFromList();
		}
		
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		gLocationPicker lp = (gLocationPicker) object;
		if (e.getValueIsAdjusting() == false){
			lp.buttonToggler();
		}
				
		
		
	}

	
}
