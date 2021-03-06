package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.media.*;
import javax.media.format.AudioFormat;
import javafx.stage.*;
public class Main extends Application {
	//Variablen Deklaration
	static Player p = null;
	static boolean isPlaying = false;
	static Stage primaryStage = null;
	File file = null;
	//Controller c = new Controller();
	@Override
	public void start(Stage primaryStage) {
		
		
		try {
		
			Parent rootParent = FXMLLoader.load(getClass().getResource("PlayerView.fxml"));
			
			Scene scene = new Scene(rootParent,600,200);
			primaryStage.setTitle("Music ME");
			
			playMusic();
			
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void chooseFile(){
		FileChooser fileChooser = new FileChooser();
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.MP3");
		fileChooser.getExtensionFilters().add(extFilter);
		File file = fileChooser.showOpenDialog(primaryStage);
		setFile(file);
		System.out.println(file.getName());
		//Controller.setLblSong(file.getName());

    	try {
			p = Manager.createRealizedPlayer(new MediaLocator(getFile().toURI().toURL()));
		} catch (NoPlayerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CannotRealizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//new File(sfile).toURI().toURL()));	        	
    	p.start();
    	isPlaying = true;
  
		
	}

    public String getFileName(){
        if(file.getName()!=null){
            return file.getName();
        }else{
            return "no File";
        }
    }
	
	public void playMusic() throws MalformedURLException{
		Format inMP3 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format inMPEG = new AudioFormat(AudioFormat.MPEG);
        Format out = new AudioFormat(AudioFormat.LINEAR);
        
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{inMP3,inMPEG}, 
        		new Format[]{out}, PlugInManager.CODEC);




        try {
        	//w‰hle einen SoundTrack aus f¸r mac (//file:...pfad)
			FileChooser fileChooser = new FileChooser();
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.MP3");
			fileChooser.getExtensionFilters().add(extFilter);
			File file = fileChooser.showOpenDialog(primaryStage);
			setFile(file);

        	p = Manager.createRealizedPlayer(new MediaLocator(getFile().toURI().toURL()));//new File(sfile).toURI().toURL()));
        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}


	}
	
	public void pauseMusic(){
		isPlaying = false;
		p.stop();
	}
	
	public void stopMusic(){
		isPlaying = false;
		p.stop();
		
	
	}
	
	public void restartMusic(){
		isPlaying = true;
		p.start();
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
}