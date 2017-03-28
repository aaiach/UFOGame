package game.model;
import game.view.GamePanel;

public class UFO extends Coordinates{

	//Models each instance of a moving ufo
	
	public int speed;

	public UFO(int x, int y, int speed) {
		super(x, y);
		this.speed = speed;
	}
	
	//Method which moved a UFO based on its current position and speed, such that it bounces on panel borders
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
	
	public int getSpeed(){
		return this.speed;
	}
	
	public void setSpeed(int speed){
		this.speed = speed;
	}
}