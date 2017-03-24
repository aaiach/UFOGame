package models.statistics;

import api.ripley.Incident;

import java.util.List;

/**
 * HoaxStatistic is a Statistic for the number of hoaxes in a supplied ArrayList of incidents
 *
 * @author Robert Greener
 */
public class HoaxStatistic extends Statistic {

    /**
     * A List of incidents that will be searched for hoaxes
     */
    private List<Incident> incidents;

    /**
     * Creates a new HoaxStatistic
     * @param incidents A List of incidents that will be searched for hoaxes
     */
    public HoaxStatistic(List<Incident> incidents) {
        // Call the superclass constructor to set the name of the statistic
        super("Number Of Hoaxes");
        // Set the fields
        this.incidents = incidents;

        // Set the data of this statistic, equal to the value returned by calculateData()
        setData(calculateData());
    }

    /**
     * Calculates the number of hoaxes
     * @return the number of (suspected) hoaxes as a string
     */
    @Override
    protected String calculateData() {
        // Set the initial numberOfHoaxes to 0
        int numberOfHoaxes = 0;

        // For each incident in incidents
        for (Incident i : incidents) {
            // If the summary contains the string "hoax" increment numberOfHoaxes by 1
            if (i.getSummary().toLowerCase().contains("hoax")) numberOfHoaxes++;
        }

        // Return the numberOfHoaxes as a string
        return Integer.toString(numberOfHoaxes);
    }
}
