package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.SpaceInvader;


public class Main extends Application{
	private SpaceInvader spaceInvader;
	private FXSpaceInvader fXSpaceInvader;
	public Main() {
		spaceInvader = new SpaceInvader();
	}
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		fXSpaceInvader = new FXSpaceInvader(spaceInvader, primaryStage);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
		
		fxmlLoader.setController(fXSpaceInvader);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Space_invader");
		Image icon = new Image("/images/Title.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setResizable(false);
		primaryStage.show();
		fXSpaceInvader.loadBanner();
	}
	
}
