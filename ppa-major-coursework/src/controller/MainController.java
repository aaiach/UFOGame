package controller;

import api.ripley.Incident;
import api.ripley.Ripley;
import views.Directions;
import views.MainWindow;
import views.WelcomePanel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The controller for the MainWindow and for the WelcomePanel
 *
 * @author  Robert Greener
 */
public class MainController {
    /**
     * The format for the date as yyyy-MM-dd
     */
    private SimpleDateFormat dateFormat;

    /**
     * The Ripley API
     */
    private Ripley ripley;

    /**
     * The MainWindow - the JFrame for the program
     */
    private MainWindow window;

    /**
     * A list of JPanels that makes up the window
     */
    private List<JPanel> panels;

    /**
     * The date format needed for the Ripley API
     */
    private SimpleDateFormat ripleyAPIDateFormat;

    /**
     * The Private Key for the Ripley API
     */
    private static final String PRIVATE_API_KEY = "90tLI3CRs9iyVD6ql2OMtA==";

    /**
     * The Public Key for the Ripley API
     */
    private static final String PUBLIC_API_KEY =  "lBgm4pRv9wjVqL46EnH7ew==";

    /**
     * The first line to display on the WelcomePanel
     */
    private String firstLine;

    /**
     * Creates a new MainController, that will run the whole program
     */
    public MainController() {
        // Create an instance of ripley
        ripley = new Ripley(PRIVATE_API_KEY, PUBLIC_API_KEY);

        // Print acknowledgment string to STDOUT
        System.out.println(ripley.getAcknowledgementString());

        // Set the date format required for the ripley API
        ripleyAPIDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Set the date format used to display the date chosen to the user
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Initialise the firstLine to display on the WelcomePanel with the Ripley version
        firstLine = "Welcome to the Ripley API v" + ripley.getVersion() +
                "\nPlease select from the dates above, in order to" +
                "\nbegin analysing UFO sighting data";

        // Create a new WelcomePanel
        WelcomePanel welcomePanel = new WelcomePanel();

        // Add the first line to the welcomePanel
        welcomePanel.addText(firstLine);

        // Create a new MainWindow with the initial JPanel as the welcomePanel
        window = new MainWindow(this, welcomePanel);
        // Disable the left and right buttons on the window
        window.enableButtons(false);

        // Create an ArrayList to store the JPanels of the display in order
        panels = new ArrayList<>();
        panels.add(0, welcomePanel);
    }

    /**
     * Calculates all of the data, getting the incidents from ripley, and calculating the statistics
     * @param dateFrom the start date
     * @param dateTo the end date
     */
    public void reCalculateData(Date dateFrom, Date dateTo) {
        // If either date is null, show an error message
        if (dateFrom == null || dateTo == null) window.showErrorMessage("Date Error",
                "Please make sure there is a start and end date");
        // If the start date is after the end date, show an error message
        else if (dateFrom.getTime() > dateTo.getTime()) window.showErrorMessage("Date Error",
                "The start date is after the end date");
        else {
            // Disable the left and right buttons on the window
            window.enableButtons(false);

            // Get the welcomePanel
            WelcomePanel welcomePanel = (WelcomePanel) panels.get(0);
            window.setPanelOnDisplay(welcomePanel);
            // Clear the window
            welcomePanel.clear();

            // Re-add the first line
            welcomePanel.addText(firstLine);
            // Add the date range selected
            welcomePanel.addText("Date range selected: " + dateFormat.format(dateFrom) + " to " +
                    dateFormat.format(dateTo));
            welcomePanel.addText("Grabbing data....");

            // Get the current time as the start time
            long startTime = System.currentTimeMillis();
            // Get the incidents
            List<Incident> incidents = ripley.getIncidentsInRange(ripleyAPIDateFormat.format(dateFrom), ripleyAPIDateFormat.format(dateTo));

            // If there were no incidents, show an error message
            if (incidents == null || incidents.size() == 0) window.showErrorMessage("No incidents in date range",
                    "No incidents were found in the supplied date range");
            else {
                // Create a new StatisticController
                StatisticController statisticController = new StatisticController(incidents, dateFrom, dateTo);
                // Add the StatisticPanel to the panels List
                panels.add(1, statisticController.getPanel());

                // Get the end time
                long endTime = System.currentTimeMillis();

                // Calculate the time difference
                long timeDifference = endTime - startTime;

                // Calculate the minutes and seconds
                long minutes = timeDifference / 1000 / 60;
                long seconds = (timeDifference / 1000) % 60;

                // Add time taken to welcome panel
                welcomePanel.addText("Data grabbed in: " + minutes + " minutes " + seconds + " seconds");
                welcomePanel.addText("Please now interact with this data using the" +
                        "\nbuttons to the left and right", true);

                // Change last updated time
                window.setLastUpdated(ripley.getLastUpdated());

                // Enable the right button
                window.enableButton(Directions.RIGHT, true);
            }
        }
    }

    /**
     * Moves between the JPanels in a supplied direction
     * @param direction The direction to move in
     */
    public void move (Directions direction) {
        // Get the index of the current panel
        int index = panels.indexOf(window.getPanelOnDisplay());
        // Move by the value of the direction (+/-)1
        int newIndex = index + direction.getValue();
        // Enable both the left and right button
        window.enableButtons(true);

        // If there is no JPanel to the left, disable the left button
        if (newIndex == 0) window.enableButton(Directions.LEFT, false);
        // If there is no JPanel to the right, disable the right button
        else if (newIndex == panels.size() - 1) window.enableButton(Directions.RIGHT, false);
        // Change the window's panel
        window.setPanelOnDisplay(panels.get(newIndex));
    }
}
