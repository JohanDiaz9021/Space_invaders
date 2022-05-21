package model;

/**
 * This class contains methods, attributes,  and relations of a aliens invaders.
 * @version 1
 * @author Santiago Trochez Velasco, https://github.com/Santiagokmids <br>
 * @author Luis Miguel Ossa Arias, https://github.com/Itsumohitoride <br>
 */

public class Easy extends Level{
	
	private int movementSpeed;
	
	/**
	 * <b>name:</b> EasyLevel. <br>
	 * Create an object easy level. <br>
	 * <b>post:</b> An object easy level has created. <br>
	 * @param aliens is the number of aliens. aliens != null.
	 * @param movementSpeed is the movement speed of the aliens. movementSpeed != null.
	 */
	
	public Easy(int aliens, int movementSpeed) {
		super(aliens);
		this.movementSpeed = movementSpeed;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	
	
}