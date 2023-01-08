package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JPanel;

import model.Player;

public class GamePanel extends JPanel implements Runnable{
	final int originalTileSize = 10;
	public int scale = 0;
	
	public int tileSize = originalTileSize * scale; // 10
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 20;
	public int screenWidth = tileSize * maxScreenCol; 
	public int screenHeight = tileSize * maxScreenRow; 
	public int speed = 4;
	public int getX = 120;
	public int getY = 70;
	public Color c = Color.black;
	public int height = 24;
	public boolean play = true;
	// FPS
	LinkedList<String> lm = new LinkedList<String>();
	LinkedList<Player> player = new LinkedList<Player>();
	public GamePanel(int i) {            // This is to add the basic functionality of window size
		scale = i;
		tileSize = originalTileSize * scale;
		screenWidth = tileSize * maxScreenCol; 
		screenHeight = tileSize * maxScreenRow; 
		//System.out.println("------" + this.scale);
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		Player p = new Player(this, keyH);
		player.add(p);
		addString();
	}
	
	public void removeSnake() {
		Random rand = new Random();
		int Num = rand.nextInt();
		Num = Num % player.size();
		player.remove(Num);
	}
	
	public void setSpeed(int sp) {
		speed = sp;
	}
	
	public void addString() {
		lm.add("sss");
	}
	
	int FPS = 60;   //FPS can be chnaged from here
	int score = 0;
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	public void startGameThread() {     //Creating game thread here to give it an infinite loop
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void addSnake(int xPos, int yPos, int hSnake, Color cl, int scl) {
		this.getX = xPos;
		this.getY = yPos;
		this.height = hSnake;
		this.c = cl;
		scale = scl;
		Player p = new Player(this, keyH);
		player.add(p);
	}
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;        //Updating changes after every single time
		double nextDrawTime = System.nanoTime() + drawInterval;
		while(gameThread != null) {
			long currentTime = System.nanoTime();
			update();
			
			repaint();
			//System.out.println("This game loop is running!\n");
			
			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime/1000000;
				if(remainingTime < 0) {
					remainingTime = 0;
				}
				Thread.sleep((long) remainingTime);
				
				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void update() {
		for(int i = 0; i < player.size(); i++) {
			player.get(i).update(speed);
		}
		
	}
	
	public boolean gamecheck(int x, int y) {          //Checking either the player has reached the golden point or not
		boolean temp = false;
		if(x > 505 && y > 170) {
			temp = true;
		}
		return temp;
	}

	
	public void paintComponent(Graphics g) {     //Repainting/Redrawing the whole game after every fps
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 0; i < player.size(); i++) {
			player.get(i).draw(g2);
		}
			
		g2.dispose();
	}
}
