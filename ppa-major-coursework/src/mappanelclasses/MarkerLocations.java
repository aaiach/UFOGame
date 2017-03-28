package mappanelclasses;

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
			"AL 910 550", "AK 100 100", "AZ 300 540", "AR 770 530", "CA 90 330",
			"CO 420 420", "CT 1195 290", "DE 1210 370", "FL 1100 710", "GA 990 550",
			"HI 40 700", "ID 260 280", "IL 830 370", "IN 900 370", "IA 700 330",
			"KS 580 430", "KY 960 440", "LA 780 610", "ME 1220 170", "MD 1210 400",
			"MA 1230 250", "MI 910 290", "MN 700 200", "MS 840 570", "MO 740 410",
			"MT 460 220", "NE 580 350", "NV 180 350", "NH 1200 240", "NJ 1160 340",
			"NM 410 550", "NY 1120 230", "NC 1100 470", "ND 550 200", "OH 960 360",
			"OK 630 510", "OR 130 230", "PA 1050 330", "RI 1250 310", "SC 1050 520",
			"SD 550 280", "TN 870 500", "TX 670 620", "UT 320 400", "VT 1170 220", 
			"VA 1090 410", "WA 200 150", "WV 1030 410", "WI 780 250", "WY 390 300",
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
