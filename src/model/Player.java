package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import controller.GamePanel;
import controller.KeyHandler;

public class Player extends Entity{
	GamePanel gp;
	KeyHandler keyH;
	
	
	public Player(GamePanel g, KeyHandler k) {
		this.gp = g;
		this.keyH = k;
		
		setDefaultValues();
	}
	int sizeY;
	int sizeX;
	public void setDefaultValues() {
		sizeX = gp.screenWidth;
		sizeY = gp.screenHeight;
		color = gp.c;
		height = gp.height;
		x = gp.getX;
		y = gp.getY;
		speed = gp.speed;
		direction = "down";
		Random rand = new Random();
		int num = rand.nextInt(10);
		num = num % 2;
		if(num == 0) {
			this.setHeight_Y();
		}else {
			this.setHeight_Y();
		}
	}
	public void speedChange(int s) {
	}
	
	public void update(int s) {               //Player Movement + Player AI
		//speed = s;
		if(keyH.exitPressed == true) {
			System.exit(0);
		}
		if(gp.play == true) {
			if(wallIndex == height) {
				this.anotherTemp = false;
				wallIndex = 0;
			}
			if(index == this.height) {
				speed = s;
				this.movCheck = false;
				index = 0;
				if(rNum == 0) {
					lDir = 0;
				}else if(rNum == 1) {
					lDir = 1;
				}else {
					lDir = rNum;
				}
			}
			if(this.wallIndex == height) {
				wallChk1 = false;
				wallChk2 = false;
				wallChk3 = false;
				wallChk4 = false;
			}
			if(this.conditionChk == false) {
				if(this.movCheck == false) {
					Random rand = new Random();
					rNum = rand.nextInt(10);
					rNum = rNum % 3;
					this.movCheck = true;
					for(int i = 0; i < height; i++) {
						if(ls.get(i).sXval + this.speed >= this.sizeX) {
							rNum = lDir;
						}else if(ls.get(i).sYval + this.speed >= this.sizeY) {
							rNum = lDir;
						}else if(ls.get(i).sXval - this.speed <= 0) {
							rNum = lDir;
						}else if(ls.get(i).sYval - this.speed <= 0) {
							rNum = lDir;
						}
					}
					
					this.storeVal[0] = ls.get(0).sXval;
					this.storeVal[1] = ls.get(0).sYval;
				}
				//System.out.println("Chk 1: " + rNum);
				//this.manageRightMov();
				
					//System.out.println(lDir + " -----------------Value------------ " + rNum);
					
					if(lDir == 0 || lDir == 1 ||(lDir == 2 && rNum == 2)) {
						rNum = 2;
						if(lDir == 0 || lDir == 1) {
							this.manageRightMov();
						}else {
							rNum = 0;
							this.manageMovement(rNum);
						}
						
						//this.manageMovement(rNum);
					}else if((lDir == 2 || lDir == 3)&& rNum == 1){
						//this.manageRightMov();
						this.manageMovement(rNum);
					}else if(rNum == 0) {
						this.manageMovement(rNum);
					}
			}
		}
		
	}
	
