package game.enemy;

import game.entity.Entity;
import game.frame.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

/**
 * Skeleton class
 * @author Michael Morgan
 *
 */
public class Skeleton extends Entity
{	
	private int x, y, direction, animation;
	private int health;
	private double hpWidth;
	private double dx, dy;

	private boolean moving, dead;

	private int attackWidth, attackHeight, aggroWidth, aggroHeight, padding;
	private boolean attack, aggro;
	private int str;

	private Timer animationTimer, movementTimer;

	private boolean left, right, up, down;

	private Image[] skeletonFront, skeletonBack, skeletonLeft, skeletonRight, hp;

	private Screen screen;

	private Rectangle attackRect, aggroRect;

	private Random random;
	private int slowMove;

	/**
	 * Skeleton constructor
	 */
	public Skeleton(Screen screen)
	{
		this.screen = screen;

		init();
	} //end Skeleton constructor

	/**
	 * init method
	 */
	private void init()
	{
		initVariables();
	} //end of init method

	/**
	 * initVariables method
	 */
	private void initVariables()
	{
		random = new Random();

		x = 0 + random.nextInt(1565);
		y = 0 + random.nextInt(1565);
		dx = 1D;
		dy = 1D;

		health = 64;

		width = 32 + (padding * 2);
		height = 32 + (padding * 2);
		hpWidth = 32;

		str = 12;

		padding = 10;
		attackWidth = 32 + (padding * 2);
		attackHeight = 32 + (padding * 2);
		aggroWidth = 32 + (padding * 16);
		aggroHeight = 32 + (padding * 16);

		attack = false;
		aggro = false;
		moving = false;
		dead = false;

		direction = 0;
		animation = 0;
		slowMove = 0;

		skeletonFront = new Image[2];
		skeletonFront[0] = new ImageIcon("res/enemy/skeleton/skeletonfront1.png").getImage();
		skeletonFront[1] = new ImageIcon("res/enemy/skeleton/skeletonfront2.png").getImage();
		skeletonBack = new Image[2];
		skeletonBack[0] = new ImageIcon("res/enemy/skeleton/skeletonback1.png").getImage();
		skeletonBack[1] = new ImageIcon("res/enemy/skeleton/skeletonback2.png").getImage();
		skeletonLeft = new Image[2];
		skeletonLeft[0] = new ImageIcon("res/enemy/skeleton/skeletonleft1.png").getImage();
		skeletonLeft[1] = new ImageIcon("res/enemy/skeleton/skeletonleft2.png").getImage();
		skeletonRight = new Image[2];
		skeletonRight[0] = new ImageIcon("res/enemy/skeleton/skeletonright1.png").getImage();
		skeletonRight[1] = new ImageIcon("res/enemy/skeleton/skeletonright2.png").getImage();

		hp = new Image[2];
		hp[0] = new ImageIcon("res/UI/game/enemyhpempty.png").getImage();
		hp[1] = new ImageIcon("res/UI/game/enemyhpfull.png").getImage();

		attackRect = new Rectangle(x - padding, y - padding, attackWidth, attackHeight);
		aggroRect = new Rectangle(x - (padding * 8), y - (padding * 8), aggroWidth, aggroHeight);

		left = false;
		right = false;
		up = false;
		down = true;

		animationTimer = new Timer();
		animationTimer.schedule(new TimerTask()
		{
			public void run()
			{
				if(moving)
				{
					if(animation == 1)
					{
						animation = 0;
					}
					else
					{
						animation++;
					}
				}
			}
		}, 0, 300);

		movementTimer = new Timer();
		movementTimer.schedule(new TimerTask()
		{
			public void run()
			{
				if(!aggro && !attack)
				{
					slowMove = random.nextInt(5);

					if(slowMove == 2)
					{
						direction = random.nextInt(4);

						moving = true;

						if(direction == 0)
						{
							resetSkeleton();
							setUp();
						}
						if(direction == 1)
						{
							resetSkeleton();
							setDown();
						}
						if(direction == 2)
						{
							resetSkeleton();
							setLeft();
						}
						if(direction == 3)
						{
							resetSkeleton();
							setRight();
						}
					}
					else
					{
						moving = false;
					}
				}
			}
		}, 0, 2000);
	} //end of initVariables method

