package game.sprite;

import game.frame.Screen;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Map class
 * @author Michael Morgan
 */
public class Map
{
	private Screen screen;

	private Image map;
	private double x, y;

	private boolean bossDead;

	/**
	 * Map constructor
	 */
	public Map(Screen screen)
	{
		this.screen = screen;

		init();
	} //end of Map constructor

	/**
	 * init method
	 */
	private void init()
	{
		x = 0;
		y = 0;

		bossDead = false;

		map = new ImageIcon("res/map/level" + screen.getPlayer().getMap() + ".png").getImage();
	} //end of init method

	/**
	 * render method
	 * @param g
	 */
	public void render(Graphics g)
	{
		g.drawImage(map, (int)Math.round(x), (int)Math.round(y), map.getWidth(null), map.getHeight(null), null);
	} //end of render method

	/**
	 * setMapX method
	 * @param amount
	 */
	public void setMapX(int amount)
	{
		x = amount;
	} //end of setMapX method

	/**
	 * setMapY method
	 * @param amount
	 */
	public void setMapY(int amount)
	{
		y = amount;
	} //end of setMapY method

	/**
	 * incrementMapX method
	 */
	public void incrementMapX(double amount)
	{
		x += amount;
	} //end of incrementMapX method

	/**
	 * decrementMapX method
	 */
	public void decrementMapX(double amount)
	{
		x -= amount;
	} //end of decrementMapX method

	/**
	 * incrementMapY method
	 */
	public void incrementMapY(double amount)
	{
		y += amount;
	} //end of incrementMapY method

	/**
	 * decrementMapY method
	 */
	public void decrementMapY(double amount)
	{
		y -= amount;
	} //end of decrementMapY method

	/**
	 * getX method
	 * @return x
	 */
	public int getX()
	{
		return (int)Math.round(x);
	} //end of getX method

	/**
	 * getY method
	 * @return y
	 */
	public int getY()
	{
		return (int)Math.round(y);
	} //end of getY method

	/**
	 * getBossDead method
	 * @return
	 */
	public boolean getBossDead()
	{
		return bossDead;
	} //end of getBossDead method

	/**
	 * setBossDead method
	 * @param answer
	 */
	public void setBossDead(boolean answer)
	{
		bossDead = answer;
	} //end of setBossDead method

} //end of Map class
