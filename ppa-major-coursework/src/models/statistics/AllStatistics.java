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

    public AllStatistics(List<Statistic> statistics) {
        this();
        this.statistics.addAll(statistics);
    }

    public void set (int index, Statistic statistic) {
        this.statistics.set(index, statistic);
    }

    public void add (Statistic s) {
        this.statistics.add(s);
    }

    public Statistic get (int index) {
        return this.statistics.get(index);
    }

    public Statistic remove (int index) {
        return this.statistics.remove(index);
    }

    public boolean remove (Statistic s) {
        return this.statistics.remove(s);
    }

    public int indexOf (Statistic s) {
        return this.statistics.indexOf(s);
    }

    public int size () {
        return this.statistics.size();
    }
}

    public AllStatistics(List<Statistic> statistics) {
        this.statistics.addAll(statistics);
        this.statistics = new ArrayList<>();