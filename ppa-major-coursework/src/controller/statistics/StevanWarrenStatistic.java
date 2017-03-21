package controller.statistics;

/**
 * TODO: Implement this statistic
 *
 * @author Stevan Warren
 */
public class StevanWarrenStatistic extends Statistic {

    /**
     * Creates a new StevanWarrenStatistic
     */
    public StevanWarrenStatistic() {
        // Calls the superclass constructor, setting the name of the statistic
        super("Stevan Warren's Statistic");

        // Sets the data of the statistic, to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the date for this statistic
     * @return always returns "Not Implemented"
     */
    @Override
    protected String calculateData() {
        return "Not implemented";
    }
}
