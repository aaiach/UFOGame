package game.model;
import java.util.Observable;

//Models all elements of the game which are positioned on the Panel
public class Coordinates extends Observable{
	
	private int x;
	private int y;
	
	private boolean backX = false;
	private boolean backY = false;
	
	public Coordinates(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getPosX() {
		return x;
	}

	public void setPosX(int posX) {
		this.x = posX;
		setChanged();
		notifyObservers();
	}

	public int getPosY() {
		return y;
	}

	public void setPosY(int posY) {
		this.y = posY;
		setChanged();
		notifyObservers();
	}
	
	public boolean getBackX(){
		return backX;
	}
	
	public void setBackX(boolean backX){
		this.backX = backX;
	}
	
	public boolean getBackY(){
		return backY;
	}
	
	public void setBackY(boolean backY){
		this.backY = backY;
	}

}
