package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SpaceInvader;

public class SpaceInvaderGui {
	private Stage primaryStage ;
	private FXAddConfiguration fxaddConfig;
	@FXML
    private ImageView ImagenGra;

    @FXML
    private ImageView Title;
    @FXML
    private BorderPane welcome;
	public SpaceInvaderGui(SpaceInvader spaceInvader, Stage primaryStage) {
		fxaddConfig = new FXAddConfiguration(spaceInvader, primaryStage);
		this.primaryStage = primaryStage;
	}

	public void loadBanner() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("startGame.fxml"));

		loader.setController(this);
		Parent load = loader.load();

		Image image = new Image("/images/todo.jpg");
		ImagenGra.setImage(image);
		
		welcome.getChildren().clear();
		welcome.setTop(load);
		
	}
	   

	    @FXML
	    void Podium(ActionEvent event) {

	    }

	    @FXML
	    void goGame(ActionEvent event) throws IOException {
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
			
			fxmlLoader.setController(fxaddConfig);
			
			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
			fxaddConfig.loadBanner();
	    }

	   
	    @FXML
	    void scores(ActionEvent event) {

	    }
}
