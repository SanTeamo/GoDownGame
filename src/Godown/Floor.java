package Godown;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Floor {
	int x;
	int y;
	int distance;
	int speed;
	int type;
	int width;
	int height;
	boolean isPass;
	BufferedImage image1;
	BufferedImage image2;
	public Floor(int num){
		isPass = false;
		distance = 150;
		type = (int) (Math.random()*2+1);
		try {
			image1 = ImageIO.read(getClass().getResource("1.png"));
			image2 = ImageIO.read(getClass().getResource("2.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x = (int) (Math.random()*(500-120));
		y = 300 + distance*(num - 1);
		speed = 3;
		width = 120;
		height = 20;
	}
	
	public void draw(Graphics g){
		move();
		if(type==1)
			g.drawImage(image1, x, y, width, height, null);
		else
			g.drawImage(image2, x, y, width, height, null);
	}

	private void move() {
		// TODO Auto-generated method stub
		y -= speed;
		if(y<-height){
			y=600+distance-20;
			isPass = false;
		}
	}
	
	public void judge(Player player) {
		// TODO Auto-generated method stub
		isOnFloor(player);
		if(!player.isOnFloor)
		isOnSide(player);
		isGetScore(player);
	}
	
	private void isGetScore(Player player) {
		// TODO Auto-generated method stub
		if(!isPass&&player.islive){
			if(player.y>y+height){
				isPass = true;
				player.score++;
			}
			
		}
	}

	public void isOnFloor(Player player){
		if(player.y<y&&y-player.y<=player.height){
			if(x>player.x&&player.x+player.width/2>=x){
				player.isOnFloor = true;
				if(type==1)player.islive=false;
				player.y = y - player.height;
				return;
				
			}else if(x<player.x&&player.x-x<=width-player.width/2){
				player.isOnFloor = true;
				if(type==1)player.islive=false;
				player.y = y - player.height;
				return;
			}else{
				player.isOnFloor = false;
			}
		}
		if(player.y>y+height){
			player.isOnFloor = false;
		}
		
	}
	
	public void isOnSide(Player player){
		if(y>player.y&&y-player.y<player.height){
			//人物在板子左边
			if(player.x<x&&player.x+player.width>x){
				player.x = x - player.width;
			}
			//人物在板子右边
			if(player.x>x&&player.x<x+width){
				player.x = x + width;
			}
		}	
	}

}
