package game.item;

import game.frame.Screen;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Equipment class
 * @author Michael Morgan
 */
public class Equipment
{
	public Image mageBoots;
	public Image[] mageBooks, mageGloves, mageStaffs, mageAmulets, mageCloaks, mageHats;

	public Image meleeBoots;
	public Image[] meleeShields, meleeGloves, meleeSwords, meleeAmulets, meleeArmors, meleeHelmets;

	public Image rangeBoots, rangeCoifs, rangeArmors, rangeArrows;
	public Image[] rangeGloves, rangeBows, rangeAmulets;

	public Image blank;

	private Screen screen;

	/**
	 * Equipment constructor
	 */
	public Equipment(Screen screen)
	{
		this.screen = screen;

		init();
	} //end of Equipment constructor

	/**
	 * init method
	 */
	private void init()
	{
		mageBoots = new ImageIcon("res/player/equipment/mage/boots.png").getImage();
		mageBooks = new Image[3];
		mageBooks[0] = new ImageIcon("res/player/equipment/mage/book1.png").getImage();
		mageBooks[1] = new ImageIcon("res/player/equipment/mage/book2.png").getImage();
		mageBooks[2] = new ImageIcon("res/player/equipment/mage/book3.png").getImage();
		mageGloves = new Image[2];
		mageGloves[0] = new ImageIcon("res/player/equipment/mage/gloves1.png").getImage();
		mageGloves[1] = new ImageIcon("res/player/equipment/mage/gloves2.png").getImage();
		mageStaffs = new Image[3];
		mageStaffs[0] = new ImageIcon("res/player/equipment/mage/staff1.png").getImage();
		mageStaffs[1] = new ImageIcon("res/player/equipment/mage/staff2.png").getImage();
		mageStaffs[2] = new ImageIcon("res/player/equipment/mage/staff3.png").getImage();
		mageAmulets = new Image[3];
		mageAmulets[0] = new ImageIcon("res/player/equipment/mage/amuletofagility.png").getImage();
		mageAmulets[1] = new ImageIcon("res/player/equipment/mage/amuletofmana.png").getImage();
		mageAmulets[2] = new ImageIcon("res/player/equipment/mage/amuletofpower.png").getImage();
		mageCloaks = new Image[3];
		mageCloaks[0] = new ImageIcon("res/player/equipment/mage/cloak1.png").getImage();
		mageCloaks[1] = new ImageIcon("res/player/equipment/mage/cloak2.png").getImage();
		mageCloaks[2] = new ImageIcon("res/player/equipment/mage/cloak3.png").getImage();
		mageHats = new Image[3];
		mageHats[0] = new ImageIcon("res/player/equipment/mage/hat1.png").getImage();
		mageHats[1] = new ImageIcon("res/player/equipment/mage/hat2.png").getImage();
		mageHats[2] = new ImageIcon("res/player/equipment/mage/hat3.png").getImage();

		meleeBoots = new ImageIcon("res/player/equipment/melee/boots.png").getImage();
		meleeShields = new Image[3];
		meleeShields[0] = new ImageIcon("res/player/equipment/melee/shield1.png").getImage();
		meleeShields[1] = new ImageIcon("res/player/equipment/melee/shield2.png").getImage();
		meleeShields[2] = new ImageIcon("res/player/equipment/melee/shield3.png").getImage();
		meleeGloves = new Image[2];
		meleeGloves[0] = new ImageIcon("res/player/equipment/melee/gloves1.png").getImage();
		meleeGloves[1] = new ImageIcon("res/player/equipment/melee/gloves2.png").getImage();
		meleeSwords = new Image[3];
		meleeSwords[0] = new ImageIcon("res/player/equipment/melee/sword.png").getImage();
		meleeSwords[1] = new ImageIcon("res/player/equipment/melee/halberd.png").getImage();
		meleeSwords[2] = new ImageIcon("res/player/equipment/melee/dragonsword.png").getImage();
		meleeAmulets = new Image[3];
		meleeAmulets[0] = new ImageIcon("res/player/equipment/melee/amuletofdefence.png").getImage();
		meleeAmulets[1] = new ImageIcon("res/player/equipment/melee/amuletofhp.png").getImage();
		meleeAmulets[2] = new ImageIcon("res/player/equipment/melee/amuletofpower.png").getImage();
		meleeArmors = new Image[3];
		meleeArmors[0] = new ImageIcon("res/player/equipment/melee/armor1.png").getImage();
		meleeArmors[1] = new ImageIcon("res/player/equipment/melee/armor2.png").getImage();
		meleeArmors[2] = new ImageIcon("res/player/equipment/melee/armor3.png").getImage();
		meleeHelmets = new Image[3];
		meleeHelmets[0] = new ImageIcon("res/player/equipment/melee/helmet1.png").getImage();
		meleeHelmets[1] = new ImageIcon("res/player/equipment/melee/helmet2.png").getImage();
		meleeHelmets[2] = new ImageIcon("res/player/equipment/melee/helmet3.png").getImage();

		rangeBoots = new ImageIcon("res/player/equipment/range/boots.png").getImage();
		rangeCoifs = new ImageIcon("res/player/equipment/range/helmet1.png").getImage();
		rangeArmors = new ImageIcon("res/player/equipment/range/armor1.png").getImage();
		rangeArrows = new ImageIcon("res/player/equipment/range/arrow.png").getImage();
		rangeGloves = new Image[2];
		rangeGloves[0] = new ImageIcon("res/player/equipment/range/gloves1.png").getImage();
		rangeGloves[1] = new ImageIcon("res/player/equipment/range/gloves2.png").getImage();
		rangeBows = new Image[3];
		rangeBows[0] = new ImageIcon("res/player/equipment/range/longbow1.png").getImage();
		rangeBows[1] = new ImageIcon("res/player/equipment/range/longbow2.png").getImage();
		rangeBows[2] = new ImageIcon("res/player/equipment/range/crossbow.png").getImage();
		rangeAmulets = new Image[3];
		rangeAmulets[0] = new ImageIcon("res/player/equipment/range/amuletofaccuracy.png").getImage();
		rangeAmulets[1] = new ImageIcon("res/player/equipment/range/amuletofhp.png").getImage();
		rangeAmulets[2] = new ImageIcon("res/player/equipment/range/amuletofpower.png").getImage();

		blank = new ImageIcon("res/player/equipment/blank.png").getImage();
	} //end of init method

