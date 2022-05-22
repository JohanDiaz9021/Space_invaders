package thread;

import java.util.ArrayList;

import model.Player;

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
