package ui;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.Player;
import model.SpaceInvader;

public class FXpodium {
	private SpaceInvader space;
	private Stage primaryStage;
	public FXpodium(SpaceInvader space,  Stage primaryStage) {	
		this.space = space;
		this.primaryStage = primaryStage;
		space.loadData();
	}

	public SpaceInvader getSpace() {
		return space;
	}

	public void setSpace(SpaceInvader space) {
		this.space = space;
	}
	@FXML
	private BorderPane welcome;
	 @FXML
	 private BorderPane welcome1;
	public void loadBanner() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("podiumIn.fxml"));

		loader.setController(this);
		Parent load = loader.load();
		welcome.getChildren().clear();
		searchPodium();
		welcome.setTop(load);

	}
	
	

	    @FXML
	    private ImageView backgroundPodium;

	    @FXML
	    private Label nick1;

	    @FXML
	    private Label score1;

	    @FXML
	    private Label nick3;

	    @FXML
	    private Label score3;

	    @FXML
	    private Label nick2;

	    @FXML
	    private Label score2;

	    @FXML
	    private Label nick5;

	    @FXML
	    private Label score5;

	    @FXML
	    private Label nick4;

	    @FXML
	    private Label score4;

	    @FXML
	    private ImageView imagePodium;

	    @FXML
	    private Label nick6;

	    @FXML
	    private Label score6;
	    private FXSpaceInvader fXSpaceInvader;
	   
	   
	    @FXML
	    void returnPage(ActionEvent event) throws IOException {
	    	fXSpaceInvader = new FXSpaceInvader(space, primaryStage);
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
	    public void searchPodium() {

			ArrayList<Player> newList = space.searchPodium();

			if(!newList.isEmpty() && newList.get(0) != null) {

				nick1.setText(newList.get(0).getNick());
				score1.setText(String.valueOf("Score: "+newList.get(0).getScore()));

				if(newList.size() > 1 && newList.get(1) != null) {

					nick2.setText(newList.get(1).getNick());
					score2.setText(String.valueOf("Score: "+newList.get(1).getScore()));

					if(newList.size() > 2 && newList.get(2) != null) {

						nick3.setText(newList.get(2).getNick());
						score3.setText(String.valueOf("Score: "+newList.get(2).getScore()));

						if(newList.size() > 3 && newList.get(3) != null) {

							nick4.setText(newList.get(3).getNick());
							score4.setText(String.valueOf("Score: "+newList.get(3).getScore()));

							if(newList.size() > 4 && newList.get(4) != null) {

								nick5.setText(newList.get(4).getNick());
								score5.setText(String.valueOf("Score: "+newList.get(4).getScore()));
							}
							if(newList.size() > 5 && newList.get(5) != null) {

								nick6.setText(newList.get(5).getNick());
								score6.setText(String.valueOf("Score: "+newList.get(5).getScore()));
							}
						}
					}
				}
			}
		}
}
