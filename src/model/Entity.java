package model;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class Entity {        //This class holds the data for PLAYER, MAPS and APPLE
	public int x, y;    //for player x-axis and y-axis respectively
	public int speed;   //for player speed
	public LinkedList<SnakeAttributes> ls = new LinkedList<SnakeAttributes>();
	public Color color = Color.black;
	public int height, index = 0, rNum = 0, lDir = 0;
	public int[] storeVal = new int[2];{
		storeVal[0] = 0;
		storeVal[1] = 0;
	}
	public int counter = 0;
	public int nDir = 2;
	public int tileSize = 5;
	public boolean conditionChk = false;
	public boolean movCheck = false;
	public BufferedImage left1, left2, right1, right2, up1, up2, map1;
	public boolean wallChk1 = false, wallChk2 = false, wallChk3 = false, wallChk4 = false;
	public int wallIndex = 0;
	public boolean anotherTemp = false;
	public BufferedImage apple1, apple2, apple3, apple4, apple5, apple6, apple7, apple8, apple9, apple10, apple11, apple12, apple13, apple14, apple15, apple16, apple17;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea;
	public boolean collisionOn = false;
}
