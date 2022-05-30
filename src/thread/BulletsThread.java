package thread;

import java.util.Random;

import javafx.application.Platform;
import model.Alien;
import ui.FXGame;
/**
 * @version 1
 * @author Johan sebastian diaz, https://github.com/JohanDiaz9021 <br>
 * @author Jose guerrero  ,https://github.com/guerreroSoloCali <br>
 * @author Juan Manuel velosa ,https://github.com/JuanVelosa <br>
 */
public class BulletsThread extends Thread {

	private FXGame aliensInvadersGUI;
	private boolean verify;
	private Alien alien;
	private Alien current;
	private int attack;
	private int aliens;

	public BulletsThread(FXGame aliensInvadersGUI, Alien alien, boolean verify, int attack, int cuantityAlins) {
		this.aliensInvadersGUI = aliensInvadersGUI;
		this.verify = verify;
		this.alien = alien;
		this.attack = attack;
		aliens = cuantityAlins;
		current = null;
	}

	public void run() {

		while (verify) {

			if (alien != null) {

				current = alien;
				Random alienToSelect = new Random();
				int aleatorio = (int) (alienToSelect.nextDouble() * aliens);
				int cont = 0;
				int erro = 0;
				
				if (aliens == 2) {
					alien.getNext().setNext(null);
				}
				if (aliens == 3) {
					alien.getNext().getNext().setNext(null);
				}
				if (aliens == 4) {
					alien.getNext().getNext().getNext().setNext(null);
				}
				if (aliens == 5) {
					alien.getNext().getNext().getNext().getNext().setNext(null);
				}

				for (int i = 0; i <= aleatorio; i++) {

					if (cont < aliens) {
						if (current.getNext() != null) {
							cont++;
							current = current.getNext();
						} else {
							cont++;
							current = current.getDown();
						}

					} else if (cont == aliens && current.getNext() == null && current.getDown() != null) {
						cont++;
						current = current.getDown();

					} else if (cont > aliens-1 && current.getPrev() != null) {
						cont++;
						current = current.getPrev();
					}
				}
			}
			Platform.runLater(new Thread() {
				public void run() {

					if (current.getVisible()) {
						try {
							int hola = 0;
							aliensInvadersGUI.selectAlien(current);
							verify = aliensInvadersGUI.getVerify();
						} catch (InterruptedException e) {
						}
					}
				}

			});

			verify = aliensInvadersGUI.getVerify();

			try {
				Thread.sleep(attack);
			} catch (InterruptedException e) {

			}

		}

	}
}
