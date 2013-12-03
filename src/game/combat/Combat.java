package game.combat;

import game.entity.Entity;
import game.frame.Screen;
import game.player.projectile.Arrow;
import game.player.projectile.Spell;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

/**
 * Combat class
 * @author Michael Morgan
 */
public class Combat implements Runnable
{
	private Screen screen;

	private boolean run, meleeAttack, rangeAttack, mageAttack;

	private int playerMeleeDamage, playerRangeDamage, playerMageDamage, enemyDamage;

	private Image splat;

	private Random random, randomHit;

	private Thread attackThread;

	/**
	 * Combat constructor
	 * @param screen
	 */
	public Combat(Screen screen)
	{
		this.screen = screen;

		init();

		attackThread.start();
		run = true;
	} //end of Combat constructor

	/**
	 * init method
	 */
	private void init()
	{
		attackThread = new Thread(this);
		run = false;

		meleeAttack = false;
		rangeAttack = false;
		mageAttack = false;

		playerMeleeDamage = 0;
		playerRangeDamage = 0;
		playerMageDamage = 0;
		enemyDamage = 0;

		random = new Random();
		randomHit = new Random();

		splat = new ImageIcon("res/UI/game/splat.png").getImage();
	} //end of init method

	/**
	 * update method
	 */
	public void update()
	{
		handleAggro();
	} //end of update method

