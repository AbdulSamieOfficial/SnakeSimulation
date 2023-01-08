package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.BorderLayout;
import javax.swing.JSlider;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Launcher {

	JFrame frame;
	public int scl;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher window = new Launcher();
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
	public Launcher() {
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
		
		JRadioButton rd1 = new JRadioButton("800*800");
		//System.out.println("Scale: " + this.scl);
		rd1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				AnOth window = new AnOth(4);
				window.scale = 4;
				window.frame.setVisible(true);
			}
		});
		rd1.setBounds(70, 100, 103, 21);
		frame.getContentPane().add(rd1);
		
		JRadioButton rd2 = new JRadioButton("400*400");
		//System.out.println("Scale: " + this.scl);
		rd2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				
				AnOth window = new AnOth(2);
				window.scale = 2;
				window.frame.setVisible(true);
			}
		});
		rd2.setBounds(188, 100, 103, 21);
		frame.getContentPane().add(rd2);
		
		JRadioButton rd3 = new JRadioButton("200*200");
		rd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scl = 1;
				frame.dispose();
				AnOth window = new AnOth(1);
				window.scale = 1;
				window.frame.setVisible(true);
				//System.out.println("yes");
			}
		});
		rd3.setBounds(307, 100, 103, 21);
		frame.getContentPane().add(rd3);
		
		JLabel lblNewLabel = new JLabel("Select Screen Size:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(152, 61, 190, 13);
		frame.getContentPane().add(lblNewLabel);
		
	}
}
