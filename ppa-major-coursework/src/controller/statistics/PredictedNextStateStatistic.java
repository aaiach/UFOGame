package controller.statistics;

import api.ripley.Incident;
import models.SightingsAtStates;

import java.util.List;

/**
 * PredictedNextStateStatistic is a Statistic that gets the state where the next sighting is predicted to take place
 *
 * It is assumed that the State with the most sightings, will be most likely to have the next sighting
 */
public class PredictedNextStateStatistic extends Statistic{
    /**
     * A List containing Incidents that will be searched to calculate where the next sighting will take place
     */
    private List<Incident> incidents;

    /**
     * Create a new PredictedNextStateStatistic
     * @param incidents A List containing Incidents that will be searched
     */
    public PredictedNextStateStatistic(List<Incident> incidents) {
        // Calls the superclass constructor to set the name of the statistic
        super("Next Predicted State");

        // Set the fields
        this.incidents = incidents;

        // Set the data of this statistic equal to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculate the state with the highest number of sightings
     * @return the state with the highest number of sightings
     */
    @Override
    protected String calculateData() {
        // Create a new SightingsAtStates
        SightingsAtStates sightingsAtStates = new SightingsAtStates();

        // For each Incident i in incidents (using the Java 8 stream forEach method)
        incidents.forEach((i) -> {
            // Get the state
            String state = i.getState();
            // Attempt to get the full state name
            String fullStateName = USStates.getFullStateName(state);

            /* If the fullStateName is null (i.e the state is not a US State, District of Columbia,
             * or a territory of the US) add a sighting with the name of the state returned by i.getState()
             */
            if (fullStateName == null) sightingsAtStates.addSighting(state);
            // Otherwise add a sighting, using the fullStateName
            else sightingsAtStates.addSighting(state);
        });

        // Return the state with the highest number of sightings
        return sightingsAtStates.getHighestSightingState();
    }
}
