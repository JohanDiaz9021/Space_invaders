package thread;

import java.util.ArrayList;

import model.Player;
import ui.FXGame;
/**
 * @version 1
 * @author Johan sebastian diaz, https://github.com/JohanDiaz9021 <br>
 * @author Jose guerrero  ,https://github.com/guerreroSoloCali <br>
 * @author Juan Manuel velosa ,https://github.com/JuanVelosa <br>
 */
public class BubbleThread extends Sorting {
	
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
	}
	
	
	public int compareTwoPlayers(Player playerOne, Player playerTwo) {
		
		int verify = playerOne.getNick().compareTo(playerTwo.getNick());
		
		if(verify == 0) {
			
				verify = 1;
		}
		
		return verify;
	}
}
