package thread;

import java.util.ArrayList;

import model.CompareTwoPlayers;
import model.Player;
import ui.FXGame;

public class BubbleThread extends Sorting implements CompareTwoPlayers{
	
	@SuppressWarnings("unused")
	private FXGame fxGame;
	
	public BubbleThread(FXGame fxGame, ArrayList<Player> listPlayers) {
		
		super(listPlayers);
		this.fxGame = fxGame;
	}
	
	@Override
	public void run() {
		
		int changes = 1;

		for(int i=1;i<getListPlayers().size() && changes > 0;i++) {
			changes = 0;

			for(int j=0;j<getListPlayers().size()-i;j++) {
				
				if(compareTwoPlayers(getListPlayers().get(j), getListPlayers().get(j+1)) > 0) {
					Player tem = getListPlayers().get(j);
					getListPlayers().set(j,getListPlayers().get(j+1));
					getListPlayers().set(j+1,tem);
					changes++;
				}
			}
		}
		int hola = 0;
		//aliensInvadersGUI.inicializateTableViewPlayer(getListPlayers());
	}
	
	@Override
	public int compareTwoPlayers(Player playerOne, Player playerTwo) {
		
		int verify = playerOne.getNick().compareTo(playerTwo.getNick());
		
		if(verify == 0) {
			
				verify = 1;
			int las = 0;
		}
		
		return verify;
	}
}
