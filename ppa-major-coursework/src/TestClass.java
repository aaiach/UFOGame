import api.ripley.Incident;
import controller.statistics.USStates;
import controller.statistics.YouTubeAPI;
import models.SightingsAtStates;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by robert on 07/03/17.
 */
public class TestClass {
    public static void main(String[] args) {
        YouTubeAPI api = new YouTubeAPI("AIzaSyCK6WyGL1jJgNUdbn_pUE4JbU1h2Z4UJds");
        HashMap<String, String> params = new HashMap<>();
        params.put("q", "ufo");
        params.put("maxResults", "0");
        String resp = api.queryAPI(params);
        System.out.println(resp);

        JSONObject jsonObject = new JSONObject(resp);
        int totalResults = jsonObject.getJSONObject("pageInfo").getInt("totalResults");
        System.out.println(totalResults);



        // new test
        List<Incident> incidentList = new ArrayList<Incident>();

        SightingsAtStates sightingsAtStates = new SightingsAtStates(incidentList);
        HashMap<String, Integer> numberOfSightings = sightingsAtStates.getNumberOfSightings();
        int numberOfSightingsInCalifornia = numberOfSightings.get(USStates.getFullStateName("CA"));
    }
}
