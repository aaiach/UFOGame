package views;

/**
 * A direction, LEFT has a value of -1, RIGHT has a value of 1
 *
 * @author Robert Greener.
 */
public enum Directions {
    LEFT(-1),
    RIGHT(1);

    /**
     * The value of this direction
     */
    private final int value;

    /**
     * Creates a new element of Directions
     * @param value the value of the direction
     */
    Directions(int value) {
        this.value = value;
    }

    /**
     * Gets the value of this direction
     * @return the value of this direction
     */
    public int getValue() {
        return value;
    }
}
