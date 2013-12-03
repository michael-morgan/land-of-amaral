package game.event;

import game.frame.Screen;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 * Mouse class
 * @author Michael Morgan
 */
public class Mouse extends MouseAdapter
{
	private Screen screen;

	public static int x, y;

	/**
	 * Mouse constructor
	 * @param screen
	 */
	public Mouse(Screen screen)
	{
		this.screen = screen;
	} //end of Mouse constructor

	/**
	 * mousePressed method
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{	
		x = e.getX();
		y = e.getY();

		if(screen.getMenu())
		{
			if(!screen.getUI().getShowNew() && !screen.getUI().getShowLoad())
			{
				if((e.getX() > 337 && e.getX() < 495) && (e.getY() > 210 && e.getY() < 256))
				{
					screen.getUI().resetMenu();
					screen.getUI().setShowNew(true);
				}
				else if((e.getX() > 337 && e.getX() < 495) && (e.getY() > 278 && e.getY() < 324))
				{
					screen.getUI().resetMenu();
					screen.getUI().setShowLoad(true);
				}
				else if((e.getX() > 337 && e.getX() < 495) && (e.getY() > 348 && e.getY() < 393))
				{
					screen.getUI().resetMenu();
					screen.getUI().setShowOptions(true);
					JOptionPane.showMessageDialog(screen, "Options coming soon!", "Notice", JOptionPane.INFORMATION_MESSAGE);
				}
				else if((e.getX() > 337 && e.getX() < 495) && (e.getY() > 415 && e.getY() < 461))
				{
					screen.getUI().resetMenu();
					screen.getUI().setShowCredits(true);
					JOptionPane.showMessageDialog(screen, "Credits coming soon!", "Notice", JOptionPane.INFORMATION_MESSAGE);
				}
				else if((e.getX() > 337 && e.getX() < 495) && (e.getY() > 481 && e.getY() < 527))
				{
					System.exit(0);
				}
			}
			else if(screen.getUI().getShowNew())
			{
				if((e.getX() > 171 && e.getX() < 316) && (e.getY() > 260 && e.getY() < 461))
				{
					screen.getUI().setClassType("melee");
				}
				else if((e.getX() > 335 && e.getX() < 480) && (e.getY() > 260 && e.getY() < 461))
				{
					screen.getUI().setClassType("range");
				}
				else if((e.getX() > 497 && e.getX() < 642) && (e.getY() > 260 && e.getY() < 461))
				{
					screen.getUI().setClassType("mage");
				}
				if((e.getX() > 324 && e.getX() < 407) && (e.getY() > 507 && e.getY() < 538))
				{
					String name = JOptionPane.showInputDialog(screen, "Enter a character name:");
					if(name != null)
					{
						screen.getUI().setName(name);
					}
				}
				if((e.getX() > 470 && e.getX() < 585) && (e.getY() > 505 && e.getY() < 537))
				{
					if(!screen.getUI().getClassType().equalsIgnoreCase("") &&
							!screen.getUI().getName().equalsIgnoreCase(""))
					{
						screen.getData().newCharacter(screen.getUI().getClassType(), screen.getUI().getName());
						screen.getData().setupCharacter();
						screen.stopMenu();
						screen.getUI().resetMenu();
						screen.startGame();
					}
					else
					{
						JOptionPane.showMessageDialog(screen, "Please choose your class and name.");
					}
				}
				if((e.getX() > 674 && e.getX() < 694) && (e.getY() > 131 && e.getY() < 150))
				{
					screen.getUI().resetMenu();
				}
			}
			else if(screen.getUI().getShowLoad())
			{
				if((e.getX() > 281 && e.getX() < 396) && (e.getY() > 515 && e.getY() < 548))
				{
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory(new File("data/character/"));
					if(chooser.showOpenDialog(screen) == JFileChooser.APPROVE_OPTION)
					{
						screen.getData().loadCharacter(chooser.getSelectedFile());
					}
				}
				else if((e.getX() > 417 && e.getX() < 532) && (e.getY() > 515 && e.getY() < 548))
				{
					screen.stopMenu();
					screen.getUI().resetMenu();
					screen.startGame();
				}
				if((e.getX() > 674 && e.getX() < 694) && (e.getY() > 131 && e.getY() < 150))
				{
					screen.getUI().resetMenu();
				}
			}
		}
		else if(screen.getGame())
		{
			if((e.getX() > 128 && e.getX() < 231) && (e.getY() > 552 && e.getY() < 584))
			{
				screen.getUI().setShowEquipment(false);
				screen.getUI().setShowInventory(true);
			}
			else if((e.getX() > 128 && e.getX() < 231) && (e.getY() > 594 && e.getY() < 626))
			{
				screen.getUI().setShowInventory(false);
				screen.getUI().setShowEquipment(true);
			}
			if((e.getX() > 551 && e.getX() < 655) && (e.getY() > 552 && e.getY() < 584))
			{
				screen.getUI().setShowCharacter(true);
			}
			if((e.getX() > 213 && e.getX() < 232) && (e.getY() > 208 && e.getY() < 228))
			{
				if(screen.getUI().getShowInventory())
				{
					screen.getUI().setShowInventory(false);
				}
				else if(screen.getUI().getShowEquipment())
				{
					screen.getUI().setShowEquipment(false);
				}
			}
			if((e.getX() > 774 && e.getX() < 793) && (e.getY() > 208 && e.getY() < 228))
			{
				if(screen.getUI().getShowCharacter())
				{
					screen.getUI().setShowCharacter(false);
				}
			}

			if(screen.getUI().getShowInventory())
			{
				if(e.getButton() == MouseEvent.BUTTON1)
				{
					if(!(screen.getUI().getInventoryList().size() == 30))
					{
						if(screen.getUI().getInventorySlot(e.getX(), e.getY()) != -1)
						{
							if(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) != screen.getPotion().getHealthPotion()
									&& screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) != screen.getPotion().getManaPotion())
							{
								screen.getEquipment().removeBonus();
								if(screen.getUI().getEquipmentList()[screen.getEquipment().getEquipmentTypeSlot(screen.getEquipment().getEquipmentType(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY()))))] != screen.getEquipment().blank)
								{
									screen.getUI().setInventoryList(screen.getUI().getEquipmentList()[screen.getEquipment().getEquipmentTypeSlot(screen.getEquipment().getEquipmentType(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY()))))]);
								}
								screen.getUI().setEquipmentList(screen.getEquipment().getEquipmentTypeSlot(screen.getEquipment().getEquipmentType(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())))), screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())));
								screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(e.getX(), e.getY()));
								screen.getEquipment().addBonus();
							}
							else
							{
								if(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) == screen.getPotion().getHealthPotion())
								{
									if(screen.getPotion().getHealthPotions() > 0)
									{
										screen.getPotion().decrementHealthPotions(1);
									}
									if(screen.getPotion().getHealthPotions() <= 0)
									{
										screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(e.getX(), e.getY()));
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
								else if(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) == screen.getPotion().getManaPotion())
								{
									if(screen.getPotion().getManaPotions() > 0)
									{
										screen.getPotion().decrementManaPotions(1);
									}
									if(screen.getPotion().getManaPotions() <= 0)
									{
										screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(e.getX(), e.getY()));
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
						}
					}
					else
					{
						screen.getDialog().sendMessage("Can't equip, inventory full!");
					}
				}

				if(e.getButton() == MouseEvent.BUTTON3)
				{
					if(screen.getUI().getInventorySlot(e.getX(), e.getY()) != -1)
					{
						if(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) != screen.getPotion().getHealthPotion()
								&& screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) != screen.getPotion().getManaPotion())
						{
							screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(e.getX(), e.getY()));
						}
						else
						{
							if(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) == screen.getPotion().getHealthPotion())
							{
								if(screen.getPotion().getHealthPotions() > 0)
								{
									screen.getPotion().decrementHealthPotions(1);
								}
								else if(screen.getPotion().getHealthPotions() <= 0)
								{
									screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(e.getX(), e.getY()));
								}

								screen.getDialog().sendMessage("You discard the health potion.");
							}
							else if(screen.getUI().getInventoryList().get(screen.getUI().getInventorySlot(e.getX(), e.getY())) == screen.getPotion().getManaPotion())
							{
								if(screen.getPotion().getManaPotions() > 0)
								{
									screen.getPotion().decrementManaPotions(1);
								}
								else if(screen.getPotion().getManaPotions() <= 0)
								{
									screen.getUI().getInventoryList().remove(screen.getUI().getInventorySlot(e.getX(), e.getY()));
								}

								screen.getDialog().sendMessage("You discard the mana potion.");
							}
						}
					}
				}
			}
			else if(screen.getUI().getShowEquipment())
			{
				if(e.getButton() == MouseEvent.BUTTON3)
				{
					if(!(screen.getUI().getInventoryList().size() == 30))
					{
						if(screen.getUI().getEquipmentSlot(e.getX(), e.getY()) != -1)
						{
							screen.getEquipment().removeBonus();
							screen.getUI().setInventoryList(screen.getUI().getEquipmentList()[screen.getUI().getEquipmentSlot(e.getX(), e.getY())]);
							screen.getUI().setEquipmentList(screen.getUI().getEquipmentSlot(e.getX(), e.getY()), screen.getEquipment().blank);
							screen.getEquipment().addBonus();
						}
					}
					else
					{
						screen.getDialog().sendMessage("Can't unequip, inventory full!");
					}
				}
			}

			if(screen.getUI().getShowCharacter())
			{
				if(screen.getPlayer().getClassType().equalsIgnoreCase("melee"))
				{
					if(screen.getPlayer().getSp() > 0)
					{
						if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 282 && e.getY() < 298))
						{
							screen.getPlayer().incrementStr(1);
							screen.getPlayer().decrementSp(1);
						}
						else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 325 && e.getY() < 340))
						{
							screen.getPlayer().incrementDef(1);
							screen.getPlayer().decrementSp(1);
						}
						else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 365 && e.getY() < 380))
						{
							screen.getPlayer().incrementHp(1);
							screen.getPlayer().decrementSp(1);
						}
					}
					if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 300 && e.getY() < 315) &&
							screen.getPlayer().getStr() != 0)
					{
						screen.getPlayer().decrementStr(1);
						screen.getPlayer().incrementSp(1);
					}
					else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 342 && e.getY() < 357) &&
							screen.getPlayer().getDef() != 0)
					{
						screen.getPlayer().decrementDef(1);
						screen.getPlayer().incrementSp(1);
					}
					else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 382 && e.getY() < 397) &&
							screen.getPlayer().getHp() != 0)
					{
						screen.getPlayer().decrementHp(1);
						screen.getPlayer().incrementSp(1);
					}
				}
				else if(screen.getPlayer().getClassType().equalsIgnoreCase("range"))
				{
					if(screen.getPlayer().getSp() > 0)
					{
						if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 282 && e.getY() < 298))
						{
							screen.getPlayer().incrementAcc(1);
							screen.getPlayer().decrementSp(1);
						}
						else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 325 && e.getY() < 340))
						{
							screen.getPlayer().incrementDis(1);
							screen.getPlayer().decrementSp(1);
						}
						else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 365 && e.getY() < 380))
						{
							screen.getPlayer().incrementHp(1);
							screen.getPlayer().decrementSp(1);
						}
					}
					if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 300 && e.getY() < 315) &&
							screen.getPlayer().getAcc() != 0)
					{
						screen.getPlayer().decrementAcc(1);
						screen.getPlayer().incrementSp(1);
					}
					else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 342 && e.getY() < 357) &&
							screen.getPlayer().getDis() != 0)
					{
						screen.getPlayer().decrementDis(1);
						screen.getPlayer().incrementSp(1);
					}
					else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 382 && e.getY() < 397) &&
							screen.getPlayer().getHp() != 0)
					{
						screen.getPlayer().decrementHp(1);
						screen.getPlayer().incrementSp(1);
					}
				}
				else if(screen.getPlayer().getClassType().equalsIgnoreCase("mage"))
				{
					if(screen.getPlayer().getSp() > 0)
					{
						if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 282 && e.getY() < 298))
						{
							screen.getPlayer().incrementPow(1);
							screen.getPlayer().decrementSp(1);
						}
						else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 325 && e.getY() < 340))
						{
							screen.getPlayer().incrementMpr(1);
							screen.getPlayer().decrementSp(1);
						}
						else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 365 && e.getY() < 380))
						{
							screen.getPlayer().incrementHp(1);
							screen.getPlayer().decrementSp(1);
						}
					}
					if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 300 && e.getY() < 315) &&
							screen.getPlayer().getPow() != 0)
					{
						screen.getPlayer().decrementPow(1);
						screen.getPlayer().incrementSp(1);
					}
					else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 342 && e.getY() < 357) &&
							screen.getPlayer().getMpr() != 0)
					{
						screen.getPlayer().decrementMpr(1);
						screen.getPlayer().incrementSp(1);
					}
					else if((e.getX() > 706 && e.getX() < 721) && (e.getY() > 382 && e.getY() < 397) &&
							screen.getPlayer().getHp() != 0)
					{
						screen.getPlayer().incrementHp(1);
						screen.getPlayer().incrementSp(1);
					}
				}
			}
			if((e.getX() > 13 && e.getX() < 117) && (e.getY() > 552 && e.getY() < 584))
			{
				screen.getData().saveCharacter();
				screen.stopGame();
				screen.startMenu();
			}
			if((e.getX() > 13 && e.getX() < 117) && (e.getY() > 594 && e.getY() < 628))
			{
				JOptionPane.showMessageDialog(screen, "Options coming soon!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			}
			if((e.getX() > 551 && e.getX() < 655) && (e.getY() > 594 && e.getY() < 627))
			{
				JOptionPane.showMessageDialog(screen, "Map coming soon!", "Notice", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	} //end of mousePressed method

} //end of Mouse class