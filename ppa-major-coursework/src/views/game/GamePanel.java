package views.game;
import controller.GameController;
import models.game.UFO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * JPanel which contains the executing game
 * 
 * @author aaiach
 *
 */
public class GamePanel extends JPanel{

	/**
	 * Unique Panel UID
	 */
	private static final long serialVersionUID = 4949883323092618015L;
	
	/**
	 * Global variable which contains the Width of the Frame the Panel is positioned in
	 */
	public static final int Width = 800;
	
	/**
	 * Global variable which contains the Height of the Frame the Panel is positioned in
	 */
	public static final int Height = 600;
	
	/**
	 * Boolean which keeps track of the status of the game
	 */
	public boolean lost;
	
	/**
	 * Timer which coordinates all events and actions within the game
	 */
	private Timer timer;

	/**
	 * GameController which keeps track of the instance of the model 
	 */
	private GameController control;

	/**
	 * Int which keeps track of the time which the game has been running for
	 */
	private int time = 0;
	
	/**
	 * Instantiates the GamePanel with a supplied GameController
	 * @param Controller which controls the games and contains the instance of the model, the GameEnv
	 */
	public GamePanel(GameController ctrl) {
		this.control = ctrl;
	}
	
	/**
	 * Starts the game clock, positions all initial elements and starts executing all movements until the game ends
	 */
	public void start(){

		//The Game follows the timer, and the view is updated periodically
		timer = new Timer(20, new ActionListener(){

			Random r = new Random();

			public void actionPerformed(ActionEvent e) {
				repaint();

				lost = control.getGame().hasLost();
				
				//Checks if the Game has ended and clears the UFOs and Stops the game is it is the case
				if(control.getGame().hasLost() || control.getGame().hasWon()){
					control.getGame().getUFOs().clear();
					timer.stop();
				}

				GamePanel.this.setBackground(getColorfromUFO());
				time++;
				
				//Spawn UFOS with randomly bounded intervals, positions and speed
				if(time%(r.nextInt(20)+80) == 0){
					if(control.getGame().getSpeedCoefficient() < 5)
						control.getGame().incrementSpeedCoefficient();
					UFO newufo = new UFO(r.nextInt(GamePanel.Width) - 60, r.nextInt(GamePanel.Height) - 60, r.nextInt(5)+control.getGame().getSpeedCoefficient());
					control.getGame().getUFOs().add(newufo);

				}
				
				//Update the position of all elements
				control.getGame().moveTarget();

				for (UFO ufo : control.getGame().getUFOs()) {
					ufo.move();
				}
			}
		});
		timer.start();
	}


	/**
	 * Paints all UFOS and the target at every repaint();
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (UFO ufo : control.getGame().getUFOs()) {
			drawUFO(g, ufo);
		}
		drawTarget(g);
	}

	/**
	 * Method which paints a ufo from a local image
	 * @param graphic which will contain the UFO image
	 * @param ufo is the UFO which will be painted
	 */
	public void drawUFO(Graphics g, UFO ufo) {

		Image ufoimg = null;
		try {
			ufoimg = ImageIO.read(new File("images/miniufo.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(ufoimg, ufo.getPosX(), ufo.getPosY(), this);         

	}

	/**
	 * Method which paints the target from a local image
	 * @param graphic which will contain the target image
	 */
	public void drawTarget(Graphics g) {

		Image ufo = null;
		try {
			ufo = ImageIO.read(new File("images/Target.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		g.drawImage(ufo, control.getGame().getTarget().getPosX(), control.getGame().getTarget().getPosY(), this); 

	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Width, Height);
	}
	
	/**
	 * Gets the GameController
	 * @return the GameController
	 */
	public GameController getControl(){
		return control;
	}
	
	/**
	 * Gets if the game has been lost
	 * @return if the game has been lost
	 */
	public boolean hasLost(){
		return lost;
	}

	/**
	 * Method which returns a color from the Green to Red range depending on how close a player is to loosing the views.models.game
	 * @return the Color which corresponds to the current status of the game
	 */
	public Color getColorfromUFO(){
		Color b;
		
		int i = control.getGame().getUFOs().size();

		int max = control.getGame().getMaxUFO();

		if(i>max){
			i=max;
		}

		int d = 135/(max/2);

		int dis_small;
		if(i<=max/2){
			dis_small = 120 + i*(d);
			b = new Color(dis_small, 255, 120);
		}else{
			dis_small = 255 - (i-max/2)*d;
			b = new Color(255, dis_small, 120);
		}
		return b;
	}

}