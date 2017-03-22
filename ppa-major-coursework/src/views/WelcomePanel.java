package views;

import javax.swing.*;
import java.awt.*;

/**
 * This is the first panel on the display
 *
 * @author Robert Greener.
 */
public class WelcomePanel extends JPanel {

    /**
     * Make a new WelcomePanel
     */
    public WelcomePanel() {
        // Set the layout to a grid layout, with 5 rows, 1 column, and vertical spacing of 10px
        this.setLayout(new GridLayout(5, 1, 0, 10));
    }

    /**
     * Add text to the WelcomePanel
     * @param text the text to add
     */
    public void addText(String text) {
        addText(text, false);
    }

    /**
     * Add text to the WelcomePanel
     * @param text the text to add
     * @param bold true if the text should be bold
     */
    public void addText(String text, boolean bold) {
        if (bold) {
            // Add a new JLabel, surrounded by HTML tags to make it bold
            this.add(new JLabel("<html><b>" + text + "</b></html>"));
        } else {
            // Add a new JLabel
            this.add(new JLabel(text));
        }
        // Redraw the display
        this.updateUI();
    }

    /**
     * Clear the display
     *
     * Removes all child JComponents
     */
    public void clear() {
        // Removes all children of the panel
        this.removeAll();

        // Redraws the display
        this.updateUI();
    }
}
