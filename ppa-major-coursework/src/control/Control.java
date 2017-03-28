package game.control;
import game.model.GameEnv;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control {

	//The control controls the model : the game and the Listeners
	private ArrowListener Al= new ArrowListener();
	private GameEnv game;

	public Control(GameEnv ge) {
		this.game = ge;
	}

	public GameEnv getGame(){
		return game;
	}

	public ArrowListener getAl(){
		return Al;
	}

	//Listener which listens to arrow keys and moves the target accordingly
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
