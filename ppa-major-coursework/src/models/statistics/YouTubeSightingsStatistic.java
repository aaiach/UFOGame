package models.statistics;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * YouTubeSightingsStatistic is the Statistic for the number of YouTube videos containing
 * the keywords "ufo sighting" published between the supplied dates
 *
 * @author Robert Greener
 */
public class YouTubeSightingsStatistic extends Statistic {
    /**
     * The date at which the videos should be published after
     */
    private Date dateBeginning;

    /**
     * The date at which the videos should be published before
     */
    private Date dateEnd;

    /**
     * Creates a new YouTubeSightingsStatistic
     * @param dateBeginning the date at which the videos should be published after
     * @param dateEnd the date at which the videos should be published before
     */
    public YouTubeSightingsStatistic(Date dateBeginning, Date dateEnd) {
        // Calls the superclass constructor to set the name of the statistic
        super("Number of YouTube sighting videos");

        // Set the fields
        this.dateBeginning = dateBeginning;
        this.dateEnd = dateEnd;

        // Set the data of the statistic equal to the value returned by calculateData
        setData(calculateData());
    }

    /**
     * Calculates the number of YouTube videos containing the keywords "ufo sighting"
     * @return the number of videos
     */
    @Override
    protected String calculateData() {
        // Create a SimpleDateFormat for the RFC3399 standard (this is used by YouTube's API)
        SimpleDateFormat rfc3399 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'z'");
        // Initialise YouTubeAPI with the API Key
        YouTubeAPI api = new YouTubeAPI("AIzaSyCK6WyGL1jJgNUdbn_pUE4JbU1h2Z4UJds");

        // Create a new HashMap to store the parameters of the API query
        HashMap<String, String> params = new HashMap<>();

        // q is the text of the search
        params.put("q", "ufo sighting");

        // maxResults is set to 0, so that the api doesn't return links to the videos
        params.put("maxResults", "0");

        // Set publishedBefore and publishedAfter to dates in the correct format
        params.put("publishedBefore", rfc3399.format(dateEnd));
        params.put("publishedAfter", rfc3399.format(dateBeginning));

        // Query the API storing the response in resp
        String resp = api.queryAPI(params);

        // Create a new JSONObject parsing resp
        JSONObject jsonObject = new JSONObject(resp);

        // Return totalResults inside pageInfo of resp as String
        return Integer.toString(jsonObject.getJSONObject("pageInfo").getInt("totalResults"));
    }
}
