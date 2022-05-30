package thread;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import model.Alien;
import ui.FXGame;
/**
 * @version 1
 * @author Johan sebastian diaz, https://github.com/JohanDiaz9021 <br>
 * @author Jose guerrero  ,https://github.com/guerreroSoloCali <br>
 * @author Juan Manuel velosa ,https://github.com/JuanVelosa <br>
 */
public class SearchAlienThread extends Thread {

	private FXGame aliensInvadersGUI;
	private double x;
	private double y;
	private double posBallX;
	private double posBallY;
	private ImageView alienImageView;
	private boolean verify;
	private boolean knowShoot;
	private Circle circle;
	private Alien alien;

	public SearchAlienThread(FXGame aliensInvadersGUI, double x, double y, double posBallX,double posBallY,
			ImageView alienImageView,boolean verify,boolean knowShoot,Circle circle,Alien alien) {

		this.aliensInvadersGUI = aliensInvadersGUI;
		this.x = x;
		this.y = y;
		this.posBallX = posBallX;
		this.posBallY = posBallY;
		this.verify = verify;
		this.alienImageView = alienImageView;
		this.knowShoot = knowShoot;
		this.circle = circle;
		this.alien = alien;
	}

	public void run() {

		boolean shoot = false;

		if(alien == null) {
			shoot = false;
		}
		else {

			while(!shoot && verify) {
				
				if(alienImageView.getLayoutX() == x && (posBallX > alienImageView.getLayoutX() && posBallX < alienImageView.getLayoutX()+73) 
						&& knowShoot &&	(alienImageView.getLayoutY() == y && (posBallY > alienImageView.getLayoutY()-60 && posBallY < alienImageView.getLayoutY()+60) && 
						alienImageView.isVisible() && circle.isVisible())) {
					
					Platform.runLater(new Thread(){
						public void run() {
							
							aliensInvadersGUI.setImage(alienImageView, alien);
							aliensInvadersGUI.setScores(1);
							try {
								aliensInvadersGUI.setLevels();
							} catch (IOException e) {
							}
							synchronized (aliensInvadersGUI) {
								aliensInvadersGUI.setCircle(circle);
							}
						}

					});
					shoot = true;

				}

				posBallX = aliensInvadersGUI.getBallInMoveX();
				posBallY = aliensInvadersGUI.getBallInMoveY();
				verify = aliensInvadersGUI.getVerify();
				knowShoot = aliensInvadersGUI.getKnowShoot();

				try{
					Thread.sleep(1200);
				}catch(InterruptedException e) {

				}
			}
		}
	}

}