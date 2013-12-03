package game.player.projectile;

import game.frame.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Arrow class
 * @author Michael Morgan
 */
public class Arrow implements Runnable
{
	private int x, y, height, width, speed, startX, startY;

	private Image[] arrow;

	private Screen screen;

	private Rectangle arrowRect;

	private boolean up, down, left, right, run;

	private Thread arrowLoop;

	/**
	 * Arrow constructor
	 * @param screen
	 */
	public Arrow(Screen screen)
	{
		this.screen = screen;

		init();

		arrowLoop.start();
		run = true;
	} //end of Arrow constructor

	/**
	 * init method
	 */
	private void init()
	{
		arrow = new Image[4];
		arrow[0] = new ImageIcon("res/player/arrow/arrowup.png").getImage();
		arrow[1] = new ImageIcon("res/player/arrow/arrowright.png").getImage();
		arrow[2] = new ImageIcon("res/player/arrow/arrowdown.png").getImage();
		arrow[3] = new ImageIcon("res/player/arrow/arrowleft.png").getImage();

		height = 32;
		width = 32;

		speed = 1;

		x = screen.getPlayer().getX() - screen.getMap().getX();
		y = screen.getPlayer().getY() - screen.getMap().getY();

		startX = x;
		startY = y;

		arrowRect = new Rectangle(x, y, width, height);

		if(screen.getPlayer().getUp())
		{
			up = true;
		}
		else if(screen.getPlayer().getDown())
		{
			down = true;
		}
		else if(screen.getPlayer().getLeft())
		{
			left = true;
		}
		else if(screen.getPlayer().getRight())
		{
			right = true;
		}

		arrowLoop = new Thread(this);
		run = false;

	} //end of init method

	/**
	 * run method
	 */
	@SuppressWarnings("static-access")
	public void run()
	{
		while(run)
		{
			update();

			try
			{
				arrowLoop.sleep(5);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	} //end of run method

	/**
	 * stop method
	 */
	@SuppressWarnings("deprecation")
	public synchronized void stop()
	{
		arrowLoop.stop();
		run = false;
	} //end of stop method

	/**
	 * render method
	 * @param g
	 */
	public void render(Graphics g)
	{
		if(up)
		{
			g.drawImage(arrow[0], x + screen.getMap().getX(), y + screen.getMap().getY(), width, height, null);
		}
		else if(down)
		{
			g.drawImage(arrow[2], x + screen.getMap().getX(), y + screen.getMap().getY(), width, height, null);
		}
		else if(left)
		{
			g.drawImage(arrow[3], x + screen.getMap().getX(), y + screen.getMap().getY(), width, height, null);
		}
		else if(right)
		{
			g.drawImage(arrow[1], x + screen.getMap().getX(), y + screen.getMap().getY(), width, height, null);
		}

		if(screen.getDebug())
		{
			g.setColor(Color.RED);
			g.drawRect(x + screen.getMap().getX(), y + screen.getMap().getY(), arrowRect.width, arrowRect.height);
		}
	} //end of render method

	/**
	 * update method
	 */
	public void update()
	{
		if(up)
		{
			y -= speed;
		}
		else if(down)
		{
			y += speed;
		}
		else if(left)
		{
			x -= speed;
		}
		else if(right)
		{
			x += speed;
		}

		arrowRect.setLocation(x + screen.getMap().getX(), y + screen.getMap().getY());
	} //end of update method

	/**
	 * getArrowRect method
	 * @return arrowRect
	 */
	public Rectangle getArrowRect()
	{
		return arrowRect;
	} //end of getArrowRect method

	/**
	 * getX method
	 * @return x
	 */
	public int getX()
	{
		return x;
	} //end of getX method

	/**
	 * getY method
	 * @return y
	 */
	public int getY()
	{
		return y;
	} //end of getY method

	/**
	 * getStartX method
	 * @return startX
	 */
	public int getStartX()
	{
		return startX;
	} //end of getStartX method

	/**
	 * getStartY method
	 * @return startY
	 */
	public int getStartY()
	{
		return startY;
	} //end of getStartY method

	/**
	 * getUp method
	 * @return up
	 */
	public boolean getUp()
	{
		return up;
	} //end of getUp method

	/**
	 * getDown method
	 * @return down
	 */
	public boolean getDown()
	{
		return down;
	} //end of getDown method

	/**
	 * getLeft method
	 * @return left
	 */
	public boolean getLeft()
	{
		return left;
	} //end of getLeft method

	/**
	 * getRight method
	 * @return right
	 */
	public boolean getRight()
	{
		return right;
	} //end of getRight method

} //end of Arrow class
