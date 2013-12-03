package game.audio;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sound class
 * @author Michael Morgan
 */
public class Sound
{
	private AudioInputStream audioInputStream;
	private Clip clip;
	private boolean soundOn;

	/**
	 * Sound contructor
	 */
	public Sound()
	{
		init();
	} //end of Sound constructor

	/**
	 * init method
	 */
	private void init()
	{
		initVariables();

		playSound();
	} //end of init method

	/**
	 * initVariables method
	 */
	private void initVariables()
	{
		try
		{
			audioInputStream = AudioSystem.getAudioInputStream(new File("res/horror.wav").getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		soundOn = false;
	} //end of initVariables method

	/**
	 * playSound method
	 */
	public void playSound()
	{
		soundOn = true;

		try
		{
			clip.loop(-1);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} //end of playSound method

	/**
	 * stopSound method
	 */
	public void stopSound()
	{
		soundOn = false;

		try
		{
			clip.stop();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	} //end of stopSound method

	/**
	 * getSoundOn method
	 * @return soundOn
	 */
	public boolean getSoundOn()
	{
		return soundOn;
	} //end of getSoundOn method

} //end of Sound class
