package controller;

import api.ripley.Incident;
import api.ripley.Ripley;
import models.SightingsAtStates;
import models.USStates;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author Robert Greener.
 */
public class CalculateStatistics {
    private ArrayList<Incident> incidents;
    private ArrayList<String> incidentDetails;
    private SightingsAtStates sightingsAtStates;
    private Date dateBeginning;
    private Date dateEnd;
    private Ripley ripley;
    public static final SimpleDateFormat RFC3399 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'z'");

    public CalculateStatistics(ArrayList<Incident> incidents, Ripley ripley, Date dateBeginning, Date dateEnd) {
        this.incidents = incidents;
        this.ripley = ripley;
        incidentDetails = new ArrayList<>();
        sightingsAtStates = new SightingsAtStates();
        calculateSightingsAtStates();

        this.dateBeginning = dateBeginning;
        this.dateEnd = dateEnd;

        incidentDetails.addAll(incidents.stream()
                .map(i -> ripley.getIncidentDetails(i.getIncidentID()))
                .collect(Collectors.toList()));
    }

    public int getNumberOfHoaxes() {
        int numberOfHoaxes = 0;
        for (String details : incidentDetails) {
            if (details.toLowerCase().contains("hoax")) numberOfHoaxes++;
        }

        return numberOfHoaxes;
    }

    public int getNumberOfNonUSSightings() {
        int numberOfNonUSSightings = 0;
        for (Incident i : incidents) {
            if (USStates.isState(i.getState())) numberOfNonUSSightings++;
        }

        return numberOfNonUSSightings;
    }

    private void calculateSightingsAtStates() {
        for (Incident i : incidents) {
            String state = i.getState();
            String fullStateName = USStates.getFullStateName(state);
            if (fullStateName == null) sightingsAtStates.addSighting(state);
            else sightingsAtStates.addSighting(fullStateName);
        }
    }

    private String getHighestNumberOfSightings() {
        return sightingsAtStates.getHighestSightingState();
    }

    private int getYoutubeSightings() {
        YouTubeAPI api = new YouTubeAPI("AIzaSyCK6WyGL1jJgNUdbn_pUE4JbU1h2Z4UJds");
        HashMap<String, String> params = new HashMap<>();
        params.put("q", "ufo sighting");
        params.put("maxResults", "0");
        params.put("publishedBefore", RFC3399.format(dateEnd));
        params.put("publishedAfter", RFC3399.format(dateBeginning));
        String resp = api.queryAPI(params);
        JSONObject jsonObject = new JSONObject(resp);
        return jsonObject.getJSONObject("pageInfo").getInt("totalResults");
    }
}
