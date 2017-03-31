/**
 * 
 */
package models;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * This class represents a parsed incident. It contains the information relevant
 * to that parsed incident.
 * 
 * @author Shantanu Shekhar Jha
 *
 */
public class ParsedIncident {
	
	/**
	 * The date and time the incident took place
	 */
	private LocalDateTime dateAndTime;
	
	/**
	 * The city in which the incident took place
	 */
	private String city;
	
	/**
	 * The shape of the object observed during the incident
	 */
	private String shape;
	
	/**
	 * The duration of the incident
	 */
	private int duration;
	
	/**
	 * The date on which the incident was posted
	 */
	private LocalDate datePosted;
	
	/**
	 * The unique ID of the incident
	 */
	private String incidentID;
	
	/**
	 * A brief summary of the incident
	 */
	private String summary;
	
	/**
	 * Gets the date and time of the incident.
	 * 
	 * @return the date and time of the incident
	 */
	public LocalDateTime getDateAndTime() {
		
		return dateAndTime;
		
	}
	
	/**
	 * Gets the city of the incident.
	 * 
	 * @return the city of the incident
	 */
	public String getCity() {
		
		return city;
		
	}
	
	/**
	 * Gets the shape of the incident.
	 * 
	 * @return the shape of the incident
	 */
	public String getShape() {
		
		return shape;
		
	}
	
	/**
	 * Gets the duration of the incident.
	 * 
	 * @return the duration of the incident
	 */
	public int getDuration() {
		
		return duration;
		
	}
	
	/**
	 * Gets the posted date of the incident.
	 * 
	 * @return the posted date of the incident
	 */
	public LocalDate getDatePosted() {
		
		return datePosted;
		
	}
	
	/**
	 * Gets the ID of the incident.
	 * 
	 * @return the incident ID
	 */
	public String getIncidentID() {
		
		return incidentID;
		
	}
	
	/**
	 * Gets the summary of the incident.
	 * 
	 * @return the summary of the incident
	 */
	public String getSummary() {
		
		return summary;
		
	}
	
	/**
	 * Sets the date and time of the incident.
	 * 
	 * @param dateAndTime the date and time to be set
	 */
	public void setDateAndTime(LocalDateTime dateAndTime) {
		
		this.dateAndTime = dateAndTime;
		
	}
	
	/**
	 * Sets the city of the incident.
	 * 
	 * @param city the city to be set
	 */
	public void setCity(String city) {
		
		this.city = city;
		
	}
	
	/**
	 * Sets the shape of the incident.
	 * 
	 * @param shape the shape to be set
	 */
	public void setShape(String shape) {
		
		this.shape = shape;
		
	}
	
	/**
	 * Sets the duration of the incident.
	 * 
	 * @param duration the duration to be set
	 */
	public void setDuration(int duration) {
		
		this.duration = duration;
		
	}
	
	/**
	 * Sets the posted date of the incident.
	 * 
	 * @param datePosted the posted date to be set
	 */
	public void setDatePosted(LocalDate datePosted) {
		
		this.datePosted = datePosted;
		
	}
	
	/**
	 * Sets the ID of the incident.
	 * 
	 * @param incidentID the incident ID to be set
	 */
	public void setIncidentID(String incidentID) {
		
		this.incidentID = incidentID;
		
	}
	
	/**
	 * Sets the summary of the incident
	 * 
	 * @param the summary to be set
	 */
	public void setSummary(String summary) {
		
		this.summary = summary;
		
	}
	
	/* (non-Javadoc)
	 * Returns the relevant string representation for the ParsedIncident.
	 * This will be used by the view while displaying the list of sightings.
	 *  
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String dateTime = dateAndTime.toString();
		return "Time: " + dateTime.replace("T", " ") + " City: " + city + " Shape: " + shape + " Duration: " + duration + " Posted: " + datePosted;
		
	}
	
}
