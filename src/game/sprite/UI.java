package game.sprite;

import game.frame.Screen;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * UI class
 * @author Michael Morgan
 */
public class UI
{
	private Screen screen;

	//menu
	private Image menu, menuNew, menuLoad;

	private boolean showNew, showLoad, showOptions, showCredits;

	//menu new
	private String classType, name;

	//game
	private Image UI, hp, mp, exp, inventory, equipment;
	private Image[] character;

	private ArrayList<Image> inventoryList;
	private Image[] equipmentList;
	private int[] inventoryX, inventoryY, equipmentX, equipmentY;

	private boolean showInventory, showEquipment, showCharacter;

	private int hpWidth, mpWidth, expWidth;

	/**
	 * UI constructor
	 * @param screen
	 */
	public UI(Screen screen)
	{
		this.screen = screen;

		init();
	} //end of UI constructor

	/**
	 * init method
	 */
	private void init()
	{
		//menu
		menu = new ImageIcon("res/UI/menu/menu.png").getImage();
		menuNew = new ImageIcon("res/UI/menu/new/menu_new.png").getImage();
		menuLoad = new ImageIcon("res/UI/menu/load/menu_load.png").getImage();

		showNew = false;
		showLoad = false;
		showOptions = false;
		showCredits = false;

		classType = "";
		name = "";

		//game
		UI = new ImageIcon("res/UI/game/UI.png").getImage();
		hp = new ImageIcon("res/UI/game/hp.png").getImage();
		mp = new ImageIcon("res/UI/game/mp.png").getImage();
		exp = new ImageIcon("res/UI/game/exp.png").getImage();
		inventory = new ImageIcon("res/UI/game/inventory.png").getImage();
		equipment = new ImageIcon("res/UI/game/equipment.png").getImage();

		character = new Image[3];
		character[0] = new ImageIcon("res/UI/game/charactermelee.png").getImage();
		character[1] = new ImageIcon("res/UI/game/characterrange.png").getImage();
		character[2] = new ImageIcon("res/UI/game/charactermage.png").getImage();

		showInventory = false;
		showEquipment = false;
		showCharacter = false;

		hpWidth = 100;
		mpWidth = 100;
		expWidth = 0;

		inventoryList = new ArrayList<>();
		inventoryList.add(screen.getPotion().getHealthPotion());
		screen.getPotion().incrementHealthPotions(2);

		if(screen.getDebug())
		{
			inventoryList.add(screen.getEquipment().mageBooks[0]);
			inventoryList.add(screen.getEquipment().mageBooks[1]);
			inventoryList.add(screen.getEquipment().mageBooks[2]);
			inventoryList.add(screen.getEquipment().meleeArmors[0]);
			inventoryList.add(screen.getEquipment().meleeArmors[1]);
			inventoryList.add(screen.getEquipment().meleeArmors[2]);
			inventoryList.add(screen.getEquipment().meleeSwords[0]);
			inventoryList.add(screen.getEquipment().meleeSwords[1]);
			inventoryList.add(screen.getEquipment().meleeSwords[2]);
			inventoryList.add(screen.getEquipment().meleeHelmets[0]);
			inventoryList.add(screen.getEquipment().meleeHelmets[1]);
			inventoryList.add(screen.getEquipment().meleeHelmets[2]);
			inventoryList.add(screen.getEquipment().meleeAmulets[0]);
			inventoryList.add(screen.getEquipment().meleeAmulets[1]);
			inventoryList.add(screen.getEquipment().meleeAmulets[2]);
			inventoryList.add(screen.getEquipment().meleeBoots);
			inventoryList.add(screen.getEquipment().meleeGloves[0]);
			inventoryList.add(screen.getEquipment().meleeGloves[1]);
			inventoryList.add(screen.getEquipment().rangeBows[0]);
			inventoryList.add(screen.getEquipment().rangeBows[1]);
			inventoryList.add(screen.getEquipment().rangeBows[2]);
			inventoryList.add(screen.getEquipment().rangeArrows);
		}

		equipmentList = new Image[8];
		equipmentList[0] = screen.getEquipment().blank;
		equipmentList[1] = screen.getEquipment().blank;
		equipmentList[2] = screen.getEquipment().blank;
		equipmentList[3] = screen.getEquipment().blank;
		equipmentList[4] = screen.getEquipment().blank;
		equipmentList[5] = screen.getEquipment().blank;
		equipmentList[6] = screen.getEquipment().blank;
		equipmentList[7] = screen.getEquipment().blank;

		equipmentX = new int[8];
		equipmentX[0] = 103;
		equipmentX[1] = 103;
		equipmentX[2] = 103;
		equipmentX[3] = 57;
		equipmentX[4] = 143;
		equipmentX[5] = 103;
		equipmentX[6] = 57;
		equipmentX[7] = 57;
		equipmentY = new int[8];
		equipmentY[0] = 249;
		equipmentY[1] = 289;
		equipmentY[2] = 334;
		equipmentY[3] = 334;
		equipmentY[4] = 334;
		equipmentY[5] = 416;
		equipmentY[6] = 416;
		equipmentY[7] = 249;

		inventoryX = new int[5];
		inventoryX[0] = 24;
		inventoryX[1] = 64;
		inventoryX[2] = 104;
		inventoryX[3] = 144;
		inventoryX[4] = 184;
		inventoryY = new int[6];
		inventoryY[0] = 249;
		inventoryY[1] = 290;
		inventoryY[2] = 330;
		inventoryY[3] = 370;
		inventoryY[4] = 411;
		inventoryY[5] = 451;

	} //end of init method

