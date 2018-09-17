package Godown;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player {
	BufferedImage image1;
	BufferedImage image2;
	int score;
	int x;
	int y;
	int index;
	int v;
	int g;
	int t;
	int speed;
	int keycode;
	boolean isOnFloor;
	boolean islive;
	int width;
	int height;
	public Player(int x){
		score = 95;
		isOnFloor = false;
		islive = true;
		this.x = x;
		y = 40;
		g = 4;
		t = 1;
		v = 0;
		speed = 3;
		try {
			image1 = ImageIO.read(getClass().getResource("player1.png"));
			image2 = ImageIO.read(getClass().getResource("player2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		width = 60;
		height = 45;
	}
	
	public void draw(Graphics g){
		move();
		if(!islive)
			return;
		if(!isOnFloor){
			fall();
		}
		if(keycode == 39)
			g.drawImage(image1, x, y, width, height, null);
		else
			g.drawImage(image2, x, y, width, height, null);
		g.drawString("hhl", x+10, y);
	}
	
	
	
	public void move() {
		// TODO Auto-generated method stub
		if(y<=0||y>=600-height)
			islive = false;
	}

	public void fall() {
		// TODO Auto-generated method stub
		y += g*t/2;
	}

	public void keyPressed(KeyEvent e) {
		keycode = e.getKeyCode();
		//System.out.println(keycode);
		switch (keycode) {
		case 37:
			x-=speed;
			break;// ��
		case 39:
			x+=speed;
			break;// ��
		}
	}

	public void keyReleased(KeyEvent e) {
		
	}

}
