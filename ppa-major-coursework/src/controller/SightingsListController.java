/**
 * 
 */
package controller;

import java.awt.event.*;

import javax.swing.JComboBox;

import models.*;

/**
 * @author bunny
 *
 */
public class SightingsListController implements ActionListener {
	
	private SightingsList sightingsList;
	
	public SightingsListController(SightingsList sightingsList) {
		
		this.sightingsList = sightingsList;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		JComboBox cb = (JComboBox)e.getSource();
		String sortOption = (String)cb.getSelectedItem();
		sightingsList.sortIncidents(sortOption);
		
	}
	
}
