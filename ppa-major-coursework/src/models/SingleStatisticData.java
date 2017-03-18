package models;

/**
 * SingleStatisticData is used for storing the information for one statistic
 *
 * @author Robert Greener.
 */
public class SingleStatisticData {
    /**
     * The title of the statistic
     */
    private String statisticTitle;

    /**
     * The data of the statistic
     */
    private String statisticData;

    /**
     * Creates a new SingleStatisticDate
     * @param statisticTitle the title of the statistic
     * @param statisticData the data of the statistic
     */
    public SingleStatisticData(String statisticTitle, String statisticData) {
        this.statisticTitle = statisticTitle;
        this.statisticData = statisticData;
    }

    /**
     * Gets the title of the statistic
     * @return the title of the statistic
     */
    public String getStatisticTitle() {
        return statisticTitle;
    }

    /**
     * Gets the data of the statistic
     * @return the data of the statistic
     */
    public String getStatisticData() {
        return statisticData;
    }
}
