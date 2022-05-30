package thread;

import java.io.IOException;

import javafx.application.Platform;
import javafx.scene.image.ImageView;
import model.Alien;
import ui.FXGame;
/**
 * @version 1
 * @author Johan sebastian diaz, https://github.com/JohanDiaz9021 <br>
 * @author Jose guerrero  ,https://github.com/guerreroSoloCali <br>
 * @author Juan Manuel velosa ,https://github.com/JuanVelosa <br>
 */
public class AlienThread extends Thread{
	
	private FXGame aliensInvadersGUI;
	private Alien alien;
	private ImageView alienImageView;
	private boolean verify;
	
	public AlienThread(FXGame aliensInvadersGUI, Alien alien, ImageView alienImageView, boolean verify) {
		
		this.aliensInvadersGUI = aliensInvadersGUI;
		this.alien = alien;
		this.alienImageView = alienImageView;
		this.verify = verify;
	}
	
	public void run() {
		
		while(verify) {
			
			alien.moveAlien();
			
			Platform.runLater(new Thread(){
				public void run() {
					
					aliensInvadersGUI.updateAlien(alien.getPositionY(), alien.getPositionX()+alien.getX(), alienImageView);
					aliensInvadersGUI.searchAlien(alienImageView.getLayoutX(), alienImageView.getLayoutY(),alienImageView,alien);
					
					if(alienImageView.isVisible()) {
						
						if(alienImageView.getImage() == alien.getImageOne()) {
							alienImageView.setImage(alien.getImageTwo());
							
						}else {
							alienImageView.setImage(alien.getImageOne());
						}
					}
					try {
						aliensInvadersGUI.validationShip(alienImageView);
						aliensInvadersGUI.validationPosition(alien, alienImageView);
						synchronized (aliensInvadersGUI) {
							aliensInvadersGUI.validationBullets();
						}
						
					} catch (IOException e) {
					}
				}
			});
			
			verify = aliensInvadersGUI.getVerify();
			
			try{
				Thread.sleep(200);
			}catch(InterruptedException e) {
				
			}
		}
	}
}

	