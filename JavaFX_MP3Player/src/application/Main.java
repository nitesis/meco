package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javax.media.*;
import javax.media.format.AudioFormat;

public class Main extends Application {
	//Variablen Deklaration
	static Player p = null;
	boolean isPlaying = true;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		
		
		try {
			//BorderPane root = new BorderPane();
			//Scene scene = new Scene(root,600,200);
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
	
	
	public void playMusic(){
		
		
		String ssdir = "music/";
		String sfile = "besser.mp3";
		Format inMP3 = new AudioFormat(AudioFormat.MPEGLAYER3);
        Format inMPEG = new AudioFormat(AudioFormat.MPEG);
        Format out = new AudioFormat(AudioFormat.LINEAR);
        
        PlugInManager.addPlugIn("com.sun.media.codec.audio.mp3.JavaDecoder", new Format[]{inMP3,inMPEG}, 
        		new Format[]{out}, PlugInManager.CODEC);
        try {
        	p = Manager.createRealizedPlayer(new MediaLocator("file:c:/hikids.mp3"));//new File(sfile).toURI().toURL()));	        	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		p.start();
	}
	
	public static void pauseMusic(){
		p.stop();
	}
	
	public static void restartMusic(){
		p.start();
	}
}