	public void manageRightMov() {
		if(index == 0) {
			SnakeAttributes e = new SnakeAttributes();
			int t1 = ls.get(index).sXval;
			int t2 = ls.get(index).sYval;
			ls.remove(index);
			e.sXval = t1 + this.speed;
			e.sYval = t2;
			ls.add(0,e);
			if(ls.get(index+1).sYval != t2) {
				for(int j = index + 1; j < height; j++) {
					SnakeAttributes e1 = new SnakeAttributes();
					int r1 = ls.get(j).sXval;
					int r2 = ls.get(j).sYval;
					ls.remove(j);
					e1.sXval = r1;
					if(lDir == 0) {
						e1.sYval = r2 - this.speed;
					}else if(lDir == 1) {
						e1.sYval = r2 + this.speed;
					}
					ls.add(j,e1);
				}
			}
			else {
				for(int j = index + 1; j < height; j++) {
					SnakeAttributes e1 = new SnakeAttributes();
					int r1 = ls.get(j).sXval;
					int r2 = ls.get(j).sYval;
					ls.remove(j);
					e1.sXval = r1 + this.speed;
					e1.sYval = r2;
					ls.add(j,e1);
				}
			}
		}
		else if(index == height - 1) {
			SnakeAttributes e = new SnakeAttributes();
			int t1 = ls.get(index).sXval;
			int t2 = ls.get(index).sYval;
			ls.remove(index);
			e.sXval = t1 + this.speed;
			e.sYval = t2;
			ls.add(e);
				for(int j = 0; j < index; j++) {
					SnakeAttributes e1 = new SnakeAttributes();
					int r1 = ls.get(j).sXval;
					int r2 = ls.get(j).sYval;
					ls.remove(j);
					e1.sXval = r1 + this.speed;
					e1.sYval = r2;
					ls.add(j,e1);
				}
		}
		else {
			SnakeAttributes e = new SnakeAttributes();
			int t1 = ls.get(index).sXval;
			int t2 = ls.get(index).sYval;
			ls.remove(index);
			e.sXval = t1 + this.speed;
			e.sYval = t2;
			ls.add(index,e);
			if(ls.get(index+1).sYval != t2) {
				for(int j = 0; j < index; j++) {
					SnakeAttributes e1 = new SnakeAttributes();
					int r1 = ls.get(j).sXval;
					int r2 = ls.get(j).sYval;
					ls.remove(j);
					e1.sXval = r1 + this.speed;
					e1.sYval = r2;
					ls.add(j,e1);
				}
				for(int j = index + 1; j < height; j++) {
					SnakeAttributes e1 = new SnakeAttributes();
					int r1 = ls.get(j).sXval;
					int r2 = ls.get(j).sYval;
					ls.remove(j);
					e1.sXval = r1;
					if(lDir == 0) {
						e1.sYval = r2 - this.speed;
					}else if(lDir == 1) {
						e1.sYval = r2 + this.speed;
					}
					ls.add(j,e1);
				}
			}
			else {
				for(int j = 0; j < index; j++) {
					SnakeAttributes e1 = new SnakeAttributes();
					int r1 = ls.get(j).sXval;
					int r2 = ls.get(j).sYval;
					ls.remove(j);
					e1.sXval = r1 + this.speed;
					e1.sYval = r2;
					ls.add(j,e1);
				}
				for(int j = index + 1; j < height; j++) {
					SnakeAttributes e1 = new SnakeAttributes();
					int r1 = ls.get(j).sXval;
					int r2 = ls.get(j).sYval;
					ls.remove(j);
					e1.sXval = r1 + this.speed;
					e1.sYval = r2;
					ls.add(j,e1);
				}
			}
			
		}
	index++;
	}
	
