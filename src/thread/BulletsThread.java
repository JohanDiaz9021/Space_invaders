package thread;

import java.util.Random;

import javafx.application.Platform;
import model.Alien;
import ui.FXGame;


public class BulletsThread extends Thread {

	private FXGame aliensInvadersGUI;
	private boolean verify;
	private Alien alien;
	private Alien current;
	private int attack;

	public BulletsThread(FXGame aliensInvadersGUI,Alien alien,boolean verify,int attack) {
		this.aliensInvadersGUI = aliensInvadersGUI;
		this.verify = verify;
		this.alien = alien;
		this.attack = attack;
		current = null;
	}

	public void run() {
		
		while(verify) {

			if(alien != null) {

				current = alien;
				Random alienToSelect = new Random();
				int aleatorio = (int)(alienToSelect.nextDouble() * 10);
				int cont = 0;
				alien.getNext().getNext().getNext().getNext().setNext(null);
				
				for (int i = 0; i <= aleatorio; i++) {
					
					if(cont < 5) {
						 if(current.getNext() != null) {
							 cont++;
							 current = current.getNext();
						 }else {
							 cont++;
							 current = current.getDown();
						 }

					}else if(cont == 5 && current.getNext() == null && current.getDown() != null) {
						cont++;
						current = current.getDown();

					}
					else if(cont > 4 && current.getPrev() != null) {
						cont++;
						current = current.getPrev();
					}
				}
			}
			Platform.runLater(new Thread(){
				public void run() {

					if(current.getVisible()) {
						//try {
							int hola = 0;
						//	aliensInvadersGUI.selectAlien(current);
							verify = aliensInvadersGUI.getVerify();
					//	} //catch (InterruptedException e) {
						//}
					}
				}

			});

			verify = aliensInvadersGUI.getVerify();

			try{
				Thread.sleep(attack);
			}catch(InterruptedException e) {

			}

		}

	}
}
