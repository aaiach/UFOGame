package views;

import api.ripley.Incident;
import controller.SightingsListController;
import models.SightingsList;
import models.statistics.USStates;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * A class holding the action listener applied to each marker on the map panel.
 * When the marker is clicked on, this listener will create a new message panel containing
 * a list of all the incidents that have taken place in this area.
 *
 * @author Stevan Warren K1631436
 *
 */
public class AlienButtonListener implements ActionListener {

	/**
	 * A list of all the incidents that have taken place in the area linked to the relevant marker
	 */
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

		Incident incident = incidents.get(0);
		String abbreviatedStateName = incident.getState();

		String stateName = USStates.getFullStateName(abbreviatedStateName);

		// create a new instance of the SightingsList class with the required parameters
		SightingsList sightingsList = new SightingsList(stateName, abbreviatedStateName, incidents);
		// create a new instance of the SightingsListController class, passing in sightingsList
		SightingsListController sightingsListController = new SightingsListController(sightingsList);

		// create a new instance of the SightingsListWindow class, passing in sightingsListController and sightingsList
		SightingsListWindow sightingsListWindow = new SightingsListWindow(sightingsListController, sightingsList);
		// add the sightingsListWindow as an observer of sightingsList
		sightingsList.addObserver(sightingsListWindow);

	}
}