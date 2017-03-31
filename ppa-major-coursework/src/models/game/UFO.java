package models.game;
import views.game.GamePanel;

/**
 * Models each instance of a UFO within the game
 * 
 * UFO extends Coordinates as each UFO will be an element positioned on the panel
 * 
 * @author Adriel Aiach
 */
public class UFO extends Coordinates{

	/**
	 * The speed of the UFO : the displacement in each direction made by an ufo at every move
	 */
	public int speed;

	/**
	 * Creates an instance of a UFO
	 * @param x sets the initial x-coordinate
	 * @param y sets the initial y-coordinate
	 * @param speed sets the initial speed
	 */
	public UFO(int x, int y, int speed) {
		super(x, y);
		this.speed = speed;
	}
	
	/**
	 * Method which moves a UFO based on its current position and speed, such that it bounces on panel borders
	 */
	public void move() {
		int x = getPosX(), y = getPosY();

		if (x < 1){
			setBackX(false);
		}
		if (x > GamePanel.Width - 50){
			setBackX(true);
		}
		if (y < 1){
			setBackY(false);
		}
		if (y > GamePanel.Height - 50){
			setBackY(true);
		}
		if (!getBackX())
			setPosX(x+=speed);
		else
			setPosX(x-=speed);
		if (!getBackY())
			setPosY(y+=speed);
		else
			setPosY(y-=speed);
	}
	
	/**
	 * Gets the speed
	 * @return the speed
	 */
	public int getSpeed(){
		return this.speed;
	}
	
	/**
	 * Updates the speed
	 * @param the new speed
	 */
	public void setSpeed(int speed){
		this.speed = speed;
	}
}