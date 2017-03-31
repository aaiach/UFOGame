/**
 * 
 */
package views;

import controller.SightingsListController;
import models.ParsedIncident;
import models.SightingsList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * This class serves as the primary view for the Lists of Sightings functionality. It is
 * this window that gets popped up when the user clicks on one of the markers in the map
 * panel. It displays the list of sightings in the particular state represented by that 
 * marker.
 * @author Shantanu Shekhar Jha
 *
 */
public class SightingsListWindow extends JFrame implements Observer {

	/**
	 * The sightings list model object
	 */
	private SightingsList sightingsList;
	
	/**
	 * The DefaultListModel associated with the JList to be displayed
	 */
	private DefaultListModel<ParsedIncident> jListModel;
	
	/**
	 * The list of parsed incidents for the state
	 */
	private List<ParsedIncident> parsedIncidents;
	
	/**
	 * Creates a new instance of the SightingsListWindow.
	 * @param controller the controller for the window
	 * @param sightingsList the sightings list model object
	 */
	public SightingsListWindow(SightingsListController controller, SightingsList sightingsList) {
		
		// Set the sightings list model
		this.sightingsList = sightingsList;
		// Set the title for the JFrame
		setTitle(sightingsList.getStateName() + " (" + sightingsList.getAbbreviatedStateName() + ")");
		
		// set the layout of the JFrame
		setLayout(new BorderLayout());
		// make a new panel for the top
		JPanel topPanel = new JPanel();
		// create an empty surrounding border for the panel
		topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		// set the panel's layout
		topPanel.setLayout(new GridLayout(1, 1));
		
		// the different sort options
		String[] sortOptions = {"-", "Date", "City", "Shape", "Duration", "Posted"};
		// make a JComboBox to facilitate the user's selection
		JComboBox sortOptionsList = new JComboBox(sortOptions);
		// add the controller as the listener for the selection event
		sortOptionsList.addActionListener(controller);
		
		// add the JComboBox to the top panel
		topPanel.add(sortOptionsList);
		
		// make a new panel for the bottom
		JPanel bottomPanel = new JPanel();
		// create an empty surrounding border for the panel
		bottomPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		// set the panel's layout
		bottomPanel.setLayout(new GridLayout(1, 1));
		// set the background colour of the panel to white
		bottomPanel.setBackground(Color.white);
		
		// initialise the list model for the JList
		initJListModel();
		// make a new JList with the specified list model
		JList jList = new JList(jListModel);	
		// add the controller as the listener for the mouse-click event
		jList.addMouseListener(controller);
		
		// make a new scroll pane containing the JList
		JScrollPane scrollPane = new JScrollPane(jList);
		// create an empty border for the scroll pane
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		// add the scroll pane to the bottom panel
		bottomPanel.add(scrollPane);
		
		// add both the panels to the JFram within the specified layout positions
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
		
		// set the preferred size of the JFrame
		setPreferredSize(new Dimension(700, 250));
		// set the location on screen
		setLocation(300, 200);
		// set the default close operation
		// the JFrame will get closed when the user closes it
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		pack();
		setVisible(true);
		
	}
	
	/**
	 * Initialises the list model for the JList
	 */
	public void initJListModel() {
		
		// make a new list model for type ParsedIncident
		jListModel = new DefaultListModel<ParsedIncident>();
		// get the list of parsed incidents from the sightings list model
		parsedIncidents = sightingsList.getParsedIncidents();
		
		// for every incident in the list of parsed incidents
		for (ParsedIncident parsedIncident : parsedIncidents) {
			
			// add the incident to the JList model
			jListModel.addElement(parsedIncident);
			
		}
		
	}
	
	/* (non-Javadoc)
	 * Updates the view of the JFrame.
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable o, Object arg) {
		
		// for every element in the list of parsed incidents
		for (int i = 0; i < parsedIncidents.size(); i++) {
			
			// set the element at that index in the JList model to that element
			jListModel.set(i, parsedIncidents.get(i));
			
		}
		
	}
	
}
