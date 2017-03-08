package models;

import java.util.Map.Entry;
import java.util.HashMap;

/**
 * @author Robert Greener.
 */
public class SightingsAtStates {
    private HashMap<String, Integer> numberOfSightings;

    public SightingsAtStates() {
        numberOfSightings = new HashMap<>();
    }

    public void addSighting(String stateName) {
        addSighting(stateName, 1);
    }

    public void addSighting(String stateName, int number) {
        if (numberOfSightings.containsKey(stateName)) {
            numberOfSightings.put(stateName, numberOfSightings.get(stateName) + number);
        } else {
            numberOfSightings.put(stateName, 1);
        }
    }

    public String getHighestSightingState() {
        Entry<String, Integer> highestState = numberOfSightings.entrySet().iterator().next();

        for (Entry<String, Integer> entry : numberOfSightings.entrySet()) {
            if (entry.getValue() > highestState.getValue()) highestState = entry;
        }

        return highestState.getKey();
    }
}
