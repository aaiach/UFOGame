package controller;

import api.ripley.Incident;
import api.ripley.Ripley;
import models.statistics.*;
import views.Directions;
import views.statistics.Area;
import views.statistics.Directions;
import views.statistics.StatisticsPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Robert Greener.
 */
public class StatisticController {
    private Ripley ripley;
    private StatisticsPanel statisticsPanel;
    private List<Integer> visibleIndices;
    private AllStatistics allStatistics;

    public StatisticController(Ripley ripley, List<Incident> incidents, Date startDate, Date endDate) {
        this.ripley = ripley;
        allStatistics = new AllStatistics();
        allStatistics.add(new HoaxStatistic(ripley, incidents));
        allStatistics.add(new NonUSSightingsStatistic(incidents));
        allStatistics.add(new PredictedNextStateStatistic(incidents));
        allStatistics.add(new YouTubeSightingsStatistic(startDate, endDate));
        allStatistics.add(new MostCommonStatistic(incidents));
        allStatistics.add(new SightingsPerDayStatistic(incidents, startDate, endDate));
        allStatistics.add(new StevanWarrenStatistic());
        allStatistics.add(new ShantanuJhaStatistic());
        visibleIndices = new ArrayList<>(Arrays.asList(0,1,2,3));
        statisticsPanel = new StatisticsPanel(this, allStatistics.get(0), allStatistics.get(1), allStatistics.get(2),
                allStatistics.get(3));
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

    public StatisticsPanel getPanel() {
        return statisticsPanel;
    }
}
