package views.statistics;

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

    public SingleStatisticPanel(String statisticTitleText, String statisticDataText) {
        this.statisticTitle = new JLabel(statisticTitleText);
        this.statisticData = new JLabel(statisticDataText);
        this.leftButton = new JButton("<");
        this.rightButton = new JButton(">");
        this.setLayout(new BorderLayout());
        this.add(statisticTitle, BorderLayout.NORTH);
        this.add(statisticData, BorderLayout.CENTER);
        this.add(leftButton, BorderLayout.WEST);
        this.add(rightButton, BorderLayout.EAST);
    }

    public String getStatisticTitleText() {
        return statisticTitle.getText();
    }

    public String getStatisticTitleData() {
        return statisticData.getText();
    }

    public void setStatisticTitleText(String text) {
        statisticTitle.setText(text);
    }

    public void setStatisticDataText(String text) {
        statisticData.setText(text);
    }
}
