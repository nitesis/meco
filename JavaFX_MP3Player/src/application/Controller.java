package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
 
public class Controller {
	@FXML
	private Button leftButton;
	@FXML
	private Button rightButton;
    
   
    public Controller(){
    	
    }
    

	@FXML
	private void initialize() {
		leftButton.setOnAction((event) -> {
			if(Main.isPlaying){
				System.out.println("Pause Music");
				Main.pauseMusic();	
			}else{
				System.out.println("Play Music");
				Main.restartMusic();
			}
			
		});
		
		// Handle Button event.
		rightButton.setOnAction((event) -> {
			System.out.println("Stop Music");
			Main.pauseMusic();
		});
		
    }

}