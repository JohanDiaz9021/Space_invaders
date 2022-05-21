package model;

/**
 * This class contains methods, attributes,  and relations of a aliens invaders.
 * @version 1
 * @author Santiago Trochez Velasco, https://github.com/Santiagokmids <br>
 * @author Luis Miguel Ossa Arias, https://github.com/Itsumohitoride <br>
 */

public class Hard extends Level {

	private int movementSpeed;
	private int attackSpeed;
	
	/**
	 * <b>name:</b> HardLevel. <br>
	 * Create an object hard level. <br>
	 * <b>post:</b> An object hard level has created. <br>
	 * @param aliens is the number of aliens. aliens != null.
	 * @param movementSpeed is the movement speed of the aliens. movementSpeed != null.
	 * @param attackSpeed is the attack speed of the aliens. attackSpeed != null.
	 */

	public Hard(int aliens, int movementSpeed, int attackSpeed) {
		super(aliens);
		this.movementSpeed = movementSpeed;
		this.attackSpeed = attackSpeed;
	}

	public int getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(int movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
}
