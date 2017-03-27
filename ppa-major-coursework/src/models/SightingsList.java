/**
 * 
 */
package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
		this.incidents = incidents;//
		
		parseIncidents();
		
	}
	
	public void parseIncidents() {
		
		for (Incident incident : incidents) {
			
			ParsedIncident parsedIncident = new ParsedIncident();			
			parsedIncident.setDateAndTime(parseDateAndTime(incident.getDateAndTime()));
			parsedIncident.setCity(incident.getCity());
			parsedIncident.setShape(incident.getShape());
			parsedIncident.setDuration(parseDuration(incident.getDuration()));
			parsedIncident.setDatePosted(parseDatePosted(incident.getPosted()));
			
			parsedIncidents.add(parsedIncident);
			
		}
		
	}
	
	public LocalDateTime parseDateAndTime(String dateAndTime) {
		
		//remove the seconds information
		String toParse = dateAndTime.substring(0, 16);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH-mm");
		LocalDateTime parsedDateAndTime = LocalDateTime.parse(toParse, formatter);
		
		return parsedDateAndTime;
		
	}
	
	public int parseDuration(String duration) {
		
		//implement the Natty stuff
		return -1;
		
	}
	
	public void sortIncidents(String sortOption) {
		
		//sorting
		
	}
	
	public String getStateName() {
		
		return stateName;
		
	}
	
	public String getAbbreviatedStateName() {
		
		return abbreviatedStateName;
		
	}
	
}
