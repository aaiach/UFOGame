/**
 * 
 */
package models;

// import the necessary java library classes
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// import the natty library
import com.joestelmach.natty.*; 
// import the Ripley API
import api.ripley.*;

/**
 * This class serves as the primary model class for the Lists of Sightings functionality.
 * It contains the implementations for parsing and sorting the lists of sightings within a
 * particular selected state, based on various parameters.
 * 
 * @author Shantanu Shekhar Jha
 *
 */
public class SightingsList extends Observable {
	
	/**
	 * The full name of the state, where the list of sightings are from
	 */
	private String stateName;
	
	/**
	 * The abbreviated name of the state, as shown on the map
	 */
	private String abbreviatedStateName;
	
	/**
	 * The list of incidents for that state
	 */
	private List<Incident> incidents;
	
	/**
	 * The list of the parsed incidents
	 */
	private List<ParsedIncident> parsedIncidents;
	
	/**
	 * The list of the originally parsed incidents
	 * This is used to keep a record of the original order of the list
	 * So it can be sorted back to its original order if the user wishes to
	 */
	private List<ParsedIncident> originalParsedIncidents;
	 
	/**
	 * The Private Key for the Ripley API
	 */
	private static final String PRIVATE_API_KEY = "90tLI3CRs9iyVD6ql2OMtA==";
	
	/**
	 * The Public Key for the Ripley API
	 */
	private static final String PUBLIC_API_KEY = "lBgm4pRv9wjVqL46EnH7ew==";
	
	/**
	 * The Ripley API
	 */
	private Ripley ripley;
	
	/**
	 * Creates a new instance of SightingsList
	 * 
	 * @param stateName the full name of the state, where the incidents are from
	 * @param abbreviatedStateName the abbreviate name of the state
	 * @param incidents the list of incidents for the state
	 */
	public SightingsList(String stateName, String abbreviatedStateName, List<Incident> incidents) {
		
		// Set the fields
		this.stateName = stateName;
		this.abbreviatedStateName = abbreviatedStateName;
		this.incidents = incidents;
		// make an instance of the Ripley API class
		ripley = new Ripley(PRIVATE_API_KEY, PUBLIC_API_KEY);
		
		// parse the list of incidents
		parseIncidents();
		
	}
	
	/**
	 * Parses the list of incidents into processable data formats
	 */
	public void parseIncidents() {
		
		// create a new list to store the parsed incidents 
		parsedIncidents = new ArrayList<ParsedIncident>();
		
		// for every incident in the supplied incidents
		for (Incident incident : incidents) {
			
			// create a new ParsedIncident
			ParsedIncident parsedIncident = new ParsedIncident();
			// parse the incident's date and time and store within the parsed incident
			parsedIncident.setDateAndTime(parseDateAndTime(incident.getDateAndTime()));
			// parse the incident's city and store within the parsed incident
			parsedIncident.setCity(incident.getCity());
			// parse the incident's shape and store within the parsed incident
			parsedIncident.setShape(incident.getShape());
			// parse the incident's duration and store within the parsed incident
			parsedIncident.setDuration(parseDuration(incident.getDuration()));
			// parse the incident's date posted and store within the parsed incident
			parsedIncident.setDatePosted(parseDatePosted(incident.getPosted()));
			
			// store the incident's ID within the parsed incident
			parsedIncident.setIncidentID(incident.getIncidentID());
			// store the incident's summary within the parsed incident
			parsedIncident.setSummary(incident.getSummary());
			
			// add the parsed incident to the list of parsed incidents
			parsedIncidents.add(parsedIncident);
			
		}
		
		/*
		 * Create a new list to keep record of the order of these parsed incidents,
		 * when they are parsed and stored into the parsed list for the first time.
		 * This list will be used as a reference for the original order of the parsed
		 * incidents, if the user wishes to sort the list back to its original order 
		 */
		originalParsedIncidents = new ArrayList<ParsedIncident>();
		// for every parsed incident in the list of parsed incidents
		for (ParsedIncident parsedIncident : parsedIncidents) {
			
			// add that parsed incident to the originalParsedIncidents list
			originalParsedIncidents.add(parsedIncident);
			
		}
		
	}
	
	
	/**
	 * Parses the incident's date and time into a processable date-time format
	 * 
	 * @param dateAndTime the date and time of the incident
	 * @return the parsed date and time
	 */
	public LocalDateTime parseDateAndTime(String dateAndTime) {
		
		// remove the seconds information from the date and time
		String toParse = dateAndTime.substring(0, 16);
		
		// make a new date-time formatter with the specified format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm");
		// parse the string into a LocalDateTime object, using the specified formatter
		LocalDateTime parsedDateAndTime = LocalDateTime.parse(toParse, formatter);
		
		// return the parsed date and time
		return parsedDateAndTime;
		
	}
	
	/**
	 * Parses the incident's date posted into a processable date format
	 * @param datePosted the date the incident was posted or logged
	 * @return the parsed date posted
	 */
	public LocalDate parseDatePosted(String datePosted) {
		
		/*
		 * Parse the string into a LocalDate object. The default formatter
		 * of LocalDate is used, to keep the format as "yyyy-MM-dd".
		 */
		LocalDate parsedDatePosted = LocalDate.parse(datePosted);
		// return the parsed date posted
		return parsedDatePosted;
		
	}
	
