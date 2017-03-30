/**
 * 
 */
package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Observable;

import com.joestelmach.natty.*;
import api.ripley.*;

/**
 * @author Shantanu Shekhar Jha
 *
 */
public class SightingsList extends Observable {

	private String stateName;
	private String abbreviatedStateName;
	private List<Incident> incidents;
	private List<ParsedIncident> parsedIncidents;
	private List<ParsedIncident> originalParsedIncidents;
	 
	private static final String PRIVATE_API_KEY = "90tLI3CRs9iyVD6ql2OMtA==";
	private static final String PUBLIC_API_KEY = "lBgm4pRv9wjVqL46EnH7ew==";
	private Ripley ripley;
	
	public SightingsList(String stateName, String abbreviatedStateName, List<Incident> incidents) {
		
		this.stateName = stateName;
		this.abbreviatedStateName = abbreviatedStateName;
		this.incidents = incidents;
		ripley = new Ripley(PRIVATE_API_KEY, PUBLIC_API_KEY);
		
		parseIncidents();
		
	}
	
	public void parseIncidents() {
		
		parsedIncidents = new ArrayList<ParsedIncident>();
		
		for (Incident incident : incidents) {
			
			ParsedIncident parsedIncident = new ParsedIncident();			
			parsedIncident.setDateAndTime(parseDateAndTime(incident.getDateAndTime()));
			parsedIncident.setCity(incident.getCity());
			parsedIncident.setShape(incident.getShape());
			parsedIncident.setDuration(parseDuration(incident.getDuration()));
			parsedIncident.setDatePosted(parseDatePosted(incident.getPosted()));
			parsedIncident.setIncidentID(incident.getIncidentID());
			parsedIncident.setSummary(incident.getSummary());
			
			parsedIncidents.add(parsedIncident);
			
		}
		
		originalParsedIncidents = new ArrayList<ParsedIncident>();
		for (ParsedIncident parsedIncident : parsedIncidents) {
			
			originalParsedIncidents.add(parsedIncident);
			
		}
		
	}
	
	public LocalDateTime parseDateAndTime(String dateAndTime) {
		
		//remove the seconds information
		String toParse = dateAndTime.substring(0, 16);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
		LocalDateTime parsedDateAndTime = LocalDateTime.parse(toParse, formatter);
		
		return parsedDateAndTime;
		
	}
	
	public LocalDate parseDatePosted(String datePosted) {
		
		LocalDate parsedDatePosted = LocalDate.parse(datePosted);
		return parsedDatePosted;
		
	}
	
	public int parseDuration(String duration) {
		
		Parser parser = new Parser();
		List<DateGroup> dateGroups = parser.parse(duration);
		
		if (dateGroups.size() > 0) {
			
			DateGroup dateGroup = dateGroups.get(dateGroups.size()-1);
			
			String syntaxTree = dateGroup.getSyntaxTree().toStringTree();
			String[] splits = syntaxTree.split(" ");
			
			int value;
			
			try {
				value = Integer.parseInt(splits[splits.length-2]);
			} catch (NumberFormatException e) {
			    return -1;
			}
			
			String span = splits[splits.length-1];
			
			if (span.contains("minute")) {
				
				return value;
				
			}
			
			else if (span.contains("second")) {
				
				double minutes = (double) value / 60;
				int roundedMinutes = (int) Math.round(minutes);
				
				return roundedMinutes;
				
			}
			
			else if (span.contains("hour")) {
				
				return value * 60;
				
			}
			
			else {
				
				return -1;
				
			}
			
		}
		
		else {
			
			return -1;
			
		}
		
	}
	
	public void sortIncidents(String sortOption) {
		
		if (sortOption.equalsIgnoreCase("date")) {
			
			sortByDateAndTime();
			
		}
		
		else if (sortOption.equalsIgnoreCase("city")) {
			
			sortByCity();
			
		}
		
		else if (sortOption.equalsIgnoreCase("shape")) {
			
			sortByShape();
			
		}
		
		else if (sortOption.equalsIgnoreCase("duration")) {
			
			sortByDuration();
			
		}
		
		else if (sortOption.equalsIgnoreCase("posted")) {
			
			sortByDatePosted();
			
		}
		
		else {
			
			keepOriginalOrder();
			
		}
		
		setChanged();
		notifyObservers();
		
	}
	
	public void sortByDateAndTime() {
		
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	            return (firstIncident.getDateAndTime()).compareTo(secondIncident.getDateAndTime());
	            
	        }

   		});
		
	}
	
	public void sortByCity() {
		
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	            return (firstIncident.getCity()).compareTo(secondIncident.getCity());
	            
	        }

   		});
		
	}
	
	public void sortByShape() {
		
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	            return (firstIncident.getShape()).compareTo(secondIncident.getShape());
	            
	        }

   		});
		
	}
	
	public void sortByDuration() {
		
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	            return (Integer.valueOf(firstIncident.getDuration())).compareTo(Integer.valueOf(secondIncident.getDuration()));
	            
	        }

   		});
		
	}
	
	public void sortByDatePosted() {
		
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	            return (firstIncident.getDatePosted()).compareTo(secondIncident.getDatePosted());
	            
	        }

   		});
		
	}
	
	public void keepOriginalOrder() {
		
		for (int i = 0; i < originalParsedIncidents.size(); i++) {
			
			parsedIncidents.set(i, originalParsedIncidents.get(i));
			
		}
		
	}
	
	public String fetchIncidentDetails(String incidentID) {
		
		String incidentDetails = ripley.getIncidentDetails(incidentID);
		return incidentDetails;
		
	}
	
	public String getStateName() {
		
		return stateName;
		
	}
	
	public String getAbbreviatedStateName() {
		
		return abbreviatedStateName;
		
	}
	
	public List<ParsedIncident> getParsedIncidents() {
		
		return parsedIncidents;
		
	}
	
}
