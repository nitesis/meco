import java.io.File;
import java.io.IOException;

import javax.media.CannotRealizeException;
import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.EndOfMediaEvent;
import javax.media.Manager;
import javax.media.NoPlayerException;
import javax.media.Player;


public class MP3Player extends Thread {
	private Player mp3Player;
	String filePath = "";
	
	public MP3Player(String filePath)
	{
		this.filePath = filePath;
	}
	
	public void run()
	{
		try 
		{
			System.out.println(filePath);
			mp3Player = Manager.createRealizedPlayer(new File(filePath).toURI().toURL());
			
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		catch (NoPlayerException e)
		{
			System.out.println(e.getMessage());
		}
		catch (CannotRealizeException e)
		{
			System.out.println(e.getMessage());
		}
		
		mp3Player.addControllerListener(new ControllerListener() 
		{
			
			@Override
			public void controllerUpdate(ControllerEvent ce) 
			{
				// TODO Auto-generated method stub
				if(ce instanceof EndOfMediaEvent)
				{
					mp3Player.stop();
					mp3Player.close();
				}
			}
		}
		);
		
		mp3Player.realize();
		mp3Player.start();
	}
}
