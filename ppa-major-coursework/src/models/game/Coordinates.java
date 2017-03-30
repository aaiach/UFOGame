package models.game;
import java.util.Observable;

/**
 * Models all elements of the game which are positioned on the Panel
 * 
 * @author aaiach
 */
public class Coordinates extends Observable{
	
	/**
	 * X coordinate
	 */
	private int x;
	/**
	 * Y coordinate
	 */
	private int y;
	
	/**
	 * If the element should move backwards on the X axis
	 */
	private boolean backX = false;
	/**
	 * If the element should move backwards on the Y axis
	 */
	private boolean backY = false;
	
	/**
	 * Creates an instance of this class
	 * @param x this is the x coordinate
	 * @param y this is the y coordinate
	 */
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Gets the x coordinate
	 * @return the x coordinate
	 */
	public int getPosX() {
		return x;
	}

	/**
	 * Updates the x coordinate
	 * @param the new x coordinate
	 */
	public void setPosX(int posX) {
		this.x = posX;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Gets the y coordinate
	 * @return the y coordinate
	 */
	public int getPosY() {
		return y;
	}

	/**
	 * Updates the y coordinate
	 * @param the new y coordinate
	 */
	public void setPosY(int posY) {
		this.y = posY;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Gets if the element is allowed to move backwards on the X axis
	 * @return if the element is allowed to move backwards on the X axis
	 */
	public boolean getBackX(){
		return backX;
	}
	
	/**
	 * Updates the element's permission to go backwards on the X axis
	 * @param backX the true/false value which allows or not the element to go backwards on the X axis
	 */
	public void setBackX(boolean backX){
		this.backX = backX;
	}
	
	/**
	 * Gets if the element is allowed to move backwards on the Y axis
	 * @return if the element is allowed to move backwards on the Y axis
	 */
	public boolean getBackY(){
		return backY;
	}
	
	/**
	 * Updates the element's permission to go backwards on the Y axis
	 * @param backX the true/false value which allows or not the element to go backwards on the Y axis
	 */
	public void setBackY(boolean backY){
		this.backY = backY;
	}

}
