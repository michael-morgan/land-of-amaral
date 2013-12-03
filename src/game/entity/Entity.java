package game.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Entity class
 * @author Michael Morgan
 */
public abstract class Entity
{

	protected Rectangle rectangle;

	protected int width, height;
	protected int x, y;

	protected Image sprite;

	/**
	 * render method
	 * @param g
	 */
	public abstract void render(Graphics g);

	/**
	 * update method
	 */
	public abstract void update();

	/**
	 * getX method
	 * @return x
	 */
	public abstract int getX();

	/**
	 * getY method
	 * @return y
	 */
	public abstract int getY();

	/**
	 * getRectangle method
	 * @return rectangle
	 */
	public abstract Rectangle getRectangle();

} //end of Entity class