package models.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for storing all of the statistics
 *
 * @author Robert Greener.
 */
public class AllStatistics {
    /**
     * All of the statistics will be stored in here
     */
    private List<Statistic> statistics;

    /**
     * Makes a new AllStatistics
     */
    public AllStatistics() {
        this.statistics = new ArrayList<>();
    }

    /**
     * Adds a statistic
     * @param s the Statistic to add
     */
    public void add(Statistic s) {
        this.statistics.add(s);
    }

    /**
     * Gets a statistic at an index
     * @param index the index
     * @return the Statistic at that index
     */
    public Statistic get(int index) {
        return this.statistics.get(index);
    }

    /**
     * Gets the size of AllStatistics
     * @return the number of elements in the statistics List
     */
    public int size() {
        return this.statistics.size();
    }
}
