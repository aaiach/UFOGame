package views;

/**
 * A class containing the locations of each marker on the map.
 * These locations will only work if the map is at the correct size (1400x1000).
 * 
 * @author Stevan Warren K1631436
 */
public class MarkerLocations {
	// A list of the locations for each button.
	// They are listed in this format: [state abbreviation] [x-coordinate] [y-coordinate]
	private static final String[] locations = {
			"AL 950 650", "AK 75 75", "AZ 300 600", "AR 790 600", "CA 102 393",
			"CO 440 470", "CT 1245 330", "DE 1210 440", "FL 1110 790", "GA 1030 650",
			"HI 40 700", "ID 280 300", "IL 860 400", "IN 940 440", "IA 740 390",
			"KS 600 500", "KY 990 520", "LA 810 720", "ME 1280 180", "MD 1215 470",
			"MA 1240 320", "MI 960 330", "MN 720 200", "MS 860 660", "MO 780 500",
			"MT 370 200", "NE 610 410", "NV 200 400", "NH 1260 260", "NJ 1220 400",
			"NM 430 600", "NY 1140 330", "NC 1100 550", "ND 570 230", "OH 1000 430",
			"OK 660 600", "OR 146 286", "PA 1100 380", "RI 1290 344", "SC 1100 600",
			"SD 570 300", "TN 910 590", "TX 600 670", "UT 330 460", "VT 1220 256",
			"VA 1120 490", "WA 194 179", "WV 1070 480", "WI 820 300", "WY 410 340",
			"Not specified. 1200 800"
	};
	
	// A list of all state abbreviations used on the map, as well as one if the
	// location is not in the US.
	public static final String[] ABBREVIATIONS = {
			"AL", "AK", "AZ", "AR", "CA",
			"CO", "CT", "DE", "FL", "GA",
			"HI", "ID", "IL", "IN", "IA",
			"KS", "KY", "LA", "ME", "MD",
			"MA", "MI", "MN", "MS", "MO",
			"MT", "NE", "NV", "NH", "NJ",
			"NM", "NY", "NC", "ND", "OH",
			"OK", "OR", "PA", "RI", "SC",
			"SD", "TN", "TX", "UT", "VT",
			"VA", "WA", "WV", "WI", "WY",
			"Not specified."
	};
	
	/**
	 * Returns the location string of a marker depending on the location that is being queried.
	 * 
	 * @param State abbreviation
	 * @return String in 'locations' representing the location for the marker
	 */
	public static String getLocation(String state) {
		if (state.equals("Not specified.")) return locations[50];
		for (String string : locations) {
			if (string.substring(0,  2).equals(state)) return string;
		}
		return null;
	}
}
