package models;

import api.ripley.Incident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * SightingsAtState is an inner class used for storing the number of sightings at each state,
 * and getting the state with the highest number of sightings
 *
 * @author Robert Greener
 */
public class SightingsAtStates {
    /**
     * numberOfSightings is a HashMap where the key is the name of the state,
     * and the value is the number of sightings at that state
     */
    private HashMap<String, Integer> numberOfSightings;

    /**
     * Creates a new SightingsAtStates
     */
    public SightingsAtStates(List<Incident> incidents) {
        // Initialise the numberOfSightings HashMap
        numberOfSightings = new HashMap<>();

        // For each Incident i in incidents (using the Java 8 stream forEach method)
        incidents.forEach((i) -> {
            // Get the state
            String state = i.getState();
            if (state != null) this.addSighting(state);
        });
    }

    /**
     * Adds a sighting at a state
     * @param stateName the name of the state
     */
    private void addSighting(String stateName) {
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

    public HashMap<String, Integer> getNumberOfSightings() {
        return new HashMap<>(numberOfSightings);
    }
}