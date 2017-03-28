package mappanelclasses;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import api.ripley.Incident;

/**
 * A class holding a map of the US. When the user selects a date range, markers will be placed
 * on to the map in each state where a sighting took place. The markers will be sized so that if
 * more sightings took place in one state, that marker will be larger.
 * 
 * @author Stevan Warren K1631436
 */
public class MapPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	// For the map panel to work, the size of the panel must be kept constant at the specified size.
	// Width of the map panel
	public static final int WIDTH = 1400;
	// Height of the map panel
	public static final int HEIGHT = 1000;
	
	// The map of the US that the markers are placed on
	private BufferedImage backgroundMap;
	
	/**
	 * Constructor that adds the US map to the panel
	 */
	public MapPanel() {
		setLayout(null);
		try {
			backgroundMap = ImageIO.read(new File("us-map.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds the US map to the panel
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(backgroundMap, 0, 0, WIDTH, HEIGHT, this);
	}
	
	/**
	 * Updates the map panel so that it displays markers on each location where sightings took place
	 * 
	 * @param List of all sightings in the current date range
	 */
	public void update(List<Incident> incidentsInRange) {
		// Removes all markers from the map
		removeAll();
		
		// Creates a TreeMap that will keep track of the number of incidents in each state
		TreeMap<String, Integer> incidentsInEachState = new TreeMap<String, Integer>();
		
		String state;
		
		// This loop takes each incident in the specified range, checks its location and updates the TreeMap.
		for (Incident incident : incidentsInRange) {
			state = incident.getState();
			if (incidentsInEachState.containsKey(state)) {
				incidentsInEachState.put(state, incidentsInEachState.get(state) + 1);
			} else {
				incidentsInEachState.put(state, 1);
			}
		}
		
		// This loop finds the highest number of sightings in a location
		// (used to determine the size of the markers)
		int maxSightingsInAState = 0;
		for (Map.Entry<String, Integer> entry : incidentsInEachState.entrySet()) {
			if (entry.getValue() > maxSightingsInAState) {
				maxSightingsInAState = entry.getValue();
			}
		}
		
		UFOMarker currentMarker;
		ArrayList<Incident> incidentsInState;
		int markerSize;
		// For each state...
		for (String abbv : MarkerLocations.ABBREVIATIONS) {
			
			// If the location has at least one sighting, then set a marker on that location.
			if (incidentsInEachState.containsKey(abbv)) {
				
				// This loop creates a list of all the sightings in the current location
				incidentsInState = new ArrayList<Incident>();
				for (Incident incident : incidentsInRange) {
					if (incident.getState().equals(abbv)) incidentsInState.add(incident);
				}
				
				// Get the string specifying the location of the marker
				String location = MarkerLocations.getLocation(abbv);
				Scanner splitter = new Scanner(location);
				splitter.next();
				if (abbv.equals("Not specified.")) splitter.next();
				// Creates a new marker and passes the list of incidents in the current location to it
				currentMarker = new UFOMarker(incidentsInState);
				// Adds the marker to the map
				add(currentMarker);
				
				// Minimum size of the marker: 20x20
				// Maximum size of the marker: 60x60
				
				// Moves the marker to the correct location and resizes it
				markerSize = (int)(20 + (((double)incidentsInEachState.get(abbv) / (double)maxSightingsInAState) * 40));
				currentMarker.setBounds(Integer.parseInt(splitter.next()), Integer.parseInt(splitter.next()), markerSize, markerSize);
				splitter.close();
			}
		}
	}
}