	/**
	 * render method
	 * @param g
	 */
	public void render(Graphics g)
	{
		g.setFont(new Font("Arial", Font.BOLD, 12));

		if(screen.getPlayer().getMap() == 1)
		{
			if(screen.getArrows().size() > 0)
			{

				if(screen.getArrows().get(0).getArrowRect().intersects(screen.getSpider().getAttackRect()))
				{
					g.drawImage(splat, screen.getSpider().getAttackRect().x + screen.getSpider().getAttackRect().width - 10,
							screen.getSpider().getAttackRect().y + screen.getSpider().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getSpider().getAttackRect().x + screen.getSpider().getAttackRect().width - 6, 
							screen.getSpider().getAttackRect().y + screen.getSpider().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getWolf().getAttackRect()))
				{
					g.drawImage(splat, screen.getWolf().getAttackRect().x + screen.getWolf().getAttackRect().width - 10,
							screen.getWolf().getAttackRect().y + screen.getWolf().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getWolf().getAttackRect().x + screen.getWolf().getAttackRect().width - 6, 
							screen.getWolf().getAttackRect().y + screen.getWolf().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getSkeleton().getAttackRect()))
				{
					g.drawImage(splat, screen.getSkeleton().getAttackRect().x + screen.getSkeleton().getAttackRect().width - 10,
							screen.getSkeleton().getAttackRect().y + screen.getSkeleton().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getSkeleton().getAttackRect().x + screen.getSkeleton().getAttackRect().width - 6, 
							screen.getSkeleton().getAttackRect().y + screen.getSkeleton().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getNecromancer().getAttackRect()))
				{
					g.drawImage(splat, screen.getNecromancer().getAttackRect().x + screen.getNecromancer().getAttackRect().width - 10,
							screen.getNecromancer().getAttackRect().y + screen.getNecromancer().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getNecromancer().getAttackRect().x + screen.getNecromancer().getAttackRect().width - 6, 
							screen.getNecromancer().getAttackRect().y + screen.getNecromancer().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getCyclops().getAttackRect()))
				{
					g.drawImage(splat, screen.getCyclops().getAttackRect().x + screen.getCyclops().getAttackRect().width - 10,
							screen.getCyclops().getAttackRect().y + screen.getCyclops().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getCyclops().getAttackRect().x + screen.getCyclops().getAttackRect().width - 6, 
							screen.getCyclops().getAttackRect().y + screen.getCyclops().getAttackRect().width / 2 + 4);
				}
			}

			if(screen.getSpells().size() > 0)
			{

				if(screen.getSpells().get(0).getSpellRect().intersects(screen.getSpider().getAttackRect()))
				{
					g.drawImage(splat, screen.getSpider().getAttackRect().x + screen.getSpider().getAttackRect().width - 10,
							screen.getSpider().getAttackRect().y + screen.getSpider().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getSpider().getAttackRect().x + screen.getSpider().getAttackRect().width - 6, 
							screen.getSpider().getAttackRect().y + screen.getSpider().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getWolf().getAttackRect()))
				{
					g.drawImage(splat, screen.getWolf().getAttackRect().x + screen.getWolf().getAttackRect().width - 10,
							screen.getWolf().getAttackRect().y + screen.getWolf().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getWolf().getAttackRect().x + screen.getWolf().getAttackRect().width - 6, 
							screen.getWolf().getAttackRect().y + screen.getWolf().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getSkeleton().getAttackRect()))
				{
					g.drawImage(splat, screen.getSkeleton().getAttackRect().x + screen.getSkeleton().getAttackRect().width - 10,
							screen.getSkeleton().getAttackRect().y + screen.getSkeleton().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getSkeleton().getAttackRect().x + screen.getSkeleton().getAttackRect().width - 6, 
							screen.getSkeleton().getAttackRect().y + screen.getSkeleton().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getNecromancer().getAttackRect()))
				{
					g.drawImage(splat, screen.getNecromancer().getAttackRect().x + screen.getNecromancer().getAttackRect().width - 10,
							screen.getNecromancer().getAttackRect().y + screen.getNecromancer().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getNecromancer().getAttackRect().x + screen.getNecromancer().getAttackRect().width - 6, 
							screen.getNecromancer().getAttackRect().y + screen.getNecromancer().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getCyclops().getAttackRect()))
				{
					g.drawImage(splat, screen.getCyclops().getAttackRect().x + screen.getCyclops().getAttackRect().width - 10,
							screen.getCyclops().getAttackRect().y + screen.getCyclops().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getCyclops().getAttackRect().x + screen.getCyclops().getAttackRect().width - 6, 
							screen.getCyclops().getAttackRect().y + screen.getCyclops().getAttackRect().width / 2 + 4);
				}
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getSpider().getAttackRect()) || 
					screen.getPlayer().getRectangle().intersects(screen.getWolf().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getSkeleton().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getNecromancer().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getCyclops().getAttackRect()))
			{

				g.drawImage(splat, screen.getPlayer().getRectangle().x + screen.getPlayer().getRectangle().width,
						screen.getPlayer().getRectangle().y + screen.getPlayer().getRectangle().width / 3, null);

				g.drawString("" + enemyDamage, screen.getPlayer().getRectangle().x + screen.getPlayer().getRectangle().width + 4, 
						screen.getPlayer().getRectangle().y + screen.getPlayer().getRectangle().width / 2 + 7);

				if(meleeAttack)
				{

					if(screen.getPlayer().getRectangle().intersects(screen.getSpider().getAttackRect()))
					{
						g.drawImage(splat, screen.getSpider().getAttackRect().x + screen.getSpider().getAttackRect().width - 10,
								screen.getSpider().getAttackRect().y + screen.getSpider().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getSpider().getAttackRect().x + screen.getSpider().getAttackRect().width - 6, 
								screen.getSpider().getAttackRect().y + screen.getSpider().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getWolf().getAttackRect()))
					{
						g.drawImage(splat, screen.getWolf().getAttackRect().x + screen.getWolf().getAttackRect().width - 10,
								screen.getWolf().getAttackRect().y + screen.getWolf().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getWolf().getAttackRect().x + screen.getWolf().getAttackRect().width - 6, 
								screen.getWolf().getAttackRect().y + screen.getWolf().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getSkeleton().getAttackRect()))
					{
						g.drawImage(splat, screen.getSkeleton().getAttackRect().x + screen.getSkeleton().getAttackRect().width - 10,
								screen.getSkeleton().getAttackRect().y + screen.getSkeleton().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getSkeleton().getAttackRect().x + screen.getSkeleton().getAttackRect().width - 6, 
								screen.getSkeleton().getAttackRect().y + screen.getSkeleton().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getNecromancer().getAttackRect()))
					{
						g.drawImage(splat, screen.getNecromancer().getAttackRect().x + screen.getNecromancer().getAttackRect().width - 10,
								screen.getNecromancer().getAttackRect().y + screen.getNecromancer().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getNecromancer().getAttackRect().x + screen.getNecromancer().getAttackRect().width - 6, 
								screen.getNecromancer().getAttackRect().y + screen.getNecromancer().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getCyclops().getAttackRect()))
					{
						g.drawImage(splat, screen.getCyclops().getAttackRect().x + screen.getCyclops().getAttackRect().width - 10,
								screen.getCyclops().getAttackRect().y + screen.getCyclops().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getCyclops().getAttackRect().x + screen.getCyclops().getAttackRect().width - 6, 
								screen.getCyclops().getAttackRect().y + screen.getCyclops().getAttackRect().width / 2 + 4);
					}
				}
			}
		}
		else if(screen.getPlayer().getMap() == 2)
		{
			if(screen.getArrows().size() > 0)
			{

				if(screen.getArrows().get(0).getArrowRect().intersects(screen.getPanda().getAttackRect()))
				{
					g.drawImage(splat, screen.getPanda().getAttackRect().x + screen.getPanda().getAttackRect().width - 10,
							screen.getPanda().getAttackRect().y + screen.getPanda().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getPanda().getAttackRect().x + screen.getPanda().getAttackRect().width - 6, 
							screen.getPanda().getAttackRect().y + screen.getPanda().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getMonkey().getAttackRect()))
				{
					g.drawImage(splat, screen.getMonkey().getAttackRect().x + screen.getMonkey().getAttackRect().width - 10,
							screen.getMonkey().getAttackRect().y + screen.getMonkey().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getMonkey().getAttackRect().x + screen.getMonkey().getAttackRect().width - 6, 
							screen.getMonkey().getAttackRect().y + screen.getMonkey().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getScorpian().getAttackRect()))
				{
					g.drawImage(splat, screen.getScorpian().getAttackRect().x + screen.getScorpian().getAttackRect().width - 10,
							screen.getScorpian().getAttackRect().y + screen.getScorpian().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getScorpian().getAttackRect().x + screen.getScorpian().getAttackRect().width - 6, 
							screen.getScorpian().getAttackRect().y + screen.getScorpian().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getBloodLord().getAttackRect()))
				{
					g.drawImage(splat, screen.getBloodLord().getAttackRect().x + screen.getBloodLord().getAttackRect().width - 10,
							screen.getBloodLord().getAttackRect().y + screen.getBloodLord().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getBloodLord().getAttackRect().x + screen.getBloodLord().getAttackRect().width - 6, 
							screen.getBloodLord().getAttackRect().y + screen.getBloodLord().getAttackRect().width / 2 + 4);
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getBull().getAttackRect()))
				{
					g.drawImage(splat, screen.getBull().getAttackRect().x + screen.getBull().getAttackRect().width - 10,
							screen.getBull().getAttackRect().y + screen.getBull().getAttackRect().width / 3, null);

					g.drawString("" + playerRangeDamage, screen.getBull().getAttackRect().x + screen.getBull().getAttackRect().width - 6, 
							screen.getBull().getAttackRect().y + screen.getBull().getAttackRect().width / 2 + 4);
				}
			}

