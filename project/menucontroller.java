package project;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class menucontroller {

    @FXML
    private Button start;

    @FXML
    private Button exit;

    @FXML
    private Text title;
    

    @FXML
    void PressedStart(ActionEvent event) throws IOException {
    	Parent game = FXMLLoader.load(getClass().getResource("game.fxml"));
    	Scene gameScene = new Scene(game);
    	gameScene.getRoot().requestFocus();
    	project.currentStage.setScene(gameScene);
    }

    @FXML
    void PressedExit(ActionEvent event) {
    	project.currentStage.close();
    }
}
