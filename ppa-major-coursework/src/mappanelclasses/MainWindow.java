package mappanelclasses;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import java.util.Observable;
import java.util.Observer;

/**
 * A class representing the main window of the sightings viewer (not part of the CW, but for testing purposes)
 * For this program to work, the size must be kept constant at 1400x1000.
 * 
 * @author Stevan Warren K1631436
 *
 */
public class MainWindow extends JFrame implements Observer {
	private static final long serialVersionUID = 1L;
	private WelcomePanel welcomePanel;
	private MapPanel mapPanel;
	private JScrollPane scrollableMap;
	private JButton goLeft;
	private JButton goRight;
	private JComboBox<String> jcbFrom;
	private JComboBox<String> jcbTo;

	public MainWindow(RipleyDataModel dataModel) {
		welcomePanel = new WelcomePanel();
		mapPanel = new MapPanel();
		scrollableMap = new JScrollPane(mapPanel);
		init(dataModel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setSize(1400, 1000);
	}
	
	private void init(RipleyDataModel dataModel) {
		PanelChangeListener pclForwards = new PanelChangeListener(dataModel, true);
		PanelChangeListener pclBackwards = new PanelChangeListener(dataModel, false);
		RangeSelectedListener jcbListener = new RangeSelectedListener(dataModel, this);
		setTitle("UFO Sightings Viewer");
		setLayout(new BorderLayout());
		
		JPanel rangeSelector = new JPanel(new BorderLayout());
		JPanel selectorArea = new JPanel(new GridLayout(1, 4));
		JLabel from = new JLabel("From:");
		selectorArea.add(from);
		jcbFrom = new JComboBox<String>();
		jcbTo = new JComboBox<String>();
		jcbFrom.addItem("    ");
		jcbTo.addItem("    ");
		jcbFrom.addItemListener(jcbListener);
		jcbTo.addItemListener(jcbListener);
		for (int i = dataModel.getLowerBound(); i <= dataModel.getUpperBound(); i++) {
			jcbFrom.addItem("" + i);
			jcbTo.addItem("" + i);
		}
		selectorArea.add(jcbFrom);
		JLabel to = new JLabel("To:");
		selectorArea.add(to);
		selectorArea.add(jcbTo);
		rangeSelector.add(selectorArea, BorderLayout.EAST);
		add(rangeSelector, BorderLayout.NORTH);
		
		JPanel navigationPanel = new JPanel(new BorderLayout());
		goLeft = new JButton("  <  ");
		goLeft.addActionListener(pclBackwards);
		goLeft.setEnabled(false);
		navigationPanel.add(goLeft, BorderLayout.WEST);
		JLabel lastUpdated = new JLabel(dataModel.getLastUpdated(), SwingConstants.CENTER);
		navigationPanel.add(lastUpdated, BorderLayout.CENTER);
		goRight = new JButton("  >  ");
		goRight.addActionListener(pclForwards);
		goRight.setEnabled(false);
		navigationPanel.add(goRight, BorderLayout.EAST);
		add(navigationPanel, BorderLayout.SOUTH);
	}
	
	public String getFrom() {
		return (String)jcbFrom.getSelectedItem();
	}
	
	public String getTo() {
		return (String)jcbTo.getSelectedItem();
	}
	
	public boolean rangeSelected() {
		return (!(jcbFrom.getSelectedItem().equals("    ") || jcbTo.getSelectedItem().equals("    ")));
	}
	
	public void update(Observable o, Object arg) {
		goLeft.setEnabled(true);
		goRight.setEnabled(true);
		getContentPane().remove(welcomePanel);
		getContentPane().remove(mapPanel);
		int panelSelected = ((RipleyDataModel)o).panelSelected();
		if (panelSelected == 1) {
			add(welcomePanel, BorderLayout.CENTER);
		} else if (panelSelected == 2) {
			mapPanel.update(((RipleyDataModel)o).incidentsInRange());
			add(scrollableMap, BorderLayout.CENTER);
		}
		validate();
		repaint();
	}
}
