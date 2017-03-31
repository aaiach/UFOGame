package models.game;
import views.game.GamePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;

/**
 * Models the entire game by handling and positioning all elements including UFOs and the Target
 * 
 * @author Adriel Aiach
 *
 */
public class GameEnv extends Observable{

	/**
	 * ArrayList which contains all the visible UFOs at all times
	 */
	private ArrayList<UFO> ufos = new ArrayList<>();
	/**
	 * Coordinates of the target
	 */
	private Coordinates target = new Coordinates(GamePanel.Width/2 - 55, GamePanel.Height/2 - 55);

	/**
	 * Integer which defines the max number of UFOs that are allowed until a game is lost
	 */
	private int maxUFO; 
	/**
	 * Integer which defines the initial number of UFOs when a game starts
	 */
	private int minUFO;
	/**
	 * The higher this value, the more likely it is for a UFO to spawn with a higher speed
	 */
	private int speedCoefficient;

	/**
	 * Boolean which allows or not the target to move up
	 */
	private boolean target_up = false; 
	/**
	 * Boolean which allows or not the target to move down
	 */
	private boolean target_down = false;
	/**
	 * Boolean which allows or not the target to move right
	 */
	private boolean target_right = false; 
	/**
	 * Boolean which allows or not the target to move left
	 */
	private boolean target_left = false;


	/**
	 * Method which spawns the initial UFOS with random coordinates and speed
	 * @param difficulty sets the difficulty of the game by changing the initial amount of ufos, the maximum amount of ufos to loose and the speedcoefficient
	 */
	public void startGame(int difficulty){

		setDifficulty(difficulty);

		//Instantiating the initial UFOs
		for(int i = 0; i < getMinUFO(); i++){

			Random r = new Random();
			//Randomly positioning the UFOs
			int temp_x = r.nextInt(GamePanel.Width) - 10;
			int temp_y = r.nextInt(GamePanel.Height) - 10;

			boolean hasClose = true; //Checks if there are surrounding UFOs

			//Checks if there are closeby UFOs before spawning a new one
			while(hasClose == true){
				int closeUFOs = 0;
				for(UFO u : ufos){
					if((Math.abs(u.getPosX() - temp_x) < 100) && (Math.abs(u.getPosY() - temp_y) < 100)){
						closeUFOs++;
					}
				}
				if(closeUFOs == 0){ //A UFO can only be spawned if there are 0 close UFOs

					//Speed of UFO = speed coefficient + a random value between 0 and 5
					int temp_speed = r.nextInt(5)+getSpeedCoefficient();

					ufos.add(new UFO(temp_x, temp_y, temp_speed));
					hasClose = false;
				}else{
					//Attempt to spawn the UFO at a new position if there are closeby UFOs
					temp_x = r.nextInt(GamePanel.Width) - 60;
					temp_y = r.nextInt(GamePanel.Height) - 60;
				}
			}
		}
	}

	/**
	 * Gets the List of UFOs
	 * @return the List of UFOs
	 */
	public List<UFO> getUFOs(){
		return this.ufos;
	}

	/**
	 * Gets the Target
	 * @return the Target
	 */
	public Coordinates getTarget(){
		return this.target;
	}

	/**
	 * Gets the max number of ufos for this game
	 * @return the max number of ufos
	 */
	public int getMaxUFO(){
		return this.maxUFO;
	}

	/**
	 * Gets the min number of ufos for this game
	 * @return the min number of ufos
	 */
	public int getMinUFO(){
		return this.minUFO;
	}

	/**
	 * Gets the speedCoefficient of this game
	 * @return the speedCoefficient of this game
	 */
	public int getSpeedCoefficient(){
		return this.speedCoefficient;
	}

	/**
	 * Increments the speedCoefficient of this game
	 */
	public void incrementSpeedCoefficient(){
		this.speedCoefficient++;
	}

	/**
	 * Checks if a player has won by checking if there are 0 UFOs left
	 * 
	 * Notifies observers that the game ended if true
	 * 
	 * @return if the player has won
	 */
	public boolean hasWon(){
		if(ufos.size()==0){
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	/**
	 * Checks if a player has lost by checking if the number of UFOs is greater or equal than maxUFO 
	 * 
	 * Notifies observers that the game ended if true
	 * 
	 * @return if the player has lost
	 */	
	public boolean hasLost(){

		if(ufos.size() >= this.maxUFO){
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}


	/**
	 * Method which moves the target based on its permission to move and the panel borders
	 */
	public void moveTarget(){

		if(target_up == true){
			if(target.getPosY() > -10){
				target.setPosY(target.getPosY()-7);
			}
		}

		if(target_down == true){
			if(target.getPosY() < GamePanel.Height - 105){
				target.setPosY(target.getPosY()+7);
			}
		}
		if(target_left == true){
			if(target.getPosX() > -10){
				target.setPosX(target.getPosX()-7);
			}
		}
		if (target_right == true){
			if(target.getPosX() < GamePanel.Width - 105){
				target.setPosX(target.getPosX()+7);
			}
		}
	}

	/**
	 * @param s chooses one of the four directions the target can move in
	 * @param b chooses if the target should be moving in that direction or not
	 */
	public void setTargetMove(String s, boolean b){
		if(s.equals("UP")){
			target_up = b;
		}else if(s.equals("DOWN")){
			target_down = b;
		}else if(s.equals("LEFT")){
			target_left = b;
		}else if(s.equals("RIGHT")){
			target_right = b;
		}
	}


	/**
	 * Method which checks if a UFO is centered in the Target
	 * @return a UFO centered in the Target if there is one
	 */
	public UFO getUfo(){

		//Offset of 30 pixels between coordinates of the target and its center
		int target_x = target.getPosX() + 30;
		int target_y = target.getPosY() + 30;

		for(UFO ufo : getUFOs()){
			if((Math.abs(ufo.getPosX() - target_x) < 30) && (Math.abs(ufo.getPosY() - target_y) < 30)){
				return ufo;
			}
		}
		return null;
	}

	/**
	 * Sets the difficulty of the game by changing minUFO, maxUFO, speedCoefficient of the game
	 * @see minUFO
	 * @see maxUFO
	 * @see speedCoefficient
	 * @param int between 1 and 4 which differentiates between 4 difficulties
	 */
	public void setDifficulty(int dif){
		if(dif == 1){
			this.minUFO =3;
			this.maxUFO = 7;
			this.speedCoefficient = 1;
		}else if(dif == 2){
			this.minUFO = 5;
			this.maxUFO = 10;
			this.speedCoefficient = 1;

		}else if(dif == 3){
			this.minUFO = 10;
			this.maxUFO = 15;
			this.speedCoefficient = 2;

		}else if(dif == 4){
			this.minUFO = 15;
			this.maxUFO = 20;
			this.speedCoefficient = 3;
		}

	}
}
