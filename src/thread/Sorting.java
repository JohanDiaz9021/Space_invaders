package thread;

import java.util.ArrayList;

import model.Player;
/**
 * @version 1
 * @author Johan sebastian diaz, https://github.com/JohanDiaz9021 <br>
 * @author Jose guerrero  ,https://github.com/guerreroSoloCali <br>
 * @author Juan Manuel velosa ,https://github.com/JuanVelosa <br>
 */
public abstract class Sorting extends Thread{
	
	private ArrayList<Player> listPlayers;
	
	public Sorting(ArrayList<Player> lisPlayers) {
		
		this.listPlayers = lisPlayers;
	}
	
	public void run() {
		
	}

	public ArrayList<Player> getListPlayers() {
		return listPlayers;
	}

	public void setListPlayers(ArrayList<Player> listPlayers) {
		this.listPlayers = listPlayers;
	}
}
