package controller;

import api.ripley.Ripley;
import models.statistics.AllStatistics;
import views.statistics.Area;
import views.statistics.Directions;
import views.statistics.StatisticsPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Robert Greener.
 */
public class StatisticController {
    private Ripley ripley;
    private StatisticsPanel statisticsPanel;
    private List<Integer> visibleIndices;
    private AllStatistics allStatistics;

    public StatisticController(Ripley ripley) {
        this.ripley = ripley;
        allStatistics = new AllStatistics();
        visibleIndices = new ArrayList<Integer>(Arrays.asList(0,1,2,3));
    }

    public void move (Area area, Directions directions) {
        int currentIndex = visibleIndices.get(area.getValue());
        do {
            currentIndex += directions.getValue();
            if (currentIndex < 0) currentIndex = allStatistics.size() - 1;
            else if (currentIndex >= allStatistics.size()) currentIndex = 0;
        } while (visibleIndices.contains(currentIndex));
        visibleIndices.set(area.getValue(), currentIndex);
        statisticsPanel.changeStatistic(area, allStatistics.get(currentIndex));
    }
}
