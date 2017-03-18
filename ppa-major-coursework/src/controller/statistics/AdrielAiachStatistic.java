package controller.statistics;

/**
 * TODO: Implement this statistic
 *
 * @author Adriel Aiach
 */
public class AdrielAiachStatistic extends Statistic {

    /**
     * Creates a new AdrielAiachStatistic
     */
    public AdrielAiachStatistic() {
        // Calls the superclass constructor, setting the name of the statistic
        super("Adriel Aiach's Statistic");

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
