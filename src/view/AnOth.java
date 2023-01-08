package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.GamePanel;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AnOth extends Launcher{

	JFrame frame;
	public int scale;
	AnOth(int a){
		scale = a;
		initialize();
	}
	/**
	 * Launch the application.
	 */
	public static void ag() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnOth window = new AnOth();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AnOth() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Snake Menu");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Snake Simulation");
		//System.out.println("Scale: " + this.scl);
		GamePanel gamePanel = new GamePanel(scale);
		//------------------------------------------------------
		
		JTextArea getHeight = new JTextArea();
		getHeight.setBounds(110, 36, 127, 15);
		frame.getContentPane().add(getHeight);
		
		JLabel lblNewLabel = new JLabel("Add New Snake");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(125, 10, 99, 13);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSnakeHeight = new JLabel("Snake Height");
		lblSnakeHeight.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSnakeHeight.setBounds(10, 34, 90, 17);
		frame.getContentPane().add(lblSnakeHeight);
		
		JLabel lblXValue = new JLabel("X Value");
		lblXValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblXValue.setBounds(42, 61, 47, 17);
		frame.getContentPane().add(lblXValue);
		
		JLabel lblYValue = new JLabel("Y Value");
		lblYValue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblYValue.setBounds(42, 89, 47, 17);
		frame.getContentPane().add(lblYValue);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblColor.setBounds(56, 116, 33, 17);
		frame.getContentPane().add(lblColor);
		
		JTextArea getX = new JTextArea();
		getX.setBounds(110, 61, 127, 15);
		frame.getContentPane().add(getX);
		
		JTextArea getY = new JTextArea();
		getY.setBounds(110, 89, 127, 15);
		frame.getContentPane().add(getY);
		
		JTextArea getColor = new JTextArea();
		getColor.setBounds(110, 116, 127, 15);
		frame.getContentPane().add(getColor);
		
		JButton addBtn = new JButton("Add Snake");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str1 = getX.getText();
				String str2 = getY.getText();
				String str3 = getHeight.getText();
				String str4 = getColor.getText();
				Color c = Color.BLACK;
				if(str4.equals("red")) {
					c = Color.RED;
				}else if(str4.equals("blue")) {
					c = Color.BLUE;
				}else if(str4.equals("yellow")) {
					c = Color.yellow;
				}else if(str4.equals("cyan")) {
					c = Color.CYAN;
				}
				gamePanel.addSnake(Integer.valueOf(str1), Integer.valueOf(str2), Integer.valueOf(str3), c, scale);
			}
		});
		addBtn.setBounds(138, 141, 99, 27);
		frame.getContentPane().add(addBtn);
		
		JButton removeBtn = new JButton("Remove 1 Sanke");
		removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamePanel.removeSnake();
			}
		});
		removeBtn.setBounds(278, 29, 148, 27);
		frame.getContentPane().add(removeBtn);
		
		JSlider speedSlider = new JSlider();
		speedSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int i = speedSlider.getValue();
				//System.out.println("--------------------------------------------------i ------ " + i);
				if(i > 0 && i < 26) {
					gamePanel.speed = 1;
				}else if(i > 25 && i < 51) {
					gamePanel.speed = 2;
				}else if(i > 50 && i < 76) {
					gamePanel.speed = 3;
				}else if(i > 75 && i < 101) {
					gamePanel.speed = 4;
				}
			}
		});
		
		speedSlider.setBounds(10, 231, 416, 22);
		frame.getContentPane().add(speedSlider);
		
		
		JLabel lblSnakeSpeedSlider = new JLabel("Snake Speed Slider");
		lblSnakeSpeedSlider.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSnakeSpeedSlider.setBounds(152, 208, 172, 21);
		frame.getContentPane().add(lblSnakeSpeedSlider);
		
		JButton ppbtn = new JButton("Play/Pause");
		ppbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(gamePanel.play == true) {
					gamePanel.play = false;
				}else if(gamePanel.play == false) {
					gamePanel.play = true;
				}
			}
		});
		ppbtn.setBounds(278, 58, 148, 27);
		frame.getContentPane().add(ppbtn);
		
		JButton quitButton = new JButton("Quit");
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		quitButton.setBounds(278, 89, 148, 27);
		frame.getContentPane().add(quitButton);

		window.getContentPane().add(gamePanel);
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
	}
}
