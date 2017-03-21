package controller.statistics;

import api.ripley.Incident;

import java.util.List;

/**
 * NonUSSightingsStatistic is the Statistic for the number of UFO Sightings
 * that did not occur within any of the US States, District of Columbia, or any of the 16 US territories
 * (From this point onwards, these are referred to as the US)
 *
 * @author Robert Greener
 */
public class NonUSSightingsStatistic extends Statistic {
    /**
     * A List of Incidents that will be searched for sightings that did not occur within the US
     */
    private List<Incident> incidents;

    /**
     * Creates a new NonUSSightingsStatistic
     * @param incidents A List of Incidents that will be searched for sightings that did not occur within the US
     */
    public NonUSSightingsStatistic(List<Incident> incidents) {
        // Call the superclass constructor to set the name of the statistic
        super("Number of Non-US Sightings");
        // Set the fields
        this.incidents = incidents;
        // Set the data of this Statistic equal to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the number of sightings that did not occur in the US
     * @return the number of sightings that did not occur in the US
     */
    @Override
    protected String calculateData() {
        // Set the initial numberOfNonUSSightings to 0
        int numberOfNonUSSightings = 0;

        // For each Incident in incidents
        for (Incident i : incidents) {
            // If the State of the Incident is not a US State, increment numberOfNonUSSightings by 1
            if (!USStates.isState(i.getState())) numberOfNonUSSightings++;
        }

        // Return the numberOfNonUSSightings as a String
        return Integer.toString(numberOfNonUSSightings);
    }
}
