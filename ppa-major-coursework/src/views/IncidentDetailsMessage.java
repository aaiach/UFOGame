/**
 * 
 */
package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * This class is for the message window that is to pop up, when one of the sightings
 * in the list of sightings displayed to the user, is clicked.
 * 
 * @author Shantanu Shekhar Jha
 *
 */
public class IncidentDetailsMessage extends JDialog implements ActionListener {
	
	/**
	 * Creates a new instance of the IncidentDetailsMessage window
	 * @param title the title of the window to be set
	 */
	public IncidentDetailsMessage(String title) {

		/**
		 * Call the constructor for the superclass JDialog. Pass in the first parameter,
		 * the owner, as null, meaning that this dialog window will not have any owner.
		 * Pass in the second parameter, the title, as the supplied title. Pass in the
		 * third parameter, the modality type, as null, meaning that this dialog
		 * window will be non-modal; so it will not block user input to all other windows
		 * in the program while the dialog window is present.
		 */
		super(null, title, null);
		
	}
		
	/**
	 * Sets up the JDialog window.
	 * @param details the details of the selected incident
	 * @param summary the summary of the selected incident
	 */
	public void initDialog(String details, String summary) {
		
		// remove unnecessary tags from the incident details
		details = details.replace("<br>", " ");
		details = details.replace("&nbsp;", " ");
		
		// make a new text area for the incident details
		JTextArea detailsTextArea = new JTextArea(details);
		// set it as non-editable
		detailsTextArea.setEditable(false);  
		// set the cursor as null
		detailsTextArea.setCursor(null); 
		// set it as non-opaque
		detailsTextArea.setOpaque(false);  
		// set it as non-focusable
		detailsTextArea.setFocusable(false);
		// wrap long lines of text
		detailsTextArea.setLineWrap(true);
		// wrap lines around word boundaries
		detailsTextArea.setWrapStyleWord(true);

		// make a new scroll pane to contain this text area
	    JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
	    // set the scroll pane as non-opaque
	    detailsScrollPane.setOpaque(false);
	    // set its view port as non-opaque
	    detailsScrollPane.getViewport().setOpaque(false);
	    // set its preferred size
	    detailsScrollPane.setPreferredSize(new Dimension(400, 200));
	    // set its border as a new empty border
	    detailsScrollPane.setBorder(BorderFactory.createEmptyBorder());

	    // make a new text area for the incident summary
	    JTextArea summaryTextArea = new JTextArea(summary);
	    // set it as non-editable
	    summaryTextArea.setEditable(false);  
	    // set the cursor as null
	    summaryTextArea.setCursor(null); 
	    // set it as non-opaque
	    summaryTextArea.setOpaque(false);  
	    // set it as non-focusable
	    summaryTextArea.setFocusable(false);
	    // wrap long lines of text
	    summaryTextArea.setLineWrap(true);
	    // wrap lines around word boundaries
	    summaryTextArea.setWrapStyleWord(true);

	    // make a new scroll pane to contain this text area
	    JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
	    // set the scroll pane as non-opaque
	    summaryScrollPane.setOpaque(false);
	    // set its view port as non-opaque
	    summaryScrollPane.getViewport().setOpaque(false);
	    // set its preferred size
	    summaryScrollPane.setPreferredSize(new Dimension(400, 35));
	    // set its border as a new empty border
	    summaryScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
	    // make a new panel for all the text information
		JPanel messagePane = new JPanel();
		// make a new BorderLayout instance
		BorderLayout borderLayout = new BorderLayout();
		// set vertical gaps between components for the borderLayout
		borderLayout.setVgap(10); 
		// create an empty surrounding border for the panel
		messagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// set the layout of the panel to the borderLayout instance
		messagePane.setLayout(borderLayout);
		// add both the scroll panes to this panel in the specified layout positions
		messagePane.add(summaryScrollPane, BorderLayout.NORTH);
		messagePane.add(detailsScrollPane, BorderLayout.CENTER);
		
		// initialise a BufferedImage to null
		BufferedImage img = null;
		// try-catch block to get the image file of Java logo
		try {
		    img = ImageIO.read(getClass().getResource("java-logo.png"));
		} catch (IOException e) {
		}
		
		// make a new ImageIcon from this image
		ImageIcon imageIcon = new ImageIcon(img);
		// get the Image instance from the ImageIcon
		Image image = imageIcon.getImage();
		/*
		 * Resize the image to the specified size dimension, with the specified choice
		 * for the image-scaling algorithm. Image.SCALE_SMOOTH chooses an image-scaling
		 * algorithm that gives higher priority to image smoothness than scaling speed.
		 */
		Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		// make an ImageIcon instance from this resized image and store it back
		// in the imageIcon variable
		imageIcon = new ImageIcon(resizedImage);
		
		// make a new JLabel with this image icon
		JLabel imageLabel = new JLabel(imageIcon);
		// set the size of the label
		imageLabel.setSize(new Dimension(50, 50));
		// make a new panel for the image
		JPanel imagePane = new JPanel();
		// set the layout of the panel
		imagePane.setLayout(new FlowLayout());
		// add the label containing the image to the panel
		imagePane.add(imageLabel);
		
		// make a new panel for the image and text information
		JPanel infoPane = new JPanel();
		// set the layout of the panel
		infoPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		// add the image and the text information to this panel
		infoPane.add(imagePane);
		infoPane.add(messagePane);
		
		// make a new button, for closing the dialog window
		JButton button = new JButton("OK");
		// add the listener for the button click
		button.addActionListener(this);
		// make a new panel for the button
		JPanel buttonPane = new JPanel();
		// set the layout of the panel, with a right alignment
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		// add the button to the panel
		buttonPane.add(button);
		
		// add the info panel containing the image and text information to this JFrame
		add(infoPane, BorderLayout.CENTER);
		// add the button panel to this JFrame
		add(buttonPane, BorderLayout.SOUTH);
		
		// set the location on screen
		setLocation(380, 220);
		
		pack();
		setVisible(true);
		
	}

	
	/* (non-Javadoc)
	 * Handles the click event for the button.
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// make the dialog window invisible
		setVisible(false);
		// dispose the window
		dispose();
		
	}
	
}
