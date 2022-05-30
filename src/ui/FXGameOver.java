package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

import exceptions.SpaceInNickException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SpaceInvader;

public class FXGameOver {
	private SpaceInvader spaceInvader;
	@SuppressWarnings("unused")
	private Stage window;
	private int puntaje;
	private boolean win;
	@FXML
	private BorderPane welcome;
	private SpaceInvaderGui spaceInvaderGui;

	public FXGameOver(SpaceInvader spaceInvader, Stage window, int puntaje, boolean win) {
		super();
		this.spaceInvader = spaceInvader;
		this.window = window;
		this.puntaje = puntaje;
		this.win = win;
	}

	@FXML
	private Label player;
	@FXML
	private ImageView gameOver;

	@FXML
	private Label scoreOver;

	@FXML
	private TextField nickName;

	@FXML
	private Label labelWinner;

	@FXML
	void addplayer(ActionEvent event) throws FileNotFoundException, IOException, SpaceInNickException {
		if (nickName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Debe llenar los datos solicitados", "Error",
					JOptionPane.WARNING_MESSAGE);

		}else {
			int puntaje = Integer.parseInt(scoreOver.getText()) ;
			spaceInvader.addPlayer(nickName.getText(), puntaje);
			returnInicio();
		}
	}

	private void returnInicio() throws IOException {
		spaceInvaderGui = new SpaceInvaderGui(spaceInvader, window);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
		
		fxmlLoader.setController(spaceInvaderGui);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		window.setScene(scene);
		window.setTitle("Space_invader");
		Image icon = new Image("/images/Title.png");
		window.getIcons().add(icon);
		window.setResizable(false);
		window.show();
		spaceInvaderGui.loadBanner();
		
	}

	public void loadBanner() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("GameOver.fxml"));

		loader.setController(this);
		Parent load = loader.load();

		Image image = new Image("/images/FondoJuego.jpg");
		gameOver.setImage(image);
		if (win == false) {
			labelWinner.setText("PERDISTE");
		}
		scoreOver.setText(puntaje + "");
		player.setText(spaceInvader.getNamePlayerOf());
		welcome.getChildren().clear();
		welcome.setTop(load);

	}
}
