/**
 * 
 */
package models;

/**
 * @author bunny
 *
 */
public class SightingsList extends Observable {

	private String stateName;
	private String abbreviatedStateName;
	private List<Incident> incidents;
	private List<ParsedIncident> parsedIncidents;
	
	public SightingsList(String stateName, String abbreviatedStateName, List<Incident> incidents) {
		
		this.stateName = stateName;
		this.abbreviatedStateName = abbreviatedStateName;
		this.incidents = incidents;
		
		parseIncidents();
		
	}
	
	public void parseIncidents() {
		
		for (Incident incident : incidents) {
			
			ParsedIncident parsedIncident = new ParsedIncident();
			parsedIncident.set
			
		}
		
	}
	
	public void sortIncidents(String sortOption) {
		
		
		
	}
	
	public String getStateName() {
		
		return stateName;
		
	}
	
	public String getAbbreviatedStateName() {
		
		return abbreviatedStateName;
		
	}
	
}
