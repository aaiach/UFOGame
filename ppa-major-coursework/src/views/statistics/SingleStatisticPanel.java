package views.statistics;

import controller.StatisticController;
import models.statistics.Statistic;

import javax.swing.*;
import java.awt.*;

/**
 * @author Robert Greener.
 */
public class SingleStatisticPanel extends JPanel {
    private JLabel statisticTitle;
    private JLabel statisticData;
    private JButton leftButton;
    private JButton rightButton;
    private Statistic statistic;

    public SingleStatisticPanel(StatisticController controller, Statistic statistic) {
        this.statisticTitle = new JLabel();
        this.statisticData = new JLabel();
        this.leftButton = new JButton("<");
        this.rightButton = new JButton(">");
        this.setLayout(new BorderLayout());
        this.add(statisticTitle, BorderLayout.NORTH);
        this.add(statisticData, BorderLayout.CENTER);
        this.add(leftButton, BorderLayout.WEST);
        this.add(rightButton, BorderLayout.EAST);
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
        this.statisticTitle.setText(statistic.getName());
        this.statisticData.setText(statistic.getData());
        this.updateUI();
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
