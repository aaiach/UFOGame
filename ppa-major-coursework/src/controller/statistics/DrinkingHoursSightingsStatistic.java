package controller.statistics;

import api.ripley.Incident;
import java.util.List;

/**
 * This statistic determines the percentage of UFO sightings reported during drinking hours,
 * i.e. between 5pm and 11pm, in which the people were most likely to be intoxicated.
 * 
 * This time period was officially categorized by the Economist, who used the statistics
 * compiled by the National UFO Reporting Center (NUFORC) to analyze 'peak times' for UFO reports.
 *
 * @author Shantanu Shekhar Jha
 */
public class DrinkingHoursSightingsStatistic extends Statistic {

    /**
     * The list of incidents to look at
     */
    private List<Incident> incidents;
    
    /**
     * Creates a new DrinkingHoursSightingsStatistic
     * @param incidents a list of incidents to look at
     */
    public DrinkingHoursSightingsStatistic(List<Incident> incidents) {
    	
        // Calls the superclass constructor, setting the name of the statistic
        super("Sightings during drinking hours (5pm-11pm)");
        
        this.incidents = incidents;

        // Sets the data of the statistic, to the value returned by calculateData
        setData(calculateData());
        
    }

    /**
     * Calculates the percentage of sightings reported during drinking hours
     * @return the percentage of sightings during drinking hours
     */
    @Override
    protected String calculateData() {
        
    	// stores the number of sightings during drinking hours, initialized to zero
        int drinkingHoursSightings = 0;
        
        // for each incident
        for (Incident incident : incidents) {
            
        	// get the date and time of the incident
            String dateAndTime = incident.getDateAndTime();
            // extract the characters denoting the hour information from the string
            String hourString = dateAndTime.substring(11, 13);
            // parse this into an primitive integer type
            int hour = Integer.parseInt(hourString);
            
            // if the hour is between 5pm (i.e. 17) and 11pm (i.e. 23)
            if ((hour >= 17 ) && (hour <= 23))
            	// increment the number of sightings during drinking hours by 1
                drinkingHoursSightings++;
        
        }
        
        // get the total number of sightings, from the size of the incidents list
        int totalSightings = incidents.size();
        // calculate the percentage of sightings during drinking hours
        double percentageOfDrinkingHoursSightings = (double) drinkingHoursSightings / totalSightings * 100;
        // round this to one decimal place
        double roundedPercentage = Math.round(percentageOfDrinkingHoursSightings * 10) / 10.0;
        
        // return the percentage
        return roundedPercentage + "%";
        
    }
    
}
