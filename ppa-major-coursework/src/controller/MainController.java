package controller;

import api.ripley.Incident;
import api.ripley.Ripley;
import views.MainWindow;
import views.WelcomePanel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by robert on 21/03/17.
 */
public class MainController {
    private Ripley ripley;
    private MainWindow window;
    private List<Incident> incidents;
    private List<JPanel> panels;
    private SimpleDateFormat ripleyAPIDateFormat;
    private SimpleDateFormat timeFormat;
    private Date dateFrom;
    private Date dateTo;
    private static final String PRIVATE_API_KEY = "90tLI3CRs9iyVD6ql2OMtA==";
    private static final String PUBLIC_API_KEY =  "lBgm4pRv9wjVqL46EnH7ew==";

    public MainController() {
        ripley = new Ripley(PRIVATE_API_KEY, PUBLIC_API_KEY);
        ripleyAPIDateFormat = new SimpleDateFormat("yyyy-mm-dd");
        timeFormat = new SimpleDateFormat("HH:mm:ss");
        String firstLine = "Welcome to the Ripley API v" + ripley.getVersion() +
                "\nPlease select from the dates above, in order to" +
                "\nbegin analysing UFO sighting data";
        WelcomePanel welcomePanel = new WelcomePanel();
        welcomePanel.addText(firstLine);
        window = new MainWindow(this, welcomePanel);
        panels.set(0, welcomePanel);
    }

    public void reCalculateData(Date dateFrom, Date dateTo) {
        if (dateFrom.getTime() > dateTo.getTime()) window.showErrorMessage("Date Error",
                "The start date is after the end date");
        else {
            window.enableButtons(false);
            WelcomePanel welcomePanel = (WelcomePanel) panels.get(0);
            String firstLine = "Welcome to the Ripley API v" + ripley.getVersion() +
                    "\nPlease select from the dates above, in order to" +
                    "\nbegin analysing UFO sighting data";
            welcomePanel.clear();
            welcomePanel.addText(firstLine);
            welcomePanel.addText("Date range selected: " +
                    ripleyAPIDateFormat.format(dateFrom) + " to " + ripleyAPIDateFormat.format(dateTo));
            welcomePanel.addText("Grabbing data....");
            Date startTime = new Date();
            incidents = ripley.getIncidentsInRange(ripleyAPIDateFormat.format(dateFrom), ripleyAPIDateFormat.format(dateTo));
            Date endTime = new Date();
            Date timeDifference = new Date(endTime.getTime() - startTime.getTime());
            welcomePanel.addText("Data grabbed in: " + timeFormat.format(timeDifference));
            welcomePanel.addText("Please now interact with this data using the" +
                    "\nbuttons to the left and right.");
            window.setLastUpdated(new Date());
        }
    }

}
