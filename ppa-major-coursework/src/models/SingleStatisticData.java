package models;

/**
 * @author Robert Greener.
 */
public class SingleStatisticData {
    private String statisticTitle;
    private String statisticData;

    public SingleStatisticData(String statisticTitle, String statisticData) {
        this.statisticTitle = statisticTitle;
        this.statisticData = statisticData;
    }

    public String getStatisticTitle() {
        return statisticTitle;
    }

    public void setStatisticTitle(String statisticTitle) {
        this.statisticTitle = statisticTitle;
    }

    public String getStatisticData() {
        return statisticData;
    }

    public void setStatisticData(String statisticData) {
        this.statisticData = statisticData;
    }
}
