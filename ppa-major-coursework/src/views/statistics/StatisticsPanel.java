package views.statistics;

import controller.StatisticController;
import models.statistics.Statistic;

import javax.swing.*;
import java.awt.*;

/**
 * @author Robert Greener
 */
public class StatisticsPanel extends JPanel {
    private SingleStatisticPanel topLeft;
    private SingleStatisticPanel topRight;
    private SingleStatisticPanel bottomLeft;
    private SingleStatisticPanel bottomRight;
    private StatisticController controller;

    public StatisticsPanel(StatisticController controller, Statistic statisticTopLeft, Statistic statisticTopRight,
                           Statistic statisticBottomLeft, Statistic statisticBottomRight) {
        this.setLayout(new GridLayout(2, 2));
        topLeft = new SingleStatisticPanel(controller, Area.TOP_LEFT, statisticTopLeft);
        topRight = new SingleStatisticPanel(controller, Area.TOP_RIGHT, statisticTopRight);
        bottomLeft = new SingleStatisticPanel(controller, Area.BOTTOM_LEFT, statisticBottomLeft);
        bottomRight = new SingleStatisticPanel(controller, Area.BOTTOM_RIGHT, statisticBottomRight);
        this.controller = controller;
        this.add(topLeft);
        this.add(topRight);
        this.add(bottomLeft);
        this.add(bottomRight);
    }

    public void changeStatistic (Area area, Statistic statistic) {
        switch (area) {
            case TOP_LEFT:
                topLeft.setStatistic(statistic);
                break;
            case TOP_RIGHT:
                topRight.setStatistic(statistic);
                break;
            case BOTTOM_LEFT:
                bottomLeft.setStatistic(statistic);
                break;
            case BOTTOM_RIGHT:
                bottomRight.setStatistic(statistic);
        }
        this.updateUI();
    }

    public void changeStatistic(Statistic statisticTopLeft, Statistic statisticTopRight,
                                Statistic statisticBottomLeft, Statistic statisticBottomRight) {
        topLeft.setStatistic(statisticTopLeft);
        topRight.setStatistic(statisticTopRight);
        bottomLeft.setStatistic(statisticBottomLeft);
        bottomRight.setStatistic(statisticBottomRight);
        this.updateUI();
    }
}
