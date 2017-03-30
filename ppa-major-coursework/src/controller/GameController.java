package controller;
import models.game.GameEnv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class which controls the game by always handling the same instance of a GameEnv and the KeyListener
 * 
 * @author Adriel Aiach
 *
 */
public class GameController {

	/**
	 * Listener which listens to the arrows and moves the target accordingly
	 */
	private ArrowListener Al = new ArrowListener();
	/**
	 * Instance of the model: the game
	 */
	private GameEnv game;

	/**
	 * Instantiates the GameController by supplying an instance of a GameEnv
	 * @param GameEnvironment which will be controlled and shown
	 */
	public GameController(GameEnv ge) {
		this.game = ge;
	}

	/**
	 * Gets the game environment
	 * @return the GameEnc
	 */
	public GameEnv getGame(){
		return game;
	}

	/**
	 * Gets the ArrowListener
	 * @return the ArrowListener
	 */
	public ArrowListener getAl(){
		return Al;
	}

	/**
	 * ArrowListener which listens to the pressing and releasing of arrows, and updates the permissions the target has to move
	 * 
	 * @author Adriel Aiach
	 *
	 */
	class ArrowListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
		}

		@Override
		//The target starts moving when pressed
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch( keyCode ) { 
			case KeyEvent.VK_UP:
				game.setTargetMove("UP", true);
				break;
			case KeyEvent.VK_DOWN:
				game.setTargetMove("DOWN", true);
				break;
			case KeyEvent.VK_LEFT:
				game.setTargetMove("LEFT", true);
				break;
			case KeyEvent.VK_RIGHT:
				game.setTargetMove("RIGHT", true);
				break;
			case KeyEvent.VK_SPACE:
				if(game.getUFOs().size() > 0){
					game.getUFOs().remove(game.getUfo());
				}
			}
		}			


		@Override
		//The target stops moving when released
		public void keyReleased(KeyEvent e) {
			int keyCode = e.getKeyCode();
			switch( keyCode ) { 
			case KeyEvent.VK_UP:
				game.setTargetMove("UP", false);
				break;
			case KeyEvent.VK_DOWN:
				game.setTargetMove("DOWN", false);
				break;
			case KeyEvent.VK_LEFT:
				game.setTargetMove("LEFT", false);
				break;
			case KeyEvent.VK_RIGHT:
				game.setTargetMove("RIGHT", false);
				break;

			}
		}
	}
}
