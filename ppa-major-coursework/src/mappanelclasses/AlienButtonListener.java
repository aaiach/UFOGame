package mappanelclasses;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

import api.ripley.Incident;

/**
 * A class holding the action listener applied to each marker on the map panel.
 * When the marker is clicked on, this listener will create a new message panel containing
 * a list of all the incidents that have taken place in this area.
 * 
 * @author Stevan Warren K1631436
 *
 */
public class AlienButtonListener implements ActionListener {
	// A list of all the incidents that have taken place in the area linked to the relevant marker
	private ArrayList<Incident> incidents;
	
	/**
	 * Constructor that initialises the listener without any incidents (used for testing)
	 */
	public AlienButtonListener() {
		incidents = new ArrayList<Incident>();
	}
	
	/**
	 * Constructor that passes in the incidents that have occurred in the relevant area
	 * @param incidents
	 */
	public AlienButtonListener(ArrayList<Incident> incidents) {
		this.incidents = incidents;
	}
	
	/**
	 * When the marker is clicked on, a new message panel displaying information
	 * about each incident in the area will appear.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// Shantanu's code for the message panels go here
		
		JFrame infoFrame = new JFrame();
		infoFrame.add(new JLabel("" + incidents.size()));
		infoFrame.setSize(700, 300);
		infoFrame.setVisible(true);
		
		//
	}
}
