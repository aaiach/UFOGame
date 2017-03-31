package views.statistics;

import controller.StatisticController;
import models.statistics.Statistic;

import javax.swing.*;
import java.awt.*;

/**
 * StatisticsPanel is the 3rd Panel on the display, it is the one that display's
 * 4 statistics at a time
 *
 * @author Robert Greener
 */
public class StatisticsPanel extends JPanel {
    /**
	 * This is the serial version UID used for serialising this class
	 */
	private static final long serialVersionUID = 3840356204908710492L;

	/**
     * The statistic to display in the top left
     */
    private SingleStatisticPanel topLeft;

    /**
     * The statistic to display in the top right
     */
    private SingleStatisticPanel topRight;

    /**
     * The statistic to display in the bottom left
     */
    private SingleStatisticPanel bottomLeft;

    /**
     * The statistic to display in the bottom right
     */
    private SingleStatisticPanel bottomRight;

    /**
     * Makes a new StatisticPanel
     * @param controller a reference to the StatisticController
     * @param statisticTopLeft the Statistic to go in the top left
     * @param statisticTopRight the Statistic to go in the top right
     * @param statisticBottomLeft the Statistic to go in the bottom left
     * @param statisticBottomRight the Statistic to go in the bottom right
     */
    public StatisticsPanel(StatisticController controller, Statistic statisticTopLeft, Statistic statisticTopRight,
                           Statistic statisticBottomLeft, Statistic statisticBottomRight) {

        // Set the layout to a GridLayout with 2 rows, 2 columns and a hgap & vgap of 15px
        this.setLayout(new GridLayout(2, 2, 15, 15));

        // Create new SingleStatisticPanels for each area
        topLeft = new SingleStatisticPanel(controller, Area.TOP_LEFT, statisticTopLeft);
        topRight = new SingleStatisticPanel(controller, Area.TOP_RIGHT, statisticTopRight);
        bottomLeft = new SingleStatisticPanel(controller, Area.BOTTOM_LEFT, statisticBottomLeft);
        bottomRight = new SingleStatisticPanel(controller, Area.BOTTOM_RIGHT, statisticBottomRight);

        // Add the SingleStatisticPanels to this Panel
        this.add(topLeft);
        this.add(topRight);
        this.add(bottomLeft);
        this.add(bottomRight);
    }

    /**
     * Change the Statistic at a given area
     * @param area The area to change
     * @param statistic the new statistic
     */
    public void changeStatistic (Area area, Statistic statistic) {
        // Change to corresponding SingleStatisticPanel's statistic;
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

        // Redraw this panel
        this.updateUI();
    }
}
