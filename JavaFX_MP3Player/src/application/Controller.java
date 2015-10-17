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
			Main.pauseMusic();
		});
		
		// Handle Button event.
		rightButton.setOnAction((event) -> {
			Main.restartMusic();
		});
		
    }

}