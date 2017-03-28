package mappanelclasses;

/**
 * A class that handles an exception if an invalid range was selected on the main window.
 * 
 * @author Stevan Warren K1631436
 */
public class InvalidRangeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor for the exception
	 * @param Error message
	 */
	public InvalidRangeException(String message) {
		super(message);
	}
}
