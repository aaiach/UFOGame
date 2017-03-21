package views.statistics;

/**
 * @author Robert Greener.
 */
public enum Area {
    TOP_LEFT(0),
    TOP_RIGHT(1),
    BOTTOM_LEFT(2),
    BOTTOM_RIGHT(3);

    private final int value;
    Area (int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
