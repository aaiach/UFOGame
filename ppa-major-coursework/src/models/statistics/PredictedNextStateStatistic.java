package models.statistics;

import api.ripley.Incident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (state != null) sightingsAtStates.addSighting(state);
        });

        // Return the state with the highest number of sightings
        return sightingsAtStates.getHighestSightingState();
    }

    /**
     * SightingsAtState is an inner class used for storing the number of sightings at each state,
     * and getting the state with the highest number of sightings
     *
     * @author Robert Greener
     */
    private class SightingsAtStates {
        /**
         * numberOfSightings is a HashMap where the key is the name of the state,
         * and the value is the number of sightings at that state
         */
        private HashMap<String, Integer> numberOfSightings;

        /**
         * Creates a new SightingsAtStates
         */
        public SightingsAtStates() {
            // Initialise the numberOfSightings HashMap
            numberOfSightings = new HashMap<>();
        }

        /**
         * Adds a sighting at a state
         * @param stateName the name of the state
         */
        public void addSighting(String stateName) {
            // If the state already exists in numberOfSightings
            if (numberOfSightings.containsKey(stateName)) {
                // Increase the value by 1
                numberOfSightings.put(stateName, numberOfSightings.get(stateName) + 1);
            }
            // Otherwise
            else {
                // Set the number of sightings for that state to 1
                numberOfSightings.put(stateName, 1);
            }
        }

        /**
         * Gets the state with the most UFO sightings
         * @return the state with the highest number of UFO sightings
         */
        public String getHighestSightingState() {
            // Set the initial highestState key-value pair to a random pair from numberOfSightings
            Map.Entry<String, Integer> highestState = null;

            // For each key-value pair in the numberOfSightings' entrySet
            for (Map.Entry<String, Integer> entry : numberOfSightings.entrySet()) {
                // If there are more sightings at this entry, set this entry as the highest state
                if (highestState == null || entry.getValue() > highestState.getValue()) highestState = entry;
            }

            // Return the key of highestState (which is the state name)
            return highestState.getKey();
        }
    }

}
