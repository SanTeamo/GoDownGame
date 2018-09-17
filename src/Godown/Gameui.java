package Godown;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Gameui extends JPanel{
	Player player;
	Floor f1;
	Floor f2;
	Floor f3;
	Floor f4;
	Floor f5;
	int gamestate;
	int gameready = 1;
	int gaming = 2;
	int gameover = 3;
	int gamewin = 4;
	BufferedImage gover;
	MainWindow main;
	
	public Gameui(MainWindow main){
		this.main = main;
		gamestate = gameready;
		f1 = new Floor(1);
		f2 = new Floor(2);
		f3 = new Floor(3);
		f4 = new Floor(4);
		f5 = new Floor(5);
		player = new Player(250);
		try {
			gover = ImageIO.read(getClass().getResource("gameover.JPG"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setBackground(Color.white);
		this.setSize(500, 600);
		MouseAdapter ma = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(e.getButton()==MouseEvent.BUTTON1){
					if(gamestate == gameover||gamestate == gamewin){
						player = new Player(250);
						f1 = new Floor(1);
						f2 = new Floor(2);
						f3 = new Floor(3);
						f4 = new Floor(4);
						f5 = new Floor(5);
						gamestate = gaming;
					}else if(gamestate == gameready){	
						gamestate = gaming;
					}
				}
			}
		};
		addMouseListener(ma);
	}
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		if(gamestate==gaming){
			player.draw(g);
			f1.draw(g);
			f2.draw(g);
			f3.draw(g);
			f4.draw(g);
			f5.draw(g);
		}
		g.setColor(Color.black);
		g.setFont(new Font("微软雅黑", 0, 55));
		if(!player.islive){
			gamestate = gameover;
			f1.draw(g);
			f2.draw(g);
			f3.draw(g);
			f4.draw(g);
			f5.draw(g);
			g.drawImage(gover, 0, 0, 500, 600, null);
			g.drawString("Game Over", 100, 300);
			g.drawString("点击重新开始", 90, 350);
		}
		if(gamestate == gameready){
			g.drawString("点击开始游戏", 100, 300);
		}
		if(gamestate == gamewin){
			g.drawString("你真棒", 100, 300);
			g.drawString("点击重新开始", 90, 350);
		}
		g.drawString(String.valueOf(player.score), 10, 50);
	}
	
	public void run(){
		while(true){
			if(gamestate==gaming){
				f1.judge(player);
				f2.judge(player);
				f3.judge(player);
				f4.judge(player);
				f5.judge(player);
			}
			if(player.score == 100){
				gamestate = gamewin;
			}
			repaint();
			try {
				Thread.sleep(70);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		player.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		player.keyReleased(e);
	}

}