	/**
	 * render method
	 * @param g
	 */
	public void render(Graphics g)
	{
		if(screen.getMenu())
		{
			g.drawImage(menu, 0, 0, menu.getWidth(null), menu.getHeight(null), null);
		}
		else if(screen.getGame())
		{
			g.drawImage(UI, 0, 0, UI.getWidth(null), UI.getHeight(null), null);

			if(hpWidth > 0)
			{
				g.drawImage(hp, 63, 6, hpWidth, hp.getHeight(null), null);
			}
			if(mpWidth > 0)
			{
				g.drawImage(mp, 274, 6, mpWidth, mp.getHeight(null), null);
			}
			if(expWidth > 0)
			{
				g.drawImage(exp, 0, 530, expWidth, exp.getHeight(null), null);
			}

			if(showInventory)
			{
				g.drawImage(inventory, 1, 205, inventory.getWidth(null), inventory.getHeight(null), null);

				int x = 0, y = 0, resets = 0;
				for(int i = 0; i < inventoryList.size(); i++)
				{
					g.drawImage(inventoryList.get(i), inventoryX[x], inventoryY[y], null);
					if(x == 4)
					{
						x = 0;
						resets++;
					}
					else
					{
						x++;
					}
					if(resets == 1)
					{
						y = 1;
					}
					else if(resets == 2)
					{
						y = 2;
					}
					else if(resets == 3)
					{
						y = 3;
					}
					else if(resets == 4)
					{
						y = 4;
					}
					else if(resets == 5)
					{
						y = 5;
					}
				}
			}
			else if(showEquipment)
			{
				g.drawImage(equipment, 1, 205, equipment.getWidth(null), equipment.getHeight(null), null);

				for(int i = 0; i < equipmentList.length; i++)
				{
					g.drawImage(equipmentList[i], equipmentX[i], equipmentY[i], null);
				}
			}
			if(showCharacter)
			{
				if(screen.getPlayer().getClassType().equalsIgnoreCase("melee"))
				{
					g.drawImage(character[0], 562, 205, character[0].getWidth(null), character[0].getHeight(null), null);
				}
				else if(screen.getPlayer().getClassType().equalsIgnoreCase("range"))
				{
					g.drawImage(character[1], 562, 205, character[1].getWidth(null), character[1].getHeight(null), null);
				}
				else if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
				{
					g.drawImage(character[2], 562, 205, character[2].getWidth(null), character[2].getHeight(null), null);
				}
			}
		}
	} //end of render method

	/**
	 * getHpWidth method
	 * @return hpWidth
	 */
	public int getHpWidth()
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
	 * getMpWidth method
	 * @return mpWidth
	 */
	public int getMpWidth()
	{
		return mpWidth;
	} //end of getMpWidth method

	/**
	 * setMpWidth method
	 */
	public void setMpWidth(int amount)
	{
		mpWidth = amount;
	} // end of setMpWidth method

