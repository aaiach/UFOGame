/**
 * 
 */
package models;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * @author Shantanu Shekhar Jha
 *
 */
public class ParsedIncident {
	
	private LocalDateTime dateAndTime;
	private String city;
	private String shape;
	private int duration;
	private LocalDate datePosted;
	private String incidentID;
	private String summary;
	
	public LocalDateTime getDateAndTime() {
		
		return dateAndTime;
		
	}
	
	public String getCity() {
		
		return city;
		
	}
	
	public String getShape() {
		
		return shape;
		
	}
	
	public int getDuration() {
		
		return duration;
		
	}
	
	public LocalDate getDatePosted() {
		
		return datePosted;
		
	}
	
	public String getIncidentID() {
		
		return incidentID;
		
	}

	public String getSummary() {
		
		return summary;
		
	}
	
	public void setDateAndTime(LocalDateTime dateAndTime) {
		
		this.dateAndTime = dateAndTime;
		
	}
	
	public void setCity(String city) {
		
		this.city = city;
		
	}
	
	public void setShape(String shape) {
		
		this.shape = shape;
		
	}
	
	public void setDuration(int duration) {
		
		this.duration = duration;
		
	}
	
	public void setDatePosted(LocalDate datePosted) {
		
		this.datePosted = datePosted;
		
	}
	
	public void setIncidentID(String incidentID) {
		
		this.incidentID = incidentID;
		
	}
	
	public void setSummary(String summary) {
		
		this.summary = summary;
		
	}
	
	@Override
	public String toString() {
		
		String dateTime = dateAndTime.toString();
		return "Time: " + dateTime.replace("T", " ") + " City: " + city + " Shape: " + shape + " Duration: " + duration + " Posted: " + datePosted;
		
	}
	
}