	/**
	 * Parsed the duration of the incident into an integer format
	 * @param duration the duration of the incident
	 * @return the parsed duration
	 * @see <a href="https://github.com/joestelmach/natty">The Natty Library</a>
	 */
	public int parseDuration(String duration) {
		
		// The Natty library is used, for parsing the duration
		// Create a new Parser instance
		Parser parser = new Parser();
		// parse the duration and store the output of the parse operation,
		// which is a DateGroup list, into a DateGroup list "dateGroups"
		List<DateGroup> dateGroups = parser.parse(duration);
		
		// if the list is not empty, meaning that something was parsed
		if (dateGroups.size() > 0) {
			
			// get the DateGroup at the last index
			// since we need only one DateGroup, we get the latest added DateGroup
			// which will be stored at the end of the list
			DateGroup dateGroup = dateGroups.get(dateGroups.size()-1);
			
			// get the abstract syntax tree built for the DateGroup,
			// and fetch its string representation
			// within this will be contained the parsed duration information
			String syntaxTree = dateGroup.getSyntaxTree().toStringTree();
			// split the string by the space delimiter
			String[] splits = syntaxTree.split(" ");
			
			// stores the numerical value of the duration 
			int value;
			
			// try-catch block to get this value
			// if the supplied duration was able to get parsed successfully,
			// the numerical value of the duration will be contained in the 
			// second last string block in the split syntaxTree
			try {
				// try to parse the integer value from the relevant string
				value = Integer.parseInt(splits[splits.length-2]);
			} catch (NumberFormatException e) {
				// if a valid numerical value is not found, meaning that the 
				// supplied duration information was not able to get parsed,
				// then return a value of -1
			    return -1;
			}
			
			// get the span of the duration (hours, minutes, or seconds)
			// this would be contained within the last string block in the
			// split syntaxTree
			String span = splits[splits.length-1];
			
			// if the span information contains "minute"
			if (span.contains("minute")) {
				
				// directly return the numerical value of the duration,
				// as the duration value is required in minutes for the
				// lists of sightings
				return value;
				
			}
			
			// else if the span information contains "second"
			else if (span.contains("second")) {
				
				// divide the value by 60
				double minutes = (double) value / 60;
				// round it up to nearest whole number
				int roundedMinutes = (int) Math.round(minutes);
				
				// return the number of minutes
				return roundedMinutes;
				
			}
			
			// else if the span information contains "hour"
			else if (span.contains("hour")) {
				
				// return the value multiplied by 60
				return value * 60;
				
			}
			
			// otherwise, if no such span is found in the information
			else {
				
				// return a value of -1
				return -1;
				
			}
			
		}
		
		// if the parsed list is empty, meaning that nothing could be parsed
		else {
			
			// return a value of -1
			return -1;
			
		}
		
	}
	
	/**
	 * Sorts the list of incidents, based on the supplied sort-by option
	 * @param sortOption the option for the type of sorting to be performed
	 */
	public void sortIncidents(String sortOption) {
		
		// if the user selects to sort the list by the date of the incidents
		if (sortOption.equalsIgnoreCase("date")) {
			
			// call the corresponding sort method
			sortByDateAndTime();
			
		}
		
		// else if the user selects to sort the list by the city of the incidents
		else if (sortOption.equalsIgnoreCase("city")) {
			
			// call the corresponding sort method
			sortByCity();
			
		}
		
		// else if the user selects to sort the list by the shape of the incidents
		else if (sortOption.equalsIgnoreCase("shape")) {
			
			// call the corresponding sort method
			sortByShape();
			
		}
		
		// else if the user selects to sort the list by the duration of the incidents
		else if (sortOption.equalsIgnoreCase("duration")) {
			
			// call the corresponding sort method
			sortByDuration();
			
		}
		
		// else if the user selects to sort the list by the posted date of the incidents
		else if (sortOption.equalsIgnoreCase("posted")) {
			
			// call the corresponding sort method
			sortByDatePosted();
			
		}
		
		// else, if the user selects to restore the original order of the list
		else {
			
			// call the method to perform this
			keepOriginalOrder();
			
		}
		
		// update the view
		setChanged();
		notifyObservers();
		
	}
	