	/**
	 * getExpWidth method
	 * @return expWidth
	 */
	public int getExpWidth()
	{
		return expWidth;
	} // end of getExpWidth method

	/**
	 * decrementHpWidth method
	 * @param amount
	 */
	public void decrementHpWidth(int amount)
	{
		hpWidth -= amount;
	} //end of decrementHpWidth method

	/**
	 * incrementHpWidth method
	 * @param amount
	 */
	public void incrementHpWidth(int amount)
	{
		hpWidth += amount;
	} //end of incrementHpWidth method

	/**
	 * decrementMpWidth method
	 * @param amount
	 */
	public void decrementMpWidth(int amount)
	{
		mpWidth -= amount;
	} //end of decrementMpWidth method

	/**
	 * incrementMpWidth method
	 * @param amount
	 */
	public void incrementMpWidth(int amount)
	{
		mpWidth += amount;
	} //end of incrementMpWidth method

	/**
	 * decrementExpWidth method
	 * @param amount
	 */
	public void decrementExpWidth(int amount)
	{
		expWidth -= amount;
	} //end of decrementExpWidth method

	/**
	 * incrementExpWidth method
	 * @param amount
	 */
	public void incrementExpWidth(int amount)
	{
		expWidth += amount;
	} //end of incrementExpWidth method

	/**
	 * setExpWidth method
	 * @param amount
	 */
	public void setExpWidth(int amount)
	{
		expWidth = amount;
	} //end of setExpWidth method

	/**
	 * getShowInventory method
	 * @return showInventory
	 */
	public boolean getShowInventory()
	{
		return showInventory;
	} //end of getShowInventory method

	/**
	 * getShowEquipment
	 * @return showEquipment
	 */
	public boolean getShowEquipment()
	{
		return showEquipment;
	} //end of getShowEquipment

	/**
	 * getShowCharacter method
	 * @return showCharacter
	 */
	public boolean getShowCharacter()
	{
		return showCharacter;
	} //end of getShowCharacter

	/**
	 * setShowInventory method
	 * @return showInventory
	 */
	public void setShowInventory(boolean answer)
	{
		showInventory = answer;
	} //end of setShowInventory method

	/**
	 * setShowEquipment
	 * @return showEquipment
	 */
	public void setShowEquipment(boolean answer)
	{
		showEquipment = answer;
	} //end of setShowEquipment

	/**
	 * setShowCharacter method
	 * @return showCharacter
	 */
	public void setShowCharacter(boolean answer)
	{
		showCharacter = answer;
	} //end of setShowCharacter

	/**
	 * setShowNew method
	 * @param answer
	 */
	public void setShowNew(boolean answer)
	{
		showNew = answer;
	} //end of setShowNew method

	/**
	 * setShowLoad method
	 * @param answer
	 */
	public void setShowLoad(boolean answer)
	{
		showLoad = answer;
	} //end of setShowLoad method

	/**
	 * setShowOptions method
	 * @param answer
	 */
	public void setShowOptions(boolean answer)
	{
		showOptions = answer;
	} //end of setShowOptions method

	/**
	 * setShowCredits method
	 * @param answer
	 */
	public void setShowCredits(boolean answer)
	{
		showCredits = answer;
	} //end of setShowCredits method

	/**
	 * getShowNew method
	 * @return showNew
	 */
	public boolean getShowNew()
	{
		return showNew;
	} //end of getShowNew method

	/**
	 * getShowLoad method
	 * @return showLoad
	 */
	public boolean getShowLoad()
	{
		return showLoad;
	} //end of getShowLoad method

	/**
	 * getShowOptions method
	 * @return showOptions
	 */
	public boolean getShowOptions()
	{
		return showOptions;
	} //end of getShowOptions method

	/**
	 * getShowCredits method
	 * @return showCredits
	 */
	public boolean getShowCredits()
	{
		return showCredits;
	} //end of getShowCredits method

	/**
	 * resetMenu method
	 */
	public void resetMenu()
	{
		showNew = false;
		showLoad = false;
		showOptions = false;
		showCredits = false;
	} //end of resetMenu method

