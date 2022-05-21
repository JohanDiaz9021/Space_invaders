package model;

/**
 * This class contains methods, attributes,  and relations of a aliens invaders.
 * @version 1
 * @author Santiago Trochez Velasco, https://github.com/Santiagokmids <br>
 * @author Luis Miguel Ossa Arias, https://github.com/Itsumohitoride <br>
 */

public class Level {
	
	private int aliens;
	
	/**
	 * <b>name:</b> Level. <br>
	 * Create an object level. <br>
	 * <b>post:</b> An object level has created. <br>
	 * @param aliens is the number of aliens. aliens != null.
	 */
	
	public Level(int aliens) {
		this.aliens = aliens;
	}
	
	public int getAliens() {
		return aliens;
	}
	
	public void setAliens(int aliens) {
		this.aliens = aliens;
	}
	
}