	/**
	 * Sorts the list by the date and time of the incident
	 */
	public void sortByDateAndTime() {

		/*
		 * Use the sort method of the Collections class. This sorts the list of
		 * parsed incidents according to the order induced by the specified
		 * comparator for ParsedIncident objects. The compare method of the
		 * comparator is implemented within the interface definition
		 */
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

	        /* (non-Javadoc)
	         * Provides the relevant comparison implementation for the ParsedIncident objects
	         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	         */
	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	        	/* 
	        	 * Compare the incidents based on their date and time, from earliest to
	        	 * latest, in chronological order. The compareTo method defined for the 
	        	 * LocalDateTime class is used to perform the comparison
	        	 */
	            return (firstIncident.getDateAndTime()).compareTo(secondIncident.getDateAndTime());
	            
	        }

   		});
		
	}
	
	/**
	 * Sorts the list by the city of the incident
	 */
	public void sortByCity() {
		
		/*
		 * Use the sort method of the Collections class. This sorts the list of
		 * parsed incidents according to the order induced by the specified
		 * comparator for ParsedIncident objects. The compare method of the
		 * comparator is implemented within the interface definition
		 */
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

			  /* (non-Javadoc)
	         * Provides the relevant comparison implementation for the ParsedIncident objects
	         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	         */
	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	        	/* 
	        	 * Compare the incidents based on their city, in alphabetical order.
	        	 * The compareTo method defined for the String class is used to perform
	        	 * the comparison.
	        	 */
	            return (firstIncident.getCity()).compareTo(secondIncident.getCity());
	            
	        }

   		});
		
	}
	
	/**
	 * Sorts the list by the city of the incident
	 */
	public void sortByShape() {
		
		/*
		 * Use the sort method of the Collections class. This sorts the list of
		 * parsed incidents according to the order induced by the specified
		 * comparator for ParsedIncident objects. The compare method of the
		 * comparator is implemented within the interface definition
		 */
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

			 /* (non-Javadoc)
	         * Provides the relevant comparison implementation for the ParsedIncident objects
	         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	         */
	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	        	/* 
	        	 * Compare the incidents based on their shape, in alphabetical order.
	        	 * The compareTo method defined for the String class is used to perform
	        	 * the comparison.
	        	 */
	            return (firstIncident.getShape()).compareTo(secondIncident.getShape());
	            
	        }

   		});
		
	}
	
	/**
	 * Sorts the list by the duration of the incident
	 */
	public void sortByDuration() {
		
		/*
		 * Use the sort method of the Collections class. This sorts the list of
		 * parsed incidents according to the order induced by the specified
		 * comparator for ParsedIncident objects. The compare method of the
		 * comparator is implemented within the interface definition
		 */
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

			 /* (non-Javadoc)
	         * Provides the relevant comparison implementation for the ParsedIncident objects
	         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	         */
	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	        	/* 
	        	 * Compare the incidents based on their duration, in ascending order.
	        	 * The compareTo method defined for the Integer class is used to perform
	        	 * the comparison, after getting the Integer instances from the primitive
	        	 * integer types for the durations
	        	 */
	            return (Integer.valueOf(firstIncident.getDuration())).compareTo(Integer.valueOf(secondIncident.getDuration()));
	            
	        }

   		});
		
	}
	
	/**
	 * Sorts the list by the posted date of the incident
	 */
	public void sortByDatePosted() {
		
		/*
		 * Use the sort method of the Collections class. This sorts the list of
		 * parsed incidents according to the order induced by the specified
		 * comparator for ParsedIncident objects. The compare method of the
		 * comparator is implemented within the interface definition
		 */
		Collections.sort(parsedIncidents, new Comparator<ParsedIncident>() {

			 /* (non-Javadoc)
	         * Provides the relevant comparison implementation for the ParsedIncident objects
	         * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	         */
	        public int compare(ParsedIncident firstIncident, ParsedIncident secondIncident) {
	        	
	        	/* 
	        	 * Compare the incidents based on their date posted, from earliest to
	        	 * latest, in chronological order. The compareTo method defined for the 
	        	 * LocalDate class is used to perform the comparison
	        	 */
	            return (firstIncident.getDatePosted()).compareTo(secondIncident.getDatePosted());
	            
	        }

   		});
		
	}
	
	/**
	 * Rearranges the list of the incidents into their original order
	 */
	public void keepOriginalOrder() {
		
		// for every element in the originalParsedIncidents list
		for (int i = 0; i < originalParsedIncidents.size(); i++) {
			
			// set the corresponding element at that index in the 
			// parsedIncidents list to the element in original list
			parsedIncidents.set(i, originalParsedIncidents.get(i));
			
		}
		
	}
	
	/**
	 * Fetches the incident details, for a supplied incident ID.
	 * 
	 * @param incidentID the unique ID of the incident
	 * @return the incident details
	 */
	public String fetchIncidentDetails(String incidentID) {
		
		// fetch the incident details from the Ripley instance for the
		// supplied incident ID
		String incidentDetails = ripley.getIncidentDetails(incidentID);
		// return the incident details
		return incidentDetails;
		
	}
	
	/**
	 * Gets the state name, for the list of incidents.
	 * @return the state name
	 */
	public String getStateName() {
		
		return stateName;
		
	}
	
	/**
	 * Gets the abbreviated state name, for the list of incidents.
	 * @returnthe the abbreviated state name
	 */
	public String getAbbreviatedStateName() {
		
		return abbreviatedStateName;
		
	}
	
	/**
	 * Gets the list of parsed incidents.
	 * @return the list of parsed incidents
	 */
	public List<ParsedIncident> getParsedIncidents() {
		
		return parsedIncidents;
		
	}
	
}
