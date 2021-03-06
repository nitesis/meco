package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
 
public class Controller implements  Runnable{

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
		lblSong.setText("Do you really want to listen this song? choose another one.");
		progress.setProgress(0.0);
		
    }
	

	@FXML
    void chooseFile(ActionEvent event) {
        player.stopMusic();
        player.chooseFile();

        startThread();
        String name = player.getFileName();
        lblSong.setText(name);

    }

    void startThread(){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                while(update())
                {
                    update();

                    try
                    {
                        java.lang.Thread.sleep(500);
                    }
                    catch(InterruptedException ie)
                    {
                        // ...
                    }
                }
            }
        });

        t1.start();
    }

    @FXML
    void playMusic(ActionEvent event) {
    	
    	if(Main.isPlaying){
			System.out.println("Pause Music");
			player.pauseMusic();	
		}else{
			System.out.println("Play Music");
			startThread();
			// String name = player.getFileName();
            //lblSong.setText(name);
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

    public void setLabelTitle(String v){
        lblSong.setText(v);
    }

    public boolean update(){
        double sec = Main.p.getMediaTime().getSeconds();
		double all = Main.p.getDuration().getSeconds();
        double current = 1 / all * sec;
        if(sec != all){
            progress.setProgress(current);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void run(){}


}