	public void manageMovement(int d) { 
		//System.out.println(index + "Chk 2: " + d);
		if(d == 0) {
			//System.out.println("index " + index);
				if(index == 0) {
					SnakeAttributes e = new SnakeAttributes();
					int t1 = ls.get(index).sXval;
					int t2 = ls.get(index).sYval;
					ls.remove(0);
					e.sXval = t1;
					e.sYval = t2 - this.speed;
					ls.add(0,e);
					//System.out.println(index + " x " + e.sXval);
					//System.out.println(index + " y " + e.sXval);
					if(ls.get(index+1).sXval != t1) {
						//System.out.println("1 Values: " + ls.get(index).sXval + " 2 Value: " + ls.get(index + 1).sXval);
						for(int j = index + 1; j < height; j++) {
							SnakeAttributes e1 = new SnakeAttributes();
							//System.out.println(j + " x " + e.sXval);
							//System.out.println(j + " y " + e.sXval);
							int r1 = ls.get(j).sXval;
							int r2 = ls.get(j).sYval;
							e1.sXval = r1 + this.speed;
							e1.sYval = r2;
							ls.remove(j);
							ls.add(j,e1);
							//break;
						}
					}else {
						for(int j = index + 1; j < height; j++) {
							SnakeAttributes e1 = new SnakeAttributes();
							int r1 = ls.get(j).sXval;
							int r2 = ls.get(j).sYval;
							ls.remove(j);
							e1.sXval = r1;
							e1.sYval = r2 - this.speed;
							ls.add(j,e1);
						}
					}
				}
				else if(index == height - 1) {
					for(int j = 0; j < index; j++) {
							SnakeAttributes e1 = new SnakeAttributes();
							int r1 = ls.get(j).sXval;
							int r2 = ls.get(j).sYval;
							ls.remove(j);
							e1.sXval = r1;
							e1.sYval = r2 - this.speed;
							ls.add(j,e1);
						}
					SnakeAttributes e = new SnakeAttributes();
					int t1 = ls.get(index).sXval;
					int t2 = ls.get(index).sYval;
					ls.remove(index);
					e.sXval = t1;
					e.sYval = t2 - this.speed;
					ls.add(e);
						
				}
				else {
					SnakeAttributes e = new SnakeAttributes();
					int t1 = ls.get(index).sXval;
					int t2 = ls.get(index).sYval;
					ls.remove(index);
					e.sXval = t1;
					e.sYval = t2 - this.speed;
					ls.add(index,e);
					if(ls.get(index+1).sXval != t1) {
						for(int j = 0; j < index; j++) {
							SnakeAttributes e1 = new SnakeAttributes();
							int r1 = ls.get(j).sXval;
							int r2 = ls.get(j).sYval;
							ls.remove(j);
							e1.sXval = r1;
							e1.sYval = r2 - this.speed;
							ls.add(j,e1);
						}
						for(int j = index + 1; j < height; j++) {
							SnakeAttributes e1 = new SnakeAttributes();
							int r1 = ls.get(j).sXval;
							int r2 = ls.get(j).sYval;
							e1.sXval = r1 + this.speed;
							e1.sYval = r2;
							ls.remove(j);
							ls.add(j,e1);
						}
					}else {
						for(int j = 0; j < index; j++) {
							SnakeAttributes e1 = new SnakeAttributes();
							int r1 = ls.get(j).sXval;
							int r2 = ls.get(j).sYval;
							ls.remove(j);
							e1.sXval = r1;
							e1.sYval = r2 - this.speed;
							ls.add(j,e1);
						}
						for(int j = index + 1; j < height; j++) {
							SnakeAttributes e1 = new SnakeAttributes();
							int r1 = ls.get(j).sXval;
							int r2 = ls.get(j).sYval;
							ls.remove(j);
							e1.sXval = r1;
							e1.sYval = r2 - this.speed;
							ls.add(j,e1);
						}
					}
				}
				
			index++;
		}
		else if(d == 1) {  //---------------------------------------------For down
			if(index == 0) {
				SnakeAttributes e = new SnakeAttributes();
				int t1 = ls.get(index).sXval;
				int t2 = ls.get(index).sYval;
				ls.remove(index);
				e.sXval = t1;
				e.sYval = t2 + this.speed;
				ls.add(0,e);
				if(ls.get(index+1).sXval != t1) {
					for(int j = index + 1; j < height; j++) {
						SnakeAttributes e1 = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval;
						ls.remove(j);
						if(lDir == 2) {
							e1.sXval = r1 + this.speed;
							e1.sYval = r2;
						}else if(lDir == 3) {
							e1.sXval = r1 - this.speed;
							e1.sYval = r2;
						}
						ls.add(j,e1);
					}
				}else {
					for(int j = index + 1; j < height; j++) {
						SnakeAttributes e1 = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval;
						ls.remove(j);
						e1.sXval = r1;
						e1.sYval = r2 + this.speed;
						ls.add(j,e1);
					}
				}
			}
			else if(index == height - 1) {
				SnakeAttributes e = new SnakeAttributes();
				int t1 = ls.get(index).sXval;
				int t2 = ls.get(index).sYval;
				ls.remove(index);
				e.sXval = t1;
				e.sYval = t2 + this.speed;
				ls.add(e);
					for(int j = 0; j < index; j++) {
						SnakeAttributes e1 = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval;
						ls.remove(j);
						e1.sXval = r1;
						e1.sYval = r2 + this.speed;
						ls.add(j,e1);
					}
			}
			else {
				SnakeAttributes e = new SnakeAttributes();
				int t1 = ls.get(index).sXval;
				int t2 = ls.get(index).sYval;
				ls.remove(index);
				e.sXval = t1;
				e.sYval = t2 + this.speed;
				ls.add(index,e);
				if(ls.get(index+1).sXval != t1) {
					for(int j = 0; j < index; j++) {
						SnakeAttributes e1 = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval;
						ls.remove(j);
						e1.sXval = r1;
						e1.sYval = r2 + this.speed;
						ls.add(j,e1);
					}
					for(int j = index + 1; j < height; j++) {
						SnakeAttributes e1 = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval;
						ls.remove(j);
						if(lDir == 2) {
							e1.sXval = r1 + this.speed;
							e1.sYval = r2;
						}else if(lDir == 3) {
							e1.sXval = r1 - this.speed;
							e1.sYval = r2;
						}
						ls.add(j,e1);
					}
				}else {
					for(int j = 0; j < index; j++) {
						SnakeAttributes e1 = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval;
						ls.remove(j);
						e1.sXval = r1;
						e1.sYval = r2 + this.speed;
						ls.add(j,e1);
					}
					for(int j = index + 1; j < height; j++) {
						SnakeAttributes e1 = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval;
						ls.remove(j);
						e1.sXval = r1;
						e1.sYval = r2 + this.speed;
						ls.add(j,e1);
					}
				}
			}
		index++;
		}
		/*
		else if(d == 2) {   //-----------------------------------------For Right
			
		
		}*/
	}
	
