package game.frame;

import game.combat.Combat;
import game.dialog.Dialog;
import game.enemy.BloodLord;
import game.enemy.Bull;
import game.enemy.Cyclops;
import game.enemy.Monkey;
import game.enemy.Necromancer;
import game.enemy.Panda;
import game.enemy.Scorpian;
import game.enemy.Skeleton;
import game.enemy.Spider;
import game.enemy.Wolf;
import game.event.Key;
import game.event.Mouse;
import game.item.Equipment;
import game.item.Potion;
import game.player.Player;
import game.player.data.Data;
import game.player.projectile.Arrow;
import game.player.projectile.Spell;
import game.sprite.Map;
import game.sprite.UI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

/**
 * Screen class
 * @author Michael Morgan
 */
public class Screen extends Canvas implements Runnable
{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private Thread gameLoop;
	private boolean run, game, menu, debug;

	private Frame frame;

	private final int SCALE = 2;
	private int width, height;

	private Map map;
	private UI ui;

	private Dialog dialog;
	private Combat combat;

	private Data data;
	private Player player;
	private Equipment equipment;
	private Potion potion;

	private ArrayList<Arrow> arrows;
	private ArrayList<Spell> spells;

	private Spider spider;
	private Wolf wolf;
	private Skeleton skeleton;
	private Cyclops cyclops;
	private Necromancer necromancer;
	private Panda panda;
	private Monkey monkey;
	private Scorpian scorpian;
	private Bull bull;
	private BloodLord bloodlord;

	/**
	 * Screen constructor
	 * @param frame
	 */
	public Screen(Frame frame)
	{
		init();

		this.frame = frame;
		this.setSize(new Dimension(width * SCALE, height * SCALE));

		this.addKeyListener(new Key(this));
		this.addMouseListener(new Mouse(this));
	} //end of Screen constructor

	/**
	 * init method
	 */
	private void init()
	{
		gameLoop = new Thread(this);
		run = false;

		game = false;
		menu = false;
		debug = true;

		width = 400;
		height = 320;

		player = new Player(this);

		map = new Map(this);

		data = new Data(this);

		equipment = new Equipment(this);
		potion = new Potion();

		arrows = new ArrayList<>();
		spells = new ArrayList<>();

		ui = new UI(this);

		dialog = new Dialog(this);
	} //end of init method

