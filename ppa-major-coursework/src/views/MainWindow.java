package views;

import controller.MainController;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import views.game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

/**
 * This is the MainWindow of the display for the Ripley API GUI
 *
 * @author Robert Greener
 */
public class MainWindow extends JFrame {
    /**
	 * This is the serial version UID used for serialising this class
	 */
	private static final long serialVersionUID = -3546518973698851133L;

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
     * This is what stores the 'To' Date
     */
    private UtilDateModel dateModelTo;

    /**
     * This is what stores the 'From' Date
     */
    private UtilDateModel dateModelFrom;

    /**
     * Makes a new MainWindow
     *
     * @param controller The controller for the MainWindow
     * @param panelToDisplay The initial JPanel to display
     */
    public MainWindow(MainController controller, JPanel panelToDisplay) {
        // Calls the superclass constructor to set the title of the window
        super("Ripley API Interface");

        // set the default close operation to DISPOSE_ON_CLOSE
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        this.panelOnDisplay = panelToDisplay;

        // Set the layout of the window to a border layout
        this.setLayout(new BorderLayout());

        // Create a new JPanel for the south part of the JFrame, with a border layout
        JPanel jpSouth = new JPanel(new BorderLayout());

        // Create left and right buttons
        leftButton = new JButton("<");
        leftButton.addActionListener((e) -> controller.move(Directions.LEFT));

        rightButton = new JButton(">");
        rightButton.addActionListener((e) -> controller.move(Directions.RIGHT));

        // dateLabel (center-aligned)
        dateLabel = new JLabel("Last set of reported incidents: -", SwingConstants.CENTER);

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

        calculateDataButton.addActionListener((ActionEvent e) ->
                controller.reCalculateData(dateModelFrom.getValue(), dateModelTo.getValue()));


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

    /**
     * Changes the visible panel on the display
     * @param panelToDisplay the new JPanel to display
     */
    public void setPanelOnDisplay(JPanel panelToDisplay) {
        // Remove the old JPanel from the window
        this.remove(panelOnDisplay);

        // Add the new JPanel to the window
        this.add(panelToDisplay, BorderLayout.CENTER);

        // Set the window to be resizable
        this.setResizable(true);

        // If the panel is the GamePanel or the menu for it, set the windows size
        if (panelToDisplay instanceof GamePanel || panelToDisplay instanceof views.game.Menu) {
            this.setSize(GamePanel.Width + 100, GamePanel.Height + 100);
            this.setResizable(false);
        }

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
    public void enableButtons(boolean enable) {
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
     * Gets the current panel on display
     * @return the current panel on display
     */
    public JPanel getPanelOnDisplay() {
        return panelOnDisplay;
    }

    /**
     * Set the time the info was last updated
     * @param date the text to update it with
     */
    public void setLastUpdated (String date) {
        // Update the label, with the date formatted by dateFormat
        dateLabel.setText(date);
    }

    /**
     * Show an error message to the user
     * @param title The title of the dialog
     * @param message The main message of the window
     */
    public void showErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
}
