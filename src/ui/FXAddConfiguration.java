package ui;

import java.io.IOException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.SpaceInvader;

public class FXAddConfiguration {
	private Stage primaryStage ;
	private SpaceInvaderGui spaceInvader ;
	private FXlevels levels;
	private SpaceInvader spaceInvader2 ;
    public FXAddConfiguration(SpaceInvader spaceInvader2, Stage primaryStage) {
    	this.primaryStage = primaryStage;
    	this.spaceInvader2 = spaceInvader2;
		
		
	}
    @FXML
    private TextField txtNAliens;
    
	@FXML
    private ImageView paneRealName;
    @FXML
    private TextField txtName;

    @FXML
    void back(ActionEvent event) throws IOException {
    	spaceInvader = new SpaceInvaderGui(spaceInvader2, primaryStage);
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
		
		fxmlLoader.setController(spaceInvader);
		
		Parent root = fxmlLoader.load();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Space_invader");
		Image icon = new Image("/images/Title.png");
		primaryStage.getIcons().add(icon);
		primaryStage.setResizable(false);
		primaryStage.show();
		spaceInvader.loadBanner();
    }

    @FXML
    void starGame(ActionEvent event) throws IOException {
    	if (txtNAliens.getText().equals("") || txtName.getText().equals("")) {
			JOptionPane.showMessageDialog(null, "Debe llenar los datos solicitados", "Error",
					JOptionPane.WARNING_MESSAGE);

		}else {
			String name = txtName.getText();
			int cuantityAliens = Integer.parseInt(txtNAliens.getText());
			if(cuantityAliens < 3 || cuantityAliens >5) {
				JOptionPane.showMessageDialog(null, "La cantidad de aliens debe estar entre 3 y 5", "Error",
						JOptionPane.WARNING_MESSAGE);
			}			
			else {
				spaceInvader2.addPeople(name,cuantityAliens);

				levels = new FXlevels(spaceInvader2, primaryStage);
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Welcome.fxml"));
				
				fxmlLoader.setController(levels);
				
				Parent root = fxmlLoader.load();
				
				Scene scene = new Scene(root);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Space_invader");
				Image icon = new Image("/images/Title.png");
				primaryStage.getIcons().add(icon);
				primaryStage.setResizable(false);
				primaryStage.show();
				levels.loadBanner();
			}
		}
    }
    @FXML
    private BorderPane welcome;
	public void loadBanner() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("infoPlayer.fxml"));

		loader.setController(this);
		Parent load = loader.load();

		Image image = new Image("/images/todo.jpg");
		paneRealName.setImage(image);
		
		welcome.getChildren().clear();
		welcome.setTop(load);		
	}
}
