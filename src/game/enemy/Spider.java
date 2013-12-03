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
 * Spider class
 * @author Michael Morgan
 */
public class Spider extends Entity
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

	private Image[] spiderFront, spiderBack, spiderLeft, spiderRight, hp;

	private Screen screen;

	private Rectangle attackRect, aggroRect;

	private Random random;
	private int slowMove;

	/**
	 * Spider constructor
	 */
	public Spider(Screen screen)
	{
		this.screen = screen;

		init();
	} //end Spider constructor

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

		x = 1275 + random.nextInt(290);
		y = 0 + random.nextInt(285);
		dx = 1D;
		dy = 1D;

		health = 32;

		width = 32 + (padding * 2);
		height = 32 + (padding * 2);
		hpWidth = 32;

		str = 8;

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

		spiderFront = new Image[3];
		spiderFront[0] = new ImageIcon("res/enemy/spider/spiderfront1.png").getImage();
		spiderFront[1] = new ImageIcon("res/enemy/spider/spiderfront2.png").getImage();
		spiderFront[2] = new ImageIcon("res/enemy/spider/spiderfront3.png").getImage();
		spiderBack = new Image[3];
		spiderBack[0] = new ImageIcon("res/enemy/spider/spiderback1.png").getImage();
		spiderBack[1] = new ImageIcon("res/enemy/spider/spiderback2.png").getImage();
		spiderBack[2] = new ImageIcon("res/enemy/spider/spiderback3.png").getImage();
		spiderLeft = new Image[3];
		spiderLeft[0] = new ImageIcon("res/enemy/spider/spiderleft1.png").getImage();
		spiderLeft[1] = new ImageIcon("res/enemy/spider/spiderleft2.png").getImage();
		spiderLeft[2] = new ImageIcon("res/enemy/spider/spiderleft3.png").getImage();
		spiderRight = new Image[3];
		spiderRight[0] = new ImageIcon("res/enemy/spider/spiderright1.png").getImage();
		spiderRight[1] = new ImageIcon("res/enemy/spider/spiderright2.png").getImage();
		spiderRight[2] = new ImageIcon("res/enemy/spider/spiderright3.png").getImage();

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
					if(animation == 2)
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
							resetSpider();
							setUp();
						}
						if(direction == 1)
						{
							resetSpider();
							setDown();
						}
						if(direction == 2)
						{
							resetSpider();
							setLeft();
						}
						if(direction == 3)
						{
							resetSpider();
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
				g.drawImage(spiderLeft[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(right)
			{
				g.drawImage(spiderRight[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(up)
			{
				g.drawImage(spiderBack[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(down)
			{
				g.drawImage(spiderFront[animation], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
		}
		else
		{
			if(left)
			{
				g.drawImage(spiderLeft[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(right)
			{
				g.drawImage(spiderRight[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(up)
			{
				g.drawImage(spiderBack[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
			}
			if(down)
			{
				g.drawImage(spiderFront[0], x + screen.getMap().getX(), y + screen.getMap().getY(), null);
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
			x = 1275 + random.nextInt(290);
			y = 0 + random.nextInt(285);
			hpWidth = 32;
			dead = false;
		}

		if(!aggro && !attack)
		{
			if(slowMove == 2)
			{
				if(left)
				{
					if(x > 1275)
					{
						decrementSpiderX();
					}
				}
				if(right)
				{
					if(x < 1565)
					{
						incrementSpiderX();
					}
				}
				if(up)
				{
					if(y > 0)
					{
						decrementSpiderY();
					}
				}
				if(down)
				{
					if(y < 285)
					{
						incrementSpiderY();
					}
				}
			}
		}
		else if(aggro)
		{

			if(x < screen.getPlayer().getX() - screen.getMap().getX() && x < 1565)
			{
				moving = true;
				resetSpider();
				setRight();
				x++;
			}
			else if(x > screen.getPlayer().getX() - screen.getMap().getX() && x > 1275)
			{
				moving = true;
				resetSpider();
				setLeft();
				x--;
			}
			else if(y < screen.getPlayer().getY() - screen.getMap().getY() + 32 && y < 285)
			{
				moving = true;
				resetSpider();
				setDown();
				y++;
			}
			else if(y > screen.getPlayer().getY() - screen.getMap().getY() + 32 && y > 0)
			{
				moving = true;
				resetSpider();
				setUp();
				y--;
			}
			else
			{
				moving = false;
				resetSpider();
				setUp();
			}
		}

		attackRect.setLocation(x - padding + screen.getMap().getX(), y - padding + screen.getMap().getY());
		aggroRect.setLocation(x - (padding * 8) + screen.getMap().getX(), y - (padding * 8) + screen.getMap().getY());
	} //end of update method

	/**
	 * incrementSpiderX method
	 */
	public void incrementSpiderX()
	{
		x += dx;
	} //end of incrementSpiderX method

	/**
	 * decrementSpiderX method
	 */
	public void decrementSpiderX()
	{
		x -= dx;
	} //end of decrementSpiderX method

	/**
	 * incrementSpiderY method
	 */
	public void incrementSpiderY()
	{
		y += dy;
	} //end of incrementSpiderY method

	/**
	 * decrementSpiderY method
	 */
	public void decrementSpiderY()
	{
		y -= dy;
	} //end of decrementSpiderY method

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
	 * resetSpider method
	 */
	public void resetSpider()
	{
		left = false;
		right = false;
		up = false;
		down = false;
	} //end of resetSpider method

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

} //end of Spider class