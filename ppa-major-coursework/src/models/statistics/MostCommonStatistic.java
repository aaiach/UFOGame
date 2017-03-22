package models.statistics;

import api.ripley.Incident;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is a statistic that calculates the most common UFO shape
 *
 * @author Robert Greener
 */
public class MostCommonStatistic extends Statistic {
    /**
     * A List of all the incidents to look for the most common shape in
     */
    private List<Incident> incidents;


    /**
     * Creates a new MostCommonStatistic
     * @param incidents a List of Incidents to look for the most common shape in
     */
    public MostCommonStatistic(List<Incident> incidents) {
        // Calls the superclass constructor, setting the name of the statistic
        super("Most common shape of UFO");

        this.incidents = incidents;
        // Sets the data of the statistic, to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the most common shape of UFOs
     * @return The most common shape of UFOs
     */
    @Override
    protected String calculateData() {
        // Create a new HashMap to store the name of a shape, and the number of incidents with this shape
        HashMap<String, Integer> shapes = new HashMap<>();

        // For each incident
        for (Incident incident : incidents) {
            // Get the shape
            String shape = incident.getShape();
            if (shape != null) {
                // If shapes already contains that shape, add one to the number of incidents containing this shape
                if (shapes.containsKey(shape)) shapes.put(shape, shapes.get(shape) + 1);
                    // Otherwise set the number of incidents containing this shape to 1
                else shapes.put(shape, 1);
            }
        }

        // Set the initial largestEntry to null
        Map.Entry<String,Integer> largestEntry = null;

        // For each entry in shapes' entrySet
        for (Map.Entry<String, Integer> entry : shapes.entrySet()) {
            /* If largestEntry is null
             * or if the value in entry is greater than the largestEntry's value
             */
            if (largestEntry == null || entry.getValue() > largestEntry.getValue())
                // Set largestEntry to Entry
                largestEntry = entry;
        }

        // Return the key of largestEntry (shape name)
        return largestEntry.getKey();
    }
}
