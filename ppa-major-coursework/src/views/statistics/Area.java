package views.statistics;

/**
 * Area is one of the area's on the StatisticsPanel
 *
 * @author Robert Greener.
 */
public enum Area {
    TOP_LEFT(0),
    TOP_RIGHT(1),
    BOTTOM_LEFT(2),
    BOTTOM_RIGHT(3);

    /**
     * The value of the Area
     */
    private final int value;

    /**
     * Creates a new Area
     * @param value the value of the Area
     */
    Area (int value) {
        this.value = value;
    }

    /**
     * Gets the value of the Area
     * @return the integer value of the Area
     */
    public int getValue() {
        return value;
    }
}
