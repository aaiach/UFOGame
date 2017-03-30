/**
 * 
 */
package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import models.*;
import controller.*;

/**
 * @author Shantanu Shekhar Jha
 *
 */
public class SightingsListWindow extends JFrame implements Observer {

	private SightingsList sightingsList;
	private DefaultListModel<ParsedIncident> jListModel;
	private List<ParsedIncident> parsedIncidents;
	
	public SightingsListWindow(SightingsListController controller, SightingsList sightingsList) {
		
		this.sightingsList = sightingsList;
		setTitle(sightingsList.getStateName() + " (" + sightingsList.getAbbreviatedStateName() + ")");
		
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		topPanel.setLayout(new GridLayout(1, 1));
		
		String[] sortOptions = {"-", "Date", "City", "Shape", "Duration", "Posted"};
		JComboBox sortOptionsList = new JComboBox(sortOptions);
		sortOptionsList.addActionListener(controller);
		
		topPanel.add(sortOptionsList);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBorder(new EmptyBorder(8, 8, 8, 8));
		bottomPanel.setLayout(new GridLayout(1, 1));
		bottomPanel.setBackground(Color.white);
		
		initJListModel();
		JList jList = new JList(jListModel);	
		jList.addMouseListener(controller);
		
		JScrollPane scrollPane = new JScrollPane(jList);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		bottomPanel.add(scrollPane);
		
		add(topPanel, BorderLayout.NORTH);
		add(bottomPanel, BorderLayout.CENTER);
		
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
