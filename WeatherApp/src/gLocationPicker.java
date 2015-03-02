import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;


/**
 * gLocationPicker class implements a graphical location picker,
 * It is hide-able, style-able, pop-down, transparency enabled, etc.
 * 
 * TODO: Style buttons, and text field.
 * TODO: Decide on button states, and adjust the ListSelectionListener/ActionListeners appropriately.
 * @author David Newell
 *
 */
public class gLocationPicker extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7204468883163349438L;
	@SuppressWarnings("rawtypes")
	private JList locationList;
	@SuppressWarnings("rawtypes")
	private DefaultListModel lModel;
	private JButton addLocationButton;
	private JButton removeLocationButton;
	private JTextField locationName;
	private MyLocations myLocations;
	
	public gLocationPicker(){
		this.myLocations = new MyLocations();
		init();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void init() {
		
		this.setLayout(new BorderLayout());
		// allows transparency
		this.setOpaque(false);
	
/* this makes the JFrame's background 100% transparent. The color for the list is set elsewhere, for the whole JFrame.
 * this line is not strictly neccessay, but it makes it clearer where the color is coming from! */
		this.setBackground(new Color(0,0,0,0));
    	this.setSize(new Dimension(390,300));
    	
 	
    	lModel = myLocations.getLocationList();	
    	locationList = new JList(lModel);
    	

	
/* This sets the JList background color, which I use as the background for
 * the list as a whole.  Could also make this background invisible, and control
 * the color with the JFrame background.  It has different side effects, either way.*/
    	locationList.setBackground(new Color(0,0,255,80));
    	// List font color set here
    	locationList.setForeground(Color.WHITE);
    	// set so the user can only pick one location at a time
    	locationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    	locationList.setSelectedIndex(0);  	
    	// Default list size
    	locationList.setVisibleRowCount(5);
   	
// this listener watches the GUI list, and disables the remove button if there is no selection
//    	OmniListener listListener = new OmniListener(this, "list");	
//    	locationList.addListSelectionListener(listListener);

    	
    	JScrollPane scrollPane = new JScrollPane();    	
    	addLocationButton = new JButton("Add Location");
    	OmniListener addLocationListener = new OmniListener(this, "add");	
    	addLocationButton.addActionListener(addLocationListener);
    	
    	
    	JButton removeLocationButton = new JButton("Remove Location");
    	OmniListener removeLocationListener = new OmniListener(this, "remove");	
    	removeLocationButton.addActionListener(removeLocationListener);
    	JPanel bottomPane = new JPanel();
    	
    	
    	// sets layout manager for a single row at bottom of window
    	bottomPane.setLayout(new BoxLayout(bottomPane,BoxLayout.LINE_AXIS));	
        bottomPane.add(addLocationButton);
        // Invisible separator between the JScroll pane and buttons.
        bottomPane.add(Box.createHorizontalStrut(5));
        //  Separator between the JScroll pane and buttons.
        bottomPane.add(new JSeparator(SwingConstants.VERTICAL));
        bottomPane.add(Box.createHorizontalStrut(5));
        // Text box
        locationName = new JTextField(15);
        bottomPane.add(locationName);
        bottomPane.add(removeLocationButton);
        
    // Border code.    
        bottomPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPane, BorderLayout.PAGE_END);
    	
    	this.add(locationList);
	}

	

	
	
	/**
	 * Adds a new location to the list, after checking to see if it is a duplicate.
	 * @throws Exception 
	 */
	public void addLocationToList() throws Exception{
		if (lModel.contains(locationName.getText())){
			locationName.setText("Location is already in the list. Try again.");
		} else {
	/*LOCATION IS ADDED HERE*/	
			myLocations.addLocation(locationName.getText());
			this.validate();
			this.repaint();
			// clears the text area
			locationName.setText("");
		}
		
	}

	public void removeLocationFromList() {
		
		// removes the item which is selected from the list.
		// TODO What happens if no item is selected?
		if (locationList.getSelectedIndex() !=-1){
		lModel.remove(locationList.getSelectedIndex());
		this.validate();
		this.repaint();	
		}
		
	}
	
	public void buttonToggler(){
		if(locationList.getSelectedIndex() == -1){
			enableRemove();
		} else {
			disableRemove();
		}
		
	}
	/**
	 * Used by the OmniListener class
	 */
	public void disableRemove(){
		removeLocationButton.setEnabled(false);
	}

	/**
	 * Used by the OmniListener class
	 */
	public void enableRemove() {
		removeLocationButton.setEnabled(true);
		
	}
}
