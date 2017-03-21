package views.statistics;

/**
 * @author Robert Greener.
 */
public enum Directions {
    LEFT(-1),
    RIGHT(1);

    private final int value;
    Directions(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
