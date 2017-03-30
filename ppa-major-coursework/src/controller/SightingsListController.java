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
 * @author Shantanu Shekhar Jha
 *
 */
public class SightingsListController extends MouseAdapter implements ActionListener {
	
	private SightingsList sightingsList;
	
	public SightingsListController(SightingsList sightingsList) {
		
		this.sightingsList = sightingsList;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JComboBox cb = (JComboBox)e.getSource();
		String sortOption = (String)cb.getSelectedItem();
		sightingsList.sortIncidents(sortOption);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		JList jList = (JList)e.getSource();
		int index = jList.locationToIndex(e.getPoint());
        
		List<ParsedIncident> incidents = sightingsList.getParsedIncidents();
		ParsedIncident incident = incidents.get(index);
		
		String incidentDetails = sightingsList.fetchIncidentDetails(incident.getIncidentID());
		IncidentDetailsMessage incidentDetailsMessage = new IncidentDetailsMessage("Message");
		
		String incidentSummary = incident.getSummary();
		
		incidentDetailsMessage.initDialog(incidentDetails, incidentSummary);
		
    }
	
}
