import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JFrame;
import java.awt.DisplayMode;
import java.awt.Graphics;
import java.util.Random;
import java.awt.Image;
import javax.swing.ImageIcon;

public class ScreenManager {

	private GraphicsDevice vc;
	public ScreenManager()
	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = env.getDefaultScreenDevice();
	}
	
	//get all compatible display modes
	public DisplayMode[] getCompatibleDispalyMode()
	{
		return vc.getDisplayModes();
	}
	
	//compare d modes and find a match
	public DisplayMode findFirstCompatibleMode(DisplayMode modes[])
	{
		DisplayMode goodModes[] = vc.getDisplayModes();
		for(int i=0;i<modes.length;i++)
		{
			for(int j=0;j<goodModes.length;j++)
			{
				if(displayModesMatch(modes[i], goodModes[j]))
				{
					return modes[i];
				}
			}
		}
		return null;
	}
	
	//comparing two modes
	public boolean displayModesMatch(DisplayMode m1, DisplayMode m2)
	{
		if(m1.getWidth()!=m2.getWidth() || m1.getHeight()!=m2.getHeight())
		{
			return false;
		}
		if(m1.getBitDepth()!=DisplayMode.BIT_DEPTH_MULTI && m2.getBitDepth()!=DisplayMode.BIT_DEPTH_MULTI )
		{
			return false;
		}
		if(m1.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN && 
				m2.getRefreshRate()!=DisplayMode.REFRESH_RATE_UNKNOWN &&
				m1.getRefreshRate()!=m2.getRefreshRate())
		{
			return false;
		}
		return true;
		
	}
	
	//get current Display mode
	public DisplayMode getCurrentDisplayMode()
	{
		return vc.getDisplayMode();
	}
	// make frame full screen
	public void setFullScreen(DisplayMode dm)
	{
		JFrame f = new JFrame();
		f.setUndecorated(true);
		f.setIgnoreRepaint(true);
		f.setResizable(false);
		vc.setFullScreenWindow(f);
		if(dm != null && vc.isDisplayChangeSupported())
		{
			try
			{
				vc.setDisplayMode(dm);
			}catch(Exception e){}
		}
		f.createBufferStrategy(2);
	}
	// we will make graphics objects = to this
	public Graphics2D getGraphics()
	{
		Window w = (Window) vc.getFullScreenWindow();
		if(w!=null)
		{
			BufferStrategy s = w.getBufferStrategy();
			return (Graphics2D)s.getDrawGraphics();
		}
		else 
			return null;
	}
	//update display	
	public void update()
	{
		Window w = (Window) vc.getFullScreenWindow();
		if(w!=null)
		{
			BufferStrategy s = w.getBufferStrategy();
			if(!s.contentsLost())
			{
				s.show();
			}
		}
	}
	//return full screen window
	public Window getFullScreenWindow()
	{
		return (Window) vc.getFullScreenWindow();
	}
	//height and width
	public int getWidth()
	{
		Window w = (Window) vc.getFullScreenWindow();
		if(w!=null)
			return w.WIDTH;
		else 
			return 0;
	}
	public int getHeight()
	{
		Window w = (Window) vc.getFullScreenWindow();
		if(w!=null)
			return w.HEIGHT;
		else 
			return 0;
	}
	//RESTORE WINDOW
	public void restoreScreen()
	{
		Window w = (Window) vc.getFullScreenWindow();
		if(w!=null)
			w.dispose();
		vc.setFullScreenWindow(null);
	}
	//create image compatible with window
	public BufferedImage createCompatibleImage(int w,int h, int t)
	{
		Window win = (Window) vc.getFullScreenWindow();
		if(win!=null)
		{
			GraphicsConfiguration gc  = win.getGraphicsConfiguration();
			return gc.createCompatibleImage(w,h,t);
		}
		return null;
	}
	
	
}

