package game.player;

import game.entity.Entity;
import game.frame.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

/**
 * Player class
 * @author Michael Morgan
 */
public class Player extends Entity
{
	private int health, mana, level, exp, pow, mpr, str, def, acc, dis, hp, sp, map;
	private double dmgBonus, hpBonus, defBonus, mpBonus, spdBonus;

	private String classType, name;

	private int x, y;
	private int animation, width, height;
	private double dx, dy;

	private Timer animationTimer;

	private Screen screen;

	private boolean left, right, up, down, dead;
	private boolean movingUp, movingDown, movingLeft, movingRight;

	private Image[] playerFront, playerBack, playerLeft, playerRight;

	private Rectangle rectangle;

	/**
	 * Player constructor
	 */
	public Player(Screen screen)
	{
		this.screen = screen;

		init();
	} //end Player constructor

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
		x = 400;
		y = 260;
		dx = 1.5D;
		dy = 1.5D;

		health = 0;
		mana = 0;
		level = 0;
		exp = 0;
		map = 1;

		classType = "";
		name = "";

		sp = 0;
		hp = 0;
		pow = 0;
		mpr = 0;
		str = 0;
		def = 0;
		acc = 0;
		dis = 0;

		dmgBonus = 0.0;
		defBonus = 0.0;
		hpBonus = 0.0;
		mpBonus = 0.0;
		spdBonus = 0.0;

		width = 32;
		height = 32;

		animation = 0;
		animationTimer = new Timer();
		animationTimer.schedule(new TimerTask()
		{
			public void run()
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
		}, 0, 150);

		playerFront = new Image[2];
		playerFront[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "front1.png").getImage();
		playerFront[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "front2.png").getImage();
		playerBack = new Image[2];
		playerBack[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "back1.png").getImage();
		playerBack[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "back2.png").getImage();
		playerLeft = new Image[2];
		playerLeft[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "left1.png").getImage();
		playerLeft[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "left2.png").getImage();
		playerRight = new Image[2];
		playerRight[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "right1.png").getImage();
		playerRight[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "right2.png").getImage();

		rectangle = new Rectangle(x, y, width, height);

		left = false;
		right = false;
		up = false;
		down = true;

		movingUp = false;
		movingDown = false;
		movingLeft = false;
		movingRight = false;

