package models.statistics;

import api.ripley.Incident;

import java.util.Date;
import java.util.List;

/**
 * This class calculates the average number of sightings per day
 *
 * @author Adriel Aiach
 */
public class SightingsPerDayStatistic extends Statistic {
    /**
     * The list of incidents to look at
     */
	private List<Incident> incidents;
    /**
     * The start and end dates
     */
	private Date start, end;
	
    /**
     * Creates a new SightingsPerDayStatistic
     *
     * @param incidents A list of incidents to look at
     * @param start the start date
     * @param end the end date
     */
    public SightingsPerDayStatistic(List<Incident> incidents, Date start, Date end) {
    	
        // Calls the superclass constructor, setting the name of the statistic
        super("Average incidents per day");
        
    	this.incidents = incidents;
    	this.start = start;
    	this.end = end;

        // Sets the data of the statistic, to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the average number of sightings per day
     * @return the average number of sightings per day
     */
    @Override
    protected String calculateData() {
    	
    	//Measures the number of milliseconds between the two dates, then calculates how many days that is
    	double days = (start.getTime() - end.getTime()) / (1000 * 60 * 60 * 24);
        return Double.toString(incidents.size()/days);
    }
}