	public boolean horLineChk() {
		int xTemp = ls.get(0).sXval;
		for(int i = 0; i < height; i++) {
			if(xTemp != ls.get(i).sXval) {
				return false;
			}
		}
		return true;
	}
	
	public boolean verLineChk() {
		int yTemp = ls.get(0).sYval;
		for(int i = 0; i < height; i++) {
			if(yTemp != ls.get(i).sYval) {
				return false;
			}
		}
		return true;
	}
	
	public void setHeight_X() {
		//System.out.println("Horizontal");
		int tempX = x;
		int tempY = y;
		for(int i = 0; i < this.height; i++) {
			SnakeAttributes e = new SnakeAttributes();
			e.sXval = tempX;
			e.sYval = tempY;
			//System.out.println(i + " X: " + tempX + " Y: " + tempY);
			ls.add(e);
			tempX = tempX - this.speed;
		}
	}
	
	public void setHeight_Y() {
		//System.out.println("Vertical");
		int tempX = x;
		int tempY = y;
		for(int i = 0; i < this.height; i++) {
			SnakeAttributes e = new SnakeAttributes();
			e.sXval = tempX;
			e.sYval = tempY;
			//System.out.println(i + " X: " + tempX + " Y: " + tempY);
			ls.add(e);
			tempX = tempX;
			tempY = tempY + this.speed;
		}
	}
	
