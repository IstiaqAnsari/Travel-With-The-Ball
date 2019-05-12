
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.Timer;

public class Ball extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	Timer t;
	Random rand = new Random();
	Image bg;
	Action action;
	private Image f;
	ImageIcon ball, ball2;
	private double x, y, vy;
	private final double g=2;
	int power = 1, PD = 150, powerDuration = PD;
	boolean isOnPower = false;
	
	
	public Ball(){
		setFocusable(true);
		//clock1 = new Timer(100, main);
		bg = new ImageIcon("src\\hb.png").getImage();
		ball = new ImageIcon("src\\ball.png");
		ball2 = new ImageIcon("src\\powerball.png");
		f = ball.getImage();
		x = 100;y = 520;
		vy = 40;
		t = new Timer(100, action);
	}
	
	public void start(){
		t.start();
	}
	public void stop(){
		t.stop();
	}
	
	// paint  paint  paint  paint  paint  paint  paint  paint  paint  paint  
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(f, (int)x, (int)y, null);
	}
	
	public void setX(double a){
		x = a;
	}
	public void setY(double b){
		y = b;
	}
	public int getX(){
		return (int)x;
	}
	public int getY(){
		return (int)y;
	}
	public int getWidth(){
		return f.getWidth(null);
	}
	public int getHeight(){
		return f.getHeight(null);
	}
	
	// about the power
	public int getPower(){
		return power;
	}
	public void gotPower(){
		power ++;
	}
	public void powerUp(){
		if(power>0){
			isOnPower = true;
			f = ball2.getImage();
			power --;
		}
	}
	public void powerStop(){
		if(isOnPower){
			powerDuration -- ;
			if(powerDuration <=0 ){
				isOnPower = false;
				powerDuration = PD;
				f = ball.getImage();
			}
		}
	}
	public boolean usingPower(){
		return isOnPower;
	}
	
	public void reset(){
		power = 1;
		x = 100;y = 520;
		vy = 40;
		isOnPower = false;
		f = ball.getImage();
	}
	
	public Rectangle bounds(){
		return ( new Rectangle((int)x, (int)y , f.getWidth(null), f.getHeight(null) ) );
	}
	
	
	public int xmove = 0;
	public void projectile(){
		x = x + xmove;
		if(x>1100 || x<10)
			xmove =  - xmove;
		
		y = y - vy;
		vy = vy - g;
		
		if(y>=520){
			vy = 40;
			y = 520;
		}
	}
	
	public void down(){
		vy -= 15;
	}
	public void up(){
		if(vy<10 && y>0)
			vy = 20;
	}

	
	public class Action implements ActionListener{
	
		public void actionPerformed(ActionEvent e) {
			projectile();
			repaint();
		}
	}
}




