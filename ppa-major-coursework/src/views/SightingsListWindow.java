/**
 * 
 */
package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

import models.*;
import controller.*;

/**
 * @author bunny
 *
 */
public class SightingsListWindow extends JFrame implements Observer {

	private SightingsList sightingsList;
	private DefaultListModel<ParsedIncident> jListModel;
	private List<ParsedIncident> parsedIncidents;
	
	public SightingsListWindow(SightingsList sightingsList, SightingsListController controller) {
		
		this.sightingsList = sightingsList;
		setTitle(sightingsList.getStateName() + "(" + sightingsList.getAbbreviatedStateName() + ")");
		
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		String[] sortOptions = {"-", "Date", "City", "Shape", "Duration", "Posted"};
		JComboBox sortOptionsList = new JComboBox(sortOptions);
		sortOptionsList.addActionListener(controller);
		
		topPanel.add(sortOptionsList);
		
		initJListModel();
		JList jList = new JList(jListModel);
		JScrollPane scrollPane = new JScrollPane(jList);
		
		add(topPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(700, 250));
		setLocation(300, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	
	public void initJListModel() {
		
		jListModel = new DefaultListModel<ParsedIncident>();
		parsedIncidents = sightingsList.getParsedIncidents();
		
		for (ParsedIncident parsedIncident : parsedIncidents) {
			
			jListModel.addElement(parsedIncident);
			
		}
		
	}
	
	public void update(Observable o, Object arg) {
		
		for (int i = 0; i < parsedIncidents.size(); i++) {
			
			jListModel.set(i, parsedIncidents.get(i));
			
		}
		
	}
	
}
