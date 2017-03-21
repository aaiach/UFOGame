package controller.statistics;

import java.util.Date;
import java.util.List;

import api.ripley.Incident;

/**
 * @author Adriel Aiach
 * This class calculates the average number of sightings per day
 */
public class SightingsPerDayStatistic extends Statistic {

	private List<Incident> incidents;
	private Date start, end;
	
    /**
     * Creates a new SightingsPerDayStatistic
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
