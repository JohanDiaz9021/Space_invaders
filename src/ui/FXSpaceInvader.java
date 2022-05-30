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
/**
 * @version 1
 * @author Johan sebastian diaz, https://github.com/JohanDiaz9021 <br>
 * @author Jose guerrero  ,https://github.com/guerreroSoloCali <br>
 * @author Juan Manuel velosa ,https://github.com/JuanVelosa <br>
 */
public class FXSpaceInvader {
	private Stage primaryStage;
	private FXAddConfiguration fxaddConfig;
	@FXML
	private ImageView ImagenGra;
	private SpaceInvader space;
	@FXML
	private ImageView Title;
	private FXpodium podium;
	@FXML
	private BorderPane welcome;

	public FXSpaceInvader(SpaceInvader spaceInvader, Stage primaryStage) {
		space = spaceInvader;
		space.loadData();
		podium = new FXpodium(space,primaryStage);
		fxaddConfig = new FXAddConfiguration(space, primaryStage);
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
	void Podium(ActionEvent event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));

		fxmlLoader.setController(podium);

		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Space_invader");
		Image icon = new Image("/images/Title.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setResizable(false);
		primaryStage.show();
		podium.loadBanner();
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

	
}