	/**
	 * render method
	 */
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs == null)
		{
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 2000, 2000);

		if(menu)
		{	
			ui.render(g);

			if(ui.getShowNew())
			{
				g.drawImage(ui.getMenuNew(), 100, 125, null);

				g.setColor(Color.LIGHT_GRAY);
				g.setFont(new Font("Arial", Font.PLAIN, 18));

				g.drawString(ui.getClassType(), 408, 233);
				g.drawString(ui.getName(), 230, 529);
			}
			else if(ui.getShowLoad())
			{
				g.drawImage(ui.getMenuLoad(), 100, 125, null);

				g.setColor(Color.LIGHT_GRAY);

				g.setFont(new Font("Arial", Font.PLAIN, 18));
				g.drawString(player.getClassType(), 317, 234);

				g.setFont(new Font("Arial", Font.PLAIN, 16));
				g.drawString(player.getName(), 478, 232);

				g.setFont(new Font("Arial", Font.PLAIN, 14));
				g.drawString("" + player.getHealth(), 238, 284);
				g.drawString("" + player.getMana(), 226, 321);
				g.drawString("" + player.getLevel(), 231, 360);
				g.drawString("" + player.getSp(), 329, 284);
				g.drawString("" + player.getHp(), 329, 322);
				g.drawString("" + player.getStr(), 336, 359);
				g.drawString("" + player.getDef(), 447, 284);
				g.drawString("" + player.getAcc(), 451, 320);
				g.drawString("" + player.getDis(), 444, 359);
				g.drawString("" + player.getPow(), 563, 282);
				g.drawString("" + player.getMpr(), 563, 320);
				g.drawString("" + player.getMap(), 563, 360);
			}

			if(debug)
			{
				g.setFont(new Font("Arial", Font.BOLD, 14));
				g.setColor(Color.RED);
				g.drawString("X: " + Mouse.x + " Y: " + Mouse.y, 5, 15);
			}
		}
		else if(game)
		{
			map.render(g);

			player.render(g);

			if(arrows.size() > 0)
			{
				arrows.get(0).render(g);
			}
			else if(spells.size() > 0)
			{
				spells.get(0).render(g);
			}

			if(player.getMap() == 1)
			{
				spider.render(g);
				wolf.render(g);
				skeleton.render(g);
				cyclops.render(g);
				necromancer.render(g);
			}
			else if(player.getMap() == 2)
			{
				panda.render(g);
				monkey.render(g);
				scorpian.render(g);
				bull.render(g);
				bloodlord.render(g);
			}

			ui.render(g);
			combat.render(g);

			g.setColor(Color.LIGHT_GRAY);
			g.setFont(new Font("Arial", Font.PLAIN, 12));
			g.drawString(player.getClassType(), 502, 15);
			g.drawString("" + player.getLevel(), 608, 15);
			g.drawString(player.getName(), 690, 15);

			if(dialog.getDialogs().size() > 0)
			{
				int x = 247, y = 567;
				for(int i = 0; i < dialog.getDialogs().size(); i++)
				{
					g.drawString(dialog.getDialogs().get(i), x, y);
					y += 11;
				}
			}

			if(potion.getHealthPotions() > 0)
			{
				g.drawImage(potion.getHealthPotion(), 680, 575, null);
				g.drawString("" + potion.getHealthPotions(), 705, 605);
			}
			if(potion.getManaPotions() > 0)
			{
				g.drawImage(potion.getManaPotion(), 745, 575, null);
				g.drawString("" + potion.getManaPotions(), 770, 605);
			}

			if(getUI().getShowCharacter())
			{
				g.setFont(new Font("Arial", Font.PLAIN, 12));
				g.setColor(Color.BLACK);
				g.drawString(player.getName(), 623, 267);
				if(player.getClassType().equalsIgnoreCase("melee"))
				{
					g.drawString("" + player.getStr(), 667, 303);
					g.drawString("" + player.getDef(), 667, 344);
					g.drawString("" + player.getHp(), 667, 385);
				}
				else if(player.getClassType().equalsIgnoreCase("range"))
				{
					g.drawString("" + player.getAcc(), 667, 303);
					g.drawString("" + player.getDis(), 667, 344);
					g.drawString("" + player.getHp(), 667, 385);
				}
				else if(player.getClassType().equalsIgnoreCase("mage"))
				{
					g.drawString("" + player.getPow(), 667, 303);
					g.drawString("" + player.getMpr(), 667, 344);
					g.drawString("" + player.getHp(), 667, 385);
				}
				g.setFont(new Font("Arial", Font.PLAIN, 11));
				g.drawString("" + player.getSp(), 693, 420);
			}

			if(getUI().getShowEquipment())
			{
				g.setFont(new Font("Arial", Font.PLAIN, 12));
				g.setColor(Color.BLACK);
				g.drawString((int)(player.getDmgBonus() * 100) + "%", 42, 501);
				g.drawString((int)(player.getDefBonus() * 100) + "%", 42, 521);
				g.drawString((int)(player.getSpdBonus() * 100) + "%", 111, 511);
				g.drawString((int)(player.getHpBonus() * 100) + "%", 176, 501);
				g.drawString((int)(player.getMpBonus() * 100) + "%", 176, 521);
			}

			if(debug)
			{
				g.setFont(new Font("Arial", Font.BOLD, 14));
				g.setColor(Color.BLUE);
				g.drawString("X: " + map.getX() + " Y: " + map.getY(), 5, 40);
				g.setColor(Color.RED);
				g.drawString("X: " + Mouse.x + " Y: " + Mouse.y, 5, 60);
			}
		}

		g.dispose();
		bs.show();
	} //end of render method

	/**
	 * update method
	 */
	private void update()
	{
		if(game)
		{
			player.update();
			combat.update();

			if(player.getMap() == 1)
			{

				if((map.getX() <= -1006 && map.getX() >= -1010) && (map.getY() <= -1305 && map.getY() >= -1307) && map.getBossDead())
				{
					stopGame();
					player.setMap(2);
					map.setBossDead(false);
					startGame();
				}

				spider.update();
				wolf.update();
				skeleton.update();
				cyclops.update();
				necromancer.update();
			}
			else if(player.getMap() == 2)
			{
				panda.update();
				monkey.update();
				scorpian.update();
				bull.update();
				bloodlord.update();
			}
		}
	} //end of  process method

	/**
	 * run method
	 */
	public void run()
	{
		long lastTime = System.nanoTime(), lastUpdate = System.currentTimeMillis();
		double nsPerUpdate = 1000000000D / 60D, delta = 0D;
		int updates = 0, frames = 0;

		while(run)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerUpdate;
			lastTime = now;

			while(delta >= 1)
			{
				updates++;

				update();

				delta -= 1;
			}

			frames++;

			if((System.currentTimeMillis() - lastUpdate) >= 1000)
			{
				lastUpdate += 1000;
				System.out.println("RPS: " + frames + " UPS: " + updates);
				frames = 0;
				updates = 0;
			}

			render();
		}
	} //end of run method

	/**
	 * start method
	 */
	public synchronized void start()
	{
		gameLoop.start();
		run = true;
	} //end of start method

	/**
	 * stop method
	 */
	@SuppressWarnings("deprecation")
	public synchronized void stop()
	{
		gameLoop.stop();
		run = false;
	} //end of stop method

	/**
	 * startGame method
	 */
	public synchronized void startGame()
	{
		map = new Map(this);

		if(player.getMap() == 1)
		{
			spider = new Spider(this);
			wolf = new Wolf(this);
			skeleton = new Skeleton(this);
			cyclops = new Cyclops(this);
			necromancer = new Necromancer(this);
		}
		else if(player.getMap() == 2)
		{
			panda = new Panda(this);
			monkey = new Monkey(this);
			scorpian = new Scorpian(this);
			bull = new Bull(this);
			bloodlord = new BloodLord(this);
		}

		combat = new Combat(this);

		dialog = new Dialog(this);

		game = true;
	} //end of startGame method

	/**
	 * stopGame method
	 */
	public synchronized void stopGame()
	{	
		game = false;

		combat.stop();
	} //end of stopGame method

	/**
	 * startMenu method
	 */
	public synchronized void startMenu()
	{
		menu = true;
	} //end of startGame method

	/**
	 * stopMenu method
	 */
	public synchronized void stopMenu()
	{	
		menu = false;
	} //end of stopMenu method

	/**
	 * getFrame method
	 * @return frame
	 */
	public Frame getFrame()
	{
		return frame;
	} //end of getFrame method

	/**
	 * getPlayer method
	 * @return player
	 */
	public Player getPlayer()
	{
		return player;
	} //end of getPlayer method

	/**
	 * getEquipment method
	 * @return equipment
	 */
	public Equipment getEquipment()
	{
		return equipment;
	} //end of getEquipment method

	/**
	 * getPotion method
	 * @return potion
	 */
	public Potion getPotion()
	{
		return potion;
	} //end of getPotion method

	/**
	 * getMap method
	 * @return map
	 */
	public Map getMap()
	{
		return map;
	} //end of getMap method

	/**
	 * getUI method
	 * @return ui
	 */
	public UI getUI()
	{
		return ui;
	} //end of getUI method

	/**
	 * getCombat method
	 * @return combat
	 */
	public Combat getCombat()
	{
		return combat;
	} //end of getCombat method
	/**
	 * getDialog method
	 * @return dialog
	 */
	public Dialog getDialog()
	{
		return dialog;
	} //end of getDialog method

	/**
	 * getArrows method
	 * @return arrows
	 */
	public ArrayList<Arrow> getArrows()
	{
		return arrows;
	} //end of getArrows method

	/**
	 * getSpells method
	 * @return spells
	 */
	public ArrayList<Spell> getSpells()
	{
		return spells;
	} //end of getSpells method

	/**
	 * getSpider method
	 * @return spider
	 */
	public Spider getSpider()
	{
		return spider;
	} //end of getSpider method

	/**
	 * getWolf method
	 * @return wolf
	 */
	public Wolf getWolf()
	{
		return wolf;
	} //end of getWolf method

	/**
	 * getNecromancer method
	 * @return necromancer
	 */
	public Necromancer getNecromancer()
	{
		return necromancer;
	} //end of getNecromancer method

	/**
	 * getCyclops method
	 * @return cyclops
	 */
	public Cyclops getCyclops()
	{
		return cyclops;
	} //end of getCyclops method

	/**
	 * getSkeleton method
	 * @return skeleton
	 */
	public Skeleton getSkeleton()
	{
		return skeleton;
	} //end of getSkeleton method

	/**
	 * getPanda method
	 * @return panda
	 */
	public Panda getPanda()
	{
		return panda;
	} //end of getPanda method

	/**
	 * getMonkey method
	 * @return monkey
	 */
	public Monkey getMonkey()
	{
		return monkey;
	} //end of getMonkey method

	/**
	 * getBloodLord method
	 * @return bloodlord
	 */
	public BloodLord getBloodLord()
	{
		return bloodlord;
	} //end of getBloodLord method

	/**
	 * getBull method
	 * @return bull
	 */
	public Bull getBull()
	{
		return bull;
	} //end of getBull method

	/**
	 * getScorpian method
	 * @return scorpian
	 */
	public Scorpian getScorpian()
	{
		return scorpian;
	} //end of getScorpian method

	/**
	 * getData method
	 * @return data
	 */
	public Data getData()
	{
		return data;
	} //end of getData method

	/**
	 * getDebug method
	 * @return debug
	 */
	public boolean getDebug()
	{
		return debug;
	} //end of getDebug method

	/**
	 * getGame method
	 * @return game
	 */
	public boolean getGame()
	{
		return game;
	} //end of getGame method

	/**
	 * getMenu method
	 * @return menu
	 */
	public boolean getMenu()
	{
		return menu;
	} //end of getMenu method

} //end of Screen class