package game.player.data;

import game.frame.Screen;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Data class
 * @author Michael Morgan
 */
public class Data
{
	private Screen screen;

	private int health, mana, level, exp, pow, mpr, str, def, acc, dis, hp, sp, map;

	private double dmgBonus, hpBonus, defBonus, mpBonus, spdBonus;

	private String classType, name;

	/**
	 * Data constructor
	 * @param screen
	 */
	public Data(Screen screen)
	{
		this.screen = screen;

		init();
	} //end of Data constructor

	/**
	 * init method
	 */
	private void init()
	{
		health = 0;
		mana = 0;
		level = 0;
		exp = 0;
		map = 1;

		pow = 0;
		mpr = 0;

		str = 0;
		def = 0;

		acc = 0;
		dis = 0;

		hp = 0;
		sp = 0;

		dmgBonus = 0;
		hpBonus = 0;
		defBonus = 0;
		mpBonus = 0;
		spdBonus = 0;

		classType = "";
		name = "";
	} //end of init method

	/**
	 * newCharacter method
	 * @param classType
	 * @param name
	 */
	public void newCharacter(String classType, String name)
	{
		this.classType = classType;
		this.name = name;

		health = 100;
		mana = 100;
		level = 1;
		exp = 0;
		map = 1;

		sp = 2;
		hp = 0;

		if(classType.equalsIgnoreCase("mage"))
		{
			pow = 8;
			mpr = 0;
		}
		else if(classType.equalsIgnoreCase("melee"))
		{
			str = 5;
			def = 0;
		}
		else if(classType.equalsIgnoreCase("range"))
		{
			acc = 7;
			dis = 250;
		}

		dmgBonus = 0.0;
		defBonus = 0.0;
		hpBonus = 0.0;
		mpBonus = 0.0;
		spdBonus = 0.0;
	} //end of newCharacter method

	/**
	 * loadCharacter method
	 * @param account
	 */
	public void loadCharacter(File account)
	{	
		try(Scanner scanner = new Scanner(account))
		{
			String[] values = new String[22];
			String value = "";
			int i = 0;
			while(i < 22)
			{
				scanner.nextLine();

				value = scanner.next();
				values[i] = value.substring(value.indexOf(':') + 1, value.length());

				i++;
			}

			screen.getPlayer().setClassType(values[0]);
			screen.getPlayer().setPlayerImage(values[0]);
			screen.getPlayer().setName(values[1]);
			screen.getPlayer().setHealth(Integer.parseInt(values[2]));
			screen.getPlayer().setMana(Integer.parseInt(values[3]));
			screen.getPlayer().setLevel(Integer.parseInt(values[4]));
			screen.getPlayer().setExp(Integer.parseInt(values[5]));
			screen.getPlayer().setSp(Integer.parseInt(values[6]));
			screen.getPlayer().setHp(Integer.parseInt(values[7]));
			screen.getPlayer().setStr(Integer.parseInt(values[8]));
			screen.getPlayer().setDef(Integer.parseInt(values[9]));
			screen.getPlayer().setAcc(Integer.parseInt(values[10]));
			screen.getPlayer().setDis(Integer.parseInt(values[11]));
			screen.getPlayer().setPow(Integer.parseInt(values[12]));
			screen.getPlayer().setMpr(Integer.parseInt(values[13]));
			screen.getPlayer().setDmgBonus(Double.parseDouble(values[14]));
			screen.getPlayer().setDefBonus(Double.parseDouble(values[15]));
			screen.getPlayer().setHpBonus(Double.parseDouble(values[16]));
			screen.getPlayer().setMpBonus(Double.parseDouble(values[17]));
			screen.getPlayer().setSpdBonus(Double.parseDouble(values[18]));
			screen.getPlayer().setMap(Integer.parseInt(values[19]));
			screen.getMap().setMapX(Integer.parseInt(values[20]));
			screen.getMap().setMapY(Integer.parseInt(values[21]));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} //end of loadCharacter method

	/**
	 * saveCharacter method
	 */
	public void saveCharacter()
	{
		File account = new File("data/character/" + screen.getPlayer().getName() + ".txt");
		try(PrintWriter writer = new PrintWriter(account))
		{
			writer.println("[CHARACTER]");
			writer.println("class:" + screen.getPlayer().getClassType());
			writer.println("name:" + screen.getPlayer().getName());
			writer.println("health:" + screen.getPlayer().getHealth());
			writer.println("mana:" + screen.getPlayer().getMana());
			writer.println("level:" + screen.getPlayer().getLevel());
			writer.println("exp:" + screen.getPlayer().getExp());
			writer.println("sp:" + screen.getPlayer().getSp());
			writer.println("hp:" + screen.getPlayer().getHp());
			writer.println("str:" + screen.getPlayer().getStr());
			writer.println("def:" + screen.getPlayer().getDef());
			writer.println("acc:" + screen.getPlayer().getAcc());
			writer.println("dis:" + screen.getPlayer().getDis());
			writer.println("pow:" + screen.getPlayer().getPow());
			writer.println("mpr:" + screen.getPlayer().getMpr());
			writer.println("dmgbonus:" + screen.getPlayer().getDmgBonus());
			writer.println("defbonus:" + screen.getPlayer().getDefBonus());
			writer.println("hpbonus:" + screen.getPlayer().getHpBonus());
			writer.println("mpbonus:" + screen.getPlayer().getMpBonus());
			writer.println("spdbonus:" + screen.getPlayer().getSpdBonus());
			writer.println("map:" + screen.getPlayer().getMap());
			writer.println("x:" + screen.getMap().getX());
			writer.println("y:" + screen.getMap().getY());
			writer.println("[END]");
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} //end of saveCharacter method

	/**
	 * setupCharacter method
	 */
	public void setupCharacter()
	{
		screen.getPlayer().setClassType(classType);
		screen.getPlayer().setPlayerImage(classType);
		screen.getPlayer().setName(name);

		screen.getPlayer().setHealth(health);
		screen.getPlayer().setMana(mana);
		screen.getPlayer().setLevel(level);
		screen.getPlayer().setExp(exp);
		screen.getPlayer().setMap(map);

		screen.getPlayer().setSp(sp);
		screen.getPlayer().setHp(hp);

		if(classType.equalsIgnoreCase("mage"))
		{
			screen.getPlayer().setPow(pow);
			screen.getPlayer().setMpr(mpr);
		}
		else if(classType.equalsIgnoreCase("melee"))
		{
			screen.getPlayer().setStr(str);
			screen.getPlayer().setDef(def);
		}
		else if(classType.equalsIgnoreCase("range"))
		{
			screen.getPlayer().setAcc(acc);
			screen.getPlayer().setDis(dis);
		}

		screen.getPlayer().setDmgBonus(dmgBonus);
		screen.getPlayer().setDefBonus(defBonus);
		screen.getPlayer().setHpBonus(hpBonus);
		screen.getPlayer().setMpBonus(mpBonus);
		screen.getPlayer().setSpdBonus(spdBonus);
	} //end of setupCharacter method

} //end of Data class