			if(screen.getSpells().size() > 0)
			{

				if(screen.getSpells().get(0).getSpellRect().intersects(screen.getPanda().getAttackRect()))
				{
					g.drawImage(splat, screen.getPanda().getAttackRect().x + screen.getPanda().getAttackRect().width - 10,
							screen.getPanda().getAttackRect().y + screen.getPanda().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getPanda().getAttackRect().x + screen.getPanda().getAttackRect().width - 6, 
							screen.getPanda().getAttackRect().y + screen.getPanda().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getMonkey().getAttackRect()))
				{
					g.drawImage(splat, screen.getMonkey().getAttackRect().x + screen.getMonkey().getAttackRect().width - 10,
							screen.getMonkey().getAttackRect().y + screen.getMonkey().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getMonkey().getAttackRect().x + screen.getMonkey().getAttackRect().width - 6, 
							screen.getMonkey().getAttackRect().y + screen.getMonkey().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getScorpian().getAttackRect()))
				{
					g.drawImage(splat, screen.getScorpian().getAttackRect().x + screen.getScorpian().getAttackRect().width - 10,
							screen.getScorpian().getAttackRect().y + screen.getScorpian().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getScorpian().getAttackRect().x + screen.getScorpian().getAttackRect().width - 6, 
							screen.getScorpian().getAttackRect().y + screen.getScorpian().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getBloodLord().getAttackRect()))
				{
					g.drawImage(splat, screen.getBloodLord().getAttackRect().x + screen.getBloodLord().getAttackRect().width - 10,
							screen.getBloodLord().getAttackRect().y + screen.getBloodLord().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getBloodLord().getAttackRect().x + screen.getBloodLord().getAttackRect().width - 6, 
							screen.getBloodLord().getAttackRect().y + screen.getBloodLord().getAttackRect().width / 2 + 4);
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getBull().getAttackRect()))
				{
					g.drawImage(splat, screen.getBull().getAttackRect().x + screen.getBull().getAttackRect().width - 10,
							screen.getBull().getAttackRect().y + screen.getBull().getAttackRect().width / 3, null);

					g.drawString("" + playerMageDamage, screen.getBull().getAttackRect().x + screen.getBull().getAttackRect().width - 6, 
							screen.getBull().getAttackRect().y + screen.getBull().getAttackRect().width / 2 + 4);
				}
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getPanda().getAttackRect()) || 
					screen.getPlayer().getRectangle().intersects(screen.getMonkey().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getScorpian().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getBloodLord().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getBull().getAttackRect()))
			{

				g.drawImage(splat, screen.getPlayer().getRectangle().x + screen.getPlayer().getRectangle().width,
						screen.getPlayer().getRectangle().y + screen.getPlayer().getRectangle().width / 3, null);

				g.drawString("" + enemyDamage, screen.getPlayer().getRectangle().x + screen.getPlayer().getRectangle().width + 4, 
						screen.getPlayer().getRectangle().y + screen.getPlayer().getRectangle().width / 2 + 7);

				if(meleeAttack)
				{

					if(screen.getPlayer().getRectangle().intersects(screen.getPanda().getAttackRect()))
					{
						g.drawImage(splat, screen.getPanda().getAttackRect().x + screen.getPanda().getAttackRect().width - 10,
								screen.getPanda().getAttackRect().y + screen.getPanda().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getPanda().getAttackRect().x + screen.getPanda().getAttackRect().width - 6, 
								screen.getPanda().getAttackRect().y + screen.getPanda().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getMonkey().getAttackRect()))
					{
						g.drawImage(splat, screen.getMonkey().getAttackRect().x + screen.getMonkey().getAttackRect().width - 10,
								screen.getMonkey().getAttackRect().y + screen.getMonkey().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getMonkey().getAttackRect().x + screen.getMonkey().getAttackRect().width - 6, 
								screen.getMonkey().getAttackRect().y + screen.getMonkey().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getScorpian().getAttackRect()))
					{
						g.drawImage(splat, screen.getScorpian().getAttackRect().x + screen.getScorpian().getAttackRect().width - 10,
								screen.getScorpian().getAttackRect().y + screen.getScorpian().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getScorpian().getAttackRect().x + screen.getScorpian().getAttackRect().width - 6, 
								screen.getScorpian().getAttackRect().y + screen.getScorpian().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getBloodLord().getAttackRect()))
					{
						g.drawImage(splat, screen.getBloodLord().getAttackRect().x + screen.getBloodLord().getAttackRect().width - 10,
								screen.getBloodLord().getAttackRect().y + screen.getBloodLord().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getBloodLord().getAttackRect().x + screen.getBloodLord().getAttackRect().width - 6, 
								screen.getBloodLord().getAttackRect().y + screen.getBloodLord().getAttackRect().width / 2 + 4);
					}
					else if(screen.getPlayer().getRectangle().intersects(screen.getBull().getAttackRect()))
					{
						g.drawImage(splat, screen.getBull().getAttackRect().x + screen.getBull().getAttackRect().width - 10,
								screen.getBull().getAttackRect().y + screen.getBull().getAttackRect().width / 3, null);

						g.drawString("" + playerMeleeDamage, screen.getBull().getAttackRect().x + screen.getBull().getAttackRect().width - 6, 
								screen.getBull().getAttackRect().y + screen.getBull().getAttackRect().width / 2 + 4);
					}
				}
			}
		}
	} //end of render method

	/**
	 * run method
	 */
	@SuppressWarnings("static-access")
	public void run()
	{
		while(run)
		{
			handleAttack();
			handleBossHealing();

			try
			{
				attackThread.sleep(500);
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
		attackThread.stop();
		run = false;
	} //end of stop method

	/**
	 * handleAggro method
	 */
	private void handleAggro()
	{
		if(screen.getPlayer().getMap() == 1)
		{
			if(screen.getPlayer().getRectangle().intersects(screen.getCyclops().getAggroRect()))
			{
				screen.getCyclops().setAggro(true);
			}
			else
			{
				screen.getCyclops().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getNecromancer().getAggroRect()))
			{
				screen.getNecromancer().setAggro(true);
			}
			else
			{
				screen.getNecromancer().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getSkeleton().getAggroRect()))
			{
				screen.getSkeleton().setAggro(true);
			}
			else
			{
				screen.getSkeleton().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getSpider().getAggroRect()))
			{
				screen.getSpider().setAggro(true);
			}
			else
			{
				screen.getSpider().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getWolf().getAggroRect()))
			{
				screen.getWolf().setAggro(true);
			}
			else
			{
				screen.getWolf().setAggro(false);
			}
		}
		else if(screen.getPlayer().getMap() == 2)
		{
			if(screen.getPlayer().getRectangle().intersects(screen.getPanda().getAggroRect()))
			{
				screen.getPanda().setAggro(true);
			}
			else
			{
				screen.getPanda().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getMonkey().getAggroRect()))
			{
				screen.getMonkey().setAggro(true);
			}
			else
			{
				screen.getMonkey().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getScorpian().getAggroRect()))
			{
				screen.getScorpian().setAggro(true);
			}
			else
			{
				screen.getScorpian().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getBull().getAggroRect()))
			{
				screen.getBull().setAggro(true);
			}
			else
			{
				screen.getBull().setAggro(false);
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getBloodLord().getAggroRect()))
			{
				screen.getBloodLord().setAggro(true);
			}
			else
			{
				screen.getBloodLord().setAggro(false);
			}
		}
	} //end of handleAggro method

	/**
	 * handleAttack method
	 */
	public void handleAttack()
	{
		if(screen.getPlayer().getMap() == 1)
		{
			if(rangeAttack || screen.getArrows().size() > 0)
			{
				if(screen.getArrows().size() == 0)
				{
					screen.getArrows().add(new Arrow(screen));
				}


				if((screen.getArrows().get(0).getX() - screen.getArrows().get(0).getStartX()) > screen.getPlayer().getDis() ||
						(screen.getArrows().get(0).getY() - screen.getArrows().get(0).getStartY()) > screen.getPlayer().getDis() ||
						(screen.getArrows().get(0).getX() - screen.getArrows().get(0).getStartX()) < (screen.getPlayer().getDis() * -1) ||
						(screen.getArrows().get(0).getY() - screen.getArrows().get(0).getStartY()) < (screen.getPlayer().getDis() * -1))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getSpider().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getSpider().getHpWidth() - playerRangeDamage) > 0)
					{
						screen.getSpider().decrementHealth(playerRangeDamage);
						screen.getSpider().decrementHpWidth(playerRangeDamage);
					}
					else
					{
						dead(screen.getSpider());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getWolf().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getWolf().getHpWidth() - (playerRangeDamage / 4)) > 0)
					{
						screen.getWolf().decrementHealth(playerRangeDamage);
						screen.getWolf().decrementHpWidth(playerRangeDamage / 4);
					}
					else
					{
						dead(screen.getWolf());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getSkeleton().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getSkeleton().getHpWidth() - (playerRangeDamage / 2)) > 0)
					{
						screen.getSkeleton().decrementHealth(playerRangeDamage);
						screen.getSkeleton().decrementHpWidth(playerRangeDamage / 2);
					}
					else
					{
						dead(screen.getSkeleton());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getCyclops().getAttackRect()))
				{
					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getCyclops().getHpWidth() - (playerRangeDamage / 3)) > 0)
					{
						screen.getCyclops().decrementHealth(playerRangeDamage);
						screen.getCyclops().decrementHpWidth(playerRangeDamage / 3);
					}
					else
					{
						dead(screen.getCyclops());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getNecromancer().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getNecromancer().getHpWidth() - (playerRangeDamage / 8)) > 0)
					{
						screen.getNecromancer().decrementHealth(playerRangeDamage);
						screen.getNecromancer().decrementHpWidth(playerRangeDamage / 8);
					}
					else
					{
						screen.getMap().setBossDead(true);
						dead(screen.getNecromancer());
						screen.getDialog().sendMessage("You killed the boss!");
						screen.getDialog().sendMessage("You can use the red staircase now.");
					}
				}
			}
			else if(mageAttack || screen.getSpells().size() > 0)
			{
				if(screen.getSpells().size() == 0)
				{
					screen.getSpells().add(new Spell(screen));
				}

				if((screen.getSpells().get(0).getX() - screen.getSpells().get(0).getStartX()) > screen.getPlayer().getDis() ||
						(screen.getSpells().get(0).getY() - screen.getSpells().get(0).getStartY()) > screen.getPlayer().getDis() ||
						(screen.getSpells().get(0).getX() - screen.getSpells().get(0).getStartX()) < (screen.getPlayer().getDis() * -1) ||
						(screen.getSpells().get(0).getY() - screen.getSpells().get(0).getStartY()) < (screen.getPlayer().getDis() * -1))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getSpider().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getSpider().getHpWidth() - playerMageDamage) > 0)
					{
						screen.getSpider().decrementHealth(playerMageDamage);
						screen.getSpider().decrementHpWidth(playerMageDamage);
					}
					else
					{
						dead(screen.getSpider());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getWolf().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getWolf().getHpWidth() - (playerMageDamage / 4)) > 0)
					{
						screen.getWolf().decrementHealth(playerMageDamage);
						screen.getWolf().decrementHpWidth(playerMageDamage / 4);
					}
					else
					{
						dead(screen.getWolf());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getSkeleton().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getSkeleton().getHpWidth() - (playerMageDamage / 2)) > 0)
					{
						screen.getSkeleton().decrementHealth(playerMageDamage);
						screen.getSkeleton().decrementHpWidth(playerMageDamage / 2);
					}
					else
					{
						dead(screen.getSkeleton());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getCyclops().getAttackRect()))
				{
					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getCyclops().getHpWidth() - (playerMageDamage / 3)) > 0)
					{
						screen.getCyclops().decrementHealth(playerMageDamage);
						screen.getCyclops().decrementHpWidth(playerMageDamage / 3);
					}
					else
					{
						dead(screen.getCyclops());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getNecromancer().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getNecromancer().getHpWidth() - (playerMageDamage / 8)) > 0)
					{
						screen.getNecromancer().decrementHealth(playerMageDamage);
						screen.getNecromancer().decrementHpWidth(playerMageDamage / 8);
					}
					else
					{
						screen.getMap().setBossDead(true);
						dead(screen.getNecromancer());
						screen.getDialog().sendMessage("You killed the boss!");
						screen.getDialog().sendMessage("You can use the red staircase now.");
					}
				}
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getSpider().getAttackRect()) || 
					screen.getPlayer().getRectangle().intersects(screen.getWolf().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getSkeleton().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getNecromancer().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getCyclops().getAttackRect()))
			{

				if(screen.getPlayer().getRectangle().intersects(screen.getSpider().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getSpider().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getSpider().getHpWidth() - playerMeleeDamage) > 0)
						{
							screen.getSpider().decrementHealth(playerMeleeDamage);
							screen.getSpider().decrementHpWidth(playerMeleeDamage);
						}
						else
						{
							dead(screen.getSpider());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getWolf().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getWolf().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getWolf().getHpWidth() - (playerMeleeDamage / 4)) > 0)
						{
							screen.getWolf().decrementHealth(playerMeleeDamage);
							screen.getWolf().decrementHpWidth(playerMeleeDamage / 4);
						}
						else
						{
							dead(screen.getWolf());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getSkeleton().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getSkeleton().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getSkeleton().getHpWidth() - (playerMeleeDamage / 2)) > 0)
						{
							screen.getSkeleton().decrementHealth(playerMeleeDamage);
							screen.getSkeleton().decrementHpWidth(playerMeleeDamage / 2);
						}
						else
						{
							dead(screen.getSkeleton());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getCyclops().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getCyclops().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getCyclops().getHpWidth() - (playerMeleeDamage / 3)) > 0)
						{
							screen.getCyclops().decrementHealth(playerMeleeDamage);
							screen.getCyclops().decrementHpWidth(playerMeleeDamage / 3);
						}
						else
						{
							dead(screen.getCyclops());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getNecromancer().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getNecromancer().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getNecromancer().getHpWidth() - (playerMeleeDamage / 8)) > 0)
						{
							screen.getNecromancer().decrementHealth(playerMeleeDamage);
							screen.getNecromancer().decrementHpWidth(playerMeleeDamage / 8);
						}
						else
						{
							screen.getMap().setBossDead(true);
							dead(screen.getNecromancer());
							screen.getDialog().sendMessage("You killed the boss!");
							screen.getDialog().sendMessage("You can use the red staircase now.");
						}
					}
				}
			}
			else
			{
				if(screen.getPlayer().getHealth() < 100)
				{
					screen.getPlayer().incrementHealth(1);
				}

				if(screen.getUI().getHpWidth() < 100)
				{
					screen.getUI().incrementHpWidth(1);
				}

				if(screen.getPlayer().getMana() < 100)
				{
					screen.getPlayer().incrementMana(1);
				}

				if(screen.getUI().getMpWidth() < 100)
				{
					screen.getUI().incrementMpWidth(1);
				}
			}
		}
		else if(screen.getPlayer().getMap() == 2)
		{
			if(rangeAttack || screen.getArrows().size() > 0)
			{
				if(screen.getArrows().size() == 0)
				{
					screen.getArrows().add(new Arrow(screen));
				}


				if((screen.getArrows().get(0).getX() - screen.getArrows().get(0).getStartX()) > screen.getPlayer().getDis() ||
						(screen.getArrows().get(0).getY() - screen.getArrows().get(0).getStartY()) > screen.getPlayer().getDis() ||
						(screen.getArrows().get(0).getX() - screen.getArrows().get(0).getStartX()) < (screen.getPlayer().getDis() * -1) ||
						(screen.getArrows().get(0).getY() - screen.getArrows().get(0).getStartY()) < (screen.getPlayer().getDis() * -1))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getPanda().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getPanda().getHpWidth() - playerRangeDamage) > 0)
					{
						screen.getPanda().decrementHealth(playerRangeDamage);
						screen.getPanda().decrementHpWidth(playerRangeDamage);
					}
					else
					{
						dead(screen.getPanda());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getMonkey().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getMonkey().getHpWidth() - (playerRangeDamage / 4)) > 0)
					{
						screen.getMonkey().decrementHealth(playerRangeDamage);
						screen.getMonkey().decrementHpWidth(playerRangeDamage / 4);
					}
					else
					{
						dead(screen.getMonkey());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getScorpian().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getScorpian().getHpWidth() - (playerRangeDamage / 2)) > 0)
					{
						screen.getScorpian().decrementHealth(playerRangeDamage);
						screen.getScorpian().decrementHpWidth(playerRangeDamage / 2);
					}
					else
					{
						dead(screen.getScorpian());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getBull().getAttackRect()))
				{
					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getBull().getHpWidth() - (playerRangeDamage / 3)) > 0)
					{
						screen.getBull().decrementHealth(playerRangeDamage);
						screen.getBull().decrementHpWidth(playerRangeDamage / 3);
					}
					else
					{
						dead(screen.getBull());
					}
				}
				else if(screen.getArrows().get(0).getArrowRect().intersects(screen.getBloodLord().getAttackRect()))
				{
					if(screen.getArrows().get(0) != null)
					{
						screen.getArrows().remove(0);
					}

					playerRangeDamage = randomHit.nextInt(screen.getPlayer().getAcc() + 1) + (int)(screen.getPlayer().getAcc() * screen.getPlayer().getDmgBonus());

					if((screen.getBloodLord().getHpWidth() - (playerRangeDamage / 8)) > 0)
					{
						screen.getBloodLord().decrementHealth(playerRangeDamage);
						screen.getBloodLord().decrementHpWidth(playerRangeDamage / 8);
					}
					else
					{
						dead(screen.getBloodLord());
					}
				}
			}
			else if(mageAttack || screen.getSpells().size() > 0)
			{
				if(screen.getSpells().size() == 0)
				{
					screen.getSpells().add(new Spell(screen));
				}

				if((screen.getSpells().get(0).getX() - screen.getSpells().get(0).getStartX()) > screen.getPlayer().getDis() ||
						(screen.getSpells().get(0).getY() - screen.getSpells().get(0).getStartY()) > screen.getPlayer().getDis() ||
						(screen.getSpells().get(0).getX() - screen.getSpells().get(0).getStartX()) < (screen.getPlayer().getDis() * -1) ||
						(screen.getSpells().get(0).getY() - screen.getSpells().get(0).getStartY()) < (screen.getPlayer().getDis() * -1))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getPanda().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getPanda().getHpWidth() - playerMageDamage) > 0)
					{
						screen.getPanda().decrementHealth(playerMageDamage);
						screen.getPanda().decrementHpWidth(playerMageDamage);
					}
					else
					{
						dead(screen.getPanda());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getMonkey().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getMonkey().getHpWidth() - (playerMageDamage / 4)) > 0)
					{
						screen.getMonkey().decrementHealth(playerMageDamage);
						screen.getMonkey().decrementHpWidth(playerMageDamage / 4);
					}
					else
					{
						dead(screen.getMonkey());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getScorpian().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getScorpian().getHpWidth() - (playerMageDamage / 2)) > 0)
					{
						screen.getScorpian().decrementHealth(playerMageDamage);
						screen.getScorpian().decrementHpWidth(playerMageDamage / 2);
					}
					else
					{
						dead(screen.getScorpian());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getBull().getAttackRect()))
				{
					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getBull().getHpWidth() - (playerMageDamage / 3)) > 0)
					{
						screen.getBull().decrementHealth(playerMageDamage);
						screen.getBull().decrementHpWidth(playerMageDamage / 3);
					}
					else
					{
						dead(screen.getBull());
					}
				}
				else if(screen.getSpells().get(0).getSpellRect().intersects(screen.getBloodLord().getAttackRect()))
				{
					if(screen.getSpells().get(0) != null)
					{
						screen.getSpells().remove(0);
					}

					playerMageDamage = randomHit.nextInt(screen.getPlayer().getPow() + 1) + (int)(screen.getPlayer().getPow() * screen.getPlayer().getDmgBonus());

					if((screen.getBloodLord().getHpWidth() - (playerMageDamage / 8)) > 0)
					{
						screen.getBloodLord().decrementHealth(playerMageDamage);
						screen.getBloodLord().decrementHpWidth(playerMageDamage / 8);
					}
					else
					{
						dead(screen.getBloodLord());
					}
				}
			}

			if(screen.getPlayer().getRectangle().intersects(screen.getPanda().getAttackRect()) || 
					screen.getPlayer().getRectangle().intersects(screen.getMonkey().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getScorpian().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getBloodLord().getAttackRect()) ||
					screen.getPlayer().getRectangle().intersects(screen.getBull().getAttackRect()))
			{

				if(screen.getPlayer().getRectangle().intersects(screen.getPanda().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getPanda().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getPanda().getHpWidth() - playerMeleeDamage) > 0)
						{
							screen.getPanda().decrementHealth(playerMeleeDamage);
							screen.getPanda().decrementHpWidth(playerMeleeDamage);
						}
						else
						{
							dead(screen.getPanda());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getMonkey().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getMonkey().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getMonkey().getHpWidth() - (playerMeleeDamage / 4)) > 0)
						{
							screen.getMonkey().decrementHealth(playerMeleeDamage);
							screen.getMonkey().decrementHpWidth(playerMeleeDamage / 4);
						}
						else
						{
							dead(screen.getMonkey());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getScorpian().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getScorpian().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getScorpian().getHpWidth() - (playerMeleeDamage / 2)) > 0)
						{
							screen.getScorpian().decrementHealth(playerMeleeDamage);
							screen.getScorpian().decrementHpWidth(playerMeleeDamage / 2);
						}
						else
						{
							dead(screen.getScorpian());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getBull().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getBull().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getBull().getHpWidth() - (playerMeleeDamage / 3)) > 0)
						{
							screen.getBull().decrementHealth(playerMeleeDamage);
							screen.getBull().decrementHpWidth(playerMeleeDamage / 3);
						}
						else
						{
							dead(screen.getBull());
						}
					}
				}
				else if(screen.getPlayer().getRectangle().intersects(screen.getBloodLord().getAttackRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getBloodLord().getStr() + 1);

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
					if(meleeAttack)
					{
						playerMeleeDamage = randomHit.nextInt(screen.getPlayer().getStr() + 1) + (int)(screen.getPlayer().getStr() * screen.getPlayer().getDmgBonus());

						if((screen.getBloodLord().getHpWidth() - (playerMeleeDamage / 8)) > 0)
						{
							screen.getBloodLord().decrementHealth(playerMeleeDamage);
							screen.getBloodLord().decrementHpWidth(playerMeleeDamage / 8);
						}
						else
						{
							dead(screen.getBloodLord());
						}
					}
				}
			}
			else
			{
				if(screen.getPlayer().getHealth() < 100)
				{
					screen.getPlayer().incrementHealth(1);
				}

				if(screen.getUI().getHpWidth() < 100)
				{
					screen.getUI().incrementHpWidth(1);
				}

				if(screen.getPlayer().getMana() < 100)
				{
					screen.getPlayer().incrementMana(1);
				}

				if(screen.getUI().getMpWidth() < 100)
				{
					screen.getUI().incrementMpWidth(1);
				}
			}
		}
	} //end of handleAttack method

	/**
	 * dead method
	 * @param entity
	 */
	public void dead(Entity entity)
	{
		if(entity.getClass().getName().substring(12).equalsIgnoreCase("player"))
		{
			screen.getDialog().sendMessage("Oh dear, you died.");

			screen.getPlayer().setDead(true);
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("spider"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getSpider().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp((10));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= 800 - 8)
				{
					screen.getUI().incrementExpWidth(80);
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("wolf"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getWolf().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (5 * 4));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (4 * 4))))
				{
					screen.getUI().incrementExpWidth(8 * (5 * 4));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("skeleton"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getSkeleton().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (4 * 3));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (2 * 2))))
				{
					screen.getUI().incrementExpWidth(8 * (4 * 3));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("necromancer"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getNecromancer().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (8 * 8));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (8 * 8))))
				{
					screen.getUI().incrementExpWidth(8 * (8 * 8));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("cyclops"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getCyclops().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (4 * 4));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (3 * 3))))
				{
					screen.getUI().incrementExpWidth(8 * (4 * 4));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("panda"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getPanda().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp((10));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= 800 - 8)
				{
					screen.getUI().incrementExpWidth(80);
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("scorpian"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getScorpian().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (5 * 4));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (4 * 4))))
				{
					screen.getUI().incrementExpWidth(8 * (5 * 4));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("monkey"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getMonkey().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (4 * 3));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (2 * 2))))
				{
					screen.getUI().incrementExpWidth(8 * (4 * 3));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("bloodlord"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getBloodLord().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (8 * 8));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (8 * 8))))
				{
					screen.getUI().incrementExpWidth(8 * (8 * 8));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}
		}
		else if(entity.getClass().getName().substring(11).equalsIgnoreCase("bull"))
		{
			screen.getDialog().sendMessage("You killed a " + entity.getClass().getName().substring(11) + ".");

			screen.getBull().setDead(true);

			if(screen.getPlayer().getExp() < 800)
			{
				screen.getPlayer().incrementExp(1 * (4 * 4));
				if(screen.getUI().getExpWidth() >= 0 && screen.getUI().getExpWidth() <= (800 - (8 * (3 * 3))))
				{
					screen.getUI().incrementExpWidth(8 * (4 * 4));
				}
				else
				{
					screen.getUI().setExpWidth(0);
					screen.getPlayer().incrementLevel(1);
					screen.getPlayer().incrementSp(1);
					screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
					screen.getDialog().sendMessage("You gained 1 skill point.");
				}
			}
			else
			{
				screen.getUI().setExpWidth(0);
				screen.getPlayer().incrementLevel(1);
				screen.getPlayer().incrementSp(1);
				screen.getDialog().sendMessage("You advance to level " + screen.getPlayer().getLevel() + ".");
				screen.getDialog().sendMessage("You gained 1 skill point.");
			}

			if(!(screen.getUI().getInventoryList().size() == 30))
			{
				Image drop = getEnemyDrop();
				if(drop != null)
				{
					if(!screen.getEquipment().getEquipmentType(drop).equalsIgnoreCase(""))
					{
						screen.getDialog().sendMessage("You obtained a " + screen.getEquipment().getEquipmentType(drop) + " piece.");	
					}

					if(drop == screen.getPotion().getHealthPotion() ||
							drop == screen.getPotion().getManaPotion())
					{
						if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
						{
							screen.getPotion().incrementHealthPotions(1);
						}
						else if(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) == -1)
						{
							screen.getUI().setInventoryList(drop);
							screen.getPotion().incrementHealthPotions(1);
						}

						if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
						{
							if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
							{
								screen.getPotion().incrementManaPotions(1);
							}
							else if(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) == -1)
							{
								screen.getUI().setInventoryList(drop);
								screen.getPotion().incrementManaPotions(1);
							}
						}
					}
					else
					{
						screen.getUI().setInventoryList(drop);
					}
				}
			}
			else
			{
				screen.getDialog().sendMessage("Can't get loot, inventory full!");
			}
		}

	} //end of dead method

	/**
	 * handleBossHealing method
	 */
	private void handleBossHealing()
	{
		int number = random.nextInt(10 + 1);

		if(screen.getPlayer().getMap() == 1)
		{
			if(number == 5)
			{
				if(screen.getNecromancer().getHpWidth() < 32)
				{
					screen.getNecromancer().incrementHpWidth(8);
					screen.getNecromancer().incrementHealth(64);
				}
				else
				{
					screen.getNecromancer().setHpWidth(32);
				}
			}
		}
		else if(screen.getPlayer().getMap() == 2)
		{
			if(number == 5)
			{
				if(screen.getPlayer().getRectangle().intersects(screen.getBloodLord().getAggroRect()))
				{
					enemyDamage = randomHit.nextInt(screen.getBloodLord().getStr() + 1) + 12;

					if(screen.getPlayer().getHealth() > 0)
					{
						screen.getPlayer().decrementHealth(enemyDamage);

						if(screen.getUI().getHpWidth() > 0)
						{
							screen.getUI().decrementHpWidth(enemyDamage);
						}
					}
					else
					{
						dead(screen.getPlayer());
					}
				}
			}
		}
	} //end of handleBossHealing method

	/**
	 * getEnemyDrop method
	 * @return drop
	 */
	public Image getEnemyDrop()
	{
		Image drop = null;

		int randomItem = random.nextInt(14 + 1), randomPotion = random.nextInt(6 + 1);

		if(randomPotion == 1)
		{
			return screen.getPotion().getHealthPotion();
		}
		if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
		{
			if(randomPotion == 2)
			{
				return screen.getPotion().getManaPotion();
			}
		}

		if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
		{
			if(randomItem == 1)
			{
				drop = screen.getEquipment().mageBoots;
			}
			else if(randomItem == 2)
			{
				drop = screen.getEquipment().mageBooks[0];
			}
			else if(randomItem == 3)
			{
				drop = screen.getEquipment().mageGloves[0];
			}
			else if(randomItem == 4)
			{
				drop = screen.getEquipment().mageStaffs[0];
			}
			else if(randomItem == 5)
			{
				drop = screen.getEquipment().mageAmulets[0];
			}
			else if(randomItem == 6)
			{
				drop = screen.getEquipment().mageCloaks[0];
			}
			else if(randomItem == 7)
			{
				drop = screen.getEquipment().mageHats[0];
			}
		}

		if(screen.getPlayer().getClassType().equalsIgnoreCase("melee"))
		{
			if(randomItem == 1)
			{
				drop = screen.getEquipment().meleeBoots;
			}
			else if(randomItem == 2)
			{
				drop = screen.getEquipment().meleeShields[0];
			}
			else if(randomItem == 3)
			{
				drop = screen.getEquipment().meleeGloves[0];
			}
			else if(randomItem == 4)
			{
				drop = screen.getEquipment().meleeSwords[0];
			}
			else if(randomItem == 5)
			{
				drop = screen.getEquipment().meleeAmulets[0];
			}
			else if(randomItem == 6)
			{
				drop = screen.getEquipment().meleeArmors[0];
			}
			else if(randomItem == 7)
			{
				drop = screen.getEquipment().meleeHelmets[0];
			}
		}

		else if(screen.getPlayer().getClassType().equalsIgnoreCase("range"))
		{
			if(randomItem == 1)
			{
				drop = screen.getEquipment().rangeBoots;
			}
			else if(randomItem == 2)
			{
				drop = screen.getEquipment().rangeCoifs;
			}
			else if(randomItem == 3)
			{
				drop = screen.getEquipment().rangeArmors;
			}
			else if(randomItem == 4)
			{
				drop = screen.getEquipment().rangeArrows;
			}
			else if(randomItem == 5)
			{
				drop = screen.getEquipment().rangeGloves[0];
			}
			else if(randomItem == 6)
			{
				drop = screen.getEquipment().rangeBows[0];
			}
			else if(randomItem == 7)
			{
				drop = screen.getEquipment().rangeAmulets[0];
			}
		}

		return drop;
	} //end of getEnemyDrop method

	/**
	 * resetAttack method
	 */
	public void resetAttack()
	{
		meleeAttack = false;
		rangeAttack = false;
		mageAttack = false;
	} //end of resetAttack method

	/**
	 * setMeleeAttack method
	 * @param answer
	 */
	public void setMeleeAttack(boolean answer)
	{
		meleeAttack = answer;
	} //end of setMeleeAttack method

	/**
	 * setRangeAttack method
	 * @param answer
	 */
	public void setRangeAttack(boolean answer)
	{
		rangeAttack = answer;
	} //end of setRangeAttack method

	/**
	 * setMageAttack method
	 * @param answer
	 */
	public void setMageAttack(boolean answer)
	{
		mageAttack = answer;
	} //end of setMageAttack method

	/**
	 * getMeleeAttack method
	 */
	public boolean getMeleeAttack()
	{
		return meleeAttack;
	} //end of getMeleeAttack method

	/**
	 * getRangeAttack method
	 */
	public boolean getRangeAttack()
	{
		return rangeAttack;
	} //end of getRangeAttack method

	/**
	 * getMageAttack method
	 */
	public boolean getMageAttack()
	{
		return mageAttack;
	} //end of getMageAttack method

} //end of Combat class