package game.view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Menu extends JPanel implements Observer{

	private static final long serialVersionUID = 9097057926165173951L;

	//Default game difficulty
	private int difficulty = 1;

	private Timer timer;

	//Panels needed in the game : the menu, won/lost screens and the game panel
	private JPanel menuPanel = new JPanel();
	private JPanel wonPanel = new JPanel();
	private JPanel lostPanel = new JPanel();
	private GamePanel gamePanel;

	//start button
	private JButton start = new JButton();

	//Constructor which adds all pannels and leaves the Menu one visible
	public Menu(GamePanel gamePanel){

		this.gamePanel = gamePanel;
		this.add(getMenu());
		this.add(wonPanel);
		this.add(lostPanel);
		wonPanel.setVisible(false);
		lostPanel.setVisible(false);
		Menu.this.add(gamePanel);
		gamePanel.setVisible(false);

	}

	//Method which constructs the Menu
	public JPanel getMenu(){

		menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

		ImageIcon ufoimg = new ImageIcon("images/ufotitle.png");
		JLabel img = new JLabel(ufoimg);
		img.setAlignmentX(Component.CENTER_ALIGNMENT);
		menuPanel.setBackground(new Color(0xffd200));
		menuPanel.add(img);
		menuPanel.add(Box.createVerticalStrut(80));
		start = createSimpleButton("      Start      ");
		
		//Button Listener which starts the game with selected difficulty when pressed
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				menuPanel.setVisible(false);
				wonPanel.setVisible(false);
				lostPanel.setVisible(false);
				gamePanel.setVisible(true);

				gamePanel.getControl().getGame().startGame(difficulty);
				gamePanel.start();
			}

		});
		menuPanel.add(start);
		menuPanel.add(Box.createVerticalStrut(20));

		final JButton difficultyBtn = createSimpleButton("Difficulty : " + difficulty);
		//Button Listener which updates the difficulty
		difficultyBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				if(difficulty < 4){
					difficulty++;
				}else{
					difficulty = 1;
				}

				difficultyBtn.setText("Difficulty : " + difficulty);

			}

		});
		menuPanel.add(difficultyBtn);

		menuPanel.setPreferredSize(new Dimension(GamePanel.Width, GamePanel.Height));

		return menuPanel;
	}

	//Method which constructs the end panels
	public void getEndPanels(){

		wonPanel.setBackground(new Color(0x78ff78));
		ImageIcon wonImg = new ImageIcon("images/win.png");

		lostPanel.setBackground(new Color(0xff7878));
		ImageIcon lostImg = new ImageIcon("images/loose.png");

		JLabel wonLabel = new JLabel(wonImg);
		JLabel lostLabel = new JLabel(lostImg);

		wonPanel.add(wonLabel, BorderLayout.CENTER);
		lostPanel.add(lostLabel, BorderLayout.CENTER);

		wonPanel.setPreferredSize(new Dimension(GamePanel.Width, GamePanel.Height));
		lostPanel.setPreferredSize(new Dimension(GamePanel.Width, GamePanel.Height));
	}

	//Method which constructs the design of the button from the StyledButtonUI class
	private static JButton createSimpleButton(String text) {
		JButton button = new JButton(text);
		button.setFont(new Font("Calibri", Font.PLAIN, 20));
		button.setPreferredSize(new Dimension(100, 70));
		button.setBackground(new Color(0xab1515));
		button.setForeground(Color.white);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		// customize the button with your own look
		button.setUI(new StyledButtonUI());
		return button;
	}

	//Ends the game when notified by showing an end pannel and bringing the user back to the Menu
	@Override
	public void update(Observable o, Object arg) {
				
		lostPanel.setVisible(false);
		wonPanel.setVisible(false);

		gamePanel.setVisible(false);
		getEndPanels();
		if(gamePanel.hasLost()){
			lostPanel.setVisible(true);
		}else{
			wonPanel.setVisible(true);
		}
		
		timer = new Timer(1000, getAL());
		timer.setRepeats(false);
		start.setEnabled(false);
		timer.start();


	}
	
	//Method which returns the ActionListener which resets the View after it has ended
	public ActionListener getAL(){
		ActionListener al = (new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				timer.setRepeats(false);
				lostPanel.setVisible(false);
				lostPanel.setVisible(false);
				menuPanel.setVisible(true);
				
				start.setEnabled(true);
				timer.stop();
			}
		});
		
		return al;

	}



}
