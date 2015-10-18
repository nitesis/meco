package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
 
public class Controller {

    @FXML
    private Button leftButton;

    @FXML
    private Button rightButton;

    @FXML
    private Button chooseButton;

    @FXML
    public Label lblSong;
    
    @FXML
    public ProgressBar progress;
    
	Main player = new Main();
	
	
	
    public void setLblSong(String v) {
   
	}


	public Controller(){
    	
    }
    

	@FXML
	private void initialize() {
		lblSong.setText("Wähle dein Soundtrack");
		progress.setProgress(0.5);
		
    }
	

	@FXML
    void chooseFile(ActionEvent event) {
		player.stopMusic();
		player.chooseFile();
    }

    @FXML
    void playMusic(ActionEvent event) {
    	
    	if(Main.isPlaying){
			System.out.println("Pause Music");
			player.pauseMusic();	
		}else{
			System.out.println("Play Music");
			player.restartMusic();
		}
    }

    @FXML
    void stopMusic(ActionEvent event) {
		System.out.println("Stop Music");
		player.stopMusic();
    }

    @FXML
    void updateProgress(ActionEvent event) {
//		double sec = Main.p.getMediaTime().getSeconds();
//		double all = Main.p.getDuration().getSeconds();
//		
//		while(sec!=all){
//			sec = Main.p.getMediaTime().getSeconds();
//			all = Main.p.getDuration().getSeconds();
//			double current = 1/all*sec;
//			//System.out.println(current);
//			progress.setProgress(0.5);
//		}
	}


}