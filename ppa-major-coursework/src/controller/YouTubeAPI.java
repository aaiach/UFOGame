package controller;

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
 * @author Robert Greener.
 */
public class YouTubeAPI {
    private String apiKey;
    private String url;
    private String charset;

    public YouTubeAPI(String apiKey) {
        this.apiKey = apiKey;
        this.url = "https://www.googleapis.com/youtube/v3/search";
        this.charset = StandardCharsets.UTF_8.name();
    }

    public String queryAPI (HashMap<String, String> params) {
        params.put("part", "snippet");
        params.put("key", apiKey);
        ArrayList<String> paramsForURL = new ArrayList<>();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            paramsForURL.add(entry.getKey());
            paramsForURL.add(entry.getValue());
        }

        String query = "";

        for (int i = 0; i < paramsForURL.size(); i += 2) {
            try {
                query = query + String.format("%s=%s", paramsForURL.get(i),
                        URLEncoder.encode(paramsForURL.get(i + 1), charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (i != (paramsForURL.size() - 2)) query = query + "&";
        }

        InputStream response = null;

        try {
            URLConnection conn = new URL(url + "?" + query).openConnection();
            conn.addRequestProperty("Accept-Charset", charset);
            response = conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response == null) return null;

        try (Scanner scanner = new Scanner(response)) {
            return scanner.useDelimiter("\\A").next();
        }
    }
}
