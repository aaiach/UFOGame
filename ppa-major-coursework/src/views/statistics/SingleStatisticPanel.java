package views.statistics;

import controller.StatisticController;
import models.statistics.Statistic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author Robert Greener.
 */
public class SingleStatisticPanel extends JPanel {
    private JLabel statisticTitle;
    private JLabel statisticData;
    private JButton leftButton;
    private JButton rightButton;
    private Area area;
    private Statistic statistic;
    private StatisticController controller;

    public SingleStatisticPanel(StatisticController controller, Area area, Statistic statistic) {
        this.statisticTitle = new JLabel("", SwingConstants.CENTER);
        this.statisticData = new JLabel("", SwingConstants.CENTER);
        this.area = area;
        this.controller = controller;
        setStatistic(statistic);
        this.leftButton = new JButton("<");
        this.rightButton = new JButton(">");
        this.setLayout(new BorderLayout());
        this.add(statisticTitle, BorderLayout.NORTH);
        this.add(statisticData, BorderLayout.CENTER);
        this.add(leftButton, BorderLayout.WEST);
        leftButton.addActionListener((ActionEvent e) ->
            this.controller.move(this.area, Directions.LEFT)
        );

        this.add(rightButton, BorderLayout.EAST);
        rightButton.addActionListener((ActionEvent e) ->
            this.controller.move(this.area, Directions.RIGHT)
        );
    }

    public void setStatistic(Statistic statistic) {
        this.statistic = statistic;
        this.statisticTitle.setText("<html><h1>" + statistic.getName() + "</h1></html>");
        this.statisticData.setText(statistic.getData());
        this.updateUI();
    }

    public Statistic getStatistic() {
        return statistic;
    }
}
