package views.statistics;

import controller.StatisticController;
import models.statistics.Statistic;
import views.Directions;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * SingleStatisticPanel is the JPanel that is used to display one of the statistics.
 *
 * @author Robert Greener.
 */
public class SingleStatisticPanel extends JPanel {
    /**
     * The title of the statistic
     */
    private JLabel statisticTitle;

    /**
     * The data of the statistic
     */
    private JLabel statisticData;

    /**
     * The area at which this panel is displayed
     */
    private Area area;

    /**
     * The controller for the statistic
     */
    private StatisticController controller;

    /**
     * Makes a new SingleStatisticPanel
     * @param controller a reference to the StatisticController
     * @param area the area this is displayed at
     * @param statistic the statistic to display
     */
    public SingleStatisticPanel(StatisticController controller, Area area, Statistic statistic) {
        // Create blank JLabel's for the title and data
        this.statisticTitle = new JLabel("", SwingConstants.CENTER);
        this.statisticData = new JLabel("", SwingConstants.CENTER);

        // Set the controller
        this.controller = controller;

        // Set the statistic
        setStatistic(statistic);

        // Make new left and right buttons
        JButton leftButton = new JButton("<");
        JButton rightButton = new JButton(">");

        // Make the panel have a grid layout
        this.setLayout(new BorderLayout());

        // Add the title and data labels
        this.add(statisticTitle, BorderLayout.NORTH);
        this.add(statisticData, BorderLayout.CENTER);

        // Add the left button
        this.add(leftButton, BorderLayout.WEST);

        // Add an ActionListener for the left button, calling controller.move
        leftButton.addActionListener((ActionEvent e) ->
            this.controller.move(this.area, Directions.LEFT)
        );

        // Add the right button
        this.add(rightButton, BorderLayout.EAST);

        // Add an ActionListener for the right button, calling controller.move
        rightButton.addActionListener((ActionEvent e) ->
            this.controller.move(this.area, Directions.RIGHT)
        );
    }

    /**
     * Set the statistic of this panel
     * @param statistic the new statistic
     */
    public void setStatistic(Statistic statistic) {

        // Set the title of the statistic
        this.statisticTitle.setText("<html><h1>" + statistic.getName() + "</h1></html>");

        // Set the data of the statistic
        this.statisticData.setText(statistic.getData());

        // Redraw this panel
        this.updateUI();
    }
}