	public void wallCheck() {
		for(int i = 0; i < height; i++) {
			if(ls.get(i).sXval <= 0) {
				this.wallChk1 = true;
				this.index = i;
				break;
			}
		}
		
		if(wallChk1 == true) {
			for(int i = this.index; i < height; i++) {
				if(i == height - 1) {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = this.sizeX + r1;
					int t2 = r2;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}else {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = this.sizeX + r1;
					int t2 = r2;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}
			}
			this.wallChk1 = false;
		}
		
		for(int i = 0; i < height; i++) {
			if(ls.get(i).sYval <= 0) {
				this.wallChk1 = true;
				this.index = i;
				break;
			}
		}
		
		if(wallChk1 == true) {
			for(int i = this.index; i < height; i++) {
				if(i == height - 1) {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = r1;
					int t2 = this.sizeY + r2;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}else {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = r1;
					int t2 = this.sizeY + r2;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}
			}
			this.wallChk1 = false;
		}
		
		for(int i = 0; i < height; i++) {
			if(ls.get(i).sYval >= this.sizeY) {
				this.wallChk1 = true;
				this.index = i;
				break;
			}
		}
		
		if(wallChk1 == true) {
			for(int i = this.index; i < height; i++) {
				if(i == height - 1) {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = r1;
					int t2 = r2 - this.sizeY;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}else {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = r1;
					int t2 = r2 - this.sizeY;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}
			}
			this.wallChk1 = false;
		}
		
		for(int i = 0; i < height; i++) {
			if(ls.get(i).sXval >= this.sizeX) {
				this.wallChk1 = true;
				this.index = i;
				break;
			}
		}
		if(wallChk1 == true) {
			for(int i = this.index; i < height; i++) {
				if(i == height - 1) {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = r1 - this.sizeX;
					int t2 = r2;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}else {
					int r1 = ls.get(i).sXval;
					int r2 = ls.get(i).sYval;
					ls.remove(i);
					SnakeAttributes e = new SnakeAttributes();
					int t1 = r1 - this.sizeX;
					int t2 = r2;
					e.sXval = t1;
					e.sYval = t2;
					ls.add(e);
				}
			}
			this.wallChk1 = false;
		}
	}
	
	
	public void keepInStraightUp() { ////for up
		int tmp1 = ls.get(0).sXval;
		int tmp2 = ls.get(0).sYval;
		for(int i =1; i < height; i++) {
			int t1 = tmp1;
			int t2 = tmp2 + i*this.speed;
			SnakeAttributes e = new SnakeAttributes();
			e.sXval = t1;
			e.sYval = t2;
			ls.remove(i);
			ls.add(i,e);
		}
	}
	
	public void keepInStraightDown() { ////for up
		int tmp1 = ls.get(height - 1).sXval;
		int tmp2 = ls.get(height - 1).sYval;
		for(int i = 0; i < height; i++) {
			int t1 = tmp1;
			int t2 = tmp2 - i*this.speed;
			SnakeAttributes e = new SnakeAttributes();
			e.sXval = t1;
			e.sYval = t2;
			ls.remove(i);
			ls.add(i,e);
		}
	}
	
	public void keepInRight() {
		int tmp1 = ls.get(0).sXval;
		int tmp2 = ls.get(0).sYval;
		for(int i =1; i < height; i++) {
			int t1 = tmp1 + ((height - 1) - i)*this.speed;
			int t2 = tmp2;
			SnakeAttributes e = new SnakeAttributes();
			e.sXval = t1;
			e.sYval = t2;
			ls.remove(i);
			ls.add(i,e);
		}
	}
	
