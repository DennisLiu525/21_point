package project;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class exitcontroller implements Initializable {
    @FXML
    private ImageView exitImage;
    
    @FXML
    private Button exit_but;

    @FXML
    private Button replay_but;

	@FXML
	void replay(ActionEvent event) throws IOException {
		Parent exit = FXMLLoader.load(getClass().getResource("game.fxml"));
    	Scene exitScene = new Scene(exit);
    	exitScene.getRoot().requestFocus();
    	project.currentStage.setScene(exitScene);
	}

	@FXML
	void exit(ActionEvent event) {
		project.currentStage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Image image;
		try {
			image = new Image(FileSystems.getDefault().getPath("src/project/PNG/exitimage.png").toUri().toURL().toString());
			exitImage.setImage(image);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