		dead = false;
	} //end of initVariables method

	/**
	 * render method
	 * @param g
	 */
	public void render(Graphics g)
	{
		if(movingUp || movingDown || movingLeft || movingRight)
		{
			if(left)
			{
				g.drawImage(playerLeft[animation], x, y, null);
			}
			if(right)
			{
				g.drawImage(playerRight[animation], x, y, null);
			}
			if(up)
			{
				g.drawImage(playerBack[animation], x, y, null);
			}
			if(down)
			{
				g.drawImage(playerFront[animation], x, y, null);
			}
		}
		else
		{
			if(left)
			{
				g.drawImage(playerLeft[0], x, y, null);
			}
			if(right)
			{
				g.drawImage(playerRight[0], x, y, null);
			}
			if(up)
			{
				g.drawImage(playerBack[0], x, y, null);
			}
			if(down)
			{
				g.drawImage(playerFront[0], x, y, null);
			}
		}

		if(screen.getDebug())
		{
			g.setColor(Color.RED);
			g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
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
			screen.getMap().setMapX(0);
			screen.getMap().setMapY(0);

			screen.getUI().setHpWidth(100);
			health = 100;
			mana = 100;
			exp = 0;

			dead = false;
		}

		if(movingUp || movingDown || movingLeft || movingRight)
		{
			if(left)
			{
				if(screen.getMap().getX() < 400)
				{
					decrementPlayerX();
				}
			}
			if(right)
			{
				if(screen.getMap().getX() > -1167)
				{
					incrementPlayerX();
				}
			}
			if(up)
			{
				if(screen.getMap().getY() < 260)
				{
					decrementPlayerY();
				}
			}
			if(down)
			{
				if(screen.getMap().getY() > -1307)
				{
					incrementPlayerY();
				}
			}
		}

		rectangle.setLocation(x, y);
	} //end of update method

	/**
	 * setName method
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	} //end of setName method

	/**
	 * incrementPlayerX method
	 */
	public void incrementPlayerX()
	{
		screen.getMap().decrementMapX(dx + spdBonus);
	} //end of incrementPlayerX method

	/**
	 * decrementPlayerX method
	 */
	public void decrementPlayerX()
	{
		screen.getMap().incrementMapX(dx + spdBonus);
	} //end of decrementPlayerX method

	/**
	 * incrementPlayerY method
	 */
	public void incrementPlayerY()
	{
		screen.getMap().decrementMapY(dy + spdBonus);
	} //end of incrementPlayerY method

	/**
	 * decrementPlayerY method
	 */
	public void decrementPlayerY()
	{
		screen.getMap().incrementMapY(dy + spdBonus);
	} //end of decrementPlayerY method

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
	 * decrementMana method
	 * @param amount
	 */
	public void decrementMana(int amount)
	{
		mana -= amount;
	} //end of decrementMana method

	/**
	 * incrementMana method
	 * @param amount
	 */
	public void incrementMana(int amount)
	{
		mana += amount;
	} //end of incrementMana method

	/**
	 * decrementExp method
	 * @param amount
	 */
	public void decrementExp(int amount)
	{
		exp -= amount;
	} //end of decrementExp method

	/**
	 * incrementExp method
	 * @param amount
	 */
	public void incrementExp(int amount)
	{
		exp += amount;
	} //end of incrementExp method

	/**
	 * setPlayerImage method
	 * @param classType
	 */
	public void setPlayerImage(String classType)
	{
		playerFront[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "front1.png").getImage();
		playerFront[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "front2.png").getImage();

		playerBack[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "back1.png").getImage();
		playerBack[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "back2.png").getImage();

		playerLeft[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "left1.png").getImage();
		playerLeft[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "left2.png").getImage();

		playerRight[0] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "right1.png").getImage();
		playerRight[1] = new ImageIcon("res/player/" + classType.toLowerCase() + "/" + classType.toLowerCase() + "right2.png").getImage();
	} //end of setPlayerImage method

	/**
	 * resetPlayer method
	 */
	public void resetPlayer()
	{
		left = false;
		right = false;
		up = false;
		down = false;
	} //end of resetPlayer method

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
	 * getLeft method
	 */
	public boolean getLeft()
	{
		return left;
	} //end of getLeft method

	/**
	 * getRight method
	 */
	public boolean getRight()
	{
		return right;
	} //end of getRight method

	/**
	 * getUp method
	 */
	public boolean getUp()
	{
		return up;
	} //end of getUp method

	/**
	 * getDown method
	 */
	public boolean getDown()
	{
		return down;
	} //end of getDown method

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
	 * getMana method
	 * @return mana
	 */
	public int getMana()
	{
		return mana;
	} //end of getMana method

	/**
	 * getExp method
	 * @return exp
	 */
	public int getExp()
	{
		return exp;
	} //end of getExp method

	/**
	 * setExp method
	 */
	public void setExp(int amount)
	{
		this.exp = amount;
	} //end of setExp method

	/**
	 * setHealth method
	 * @param amount
	 */
	public void setHealth(int amount)
	{
		health = amount;
	} //end of setHealth method

	/**
	 * setMana method
	 * @param amount
	 */
	public void setMana(int amount)
	{
		mana = amount;
	} //end of setMana method

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
	 * getRectangle method
	 */
	@Override
	public Rectangle getRectangle()
	{
		return rectangle;
	} //end of getRectangle method

	/**
	 * getLevel method
	 * @return level
	 */
	public int getLevel()
	{
		return level;
	} //end of getLevel method

	/**
	 * setLevel method
	 */
	public void setLevel(int amount)
	{
		this.level = amount;
	} //end of setLevel method

	/**
	 * incrementLevel method
	 * @param amount
	 */
	public void incrementLevel(int amount)
	{
		level += amount;
	} //end of incrementLevel method

	/**
	 * getClassType method
	 * @return classType
	 */
	public String getClassType()
	{
		return classType;
	} //end of getClassType method

	/**
	 * setClassType method
	 */
	public void setClassType(String classType)
	{
		this.classType = classType;
	} //end of setClassType method

	/**
	 * getName method
	 * @return name
	 */
	public String getName()
	{
		return name;
	} //end of getName method

	/**
	 * setMovingUp method
	 * @param answer
	 */
	public void setMovingUp(boolean answer)
	{
		movingUp = answer;
	} //end of setMovingUp method

	/**
	 * setMovingDown method
	 * @param answer
	 */
	public void setMovingDown(boolean answer)
	{
		movingDown = answer;
	} //end of setMovingDown method

	/**
	 * setMovingLeft method
	 * @param answer
	 */
	public void setMovingLeft(boolean answer)
	{
		movingLeft = answer;
	} //end of setMovingLeft method

	/**
	 * setMovingRight method
	 * @param answer
	 */
	public void setMovingRight(boolean answer)
	{
		movingRight = answer;
	} //end of setMovingRight method

	/**
	 * getSp method
	 * @return sp
	 */
	public int getSp()
	{
		return sp;
	} //end of getSp method

	/**
	 * setSp method
	 */
	public void setSp(int amount)
	{
		this.sp = amount;
	} //end of setSp method

	/**
	 * getHp method
	 * @return hp
	 */
	public int getHp()
	{
		return hp;
	} //end of getHp method

	/**
	 * setHp method
	 */
	public void setHp(int amount)
	{
		hp = amount;
	} //end of setHp method

	/**
	 * getStr method
	 * @return str
	 */
	public int getStr()
	{
		return str;
	} //end of getStr method

	/**
	 * setStr method
	 */
	public void setStr(int amount)
	{
		str = amount;
	} //end of setStr method

	/**
	 * getDef method
	 * @return def
	 */
	public int getDef()
	{
		return def;
	} //end of getDef method

	/**
	 * setDef method
	 */
	public void setDef(int amount)
	{
		def = amount;
	} //end of setDef method

	/**
	 * getAcc method
	 * @return acc
	 */
	public int getAcc()
	{
		return acc;
	} //end of getAcc method

	/**
	 * setAcc method
	 */
	public void setAcc(int amount)
	{
		acc = amount;
	} //end of setAcc method

	/**
	 * getPow method
	 * @return pow
	 */
	public int getPow()
	{
		return pow;
	} //end of getPow method

	/**
	 * setPow method
	 */
	public void setPow(int amount)
	{
		pow = amount;
	} //end of setPow method

	/**
	 * getMpr method
	 * @return mpr
	 */
	public int getMpr()
	{
		return mpr;
	} //end of getMpr method

	/**
	 * setMpr method
	 */
	public void setMpr(int amount)
	{
		mpr = amount;
	} //end of setMpr method

	/**
	 * getDis method
	 * @return dis
	 */
	public int getDis()
	{
		return dis;
	} //end of getDis method

	/**
	 * setDis method
	 */
	public void setDis(int amount)
	{
		dis = amount;
	} //end of setDis method

	/**
	 * incrementSp method
	 * @param amount
	 */
	public void incrementSp(int amount)
	{
		sp += amount;
	} //end of incrementSp method

	/**
	 * decrementSp method
	 * @param amount
	 */
	public void decrementSp(int amount)
	{
		sp -= amount;
	} //end of decrementSp method

	/**
	 * incrementHp method
	 * @param amount
	 */
	public void incrementHp(int amount)
	{
		hp += amount;
	} //end of incrementHp method

	/**
	 * decrementHp method
	 * @param amount
	 */
	public void decrementHp(int amount)
	{
		hp -= amount;
	} //end of decrementHp method

	/**
	 * incrementStr method
	 * @param amount
	 */
	public void incrementStr(int amount)
	{
		str += amount;
	} //end of incrementStr method

	/**
	 * decrementStr method
	 * @param amount
	 */
	public void decrementStr(int amount)
	{
		str -= amount;
	} //end of decrementStr method

	/**
	 * incrementDef method
	 * @param amount
	 */
	public void incrementDef(int amount)
	{
		def += amount;
	} //end of incrementDef method

	/**
	 * decrementDef method
	 * @param amount
	 */
	public void decrementDef(int amount)
	{
		def -= amount;
	} //end of decrementDef method

	/**
	 * incrementAcc method
	 * @param amount
	 */
	public void incrementAcc(int amount)
	{
		acc += amount;
	} //end of incrementAcc method

	/**
	 * decrementAcc method
	 * @param amount
	 */
	public void decrementAcc(int amount)
	{
		acc -= amount;
	} //end of decrementAcc method

	/**
	 * incrementDis method
	 * @param amount
	 */
	public void incrementDis(int amount)
	{
		dis += amount;
	} //end of incrementDis method

	/**
	 * decrementDis method
	 * @param amount
	 */
	public void decrementDis(int amount)
	{
		dis -= amount;
	} //end of decrementDis method

	/**
	 * incrementPow method
	 * @param amount
	 */
	public void incrementPow(int amount)
	{
		pow += amount;
	} //end of incrementPow method

	/**
	 * decrementPow method
	 * @param amount
	 */
	public void decrementPow(int amount)
	{
		pow -= amount;
	} //end of decrementPow method

	/**
	 * incrementMpr method
	 * @param amount
	 */
	public void incrementMpr(int amount)
	{
		mpr += amount;
	} //end of incrementMpr method

	/**
	 * decrementMpr method
	 * @param amount
	 */
	public void decrementMpr(int amount)
	{
		mpr -= amount;
	} //end of decrementHp method

	/**
	 * addDefBonus method
	 * @param amount
	 */
	public void addDefBonus(double amount)
	{
		defBonus += amount;
	} //end of addDefBonus method

	/**
	 * removeDefBonus method
	 * @param amount
	 */
	public void removeDefBonus(double amount)
	{
		defBonus -= amount;
	} //end of removeDefBonus method

	/**
	 * addHpBonus method
	 * @param amount
	 */
	public void addHpBonus(double amount)
	{
		hpBonus += amount;
	} //end of addHpBonus method

	/**
	 * removeHpBonus method
	 * @param amount
	 */
	public void removeHpBonus(double amount)
	{
		hpBonus -= amount;
	} //end of removeHpBonus method

	/**
	 * addDmgBonus method
	 * @param amount
	 */
	public void addDmgBonus(double amount)
	{
		dmgBonus += amount;
	} //end of addDmgBonus method

	/**
	 * removeDmgBonus method
	 * @param amount
	 */
	public void removeDmgBonus(double amount)
	{
		dmgBonus -= amount;
	} //end of removeDmgBonus method

	/**
	 * addMpBonus method
	 * @param amount
	 */
	public void addMpBonus(double amount)
	{
		mpBonus += amount;
	} //end of addMpBonus method

	/**
	 * removeMpBonus method
	 * @param amount
	 */
	public void removeMpBonus(double amount)
	{
		mpBonus -= amount;
	} //end of removeMpBonus method

	/**
	 * addSpdBonus method
	 * @param amount
	 */
	public void addSpdBonus(double amount)
	{
		spdBonus += amount;
	} //end of addSpdBonus method

	/**
	 * removeSpdBonus method
	 * @param amount
	 */
	public void removeSpdBonus(double amount)
	{
		spdBonus -= amount;
	} //end of removeSpdBonus method

	/**
	 * getDmgBonus method
	 * @return dmgBonus
	 */
	public double getDmgBonus()
	{
		return dmgBonus;
	} //end of getDmgBonus method

	/**
	 * setDmgBonus method
	 */
	public void setDmgBonus(double amount)
	{
		dmgBonus = amount;
	} //end of setDmgBonus method

	/**
	 * getDefBonus method
	 * @return defBonus
	 */
	public double getDefBonus()
	{
		return defBonus;
	} //end of getDefBonus method

	/**
	 * setDefBonus method
	 */
	public void setDefBonus(double amount)
	{
		defBonus = amount;
	} //end of setDefBonus method

	/**
	 * getSpdBonus method
	 * @return spdBonus
	 */
	public double getSpdBonus()
	{
		return spdBonus;
	} //end of getSpdBonus method

	/**
	 * setSpdBonus method
	 */
	public void setSpdBonus(double amount)
	{
		spdBonus = amount;
	} //end of setSpdBonus method

	/**
	 * getHpBonus method
	 * @return hpBonus
	 */
	public double getHpBonus()
	{
		return hpBonus;
	} //end of getHpBonus method

	/**
	 * setHpBonus method
	 */
	public void setHpBonus(double amount)
	{
		hpBonus = amount;
	} //end of setHpBonus method

	/**
	 * getMpBonus method
	 * @return mpBonus method
	 */
	public double getMpBonus()
	{
		return mpBonus;
	} //end of getMpBonus method

	/**
	 * setMpBonus method
	 */
	public void setMpBonus(double amount)
	{
		mpBonus = amount;
	} //end of setMpBonus method

	/**
	 * setDead method
	 * @param answer
	 */
	public void setDead(boolean answer)
	{
		dead = answer;
	} //end of setDead method

	/**
	 * getMap method
	 * @return map
	 */
	public int getMap()
	{
		return map;
	} //end of getMap method

	/**
	 * setMap method
	 * @param amount
	 */
	public void setMap(int amount)
	{
		map = amount;
	} //end of setMap method

} //end of Player class