	/**
	 * render method
	 * @param g
	 */
	public void render(Graphics g)
	{
		g.drawImage(hp[0], x + screen.getMap().getX(), y + screen.getMap().getY() - 6, hp[0].getWidth(null), hp[0].getHeight(null) + 3, null);
		g.drawImage(hp[1], x + screen.getMap().getX(), y + screen.getMap().getY() - 6, (int)hpWidth, hp[1].getHeight(null) + 3, null);

		if(moving)
		{
			if(left)
			{
				g.drawImage(skeletonLeft[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(right)
			{
				g.drawImage(skeletonRight[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(up)
			{
				g.drawImage(skeletonBack[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(down)
			{
				g.drawImage(skeletonFront[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
		}
		else
		{
			if(left)
			{
				g.drawImage(skeletonLeft[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(right)
			{
				g.drawImage(skeletonRight[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(up)
			{
				g.drawImage(skeletonBack[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(down)
			{
				g.drawImage(skeletonFront[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
		}

		if(screen.getDebug())
		{
			g.setColor(Color.RED);
			g.drawRect(attackRect.x, attackRect.y, attackRect.width, attackRect.height);
			g.setColor(Color.BLUE);
			g.drawRect(aggroRect.x, aggroRect.y, aggroRect.width, aggroRect.height);
		}
	} //end of render method

	/**
	 * update method
	 */
	@Override
	public void update()
	{
		if(dead)
		{
			x = 0 + random.nextInt(1565);
			y = 0 + random.nextInt(1565);
			hpWidth = 32;
			dead = false;
		}

		if(!aggro && !attack)
		{
			if(slowMove == 2)
			{
				if(left)
				{
					if(x > 0)
					{
						decrementSkeletonX();
					}
				}
				if(right)
				{
					if(x < 1565)
					{
						incrementSkeletonX();
					}
				}
				if(up)
				{
					if(y > 0)
					{
						decrementSkeletonY();
					}
				}
				if(down)
				{
					if(y < 1565)
					{
						incrementSkeletonY();
					}
				}
			}
		}
		else if(aggro)
		{

			if(x < screen.getPlayer().getX() - screen.getMap().getX() && x < 1565)
			{
				moving = true;
				resetSkeleton();
				setRight();
				x++;
			}
			else if(x > screen.getPlayer().getX() - screen.getMap().getX() && x > 0)
			{
				moving = true;
				resetSkeleton();
				setLeft();
				x--;
			}
			else if(y < screen.getPlayer().getY() - screen.getMap().getY() + 32 && y < 1565)
			{
				moving = true;
				resetSkeleton();
				setDown();
				y++;
			}
			else if(y > screen.getPlayer().getY() - screen.getMap().getY() + 32 && y > 0)
			{
				moving = true;
				resetSkeleton();
				setUp();
				y--;
			}
			else
			{
				moving = false;
				resetSkeleton();
				setUp();
			}
		}

		attackRect.setLocation(x - padding + screen.getMap().getX(), y - padding + screen.getMap().getY());
		aggroRect.setLocation(x - (padding * 8) + screen.getMap().getX(), y - (padding * 8) + screen.getMap().getY());
	} //end of update method

	/**
	 * incrementSkeletonX method
	 */
	public void incrementSkeletonX()
	{
		x += dx;
	} //end of incrementSkeletonX method

	/**
	 * decrementSkeletonX method
	 */
	public void decrementSkeletonX()
	{
		x -= dx;
	} //end of decrementSkeletonX method

	/**
	 * incrementSkeletonY method
	 */
	public void incrementSkeletonY()
	{
		y += dy;
	} //end of incrementSkeletonY method

	/**
	 * decrementSkeletonY method
	 */
	public void decrementSkeletonY()
	{
		y -= dy;
	} //end of decrementSkeletonY method

	/**
	 * decrementHealth method
	 * @param amount
	 */
	public void decrementHealth(int amount)
	{
		health -= amount;
	} //end of decrementHealth method

	/**
	 * incrementHealth method
	 * @param amount
	 */
	public void incrementHealth(int amount)
	{
		health += amount;
	} //end of incrementHealth method

	/**
	 * resetSkeleton method
	 */
	public void resetSkeleton()
	{
		left = false;
		right = false;
		up = false;
		down = false;
	} //end of resetSkeleton method

	/**
	 * setLeft method
	 */
	public void setLeft()
	{
		left = true;
	} //end of setLeft method

	/**
	 * setRight method
	 */
	public void setRight()
	{
		right = true;
	} //end of setRight method

	/**
	 * setUp method
	 */
	public void setUp()
	{
		up = true;
	} //end of setUp method

	/**
	 * setDown method
	 */
	public void setDown()
	{
		down = true;
	} //end of setDown method

	/**
	 * resetLeft method
	 */
	public void resetLeft()
	{
		left = false;
	} //end of resetLeft method

	/**
	 * resetRight method
	 */
	public void resetRight()
	{
		right = false;
	} //end of resetRight method

	/**
	 * resetUp method
	 */
	public void resetUp()
	{
		up = false;
	} //end of resetUp method

	/**
	 * resetDown method
	 */
	public void resetDown()
	{
		down = false;
	} //end of resetDown method

	/**
	 * getHealth method
	 * @return health
	 */
	public int getHealth()
	{
		return health;
	} //end of getHealth method

	/**
	 * setHealth method
	 * @param amount
	 */
	public void setHealth(int amount)
	{
		health = amount;
	} //end of setHealth method

	/**
	 * getX method
	 */
	@Override
	public int getX()
	{
		return x;
	} //end of getX method

	/**
	 * getY method
	 */
	@Override
	public int getY()
	{
		return y;
	} //end of getY method

	/**
	 * setX method
	 */
	public void setX(int amount)
	{
		x = amount;
	} //end of setX method

	/**
	 * setY method
	 */
	public void setY(int amount)
	{
		y = amount;
	} //end of setY method

	/**
	 * getRectangle method
	 */
	@Override
	public Rectangle getRectangle()
	{
		return rectangle;
	} //end of getRectangle method

	/**
	 * getAttackRect method
	 */
	public Rectangle getAttackRect()
	{
		return attackRect;
	} //end of getAttackRect method

	/**
	 * getAggroRect method
	 */
	public Rectangle getAggroRect()
	{
		return aggroRect;
	} //end of getAggroRect method

	/**
	 * getAttack method
	 * @return attack
	 */
	public boolean getAttack()
	{
		return attack;
	} //end of getAttack method

	/**
	 * getAggro method
	 * @return aggro
	 */
	public boolean getAggro()
	{
		return aggro;
	} //end of getAggro method

	/**
	 * setAggro method
	 */
	public void setAggro(boolean answer)
	{
		aggro = answer;
	} //end of setAggro method

	/**
	 * getStr method
	 * @return str
	 */
	public int getStr()
	{
		return str;
	} //end of getStr method

	/**
	 * getHpWidth method
	 * @return hpWidth
	 */
	public double getHpWidth()
	{
		return hpWidth;
	} // end of getHpWidth method

	/**
	 * setHpWidth method
	 */
	public void setHpWidth(int amount)
	{
		hpWidth = amount;
	} // end of setHpWidth method

	/**
	 * decrementHpWidth method
	 * @param amount
	 */
	public void decrementHpWidth(double amount)
	{
		hpWidth -= amount;
	} //end of decrementHpWidth method

	/**
	 * incrementHpWidth method
	 * @param amount
	 */
	public void incrementHpWidth(double amount)
	{
		hpWidth += amount;
	} //end of incrementHpWidth method

	/**
	 * setDead method
	 * @param answer
	 */
	public void setDead(boolean answer)
	{
		dead = answer;
	} //end of setDead method

} //end of Skeleton class