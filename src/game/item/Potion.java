package game.item;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Potion class
 * @author Michael Morgan
 */
public class Potion
{
	private Image healthPotion, manaPotion;

	private int healthAmount, manaAmount, healthPotions, manaPotions;

	/**
	 * Potion constructor
	 */
	public Potion()
	{
		init();
	} //end of Potion constructor

	/**
	 * init method
	 */
	private void init()
	{
		healthPotion = new ImageIcon("res/player/item/hppotion.png").getImage();
		manaPotion = new ImageIcon("res/player/item/manapotion.png").getImage();

		healthAmount = 25;
		manaAmount = 50;
		healthPotions = 0;
		manaPotions = 0;
	} //end of init method

	/**
	 * getHealthPotion method
	 * @return healthPotion
	 */
	public Image getHealthPotion()
	{
		return healthPotion;
	} //end of getHealthPotion method

	/**
	 * getManaPotion method
	 * @return manaPotion
	 */
	public Image getManaPotion()
	{
		return manaPotion;
	} //end of getManaPotion method

	/**
	 * incrementHealthPotions method
	 * @param amount
	 */
	public void incrementHealthPotions(int amount)
	{
		healthPotions += amount;
	} //end of incrementHealthPotions method

	/**
	 * incrementManaPotions method
	 * @param amount
	 */
	public void incrementManaPotions(int amount)
	{
		manaPotions += amount;
	} //end of incrementManaPotions method

	/**
	 * decrementHealthPotions method
	 * @param amount
	 */
	public void decrementHealthPotions(int amount)
	{
		if(healthPotions > 0)
		{
			healthPotions -= amount;
		}
	} //end of decrementHealthPotions method

	/**
	 * decrementManaPotions method
	 * @param amount
	 */
	public void decrementManaPotions(int amount)
	{
		if(manaPotions > 0)
		{
			manaPotions -= amount;
		}
	} //end of decrementManaPotions method

	/**
	 * getHealthPotions method
	 * @return healthPotions
	 */
	public int getHealthPotions()
	{
		return healthPotions;
	} //end of getHealthPotions method

	/**
	 * getManaPotions method
	 * @return manaPotions
	 */
	public int getManaPotions()
	{
		return manaPotions;
	} //end of getManaPotions method

	/**
	 * getHealthAmount method
	 * @return
	 */
	public int getHealthAmount()
	{
		return healthAmount;
	} //end of getHealthAmount method

	/**
	 * getManaAmount method
	 * @return manaAmount
	 */
	public int getManaAmount()
	{
		return manaAmount;
	} //end of getManaAmount method

} //end of Potion class