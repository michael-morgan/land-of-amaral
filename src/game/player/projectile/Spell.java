package game.player.projectile;

import game.frame.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

/**
 * Spell class
 * @author Michael Morgan
 */
public class Spell implements Runnable
{
	private int x, y, height, width, speed, startX, startY;

	private Image fireball;

	private Screen screen;

	private Rectangle spellRect;

	private boolean up, down, left, right, run;

	private Thread spellLoop;

	/**
	 * Spell constructor
	 * @param screen
	 */
	public Spell(Screen screen)
	{
		this.screen = screen;

		init();
		
		screen.getPlayer().decrementMana(10);
		screen.getUI().decrementMpWidth(10);

		spellLoop.start();
		run = true;
	} //end of Spell constructor

	/**
	 * init method
	 */
	private void init()
	{
		fireball = new ImageIcon("res/player/spell/fireball.png").getImage();

		height = 32;
		width = 32;

		speed = 1;

		x = screen.getPlayer().getX() - screen.getMap().getX();
		y = screen.getPlayer().getY() - screen.getMap().getY();

		startX = x;
		startY = y;

		spellRect = new Rectangle(x, y, width, height);
		
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

		spellLoop = new Thread(this);
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
				spellLoop.sleep(5);
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
		spellLoop.stop();
		run = false;
	} //end of stop method

	/**
	 * render method
	 * @param g
	 */
	public void render(Graphics g)
	{
		g.drawImage(fireball, x + screen.getMap().getX(), y + screen.getMap().getY(), width, height, null);

		if(screen.getDebug())
		{
			g.setColor(Color.RED);
			g.drawRect(x + screen.getMap().getX(), y + screen.getMap().getY(), spellRect.width, spellRect.height);
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

		spellRect.setLocation(x + screen.getMap().getX(), y + screen.getMap().getY());
	} //end of update method

	/**
	 * getSpellRect method
	 * @return spellRect
	 */
	public Rectangle getSpellRect()
	{
		return spellRect;
	} //end of getSpellRect method

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
	
} //end of Spell class