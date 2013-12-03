package game.event;

import game.frame.Screen;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * KeyHandler class
 * @author Michael Morgan
 */
public class Key extends KeyAdapter
{
	private Screen screen;

	/**
	 * Key constructor
	 * @param screen
	 */
	public Key(Screen screen)
	{
		this.screen = screen;
	} //end of Key constructor

	/**
	 * keyPressed method
	 * @param e
	 */
	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent e)
	{	
		if(e.getKeyCode() == e.VK_UP)
		{
			screen.getPlayer().resetPlayer();
			screen.getPlayer().setMovingUp(true);
			screen.getPlayer().setUp();
		}
		if(e.getKeyCode() == e.VK_DOWN)
		{
			screen.getPlayer().resetPlayer();
			screen.getPlayer().setMovingDown(true);
			screen.getPlayer().setDown();
		}
		if(e.getKeyCode() == e.VK_LEFT)
		{
			screen.getPlayer().resetPlayer();
			screen.getPlayer().setMovingLeft(true);
			screen.getPlayer().setLeft();
		}
		if(e.getKeyCode() == e.VK_RIGHT)
		{
			screen.getPlayer().resetPlayer();
			screen.getPlayer().setMovingRight(true);
			screen.getPlayer().setRight();
		}
		if(e.getKeyCode() == e.VK_SPACE)
		{
			if(screen.getPlayer().getClassType().equalsIgnoreCase("melee"))
			{
				screen.getCombat().setMeleeAttack(true);
			}
			else if(screen.getPlayer().getClassType().equalsIgnoreCase("range"))
			{
				screen.getCombat().setRangeAttack(true);
			}
			else if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
			{
				if(screen.getPlayer().getMana() >= 10 && screen.getUI().getMpWidth() >= 10)
				{
					screen.getCombat().setMageAttack(true);
				}
			}
		}
		if(e.getKeyCode() == e.VK_E)
		{
			if(screen.getUI().getShowEquipment() == false)
			{
				screen.getUI().setShowInventory(false);
				screen.getUI().setShowEquipment(true);
			}
			else
			{
				screen.getUI().setShowEquipment(false);
			}
		}
		else if(e.getKeyCode() == e.VK_I)
		{
			if(screen.getUI().getShowInventory() == false)
			{
				screen.getUI().setShowEquipment(false);
				screen.getUI().setShowInventory(true);
			}
			else
			{
				screen.getUI().setShowInventory(false);
			}
		}
		if(e.getKeyCode() == e.VK_C)
		{
			if(screen.getUI().getShowCharacter() == false)
			{
				screen.getUI().setShowCharacter(true);
			}
			else
			{
				screen.getUI().setShowCharacter(false);
			}
		}
		if(e.getKeyCode() == e.VK_1)
		{
			if(screen.getPotion().getHealthPotions() > 0 && screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()) != -1)
			{
				screen.getPotion().decrementHealthPotions(1);

				if(screen.getPotion().getHealthPotions() <= 0)
				{
					screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(screen.getPotion().getHealthPotion()));
				}
				if((screen.getPlayer().getHealth() + screen.getPotion().getHealthAmount()) <= 100)
				{
					screen.getPlayer().incrementHealth(screen.getPotion().getHealthAmount());
					screen.getUI().incrementHpWidth(screen.getPotion().getHealthAmount());
				}
				else
				{
					screen.getPlayer().setHealth(100);
					screen.getUI().setHpWidth(100);
				}

				screen.getDialog().sendMessage("You drink the health potion.");
			}
		}
		if(e.getKeyCode() == e.VK_2)
		{
			if(screen.getPotion().getManaPotions() > 0 && screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()) != -1)
			{
				screen.getPotion().decrementManaPotions(1);

				if(screen.getPotion().getManaPotions() <= 0)
				{
					screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(screen.getPotion().getManaPotion()));
				}
				if((screen.getPlayer().getMana() + screen.getPotion().getManaAmount()) <= 100)
				{
					screen.getPlayer().incrementMana(screen.getPotion().getManaAmount());
					screen.getUI().incrementMpWidth(screen.getPotion().getManaAmount());
				}
				else
				{
					screen.getPlayer().setMana(100);
					screen.getUI().setMpWidth(100);
				}

				screen.getDialog().sendMessage("You drink the mana potion.");
			}
		}
	} //end of keyPressed method

	/**
	 * keyReleased method
	 * @param e 
	 */
	@SuppressWarnings("static-access")
	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == e.VK_UP)
		{
			screen.getPlayer().setMovingUp(false);
		}
		if(e.getKeyCode() == e.VK_DOWN)
		{
			screen.getPlayer().setMovingDown(false);
		}
		if(e.getKeyCode() == e.VK_LEFT)
		{
			screen.getPlayer().setMovingLeft(false);
		}
		if(e.getKeyCode() == e.VK_RIGHT)
		{
			screen.getPlayer().setMovingRight(false);
		}
		if(e.getKeyCode() == e.VK_SPACE)
		{
			screen.getCombat().resetAttack();
		}
	} //end of keyReleased method

} //end of KeyHandler class