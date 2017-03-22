package models.statistics;

/**
 * USStates is a class used for checking whether a state is a US State or not
 *
 * @author Robert Greener
 */
public class USStates {
    /**
     * This is an array of all US State names, District of Columbia, and US territory names
     *
     * This was found inside the decompiled ripley.jar -- thanks Martin
     */
    private static String[] states = {
            "Alabama,Ala.,AL",
            "Alaska,Alaska,AK",
            "American,Samoa,,,AS",
            "Arizona,Ariz.,AZ",
            "Arkansas,Ark.,AR",
            "California,Calif.,CA",
            "Colorado,Colo.,CO",
            "Connecticut,Conn.,CT",
            "Delaware,Del.,DE",
            "Dist.,of,Columbia,D.C.,DC",
            "Florida,Fla.,FL",
            "Georgia,Ga.,GA",
            "Guam,Guam,GU",
            "Hawaii,Hawaii,HI",
            "Idaho,Idaho,ID",
            "Illinois,Ill.,IL",
            "Indiana,Ind.,IN",
            "Iowa,Iowa,IA",
            "Kansas,Kans.,KS",
            "Kentucky,Ky.,KY",
            "Louisiana,La.,LA",
            "Maine,Maine,ME",
            "Maryland,Md.,MD",
            "Marshall,Islands,,,MH",
            "Massachusetts,Mass.,MA",
            "Michigan,Mich.,MI",
            "Micronesia,,,FM",
            "Minnesota,Minn.,MN",
            "Mississippi,Miss.,MS",
            "Missouri,Mo.,MO",
            "Montana,Mont.,MT",
            "Nebraska,Nebr.,NE",
            "Nevada,Nev.,NV",
            "New_Hampshire,N.H.,NH",
            "New_Jersey,N.J.,NJ",
            "New_Mexico,N.M.,NM",
            "New_York,N.Y.,NY",
            "North_Carolina,N.C.,NC",
            "North_Dakota,N.D.,ND",
            "Northern_Marianas,,MP",
            "Ohio,Ohio,OH",
            "Oklahoma,Okla.,OK",
            "Oregon,Ore.,OR",
            "Palau,,PW",
            "Pennsylvania,Pa.,PA",
            "Puerto,Rico,P.R.,PR",
            "Rhode,Island,R.I.,RI",
            "South_Carolina,S.C.,SC",
            "South_Dakota,S.D.,SD",
            "Tennessee,Tenn.,TN",
            "Texas,Tex.,TX",
            "Utah,Utah,UT",
            "Vermont,Vt.,VT",
            "Virginia,Va.,VA",
            "Virgin_Islands,V.I.,VI",
            "Washington,Wash.,WA",
            "West,Virginia,W.Va.,WV",
            "Wisconsin,Wis.,WI",
            "Wyoming,Wyo.,WY" };

    /**
     * Checks whether a supplied String is a US State
     * @param name the String to check
     * @return true if the String is a US State, false otherwise
     */
    public static boolean isState(String name) {
        // For each state in states
        for (String state : states) {
            // if the state string contains name, return true
            if (state.contains(name)) return true;
        }

        // otherwise return false
        return false;
    }

    /**
     * Gets the full state name from a segment of the state name
     * @param name the segment of the state name
     * @return the full state name if found, if not null
     */
    public static String getFullStateName(String name) {
        // For each state in states
        for (String state : states) {
            // if the state string contains name, return the state string
            if (state.contains(name)) return state;
        }

        // Otherwise return null
        return null;
    }

    public static String[] getStates () {
        return states.clone();
    }
}
