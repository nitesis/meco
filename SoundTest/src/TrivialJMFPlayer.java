import javax.media.*;
import javax.media.format.AudioFormat;
import javax.swing.BorderFactory;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.Border;

import java.util.Calendar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
/**
 * see http://www.youtube.com/watch?v=q9Gb6bZLKq4
 * @author gundelsw
 *
 */
public class TrivialJMFPlayer extends JApplet implements ActionListener
{
	private static final long serialVersionUID = -4137350405140331929L;
	JLabel title = new JLabel("Mein MP3-Player");
	JLabel fileName = new JLabel("Playing ...");
	JButton playBtn = new JButton();
	JButton pauseBtn = new JButton();
	JFileChooser fc = new JFileChooser();
	Player p;
	String ssdir = "music/";
	String sfile = "besser.mp3";
	JProgressBar progressBar;
	Timer timer = new Timer(0, this);
	Calendar calendar = Calendar.getInstance();
	
	public void init()
	{
		setLayout(new FlowLayout());
		getContentPane().add(title);
		playBtn.setText("Play");
		pauseBtn.setText("Pause");
		getContentPane().add(playBtn);
		getContentPane().add(pauseBtn);
		playBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		//fc.setMultiSelectionEnabled(true);
		progressBar = new JProgressBar();
	    progressBar.setValue(0);
	    progressBar.setStringPainted(true);
	    Border border = BorderFactory.createTitledBorder("Dauer");
	    progressBar.setBorder(border);
	    getContentPane().add(progressBar);
	    
		fc.addActionListener(this);
        //fileChooser.showOpenDialog(fileChooser);
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fc.setDialogTitle("Öffnen"); 
        getContentPane().add(fc); 
		
		
        
        /*try {
			
			sfile = getFileLocation(sfile,ssdir);
			System.out.println(sfile);
		
			
			//System.out.println(p.getDuration().getSeconds());
			
			//p.start();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	//Hilfsklasse, die den Filenamen als String zurückgibt
	public String getFileLocation(String file, String dir)
	{
		file = dir+file;
		System.out.println(file);
		file = getClass().getResource(file).toString();
		//nur unter Windows
		//file = file.replace("/", "\\\\");
		file = file.substring(5);
		return file;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		// TODO Lautstaerke
		//GainControl gain = p.getGainControl();
		//gain.setLevel((volume.getValue()) * 0.3f);
		//		
		if(arg0.getSource() instanceof JButton)
		{
			if(p != null) {
				JButton btn = (JButton)arg0.getSource();
				if(btn.getText().compareTo("Play")==0)
				{
					p.start();
					Time t = p.getDuration();
					//set slider to seconds
		        	Double time = t.getSeconds() / 60;	        	
		        	System.out.println(time);
		        	progressBar.setMaximum(time.intValue());
		        	progressBar.setMinimum(0);
		        	timer.start();
		        	
				}
				else if(btn.getText().compareTo("Pause")==0)
				{
					timer.stop();
					p.stop();
				}
			}
		}
		else if(arg0.getSource() instanceof Timer)
		{
			calendar.setTimeInMillis(System.currentTimeMillis());
	        //this.clockLabel.setText(this.dateFormat.format(this.calendar.getTime()));
	        int seconds = this.calendar.get(Calendar.MILLISECOND);

	        progressBar.setValue(seconds);
		}
		else
		{
			//ssdir = fc.getCurrentDirectory().getName();
			//sfile = fc.getSelectedFile().getName();
			//sfile = getFileLocation(sfile,ssdir);
			//System.out.println(ssdir+"/");
			//System.out.println(sfile);
			
			Format inMP3 = new AudioFormat(AudioFormat.MPEGLAYER3);
	        Format inMPEG = new AudioFormat(AudioFormat.MPEG);
	        Format out = new AudioFormat(AudioFormat.LINEAR);
	        
	        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{inMP3,inMPEG}, 
	        		new Format[]{out}, PlugInManager.CODEC);
	        try {
	        	p = Manager.createRealizedPlayer(new MediaLocator(fc.getSelectedFile().toURI().toURL()));//new File(sfile).toURI().toURL()));	        	
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}
	}
}