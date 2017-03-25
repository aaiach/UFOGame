/**
 * 
 */
package models;

/**
 * @author bunny
 *
 */
public class SightingsList extends Observable {

	private String state;
	private List<Incident> incidents;
	
	public SightingsList(String state, List<Incident> incidents) {
		
		this.state = state;
		this.incidents = incidents;
		
	}
	
	public void sortIncidents(Object key) {
		
		
		
	}
	
	public String getState() {
		
		return state;
		
	}
	
}
