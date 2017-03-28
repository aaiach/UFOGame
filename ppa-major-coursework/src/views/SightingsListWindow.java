/**
 * 
 */
package views;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.*;

import models.*;
import controller.*;

/**
 * @author bunny
 *
 */
public class SightingsListWindow extends JFrame implements Observer {

	private SightingsList sightingsList;
	
	public SightingsListWindow(SightingsList sightingsList, SightingsListController controller) {
		
		this.sightingsList = sightingsList;
		setTitle(sightingsList.getStateName() + "(" + sightingsList.getAbbreviatedStateName() + ")");
		
		setLayout(new BorderLayout());
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		String[] sortOptions = {"-", "Date", "City", "Shape", "Duration", "Posted"};
		JComboBox sortOptionsList = new JComboBox(sortOptions);
		sortOptionsList.addActionListener(controller);
		//
		topPanel.add(sortOptionsList);
		
	}
	
}
