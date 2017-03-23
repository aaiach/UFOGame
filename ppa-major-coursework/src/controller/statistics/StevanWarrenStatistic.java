package controller.statistics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.ripley.Ripley;

/**
 * This statistic finds the most sightings in a selected list that were reported in one day.
 *
 * @author Stevan Warren
 */
public class StevanWarrenStatistic extends Statistic {
	private List<Incident> incidents;
	
    /**
     * Creates a new StevanWarrenStatistic
     */
    public StevanWarrenStatistic(List<Incident> incidents) {
        // Calls the superclass constructor, setting the name of the statistic
        super("Percentage of US sightings");
        this.incidents = incidents;

        // Sets the data of the statistic, to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the most sightings recorded in a day.
     */
    @Override
    protected String calculateData() {
    	// This hashmap will store each unique date in the list of incidents, mapped to the number of sightings recorded on that date.
    	HashMap<String, Integer> days = new HashMap<String, Integer>();
    	String date;
    	
    	for (Incident incident : incidents) {
    		// Retrieves the date of the incident, which is the first 9 characters of the dateAndTime.
    		date = incident.getDateAndTime().substring(0, 10);
    		// If the date is already in the HashMap, then increment the number of sightings stored for that day.
    		if (days.containsKey(date)) {
    			days.put(date, days.get(date) + 1);
    		// Otherwise, create a new mapping from that date to the value '1'.
    		} else {
    			days.put(date, 1);
    		}
    	}
    	
    	int maxSightings = 0;
    	// This loop iterates through the hashmap to find the date with the highest sightings and records that number.
    	for (Map.Entry<String, Integer> entry : days.entrySet()) {
    		if (entry.getValue() > maxSightings) {
    			maxSightings = entry.getValue();
    		}
    	}
    	
        return "" + maxSightings;
    }
}
