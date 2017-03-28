package game.model;
import game.view.GamePanel;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Random;


public class GameEnv extends Observable{
	//Model : The game consists of a collection of UFOS, a Target, and is defined by its starting number of UFOs, UFOs required for a loose, and initial speed;

	private ArrayList<UFO> ufos = new ArrayList<UFO>();
	private Coordinates target = new Coordinates(GamePanel.Width/2 - 55, GamePanel.Height/2 - 55);
	private int maxUFO, minUFO, speedCoefficient;


	//Booleans which dictate the movement of the target
	private boolean target_up = false, target_down = false, target_right = false, target_left = false;


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
					temp_x = r.nextInt(GamePanel.Width) - 60;
					temp_y = r.nextInt(GamePanel.Height) - 60;
				}
			}
		}
	}

	//Class getters
	public List<UFO> getUFOs(){
		return this.ufos;
	}

	public Coordinates getTarget(){
		return this.target;
	}

	public int getMaxUFO(){
		return this.maxUFO;
	}

	public int getMinUFO(){
		return this.minUFO;
	}

	public int getSpeedCoefficient(){
		return this.speedCoefficient;
	}

	public void incrementSpeedCoefficient(){
		this.speedCoefficient++;
	}

	//A player has won if all UFOs are removed;
	public boolean hasWon(){
		if(ufos.size()==0){
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	//A player has lost if the total number of UFOs exceeds the maxUFO
	public boolean hasLost(){

		if(ufos.size() >= this.maxUFO){
			setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	//updates the position of the target
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
	 * @param b chooses is the target should be moving in that direction or not
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

	//Method which returns a UFO inside the target if there is one
	public UFO getUfo(){

		int target_x = target.getPosX() + 30;
		int target_y = target.getPosY() + 30;

		for(UFO ufo : getUFOs()){
			if((Math.abs(ufo.getPosX() - target_x) < 30) && (Math.abs(ufo.getPosY() - target_y) < 30)){
				return ufo;
			}
		}
		return null;
	}

	//Sets the difficulty of the game, which depends on the speed coefficient, the start and end number of UFOs
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
