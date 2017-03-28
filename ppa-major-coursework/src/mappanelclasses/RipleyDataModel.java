package mappanelclasses;

import java.util.ArrayList;
import java.util.Observable;

import api.ripley.Ripley;
import api.ripley.Incident;

/**
 * A class holding the Ripley file and allows the view to query the Ripley file to
 * retrieve the data from it.
 * 
 * @author Stevan Warren K1631436
 */
public class RipleyDataModel extends Observable {
	// The ripley file
	private Ripley ripley;
	// Integer of the panel that is currently selected by the user.
	// 1 = Welcome panel
	// 2 = Map Panel
	// 3 = Statistics Panel
	// 4 = Game Panel
	private int panelSelected;
	// A list of all the incidents that are in the date range that the user has currently selected
	public ArrayList<Incident> incidentsInRange;
	
	/**
	 * Constructor that initialises the Ripley file and the incident list
	 */
	public RipleyDataModel() {
		ripley = new Ripley("90tLI3CRs9iyVD6ql2OMtA==", "lBgm4pRv9wjVqL46EnH7ew==");
		panelSelected = 1;
		incidentsInRange = new ArrayList<Incident>();
	}
	
	/**
	 * Returns the integer value representing the panel that is currently selected
	 */
	public int panelSelected() {
		return panelSelected;
	}
	
	/**
	 * Called when the user presses the right button on the main window and moves forward by one panel.
	 */
	public void nextPanel() {
		if (panelSelected < 2) {
			panelSelected++;
			updateView();
		}
	}
	
	/**
	 * Called when the user presses the left button on the main window and moves backward by one panel.
	 */
	public void previousPanel() {
		if (panelSelected > 1) {
			panelSelected--;
			updateView();
		}
	}
	
	/**
	 * Returns when the Ripley file was last updated.
	 */
	public String getLastUpdated() {
		return ripley.getLastUpdated();
	}
	
	/**
	 * Retrieves the incidents in the user's specified range and updates the incident list
	 * 
	 * @param Start date
	 * @param End date
	 */
	public void updateSelectedIncidents(String start, String end) {
		incidentsInRange = ripley.getIncidentsInRange(start + "-01-01 00:00:00", end + "-12-31 23:59:59");
		updateView();
	}
	
	/**
	 * Returns a list of all the incidents in the current range
	 */
	public ArrayList<Incident> incidentsInRange() {
		return incidentsInRange;
	}
	
	/**
	 * Returns the year when the earliest sighting took place
	 */
	public int getLowerBound() {
		return ripley.getStartYear();
	}
	
	/**
	 * Returns the year when the most recent sighting took place
	 */
	public int getUpperBound() {
		return ripley.getLatestYear();
	}
	
	/**
	 * Called whenever the user changes panels or changes the date range.
	 * This updates the view to represent the new information.
	 */
	public void updateView() {
		setChanged();
		notifyObservers();
	}
}
