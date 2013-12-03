package game.dialog;

import game.frame.Screen;

import java.util.ArrayList;

/**
 * Dialog class
 * @author Michael Morgan
 */
public class Dialog
{
	private ArrayList<String> dialogs;

	private Screen screen;

	/**
	 * Dialog constructor
	 */
	public Dialog(Screen screen)
	{
		this.screen = screen;

		init();
	} //end of Dialog constructor

	/**
	 * init method
	 */
	private void init()
	{
		dialogs = new ArrayList<>();
		dialogs.add("Welcome " + screen.getPlayer().getName() + " to the Land of Amaral.");
	} //end of init method

	/**
	 * sendMessage method
	 * @param message
	 */
	public void sendMessage(String message)
	{
		if(dialogs.size() < 6)
		{
			dialogs.add(0, message);
		}
		else
		{
			dialogs.remove(5);
			dialogs.add(0, message);
		}
	} //end of sendMessage method

	/**
	 * getDialogs method
	 * @return dialogs
	 */
	public ArrayList<String> getDialogs()
	{
		return dialogs;
	} //end of getDialogs method

} //end of Dialog class