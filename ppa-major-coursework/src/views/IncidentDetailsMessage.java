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
 * @author Shantanu Shekhar Jha
 *
 */
public class IncidentDetailsMessage extends JDialog implements ActionListener {
	
	public IncidentDetailsMessage(String title) {
		
		//owner, title, modality type
		super(null, title, null);
		
	}
		
	public void initDialog(String details, String summary) {
		
		details = details.replace("<br>", " ");
//for details
		JTextArea detailsTextArea = new JTextArea(details);
		detailsTextArea.setEditable(false);  
		detailsTextArea.setCursor(null);  
		detailsTextArea.setOpaque(false);  
		detailsTextArea.setFocusable(false);
		detailsTextArea.setLineWrap(true);
		detailsTextArea.setWrapStyleWord(true);

	    JScrollPane detailsScrollPane = new JScrollPane(detailsTextArea);
	    detailsScrollPane.setOpaque(false);
	    detailsScrollPane.getViewport().setOpaque(false);
	    detailsScrollPane.setPreferredSize(new Dimension(400, 200));
	    detailsScrollPane.setBorder(BorderFactory.createEmptyBorder());
	//for summary    
	    JTextArea summaryTextArea = new JTextArea(summary);
	    summaryTextArea.setEditable(false);  
	    summaryTextArea.setCursor(null);  
	    summaryTextArea.setOpaque(false);  
	    summaryTextArea.setFocusable(false);
	    summaryTextArea.setLineWrap(true);
	    summaryTextArea.setWrapStyleWord(true);

	    JScrollPane summaryScrollPane = new JScrollPane(summaryTextArea);
	    summaryScrollPane.setOpaque(false);
	    summaryScrollPane.getViewport().setOpaque(false);
	    summaryScrollPane.setPreferredSize(new Dimension(400, 35));
	    summaryScrollPane.setBorder(BorderFactory.createEmptyBorder());
		
		JPanel messagePane = new JPanel();
	
		BorderLayout borderLayout = new BorderLayout();
		borderLayout.setVgap(10); 
		messagePane.setBorder(new EmptyBorder(5, 5, 5, 5));
		messagePane.setLayout(borderLayout);
		messagePane.add(summaryScrollPane, BorderLayout.NORTH);
		messagePane.add(detailsScrollPane, BorderLayout.CENTER);
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(getClass().getResource("java-logo.png"));
		} catch (IOException e) {
		}
		
		ImageIcon imageIcon = new ImageIcon(img);
		Image image = imageIcon.getImage();
		Image resizedImage = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		imageIcon = new ImageIcon(resizedImage);
		
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setSize(new Dimension(50, 50));
		JPanel imagePane = new JPanel();
		imagePane.setLayout(new FlowLayout());
		imagePane.add(imageLabel);
		
		JPanel infoPane = new JPanel();
		infoPane.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		
		infoPane.add(imagePane);
		infoPane.add(messagePane);
		
		JButton button = new JButton("OK");
		button.addActionListener(this);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT, 15, 15));
		buttonPane.add(button);
		
		add(infoPane, BorderLayout.CENTER);
		add(buttonPane, BorderLayout.SOUTH);
		
		setLocation(380, 220);
		pack();
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		setVisible(false);
		dispose();
		
		
	}
	
}
