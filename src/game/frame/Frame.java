package game.frame;

import javax.swing.JFrame;

/**
 * Frame class
 * @author Michael Morgan
 */
public class Frame extends JFrame
{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Frame constructor
	 */
	public Frame()
	{
		this.setTitle("Land of Amaral || By Michael Morgan & Jacob Amaral");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		Screen screen = new Screen(this);
		screen.setFocusable(true);

		this.add(screen);
		this.pack();
		this.setLocationRelativeTo(null);

		this.setVisible(true);

		screen.startMenu();
		screen.start();
	} //end of Frame constructor

} //end of Frame class