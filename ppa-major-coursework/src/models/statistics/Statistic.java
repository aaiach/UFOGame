package models.statistics;

/**
 * Statistic is an abstract class, who's subclasses will be one of the Statistics about UFO's
 *
 * @author Robert Greener
 */
public abstract class Statistic {
    /**
     * The name of the Statistic (i.e. the title)
     */
    private String name;

    /**
     * The data of the statistic
     *
     * A String was chosen as the data type as different statistics could contain different types of data
     */
    private String data;

    /**
     * Creates a new statistic
     * @param name the name of the statistic
     */
    public Statistic(String name) {
        // Sets the name of the statistic, and sets the initial data value to null
        this.name = name;
        this.data = null;
    }

    /**
     * Gets the name of the statistic
     * @return the name of the statistic - that should be used as the title
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the data for the statistic
     * @return the main data for the statistic
     */
    public String getData() {
        return data;
    }

    /**
     * Sets the data - should be called in the subclass' constructor
     * @param data the data for the statistic
     */
    protected void setData(String data) {
        this.data = data;
    }

    /**
     * Calculates the data for the statistic - should be called in the subclass' constructor
     * @return the data
     */
    protected abstract String calculateData();
}