	/**
	 * getInventoryList method
	 * @return inventoryList
	 */
	public ArrayList<Image> getInventoryList()
	{
		return inventoryList;
	} //end of getInventoryList method

	/**
	 * getEquipmentList method
	 * @return equipmentList
	 */
	public Image[] getEquipmentList()
	{
		return equipmentList;
	} //end of getEquipmentList method

	/**
	 * setInventoryList method
	 */
	public void setInventoryList(Image image)
	{
		inventoryList.add(image);
	} //end of setInventoryList method

	/**
	 * setEquipmentList method
	 */
	public void setEquipmentList(int index, Image image)
	{
		equipmentList[index] = image;
	} //end of setEquipmentList method

	/**
	 * getMenuNew method
	 * @return menuNew
	 */
	public Image getMenuNew()
	{
		return menuNew;
	} //end of getMenuNew method

	/**
	 * getMenuLoad method
	 * @return menuLoad
	 */
	public Image getMenuLoad()
	{
		return menuLoad;
	} //end of getMenuLoad method

	/**
	 * setClassType method
	 * @param classType
	 */
	public void setClassType(String classType)
	{
		this.classType = classType;
	} //end of setClassType method

	/**
	 * getClassType method
	 * @return classType
	 */
	public String getClassType()
	{
		return classType;
	} //end of getClassType method

	/**
	 * setName method
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;
	} //end of setName method

	/**
	 * getName method
	 * @return name
	 */
	public String getName()
	{
		return name;
	} //end of getName method

	/**
	 * getInventorySlot method
	 * @param x
	 * @param y
	 * @return slot number
	 */
	public int getInventorySlot(int x, int y)
	{
		if((x >= 24 && x <= 55) && (y >= 249 && y <= 280) && inventoryList.size() > 0)
		{
			return 0;
		}
		else if((x >= 64 && x <= 95) && (y >= 249 && y <= 280) && inventoryList.size() > 1)
		{
			return 1;
		}
		else if((x >= 104 && x <= 135) && (y >= 249 && y <= 280) && inventoryList.size() > 2)
		{
			return 2;
		}
		else if((x >= 144 && x <= 175) && (y >= 249 && y <= 280) && inventoryList.size() > 3)
		{
			return 3;
		}
		else if((x >= 184 && x <= 215) && (y >= 249 && y <= 280) && inventoryList.size() > 4)
		{
			return 4;
		}
		else if((x >= 24 && x <= 55) && (y >= 290 && y <= 321) && inventoryList.size() > 5)
		{
			return 5;
		}
		else if((x >= 64 && x <= 95) && (y >= 290 && y <= 321) && inventoryList.size() > 6)
		{
			return 6;
		}
		else if((x >= 104 && x <= 135) && (y >= 290 && y <= 321) && inventoryList.size() > 7)
		{
			return 7;
		}
		else if((x >= 144 && x <= 175) && (y >= 290 && y <= 321) && inventoryList.size() > 8)
		{
			return 8;
		}
		else if((x >= 184 && x <= 215) && (y >= 290 && y <= 321) && inventoryList.size() > 9)
		{
			return 9;
		}
		else if((x >= 24 && x <= 55) && (y >= 330 && y <= 361) && inventoryList.size() > 10)
		{
			return 10;
		}
		else if((x >= 64 && x <= 95) && (y >= 330 && y <= 361) && inventoryList.size() > 11)
		{
			return 11;
		}
		else if((x >= 104 && x <= 135) && (y >= 330 && y <= 361) && inventoryList.size() > 12)
		{
			return 12;
		}
		else if((x >= 144 && x <= 175) && (y >= 330 && y <= 361) && inventoryList.size() > 13)
		{
			return 13;
		}
		else if((x >= 184 && x <= 215) && (y >= 330 && y <= 361) && inventoryList.size() > 14)
		{
			return 14;
		}
		else if((x >= 24 && x <= 55) && (y >= 370 && y <= 401) && inventoryList.size() > 15)
		{
			return 15;
		}
		else if((x >= 64 && x <= 95) && (y >= 370 && y <= 401) && inventoryList.size() > 16)
		{
			return 16;
		}
		else if((x >= 104 && x <= 135) && (y >= 370 && y <= 401) && inventoryList.size() > 17)
		{
			return 17;
		}
		else if((x >= 144 && x <= 175) && (y >= 370 && y <= 401) && inventoryList.size() > 18)
		{
			return 18;
		}
		else if((x >= 184 && x <= 215) && (y >= 370 && y <= 401) && inventoryList.size() > 19)
		{
			return 19;
		}
		else if((x >= 24 && x <= 55) && (y >= 411 && y <= 442) && inventoryList.size() > 20)
		{
			return 20;
		}
		else if((x >= 64 && x <= 95) && (y >= 411 && y <= 442) && inventoryList.size() > 21)
		{
			return 21;
		}
		else if((x >= 104 && x <= 135) && (y >= 411 && y <= 442) && inventoryList.size() > 22)
		{
			return 22;
		}
		else if((x >= 144 && x <= 175) && (y >= 411 && y <= 442) && inventoryList.size() > 23)
		{
			return 23;
		}
		else if((x >= 184 && x <= 215) && (y >= 411 && y <= 442) && inventoryList.size() > 24)
		{
			return 24;
		}
		else if((x >= 24 && x <= 55) && (y >= 451 && y <= 482) && inventoryList.size() > 25)
		{
			return 25;
		}
		else if((x >= 64 && x <= 95) && (y >= 451 && y <= 482) && inventoryList.size() > 26)
		{
			return 26;
		}
		else if((x >= 104 && x <= 135) && (y >= 451 && y <= 482) && inventoryList.size() > 27)
		{
			return 27;
		}
		else if((x >= 144 && x <= 175) && (y >= 451 && y <= 482) && inventoryList.size() > 28)
		{
			return 28;
		}
		else if((x >= 184 && x <= 215) && (y >= 451 && y <= 482) && inventoryList.size() > 29)
		{
			return 29;
		}

		return -1;
	} //end of getInventorySlot method

