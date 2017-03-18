package controller.statistics;

/**
 * TODO: Implement this statistic
 *
 * @author Robert Greener
 */
public class RobertGreenerStatistic extends Statistic {

    /**
     * Creates a new RobertGreenerStatistic
     */
    public RobertGreenerStatistic() {
        // Calls the superclass constructor, setting the name of the statistic
        super("Robert Greener's Statistic");

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
