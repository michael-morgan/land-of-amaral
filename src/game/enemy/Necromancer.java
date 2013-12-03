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
 * Necromancer class
 * @author Michael Morgan
 */
public class Necromancer extends Entity
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

	private Image[] necromancerFront, necromancerBack, necromancerLeft, necromancerRight, hp;

	private Screen screen;

	private Rectangle attackRect, aggroRect;

	private Random random;
	private int slowMove;

	/**
	 * Necromancer constructor
	 */
	public Necromancer(Screen screen)
	{
		this.screen = screen;

		init();
	} //end Necromancer constructor

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

		x = 1120 + random.nextInt(445);
		y = 1245 + random.nextInt(320);
		dx = 1D;
		dy = 1D;

		health = 256;

		width = 32 + (padding * 2);
		height = 32 + (padding * 2);
		hpWidth = 32;

		str = 24;

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

		necromancerFront = new Image[2];
		necromancerFront[0] = new ImageIcon("res/enemy/necromancer/necromancerfront1.png").getImage();
		necromancerFront[1] = new ImageIcon("res/enemy/necromancer/necromancerfront2.png").getImage();
		necromancerBack = new Image[2];
		necromancerBack[0] = new ImageIcon("res/enemy/necromancer/necromancerback1.png").getImage();
		necromancerBack[1] = new ImageIcon("res/enemy/necromancer/necromancerback2.png").getImage();
		necromancerLeft = new Image[2];
		necromancerLeft[0] = new ImageIcon("res/enemy/necromancer/necromancerleft1.png").getImage();
		necromancerLeft[1] = new ImageIcon("res/enemy/necromancer/necromancerleft2.png").getImage();
		necromancerRight = new Image[2];
		necromancerRight[0] = new ImageIcon("res/enemy/necromancer/necromancerright1.png").getImage();
		necromancerRight[1] = new ImageIcon("res/enemy/necromancer/necromancerright2.png").getImage();

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
							resetNecromancer();
							setUp();
						}
						if(direction == 1)
						{
							resetNecromancer();
							setDown();
						}
						if(direction == 2)
						{
							resetNecromancer();
							setLeft();
						}
						if(direction == 3)
						{
							resetNecromancer();
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
				g.drawImage(necromancerLeft[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(right)
			{
				g.drawImage(necromancerRight[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(up)
			{
				g.drawImage(necromancerBack[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(down)
			{
				g.drawImage(necromancerFront[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
		}
		else
		{
			if(left)
			{
				g.drawImage(necromancerLeft[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(right)
			{
				g.drawImage(necromancerRight[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(up)
			{
				g.drawImage(necromancerBack[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(down)
			{
				g.drawImage(necromancerFront[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
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
			x = 1120 + random.nextInt(445);
			y = 1245 + random.nextInt(320);
			hpWidth = 32;
			dead = false;
		}

		if(!aggro && !attack)
		{
			if(slowMove == 2)
			{
				if(left)
				{
					if(x > 1120)
					{
						decrementNecromancerX();
					}
				}
				if(right)
				{
					if(x < 1565)
					{
						incrementNecromancerX();
					}
				}
				if(up)
				{
					if(y > 1245)
					{
						decrementNecromancerY();
					}
				}
				if(down)
				{
					if(y < 1565)
					{
						incrementNecromancerY();
					}
				}
			}
		}
		else if(aggro)
		{

			if(x < screen.getPlayer().getX() - screen.getMap().getX() && x < 1565)
			{
				moving = true;
				resetNecromancer();
				setRight();
				x++;
			}
			else if(x > screen.getPlayer().getX() - screen.getMap().getX() && x > 1120)
			{
				moving = true;
				resetNecromancer();
				setLeft();
				x--;
			}
			else if(y < screen.getPlayer().getY() - screen.getMap().getY() + 32 && y < 1565)
			{
				moving = true;
				resetNecromancer();
				setDown();
				y++;
			}
			else if(y > screen.getPlayer().getY() - screen.getMap().getY() + 32 && y > 1245)
			{
				moving = true;
				resetNecromancer();
				setUp();
				y--;
			}
			else
			{
				moving = false;
				resetNecromancer();
				setUp();
			}
		}

		attackRect.setLocation(x - padding + screen.getMap().getX(), y - padding + screen.getMap().getY());
		aggroRect.setLocation(x - (padding * 8) + screen.getMap().getX(), y - (padding * 8) + screen.getMap().getY());
	} //end of update method

	/**
	 * incrementNecromancerX method
	 */
	public void incrementNecromancerX()
	{
		x += dx;
	} //end of incrementNecromancerX method

	/**
	 * decrementNecromancerX method
	 */
	public void decrementNecromancerX()
	{
		x -= dx;
	} //end of decrementNecromancerX method

	/**
	 * incrementNecromancerY method
	 */
	public void incrementNecromancerY()
	{
		y += dy;
	} //end of incrementNecromancerY method

	/**
	 * decrementNecromancerY method
	 */
	public void decrementNecromancerY()
	{
		y -= dy;
	} //end of decrementNecromancerY method

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
	 * resetNecromancer method
	 */
	public void resetNecromancer()
	{
		left = false;
		right = false;
		up = false;
		down = false;
	} //end of resetNecromancer method

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

} //end of Necromancer class