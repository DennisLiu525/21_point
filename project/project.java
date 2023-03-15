package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class project extends Application{
	public static Stage currentStage;
	public static Scene currentScene;
	@Override

	public void start(Stage PrimaryStage) throws Exception {
		// TODO Auto-generated method stub
		currentStage = PrimaryStage;
		Parent root=FXMLLoader.load(getClass().getResource("menu.fxml"));
		currentScene = new Scene(root);
		currentStage.setTitle("21");
		currentStage.setScene(currentScene);
		currentStage.show();
		
	}
	public static void main(String[] args) {
		launch(args);
	}

}
