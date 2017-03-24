package controller;

import api.ripley.Incident;
import models.statistics.*;
import views.Directions;
import views.statistics.Area;
import views.statistics.StatisticsPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * This is the controller between the models.statistics model and the StatisticPanel view
 *
 * @author Robert Greener.
 */
public class StatisticController {
    /**
     * The StatisticsPanel
     */
    private StatisticsPanel statisticsPanel;

    /**
     * A List of the indices of allStatistics that are currently visible on the display
     */
    private List<Integer> visibleIndices;

    /**
     * All of the statistics
     */
    private AllStatistics allStatistics;

    /**
     * Makes a new StatisticController
     * @param incidents All of the incidents to calculate statistics for
     * @param startDate the start date
     * @param endDate the end date
     */
    public StatisticController(List<Incident> incidents, Date startDate, Date endDate) {
        // Make a new AllStatistics and add all of the statistics
        allStatistics = new AllStatistics();
        allStatistics.add(new HoaxStatistic(incidents));
        allStatistics.add(new NonUSSightingsStatistic(incidents));
        allStatistics.add(new PredictedNextStateStatistic(incidents));
        allStatistics.add(new YouTubeSightingsStatistic(startDate, endDate));
        allStatistics.add(new MostCommonStatistic(incidents));
        allStatistics.add(new SightingsPerDayStatistic(incidents, startDate, endDate));
        allStatistics.add(new DrinkingHoursSightingsStatistic(incidents));
        allStatistics.add(new MaxSightingsInADayStatistic(incidents));

        // Set the initial visibleIndices to [0,1,2,3]
        visibleIndices = new ArrayList<>(Arrays.asList(0,1,2,3));
        // Create a new statisticsPanel
        statisticsPanel = new StatisticsPanel(this, allStatistics.get(0), allStatistics.get(1), allStatistics.get(2),
                allStatistics.get(3));
    }

    /**
     * Moves between the statistics at a location, in a direction
     * @param area what area of the screen should be moved
     * @param directions the direction to move in
     */
    public void move (Area area, Directions directions) {
        // Get the currentIndex
        int currentIndex = visibleIndices.get(area.getValue());
        do {
            // add the value of directions to currentIndex
            currentIndex += directions.getValue();

            // If currentIndex is less than 0, set it to the maximum index
            if (currentIndex < 0) currentIndex = allStatistics.size() - 1;
            // If currentIndex is higher than the maximum index, set it to 0
            else if (currentIndex >= allStatistics.size()) currentIndex = 0;
        } while (visibleIndices.contains(currentIndex)); // While the index is already visible
        // Update visibleIndices
        visibleIndices.set(area.getValue(), currentIndex);

        // Change the statistic on the panel
        statisticsPanel.changeStatistic(area, allStatistics.get(currentIndex));
    }

    /**
     * Gets the panel for the StatisticController
     * @return the StatisticsPanel
     */
    public StatisticsPanel getPanel() {
        return statisticsPanel;
    }
}