	/**
	 * getEquipmentType method
	 * @param slot
	 * @return String type
	 */
	public String getEquipmentType(Image slot)
	{
		if(slot.equals(mageHats[0]) || slot.equals(mageHats[1]) || slot.equals(mageHats[2]) ||
				slot.equals(meleeHelmets[0]) || slot.equals(meleeHelmets[1]) || slot.equals(meleeHelmets[2]) ||
				slot.equals(rangeCoifs))
		{
			return "head";
		}
		else if(slot.equals(mageAmulets[0]) || slot.equals(mageAmulets[1]) || slot.equals(mageAmulets[2]) ||
				slot.equals(meleeAmulets[0]) || slot.equals(meleeAmulets[1]) || slot.equals(meleeAmulets[2]) ||
				slot.equals(rangeAmulets[0]) || slot.equals(rangeAmulets[1]) || slot.equals(rangeAmulets[2]))
		{
			return "neck";
		}
		else if(slot.equals(mageCloaks[0]) || slot.equals(mageCloaks[1]) || slot.equals(mageCloaks[2]) ||
				slot.equals(meleeArmors[0]) || slot.equals(meleeArmors[1]) || slot.equals(meleeArmors[2]) ||
				slot.equals(rangeArmors))
		{
			return "body";
		}
		else if(slot.equals(mageStaffs[0]) || slot.equals(mageStaffs[1]) || slot.equals(mageStaffs[2]) ||
				slot.equals(meleeSwords[0]) || slot.equals(meleeSwords[1]) || slot.equals(meleeSwords[2]) ||
				slot.equals(rangeBows[0]) || slot.equals(rangeBows[1]) || slot.equals(rangeBows[2]))
		{
			return "primary";
		}
		else if(slot.equals(mageBooks[0]) || slot.equals(mageBooks[1]) || slot.equals(mageBooks[2]) ||
				slot.equals(meleeShields[0]) || slot.equals(meleeShields[1]) || slot.equals(meleeShields[2]))
		{
			return "secondary";
		}
		else if(slot.equals(mageBoots) || slot.equals(meleeBoots) || slot.equals(rangeBoots))
		{
			return "feet";
		}
		else if(slot.equals(mageGloves[0]) || slot.equals(mageGloves[1]) ||
				slot.equals(meleeGloves[0]) || slot.equals(meleeGloves[1]) ||
				slot.equals(rangeGloves[0]) || slot.equals(rangeGloves[1]))
		{
			return "hand";
		}
		else if(slot.equals(rangeArrows))
		{
			return "ammo";
		}

		return "";
	} //end of getEquipmentType method

