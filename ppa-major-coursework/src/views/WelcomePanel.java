package views;

import javax.swing.*;
import java.awt.*;

/**
 * @author Robert Greener.
 */
public class WelcomePanel extends JPanel {

    public WelcomePanel() {
        this.setLayout(new GridLayout(5, 1, 0, 10));
    }

    public void addText(String text) {
        addText(text, false);
    }

    public void addText(String text, boolean bold) {
        if (bold) {
            this.add(new JLabel("<html><b>" + text + "</b></html>"));
        } else {
            this.add(new JLabel(text));
        }
        this.updateUI();
    }

    public void clear() {
        this.removeAll();
        this.updateUI();
    }
}
