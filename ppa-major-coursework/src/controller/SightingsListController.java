/**
 * 
 */
package controller;

import java.awt.event.*;
import java.util.List;

import javax.swing.*;

import models.*;
import views.IncidentDetailsMessage;

/**
 * This class serves as the controller for the Lists of Sightings functionality, 
 * between the model and the view.
 * 
 * @author Shantanu Shekhar Jha
 *
 */
public class SightingsListController extends MouseAdapter implements ActionListener {
	
	/**
	 * The sightings list model object.
	 */
	private SightingsList sightingsList;
	
	/**
	 * Creates a new instance of the SightingsListController
	 * @param sightingsList the sightings list model object
	 */
	public SightingsListController(SightingsList sightingsList) {
		
		// Set the sightings list model
		this.sightingsList = sightingsList;
		
	}
	
	/* (non-Javadoc)
	 * Handles the selection event on the JComboBox.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// get the JComboBox in which the event has occurred
		JComboBox cb = (JComboBox)e.getSource();
		// get the user's selected sort option from it
		String sortOption = (String)cb.getSelectedItem();
		// call the method in the model for sorting the incidents,
		// with the selected sort option
		sightingsList.sortIncidents(sortOption);
		
	}

	/* (non-Javadoc)
	 * Handles the mouse-click event on the JComboBox, when one of the
	 * entries in the JList is clicked
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent e) {

		// get the JList in which the event has occurred
		JList jList = (JList)e.getSource();
		// determine what cell was clicked, based on the list's coordinate system
		// store this list index
		int index = jList.locationToIndex(e.getPoint());
        
		// get the list of incidents from the model
		List<ParsedIncident> incidents = sightingsList.getParsedIncidents();
		// get the incident at the clicked index
		ParsedIncident incident = incidents.get(index);
		
		// fetch the incident details for the incident ID of that incident
		String incidentDetails = sightingsList.fetchIncidentDetails(incident.getIncidentID());
		// get the summary of the incident
		String incidentSummary = incident.getSummary();
		
		// create a new instance of the IncidentDetailsMessage window
		IncidentDetailsMessage incidentDetailsMessage = new IncidentDetailsMessage("Message");
		// call the initDialog method, with the incident details and summary,
		// to set up the IncidentDetailsMessage window
		incidentDetailsMessage.initDialog(incidentDetails, incidentSummary);
		
    }
	
}
