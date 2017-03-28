package views.game;
import controller.GameController;
import models.game.GameEnv;

import javax.swing.JFrame;

public class View extends JFrame {

	private static final long serialVersionUID = -7872210279724795089L;

	public static void main(String[] args) {
		new View();
	}


	public View() {
		
		//Copy this wherever you want to instantiate the views.models.game
		GameEnv game = new GameEnv();
		GameController c = new GameController(game);
		GamePanel p = new GamePanel(c);
		Menu m = new Menu(p);
		
		this.setFocusable(true);
		this.addKeyListener(c.getAl());
		
		//These two lines should be added when the global JFrame is instantiated
		this.setFocusable(true);
		this.addKeyListener(c.getAl());

		game.addObserver(m);
		
		this.add(m);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setResizable(false);

	}
}