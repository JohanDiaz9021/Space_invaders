package ui;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Easy;
import model.Hard;
import model.Level;
import model.SpaceInvader;

public class FXlevels {
	private Easy easy;
	private Level level;
	private Hard hard;
	private String dificult;
	private int normalMovement;
	private boolean verify;
	private FXGame game;
	private SpaceInvader spaceInvader;
	private Stage primaryStage ;
	@FXML
	private BorderPane welcome;
	public FXlevels(SpaceInvader spaceInvader2, Stage primaryStage) {
		spaceInvader = spaceInvader2;
		this.primaryStage = primaryStage;
	}
	public void loadBanner() throws IOException {
		setVerify(false);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("levels.fxml"));

		loader.setController(this);
		Parent load = loader.load();
		Image image1 = new Image("/images/Jugador.png");
		imagenGo.setImage(image1);
		Image image = new Image("/images/Fondo.jpg");
		fondo.setImage(image);
		welcome.getChildren().clear();
		dificultad.setPromptText("Dificultad");

		dificultad.getItems().addAll("Novato", "Cadete", "Experto");
		welcome.setTop(load);
		int error = 0;
		level = new Level(spaceInvader.getCuantityAlins());
		normalMovement = 10;
		setDificult("");
	}
	@FXML
    private ImageView fondo;

    @FXML
    private ImageView imagenGo;

    @FXML
    private ComboBox<String> dificultad;

    @FXML
    void imagenGO(MouseEvent event) throws IOException {

		choiseDificult();

		if (dificultad.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("No se puede continuar");
			alert.setContentText("Debe selecionar una dificultad");
			alert.showAndWait();
		} else {
			setVerify(true);
			setGame(new FXGame(spaceInvader, primaryStage,dificultad.getValue(),spaceInvader.getCuantityAlins()));
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
			
			fxmlLoader.setController(game);
			
			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Space_invader");
			Image icon = new Image("/images/Title.png");
			primaryStage.getIcons().add(icon);
			primaryStage.setResizable(false);
			primaryStage.show();
			game.load();
		}
    }
    public void choiseDificult() {

		if (dificultad.getValue() != null) {

			if (dificultad.getValue().equalsIgnoreCase("Novato")) {
				setEasy(new Easy(level.getAliens(), (normalMovement - 5)));
				setDificult("novato");

			} else if (dificultad.getValue().equalsIgnoreCase("Experto")) {
				setHard(new Hard(level.getAliens(), (normalMovement + 5), 1000));
				setDificult("leyenda");

			} else {
				setDificult("cadete");
			}
		}
	}
	public boolean isVerify() {
		return verify;
	}
	public void setVerify(boolean verify) {
		this.verify = verify;
	}
	public String getDificult() {
		return dificult;
	}
	public void setDificult(String dificult) {
		this.dificult = dificult;
	}
	public Hard getHard() {
		return hard;
	}
	public void setHard(Hard hard) {
		this.hard = hard;
	}
	public Easy getEasy() {
		return easy;
	}
	public void setEasy(Easy easy) {
		this.easy = easy;
	}
	public FXGame getGame() {
		return game;
	}
	public void setGame(FXGame game) {
		this.game = game;
	}

}
