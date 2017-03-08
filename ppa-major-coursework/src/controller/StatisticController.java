package controller;

import api.ripley.Ripley;
import models.SingleStatisticData;

/**
 * @author Robert Greener.
 */
public class StatisticController {
    private Ripley ripley;
    private SingleStatisticData numberOfHoaxes;

    public StatisticController(Ripley ripley) {
        this.ripley = ripley;
    }

    private void calculateAllStatistics() {
    }

    private void setRipley(Ripley ripley) {
        this.ripley = ripley;
        calculateAllStatistics();
    }
}
