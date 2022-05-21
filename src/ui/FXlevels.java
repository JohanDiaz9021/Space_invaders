package ui;


import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
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
	private SpaceInvader spaceInvader;
	  @FXML
	    private BorderPane welcome;
	public FXlevels(SpaceInvader spaceInvader2, Stage primaryStage) {
spaceInvader = spaceInvader2;
}
	public void loadBanner() throws IOException {
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
		level = new Level(spaceInvader.getCuantityAlins());
		normalMovement = 10;
		dificult = "";
	}
	@FXML
    private ImageView fondo;

    @FXML
    private ImageView imagenGo;

    @FXML
    private ComboBox<String> dificultad;

    @FXML
    void imagenGO(MouseEvent event) {

    }
    public void choiseDificult() {

		if (dificultad.getValue() != null) {

			if (dificultad.getValue().equalsIgnoreCase("Novato")) {
				easy = new Easy(level.getAliens(), (normalMovement - 5));
				dificult = "novato";

			} else if (dificultad.getValue().equalsIgnoreCase("Experto")) {
				hard = new Hard(level.getAliens(), (normalMovement + 5), 1000);
				dificult = "leyenda";

			} else {
				dificult = "cadete";
			}
		}
	}

}
