import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.*;

public class Main extends JFrame implements KeyListener{
	
	ScreenManager screen;
	DisplayMode dm;
	Window w;
	Ball ball = new Ball();
	public static void main(String[] args){
		Main thiss = new Main();
		thiss.run();
	}
	
	
	public void run(){
		screen = new ScreenManager();
		dm = screen.findFirstCompatibleMode(modes1);
		screen.setFullScreen(dm);
		w = screen.getFullScreenWindow();
		w.setFont(new  Font("Arial", Font.PLAIN, 40));
		screen.update();
		
	}
	
	public void loop(){
		while(true){
			Graphics2D g = screen.getGraphics();
			paint(g);
			screen.update();
		}
	}
	
	public void paint(Graphics2D g){
		super.paint(g);
		g.drawString("running", 50, 50);
		ball.paint(g);
	}
	
	
	
	
	
	public static final DisplayMode modes1[] = {
		new DisplayMode(800,600,32,0),
		new DisplayMode(800,600,24,0),
		new DisplayMode(800,600,16,0),
		new DisplayMode(640,480,32,0),
		new DisplayMode(640,480,24,0),
		new DisplayMode(640,480,16,0)			
	};

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_ENTER){
			ball.start();
		}
			
		
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
