package controller;

import api.ripley.Incident;
import api.ripley.Ripley;

import java.util.List;

/**
 * Created by robert on 21/03/17.
 */
public class MainController {
    private Ripley ripley;
    private List<Incident> incidents;
    private static final String PRIVATE_API_KEY = "90tLI3CRs9iyVD6ql2OMtA==";
    private static final String PUBLIC_API_KEY =  "lBgm4pRv9wjVqL46EnH7ew==";

    public MainController() {
        ripley = new Ripley(PRIVATE_API_KEY, PUBLIC_API_KEY);

    }
}
