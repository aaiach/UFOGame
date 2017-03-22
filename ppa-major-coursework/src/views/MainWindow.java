package views;

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This is the MainWindow of the display for the Ripley API GUI
 *
 * @author Robert Greener
 */
public class MainWindow extends JFrame {
    /**
     * This is the JPanel that is currently being displayed
     */
    private JPanel panelOnDisplay;

    /**
     * This is the date label that appears at the bottom of the screen
     * that will show when the last update was
     */
    private JLabel dateLabel;

    /**
     * This is the JButton used to move to the JPanel to the left
     */
    private JButton leftButton;

    /**
     * This is the JButton used to move the JPanel to the right
     */
    private JButton rightButton;

    /**
     * This is the text the date will be prefixed by
     */
    private String datePrefix;

    /**
     * This is what stores the 'To' Date
     */
    private UtilDateModel dateModelTo;

    /**
     * This is what stores the 'From' Date
     */
    private UtilDateModel dateModelFrom;

    /**
     * This is how the date (at the bottom of the screen - last updated) should be formatted
     */
    private SimpleDateFormat dateFormat;

    /**
     * Makes a new MainWindow
     *
     * @param panelToDisplay The initial JPanel to display
     */
    public MainWindow(JPanel panelToDisplay) {
        // Calls the superclass constructor to set the title of the window
        super("Ripley API Interface");

        // set the default close operation to DISPOSE_ON_CLOSE
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        // Set the date format e.g. Wed Mar 22 15:05:32 GMT 2017
        dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");

        this.panelOnDisplay = panelToDisplay;

        // Set the layout of the window to a border layout
        this.setLayout(new BorderLayout());

        // Create a new JPanel for the south part of the JFrame, with a border layout
        JPanel jpSouth = new JPanel(new BorderLayout());

        // Create left and right buttons
        leftButton = new JButton("<");
        rightButton = new JButton(">");

        // Create the datePrefix and dateLabel (center-aligned)
        datePrefix = "Last set of reported incidents: ";
        dateLabel = new JLabel(datePrefix + "-", SwingConstants.CENTER);

        // Add the left and right buttons as well as the date label to jpSouth
        jpSouth.add(leftButton, BorderLayout.WEST);
        jpSouth.add(dateLabel, BorderLayout.CENTER);
        jpSouth.add(rightButton, BorderLayout.EAST);

        // Create a new empty properties (needed by the constructor for JDatePanelImpl)
        Properties properties = new Properties();

        // Get the ResourceBundle for the current locale
        ResourceBundle bundle = ResourceBundle.getBundle("org.jdatepicker.i18n.Text", Locale.getDefault());

        // Store the keys for the bundle in an ArrayList keys
        ArrayList<String> keys = Collections.list(bundle.getKeys());

        // For each key
        for (String key : keys) {
            // Add the key and value to properties
            properties.setProperty(key, bundle.getString(key));
        }

        // Create a new UtilDateModel for the 'From' Date, with no initial date
        dateModelFrom = new UtilDateModel(null);
        // Create a new JDatePanelImpl with the properties and dateModelFrom
        JDatePanelImpl jDatePanelFrom = new JDatePanelImpl(dateModelFrom, properties);
        // Create a new JDatePicker with jDatePanelFrom
        JDatePicker jDatePickerFrom = new JDatePickerImpl(jDatePanelFrom, new DateComponentFormatter());
        /* Set it so that the text cannot be manually typed in
         * (This is due to a bug listed on the library's GitHub page
         */
        jDatePickerFrom.setTextEditable(false);
        // Show the year buttons
        jDatePickerFrom.setShowYearButtons(true);

        // Same as above but for the 'To' Date
        dateModelTo = new UtilDateModel(null);
        JDatePanelImpl jDatePanelTo = new JDatePanelImpl(dateModelTo, properties);
        JDatePicker jDatePickerTo = new JDatePickerImpl(jDatePanelTo, new DateComponentFormatter());
        jDatePickerTo.setTextEditable(false);
        jDatePickerTo.setShowYearButtons(true);

        // Crate a JButton to get the data
        JButton calculateDataButton = new JButton("Calculate");


        // Create a JPanel for the north of the window, with a FlowLayout that goes from right to left
        JPanel jpNorth = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        // Add a label that says from
        jpNorth.add(new JLabel("From: "));
        // Add the jDatePickerFrom (it has to be cast to a JComponent, as per the documentation for the library)
        jpNorth.add((JComponent) jDatePickerFrom);

        // Add the to label and datePicker
        jpNorth.add(new JLabel("To: "));
        jpNorth.add((JComponent) jDatePickerTo);

        // Add the calculateDataButton
        jpNorth.add(calculateDataButton);

        // Set the preferred size of the window
        this.setPreferredSize(new Dimension(800, 600));

        // Add the 3 panels that make up the window
        this.add(jpSouth, BorderLayout.SOUTH);
        this.add(panelToDisplay, BorderLayout.CENTER);
        this.add(jpNorth, BorderLayout.NORTH);

        // Pack the window and make it visible
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("Hi"));
        MainWindow mainWindow = new MainWindow(jPanel);
    }

    /**
     * Changes the visible panel on the display
     * @param panelToDisplay the new JPanel to display
     */
    public void setPanelOnDisplay(JPanel panelToDisplay) {
        // Remove the old JPanel from the window
        this.remove(panelOnDisplay);

        // Add the new JPanel to the window
        this.add(panelToDisplay, BorderLayout.CENTER);

        // Redraw the window
        this.revalidate();
        this.repaint();

        // Change the panelOnDisplay variable to equal the new panel
        panelOnDisplay = panelToDisplay;
    }

    /**
     * Sets whether all buttons are enabled
     * @param enable true if they should be enabled, false if they should be disabled
     */
    public void enableButton(boolean enable) {
        enableButton(Directions.LEFT, enable);
        enableButton(Directions.RIGHT, enable);
    }

    /**
     * Sets whether a button is enabled
     * @param direction Directions.LEFT for left button, Directions.RIGHT for right button
     * @param enable true if the button should be enabled, false if it should be disabled
     */
    public void enableButton(Directions direction, boolean enable) {
        if (direction == Directions.LEFT) leftButton.setEnabled(enable);
        if (direction == Directions.RIGHT) rightButton.setEnabled(enable);
    }

    /**
     * Get the from Date picked
     * @return the from Date (or null, if no date has been selected)
     */
    public Date getFromDate() {
        // Get the value from the UtilDateModel
        return dateModelFrom.getValue();
    }

    /**
     * Get the to Date picked
     * @return the to Date (or null, if no date has been selected)
     */
    public Date getToDate() {
        // Get the value from the UtilDateModel
        return dateModelTo.getValue();
    }

    /**
     * Set the time the info was last updated
     * @param lastUpdated the Date when the data was updated
     */
    public void setLastUpdated (Date lastUpdated) {
        // Update the label, with the date formatted by dateFormat
        dateLabel.setText(datePrefix + dateFormat.format(lastUpdated));
    }
}
