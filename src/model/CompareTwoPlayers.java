package model;

/**
 * This class contains methods, attributes,  and relations of a aliens invaders.
 * @version 1
 * @author Santiago Trochez Velasco, https://github.com/Santiagokmids <br>
 * @author Luis Miguel Ossa Arias, https://github.com/Itsumohitoride <br>
 */

public interface CompareTwoPlayers {
	
	/**
	 * <b>name:</b> compareTwoPlayers. <br>
	 * Compare two players. <br>
	 * <b>post:</b> Two players has been compared. <br>
	 * @param playerOne is the first player to compare. playerOne != null.
	 * @param playerTwo is the second player to compare. playerTwo != null.
	 * @return <code>int</code> specifying verify is the number of the comparison of two players.
	 */
	
	public int compareTwoPlayers(Player playerOne, Player playerTwo);
}
