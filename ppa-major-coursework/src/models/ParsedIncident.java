/**
 * 
 */
package models;

import java.time.LocalDateTime;
import java.time.LocalDate;

/**
 * @author bunny
 *
 */
public class ParsedIncident {
	
	private LocalDateTime dateAndTime;
	private String city;
	private String shape;
	private int duration;
	private LocalDate datePosted;
	
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
	
	@Override
	public String toString() {
		
		String dateTime = dateAndTime.toString();
		return "Time: " + dateTime.replace("T", " ") + " City: " + city + " Shape: " + shape + " Duration: " + duration + " Posted: " + datePosted;
		
	}
	
}
