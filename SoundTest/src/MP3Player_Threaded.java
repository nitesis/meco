import java.awt.FlowLayout;
import javax.media.Format;
import javax.media.PlugInManager;
import javax.media.format.AudioFormat;
import javax.swing.JApplet;
import javax.swing.JLabel;


public class MP3Player_Threaded extends JApplet
{
	private static final long serialVersionUID = -4137350405140331928L; 
	JLabel title = new JLabel("MP3 Player");
	JLabel pathTo = new JLabel("File Path: ");
	public static JLabel status = new JLabel("NULL");
	
	public void init()
	{
		setLayout(new FlowLayout());
		getContentPane().add(title);
		getContentPane().add(pathTo);
		getContentPane().add(status);
		
		Format inMP3 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format inMPEG = new AudioFormat(AudioFormat.MPEG);
        Format out = new AudioFormat(AudioFormat.LINEAR);
        
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{inMP3,inMPEG}, 
        		new Format[]{out}, PlugInManager.CODEC);
        
        //String ssdir = "music/";
        String ssdir = "/Users/gundelsw/Documents/workspace/SoundTest/music/";
		String sfile = "besser.mp3";
		sfile = ssdir+sfile;
		
		MP3Player mp3p = new MP3Player(sfile);
		mp3p.start();
		status.setText("playing...");
	}
}
