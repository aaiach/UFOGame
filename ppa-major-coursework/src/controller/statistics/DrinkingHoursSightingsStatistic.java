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

    private List<Incident> incidents;
    
    /**
     * Creates a new DrinkingHoursSightingsStatistic
     */
    public DrinkingHoursSightingsStatistic() {
        // Calls the superclass constructor, setting the name of the statistic
        super("Sightings during drinking hours (5pm-11pm)");

        // Sets the data of the statistic, to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the data for this statistic
     * @return always returns "Not Implemented"
     */
    @Override
    protected String calculateData() {
        
        int drinkingHoursSightings = 0;
        
        for (Incident incident : incidents) {
            
            String dateAndTime = incident.getDateAndTime();
            String[] dateAndTimeArray = dateAndTime.split(" ");
            String time = dateAndTimeArray[1];
            
            String hourString = time.substring(0, 2);
            int hour = Integer.parseInt(hourString);
            
            if ((hour >= 17 ) && (hour <= 23))
                drinkingHoursSightings++;
        
        }
        
        int totalSightings = incidents.size();
        double percentageOfDrinkingHoursSightings = (double) drinkingHoursSightings / totalSightings * 100;
        double roundedPercentage = Math.round(percentageOfDrinkingHoursSightings * 10) / 10.0;
        
        return roundedPercentage + "%";
        
    }
    
}