	/**
	 * getEquipmentTypeSlot method
	 * @param equipmentType
	 * @return int type slot
	 */
	public int getEquipmentTypeSlot(String equipmentType)
	{
		if(equipmentType.equalsIgnoreCase("head"))
		{
			return 0;
		}
		else if(equipmentType.equalsIgnoreCase("neck"))
		{
			return 1;
		}
		else if(equipmentType.equalsIgnoreCase("body"))
		{
			return 2;
		}
		else if(equipmentType.equalsIgnoreCase("primary"))
		{
			return 3;
		}
		else if(equipmentType.equalsIgnoreCase("secondary"))
		{
			return 4;
		}
		else if(equipmentType.equalsIgnoreCase("feet"))
		{
			return 5;
		}
		else if(equipmentType.equalsIgnoreCase("hand"))
		{
			return 6;
		}
		else if(equipmentType.equalsIgnoreCase("ammo"))
		{
			return 7;
		}

		return -1;
	} //end of getEquipmentTypeSlot method

	/**
	 * addBonus method
	 */
	public void addBonus()
	{
		if(screen.getUI().getEquipmentList()[0] == meleeHelmets[0])
		{
			screen.getPlayer().addDefBonus(0.3);
			screen.getPlayer().addHpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[0] == meleeHelmets[1])
		{
			screen.getPlayer().addDefBonus(0.6);
			screen.getPlayer().addHpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[0] == meleeHelmets[2])
		{
			screen.getPlayer().addDefBonus(0.9);
			screen.getPlayer().addHpBonus(0.9);
			screen.getPlayer().addSpdBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[0] == mageHats[0])
		{
			screen.getPlayer().addSpdBonus(0.3);
			screen.getPlayer().addMpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[0] == mageHats[1])
		{
			screen.getPlayer().addSpdBonus(0.6);
			screen.getPlayer().addMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[0] == mageHats[2])
		{
			screen.getPlayer().addSpdBonus(0.9);
			screen.getPlayer().addMpBonus(0.9);
			screen.getPlayer().addDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[0] == rangeCoifs)
		{
			screen.getPlayer().addSpdBonus(0.6);
			screen.getPlayer().addHpBonus(0.6);
		}
		if(screen.getUI().getEquipmentList()[1] == mageAmulets[0])
		{
			screen.getPlayer().addHpBonus(0.3);
			screen.getPlayer().addMpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == mageAmulets[1])
		{
			screen.getPlayer().addHpBonus(0.6);
			screen.getPlayer().addMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == mageAmulets[2])
		{
			screen.getPlayer().addHpBonus(0.9);
			screen.getPlayer().addMpBonus(0.9);
			screen.getPlayer().addDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[0])
		{
			screen.getPlayer().addSpdBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[1])
		{
			screen.getPlayer().addSpdBonus(0.6);
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[2])
		{
			screen.getPlayer().addSpdBonus(0.9);
			screen.getPlayer().addDmgBonus(0.9);
			screen.getPlayer().addHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[1] == meleeAmulets[0])
		{
			screen.getPlayer().addHpBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == meleeAmulets[1])
		{
			screen.getPlayer().addHpBonus(0.6);
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == meleeAmulets[2])
		{
			screen.getPlayer().addSpdBonus(0.3);
			screen.getPlayer().addDmgBonus(0.9);
			screen.getPlayer().addHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[0])
		{
			screen.getPlayer().addSpdBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[1])
		{
			screen.getPlayer().addSpdBonus(0.6);
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[2])
		{
			screen.getPlayer().addSpdBonus(0.9);
			screen.getPlayer().addDmgBonus(0.9);
			screen.getPlayer().addHpBonus(0.9);
		}
		if(screen.getUI().getEquipmentList()[2] == mageCloaks[0])
		{
			screen.getPlayer().addMpBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[2] == mageCloaks[1])
		{
			screen.getPlayer().addMpBonus(0.6);
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[2] == mageCloaks[2])
		{
			screen.getPlayer().addMpBonus(0.9);
			screen.getPlayer().addDmgBonus(0.9);
			screen.getPlayer().addHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[2] == meleeArmors[0])
		{
			screen.getPlayer().addHpBonus(0.3);
			screen.getPlayer().addDefBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[2] == meleeArmors[1])
		{
			screen.getPlayer().addHpBonus(0.6);
			screen.getPlayer().addDefBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[2] == meleeArmors[2])
		{
			screen.getPlayer().addDefBonus(0.9);
			screen.getPlayer().addDmgBonus(0.3);
			screen.getPlayer().addHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[2] == rangeArmors)
		{
			screen.getPlayer().addSpdBonus(0.6);
			screen.getPlayer().addDmgBonus(0.6);
		}
		if(screen.getUI().getEquipmentList()[3] == mageStaffs[0])
		{
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == mageStaffs[1])
		{
			screen.getPlayer().addDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[3] == mageStaffs[2])
		{
			screen.getPlayer().addDmgBonus(1.2);
			screen.getPlayer().addMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == meleeSwords[0])
		{
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == meleeSwords[1])
		{
			screen.getPlayer().addDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[3] == meleeSwords[2])
		{
			screen.getPlayer().addDmgBonus(1.2);
			screen.getPlayer().addHpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == rangeBows[0])
		{
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == rangeBows[1])
		{
			screen.getPlayer().addDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[3] == rangeBows[2])
		{
			screen.getPlayer().addDmgBonus(1.2);
			screen.getPlayer().addSpdBonus(0.3);
		}
		if(screen.getUI().getEquipmentList()[4] == mageBooks[0])
		{
			screen.getPlayer().addMpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[4] == mageBooks[1])
		{
			screen.getPlayer().addMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[4] == mageBooks[2])
		{
			screen.getPlayer().addMpBonus(0.9);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[4] == meleeShields[0])
		{
			screen.getPlayer().addDefBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[4] == meleeShields[1])
		{
			screen.getPlayer().addDefBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[4] == meleeShields[2])
		{
			screen.getPlayer().addDefBonus(0.9);
			screen.getPlayer().addHpBonus(0.3);
		}
		if(screen.getUI().getEquipmentList()[5] == mageBoots)
		{
			screen.getPlayer().addMpBonus(0.3);
			screen.getPlayer().addSpdBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[5] == rangeBoots)
		{
			screen.getPlayer().addSpdBonus(0.6);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[5] == meleeBoots)
		{
			screen.getPlayer().addSpdBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		if(screen.getUI().getEquipmentList()[6] == mageGloves[0])
		{
			screen.getPlayer().addMpBonus(0.3);
			screen.getPlayer().addDefBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[6] == mageGloves[1])
		{
			screen.getPlayer().addMpBonus(0.6);
			screen.getPlayer().addDefBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[6] == rangeGloves[0])
		{
			screen.getPlayer().addDefBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[6] == rangeGloves[1])
		{
			screen.getPlayer().addDefBonus(0.6);
			screen.getPlayer().addDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[6] == meleeGloves[0])
		{
			screen.getPlayer().addDefBonus(0.3);
			screen.getPlayer().addDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[6] == meleeGloves[1])
		{
			screen.getPlayer().addDefBonus(0.6);
			screen.getPlayer().addDmgBonus(0.6);
		}
	} //end of addBonus method

	/**
	 * removeBonus method
	 */
	public void removeBonus()
	{
		if(screen.getUI().getEquipmentList()[0] == meleeHelmets[0])
		{
			screen.getPlayer().removeDefBonus(0.3);
			screen.getPlayer().removeHpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[0] == meleeHelmets[1])
		{
			screen.getPlayer().removeDefBonus(0.6);
			screen.getPlayer().removeHpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[0] == meleeHelmets[2])
		{
			screen.getPlayer().removeDefBonus(0.9);
			screen.getPlayer().removeHpBonus(0.9);
			screen.getPlayer().removeSpdBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[0] == mageHats[0])
		{
			screen.getPlayer().removeSpdBonus(0.3);
			screen.getPlayer().removeMpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[0] == mageHats[1])
		{
			screen.getPlayer().removeSpdBonus(0.6);
			screen.getPlayer().removeMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[0] == mageHats[2])
		{
			screen.getPlayer().removeSpdBonus(0.9);
			screen.getPlayer().removeMpBonus(0.9);
			screen.getPlayer().removeDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[0] == rangeCoifs)
		{
			screen.getPlayer().removeSpdBonus(0.6);
			screen.getPlayer().removeHpBonus(0.6);
		}
		if(screen.getUI().getEquipmentList()[1] == mageAmulets[0])
		{
			screen.getPlayer().removeHpBonus(0.3);
			screen.getPlayer().removeMpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == mageAmulets[1])
		{
			screen.getPlayer().removeHpBonus(0.6);
			screen.getPlayer().removeMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == mageAmulets[2])
		{
			screen.getPlayer().removeHpBonus(0.9);
			screen.getPlayer().removeMpBonus(0.9);
			screen.getPlayer().removeDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[0])
		{
			screen.getPlayer().removeSpdBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[1])
		{
			screen.getPlayer().removeSpdBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[2])
		{
			screen.getPlayer().removeSpdBonus(0.9);
			screen.getPlayer().removeDmgBonus(0.9);
			screen.getPlayer().removeHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[1] == meleeAmulets[0])
		{
			screen.getPlayer().removeHpBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == meleeAmulets[1])
		{
			screen.getPlayer().removeHpBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == meleeAmulets[2])
		{
			screen.getPlayer().removeSpdBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.9);
			screen.getPlayer().removeHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[0])
		{
			screen.getPlayer().removeSpdBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[1])
		{
			screen.getPlayer().removeSpdBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[1] == rangeAmulets[2])
		{
			screen.getPlayer().removeSpdBonus(0.9);
			screen.getPlayer().removeDmgBonus(0.9);
			screen.getPlayer().removeHpBonus(0.9);
		}
		if(screen.getUI().getEquipmentList()[2] == mageCloaks[0])
		{
			screen.getPlayer().removeMpBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[2] == mageCloaks[1])
		{
			screen.getPlayer().removeMpBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[2] == mageCloaks[2])
		{
			screen.getPlayer().removeMpBonus(0.9);
			screen.getPlayer().removeDmgBonus(0.9);
			screen.getPlayer().removeHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[2] == meleeArmors[0])
		{
			screen.getPlayer().removeHpBonus(0.3);
			screen.getPlayer().removeDefBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[2] == meleeArmors[1])
		{
			screen.getPlayer().removeHpBonus(0.6);
			screen.getPlayer().removeDefBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[2] == meleeArmors[2])
		{
			screen.getPlayer().removeDefBonus(0.9);
			screen.getPlayer().removeDmgBonus(0.3);
			screen.getPlayer().removeHpBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[2] == rangeArmors)
		{
			screen.getPlayer().removeSpdBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.6);
		}
		if(screen.getUI().getEquipmentList()[3] == mageStaffs[0])
		{
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == mageStaffs[1])
		{
			screen.getPlayer().removeDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[3] == mageStaffs[2])
		{
			screen.getPlayer().removeDmgBonus(1.2);
			screen.getPlayer().removeMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == meleeSwords[0])
		{
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == meleeSwords[1])
		{
			screen.getPlayer().removeDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[3] == meleeSwords[2])
		{
			screen.getPlayer().removeDmgBonus(1.2);
			screen.getPlayer().removeHpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == rangeBows[0])
		{
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[3] == rangeBows[1])
		{
			screen.getPlayer().removeDmgBonus(0.9);
		}
		else if(screen.getUI().getEquipmentList()[3] == rangeBows[2])
		{
			screen.getPlayer().removeDmgBonus(1.2);
			screen.getPlayer().removeSpdBonus(0.3);
		}
		if(screen.getUI().getEquipmentList()[4] == mageBooks[0])
		{
			screen.getPlayer().removeMpBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[4] == mageBooks[1])
		{
			screen.getPlayer().removeMpBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[4] == mageBooks[2])
		{
			screen.getPlayer().removeMpBonus(0.9);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[4] == meleeShields[0])
		{
			screen.getPlayer().removeDefBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[4] == meleeShields[1])
		{
			screen.getPlayer().removeDefBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[4] == meleeShields[2])
		{
			screen.getPlayer().removeDefBonus(0.9);
			screen.getPlayer().removeHpBonus(0.3);
		}
		if(screen.getUI().getEquipmentList()[5] == mageBoots)
		{
			screen.getPlayer().removeMpBonus(0.3);
			screen.getPlayer().removeSpdBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[5] == rangeBoots)
		{
			screen.getPlayer().removeSpdBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[5] == meleeBoots)
		{
			screen.getPlayer().removeSpdBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		if(screen.getUI().getEquipmentList()[6] == mageGloves[0])
		{
			screen.getPlayer().removeMpBonus(0.3);
			screen.getPlayer().removeDefBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[6] == mageGloves[1])
		{
			screen.getPlayer().removeMpBonus(0.6);
			screen.getPlayer().removeDefBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[6] == rangeGloves[0])
		{
			screen.getPlayer().removeDefBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[6] == rangeGloves[1])
		{
			screen.getPlayer().removeDefBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.6);
		}
		else if(screen.getUI().getEquipmentList()[6] == meleeGloves[0])
		{
			screen.getPlayer().removeDefBonus(0.3);
			screen.getPlayer().removeDmgBonus(0.3);
		}
		else if(screen.getUI().getEquipmentList()[6] == meleeGloves[1])
		{
			screen.getPlayer().removeDefBonus(0.6);
			screen.getPlayer().removeDmgBonus(0.6);
		}
	} //end of removeBonus method

} //end of Equipment class