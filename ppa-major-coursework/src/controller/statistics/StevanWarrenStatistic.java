package controller.statistics;

import java.util.List;

import api.ripley.Ripley;

/**
 * This statistic finds the proportion of all sightings that occurred in the US.
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
     * Calculates the proportion of sightings in the US.
     * @return Percentage of sightings that occurred in the US
     */
    @Override
    protected String calculateData() {
    	// To find the proportion, find the total number of sightings where a state is specified,
    	// divide it by the total number of sightings and muliply by 100.
    	int USSightings = 0;
    	for (Incident incident : incidents) {
    		if (!incident.getState().equals("Not specified.")) {
    			USSightings++;
    		}
    	}
    	
    	double proportion = USSightings / incidents.size() * 100;
    	
    	// The end result will be rounded to 2 decimal places.
    	proportion = Math.round(proportion * 100) / 100;
        return proportion + "%";
    }
}
