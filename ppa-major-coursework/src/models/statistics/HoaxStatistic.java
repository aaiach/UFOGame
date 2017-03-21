package models.statistics;

import api.ripley.Incident;
import api.ripley.Ripley;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * HoaxStatistic is a Statistic for the number of hoaxes in a supplied ArrayList of incidents
 *
 * @author Robert Greener
 */
public class HoaxStatistic extends Statistic {
    /**
     * A reference to the initialised Ripley API
     */
    private Ripley ripley;

    /**
     * A List of incidents that will be searched for hoaxes
     */
    private List<Incident> incidents;

    /**
     * Creates a new HoaxStatistic
     * @param ripley the Ripley API
     * @param incidents A List of incidents that will be searched for hoaxes
     */
    public HoaxStatistic(Ripley ripley, List<Incident> incidents) {
        // Call the superclass constructor to set the name of the statistic
        super("Number Of Hoaxes");
        // Set the fields
        this.ripley = ripley;
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
        // Create a new ArrayList to store additional details of incidents
        List<String> incidentDetails = new ArrayList<>();
        /* Uses a stream on incidents, to collect the incident details as a list,
         * which using the ArrayList.addAll method is added to incidentDetails
         */
        incidentDetails.addAll(incidents.stream()
                .map(i -> ripley.getIncidentDetails(i.getIncidentID()))
                .collect(Collectors.toList()));

        // Set the initial numberOfHoaxes to 0
        int numberOfHoaxes = 0;

        // For each details in incidentDetails
        for (String details : incidentDetails) {
            // If the details contains the string "hoax" increment numberOfHoaxes by 1
            if (details.toLowerCase().contains("hoax")) numberOfHoaxes++;
        }

        // Return the numberOfHoaxes as a string
        return Integer.toString(numberOfHoaxes);
    }
}