	public void draw(Graphics2D g2) {
		

		for(wallIndex = 0; wallIndex < this.height; wallIndex++) {
			if(ls.get(wallIndex).sXval <= 0) {
				//System.out.println("Smaller than X");
				//System.out.println(wallIndex +" x: " + ls.get(wallIndex).sXval+ " y: " +ls.get(wallIndex).sYval);
				SnakeAttributes s1 = new SnakeAttributes();
				int t1 = ls.get(wallIndex).sXval + this.sizeX - this.speed;
				int t2 = ls.get(wallIndex).sYval;
				s1.sXval = t1;
				s1.sYval = t2;
				ls.remove(wallIndex);
				if(wallIndex == height - 1) {
					ls.add(s1);
				}else {
					ls.add(wallIndex,s1);
				}
				for(int j = 0; j < height; j++) {
					if(j != wallIndex) {
						SnakeAttributes e = new SnakeAttributes();
						int r1 = ls.get(j).sXval + this.speed;
						int r2 = ls.get(j).sYval;
						e.sXval = r1;
						e.sYval = r2;
						ls.remove(j);
						ls.add(j,e);
					}
				}
				//this.anotherTemp = true;
				//wallIndex++;
			}
			if(ls.get(wallIndex).sYval <= 0) {
				//System.out.println("Smaller than Y");
				//System.out.println(wallIndex +" x: " + ls.get(wallIndex).sXval+ " y: " +ls.get(wallIndex).sYval);
				SnakeAttributes s1 = new SnakeAttributes();
				int t1 = ls.get(wallIndex).sXval;
				int t2 = ls.get(wallIndex).sYval + this.sizeY - this.speed;
				s1.sXval = t1;
				s1.sYval = t2;
				ls.remove(wallIndex);
				if(wallIndex == height - 1) {
					ls.add(s1);
				}else {
					ls.add(wallIndex,s1);
				}
				for(int j = 0; j < height; j++) {
					if(j != wallIndex) {
						//System.out.println("here " + j);
						SnakeAttributes e = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval  + this.speed;
						e.sXval = r1;
						e.sYval = r2;
						ls.remove(j);
						ls.add(j,e);
					}
				}
				//this.anotherTemp = true;
				//wallIndex++;
			}
			if(ls.get(wallIndex).sYval >= this.sizeY) {
				//System.out.println("Greater than Y");
				//System.out.println(wallIndex +" x: " + ls.get(wallIndex).sXval+ " y: " +ls.get(wallIndex).sYval);
				//System.out.println(i + " - " +ls.get(i).sXval);
				//int tmp = wallIndex;
				SnakeAttributes s1 = new SnakeAttributes();
				int t1 = ls.get(wallIndex).sXval;
				int t2 = ls.get(wallIndex).sYval - this.sizeY + this.speed;
				s1.sXval = t1;
				s1.sYval = t2;
				ls.remove(wallIndex);
				if(wallIndex == height - 1) {
					ls.add(s1);
				}else {
					ls.add(wallIndex,s1);
				}
				for(int j = 0; j < height; j++) {
					if(j != wallIndex) {
						SnakeAttributes e = new SnakeAttributes();
						int r1 = ls.get(j).sXval;
						int r2 = ls.get(j).sYval  + this.speed;
						e.sXval = r1;
						e.sYval = r2;
						ls.remove(j);
						ls.add(j,e);
					}
				}
				//this.anotherTemp = true;
				//wallIndex++;
			}
			if(ls.get(wallIndex).sXval >= this.sizeX) {
				//System.out.println("Greater than X");
				//this.conditionChk = true;
				//System.out.println(wallIndex +" x: " + ls.get(wallIndex).sXval+ " y: " +ls.get(wallIndex).sYval);
				SnakeAttributes s1 = new SnakeAttributes();
				int t1 = ls.get(wallIndex).sXval - this.sizeX + this.speed;
				int t2 = ls.get(wallIndex).sYval;
				s1.sXval = t1;
				s1.sYval = t2;
				ls.remove(wallIndex);
				if(wallIndex == height - 1) {
					ls.add(s1);
				}else {
					ls.add(wallIndex,s1);
				}
				for(int j = 0; j < height; j++) {
					if(j != wallIndex) {
						SnakeAttributes e = new SnakeAttributes();
						int r1 = ls.get(j).sXval + this.speed;
						int r2 = ls.get(j).sYval;
						e.sXval = r1;
						e.sYval = r2;
						ls.remove(j);
						ls.add(j,e);
					}
				}
				//this.anotherTemp = true;
				//wallIndex++;
			}
//		if(this.anotherTemp == true) {
//			wallIndex++;
//		}
			
		}
		/*
		if(rNum == 0 && index == height - 1) {
			this.keepInStraightUp();
		}else if(rNum == 1 && index == height - 1) {
			this.keepInStraightDown();
		}else if(rNum == 2 && index == height - 1) {
			this.keepInRight();
		}*/
		g2.setColor(Color.BLUE);
		g2.fillRect(ls.get(0).sXval, ls.get(0).sYval,this.speed,this.speed);
		g2.setColor(color);
		for(int i = 1; i < this.height; i++) {
			g2.fillRect(ls.get(i).sXval, ls.get(i).sYval,this.speed,this.speed);
		}
	}
}