	/**
	 * getInventorySlot method
	 * @param item
	 * @return slot
	 */
	public int getInventorySlot(Image item)
	{
		int slot = -1;

		for(int i = 0; i < screen.getUI().getInventoryList().size(); i++)
		{
			if(screen.getUI().getInventoryList().get(i) == item)
			{
				slot = i; 
			}
		}

		return slot;
	} //end of getInventorySlot method

	/**
	 * getEquipmentSlot method
	 * @param x
	 * @param y
	 * @return slot number
	 */
	public int getEquipmentSlot(int x, int y)
	{
		if((x >= 103 && x <= 134) && (y >= 249 && y <= 280) && 
				screen.getEquipment().getEquipmentType(equipmentList[0]).equalsIgnoreCase("head"))
		{
			return 0;
		}
		else if((x >= 103 && x <= 134) && (y >= 289 && y <= 320) &&
				screen.getEquipment().getEquipmentType(equipmentList[1]).equalsIgnoreCase("neck"))
		{
			return 1;
		}
		else if((x >= 103 && x <= 134) && (y >= 334 && y <= 365) &&
				screen.getEquipment().getEquipmentType(equipmentList[2]).equalsIgnoreCase("body"))
		{
			return 2;
		}
		else if((x >= 57 && x <= 88) && (y >= 334 && y <= 365) &&
				screen.getEquipment().getEquipmentType(equipmentList[3]).equalsIgnoreCase("primary"))
		{
			return 3;
		}
		else if((x >= 143 && x <= 174) && (y >= 334 && y <= 365) &&
				screen.getEquipment().getEquipmentType(equipmentList[4]).equalsIgnoreCase("secondary"))
		{
			return 4;
		}
		else if((x >= 103 && x <= 134) && (y >= 416 && y <= 447) &&
				screen.getEquipment().getEquipmentType(equipmentList[5]).equalsIgnoreCase("feet"))
		{
			return 5;
		}
		else if((x >= 57 && x <= 88) && (y >= 416 && y <= 447) &&
				screen.getEquipment().getEquipmentType(equipmentList[6]).equalsIgnoreCase("hand"))
		{
			return 6;
		}
		else if((x >= 57 && x <= 88) && (y >= 249 && y <= 280) &&
				screen.getEquipment().getEquipmentType(equipmentList[7]).equalsIgnoreCase("ammo"))
		{
			return 7;
		}

		return -1;
	} //end of getEquipmentSlot method

} //end of UI class