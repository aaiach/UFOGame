package models.statistics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robert Greener.
 */
public class AllStatistics {
    private List<Statistic> statistics;

    public AllStatistics() {
        this.statistics = new ArrayList<>();
    }

    public void add(Statistic s) {
        this.statistics.add(s);
    }

    public Statistic get(int index) {
        return this.statistics.get(index);
    }

    public int size() {
        return this.statistics.size();
    }
}
