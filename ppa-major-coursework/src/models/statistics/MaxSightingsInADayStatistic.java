package models.statistics;

import api.ripley.Incident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This statistic finds the most sightings in a selected list that were reported in one day.
 *
 * @author Stevan Warren
 */
public class MaxSightingsInADayStatistic extends Statistic {
    /**
     * The list of incidents
     */
	private List<Incident> incidents;
	
    /**
     * Creates a new MaxSightingsInADayStatistic
     * @param incidents A list of incidents to find the highest day
     */
    public MaxSightingsInADayStatistic(List<Incident> incidents) {
        // Calls the superclass constructor, setting the name of the statistic
        super("Max sightings in a day");
        this.incidents = incidents;

        // Sets the data of the statistic, to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the most sightings recorded in a day.
     * @return the maximum number of sightings that occured within one day
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
