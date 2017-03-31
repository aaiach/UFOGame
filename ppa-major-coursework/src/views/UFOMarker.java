package views;

import api.ripley.Incident;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A class representing a UFO marker that is placed on the map panel
 * 
 * @author Stevan Warren K1631436
 */
public class UFOMarker extends JButton{
	private static final long serialVersionUID = 1L;
	// Image of an alien that will be the background of the marker
	private BufferedImage alienImage;

	/**
	 * Constructor that paints the alien image onto the button
	 */
	public UFOMarker() {
		try {
			alienImage = ImageIO.read(new File("images/alien.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor that initialises the marker and creates a listener that helps to display
	 * the incidents that occurred in this location
	 */
	public UFOMarker(ArrayList<Incident> incidents) {
		this();
		// Adds an action listener that, when the marker is pressed, will create a new frame
		// displaying a list of the incidents that have occurred in this location
		addActionListener(new AlienButtonListener(incidents));
	}
	
	/**
	 * Paints the alien image onto the marker
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(alienImage, 0, 0, getWidth(), getHeight(), this);
	}

}
