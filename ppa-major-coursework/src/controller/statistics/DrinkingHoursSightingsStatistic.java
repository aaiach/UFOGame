package controller.statistics;

/**
 * This statistic determines the percentage of UFO sightings reported during drinking hours,
 * i.e. between 5pm and 11pm, in which the people were most likely to be intoxicated.
 * 
 * This time period was officially categorized by the Economist, who used the statistics
 * compiled by the National UFO Reporting Center (NUFORC) to analyze 'peak times' for UFO reports.
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
