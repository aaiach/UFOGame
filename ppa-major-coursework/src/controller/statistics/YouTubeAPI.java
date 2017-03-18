package controller.statistics;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * YouTubeAPI is a helper class used to query the YouTube API's search
 *
 * @author Robert Greener.
 */
public class YouTubeAPI {
    /**
     * The API Key needed to access the api
     */
    private String apiKey;

    /**
     * The URL to be used
     */
    private String url;

    /**
     * The charset of the query
     */
    private String charset;

    /**
     * Creates a new YouTubeAPI
     * @param apiKey the API Key needed to access the api
     */
    public YouTubeAPI(String apiKey) {
        // Sets the fields
        this.apiKey = apiKey;

        // This is the URL needed to access the search API
        this.url = "https://www.googleapis.com/youtube/v3/search";

        // Set the charset of the query to UTF-8
        this.charset = StandardCharsets.UTF_8.name();
    }

    /**
     * Query the youtube API
     * @param params A HashSet containing the keys and values of the parameters to pass to the API
     * @return the response from the API (should be JSON)
     */
    public String queryAPI (HashMap<String, String> params) {
        // Add the required parameters
        params.put("part", "snippet");
        params.put("key", apiKey);

        // Create an array list that will store the parameters in the format [key,value,key,value,key,value....]
        ArrayList<String> paramsForURL = new ArrayList<>();
        // For each Key-Value-Pair in params
        for (Map.Entry<String, String> entry : params.entrySet()) {
            // add the key to paramsForURL
            paramsForURL.add(entry.getKey());
            // add the value to paramsForURL
            paramsForURL.add(entry.getValue());
        }

        // Set the initial query equal to an empty string
        String query = "";

        // Loop through paramsForURL, increasing i by 2 each iteration
        for (int i = 0; i < paramsForURL.size(); i += 2) {
            try {
                // add the params to the query
                query = query + String.format("%s=%s", paramsForURL.get(i),
                        URLEncoder.encode(paramsForURL.get(i + 1), charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            /* If it is not the last iteration (i.e. there are still more params to come)
             * add a & at the end of the query String
             */
            if (i != (paramsForURL.size() - 2)) query = query + "&";
        }

        // Initialise response as null
        InputStream response = null;

        try {
            // Open a new URLConnection at the url with the query parameters
            URLConnection conn = new URL(url + "?" + query).openConnection();
            // Specify the charset to use
            conn.addRequestProperty("Accept-Charset", charset);
            // Store the InputStream of the connection in response
            response = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If there was no response, return null
        if (response == null) return null;

        // Try and scan the InputStream, returning the responses
        try (Scanner scanner = new Scanner(response)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
