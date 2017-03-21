package controller.statistics;

/**
 * TODO: Implement this statistic
 *
 * @author Shantanu Jha
 */
public class ShantanuJhaStatistic extends Statistic {

    /**
     * Creates a new ShantanuJhaStatistic
     */
    public ShantanuJhaStatistic() {
        // Calls the superclass constructor, setting the name of the statistic
        super("Shantanu Jha's Statistic");

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
