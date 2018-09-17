package Godown;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class MainWindow extends JFrame implements KeyListener {
	
	private Gameui mw;
	
	public MainWindow(){
		this.setLayout(null);
		
		mw = new Gameui(this);
		this.getContentPane().add(mw);
		
		this.addKeyListener(this);
		this.setTitle("hhl是男人就下100层");
		this.setSize(500, 600);
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		mw.run();

	}

	public void keyPressed(KeyEvent e) {
		mw.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		mw.keyReleased(e);
	}
	public void keyTyped(KeyEvent arg0) {}
	
}
