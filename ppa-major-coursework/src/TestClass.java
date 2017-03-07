import api.ripley.Incident;
import api.ripley.Ripley;

import java.util.ArrayList;

/**
 * Created by robert on 07/03/17.
 */
public class TestClass {
    public static void main(String[] args) {
        Ripley ripley = new Ripley("90tLI3CRs9iyVD6ql2OMtA==", "lBgm4pRv9wjVqL46EnH7ew==");
        ArrayList<Incident> incidents = ripley.getIncidentsInRange("2015-01-01 00:00:00", "2015-01-31 00:00:00");
        for (Incident i : incidents) {
            String details = ripley.getIncidentDetails(i.getIncidentID());
            if (details.toLowerCase().contains("hoax")) System.out.println(details);
        }
    }
}
