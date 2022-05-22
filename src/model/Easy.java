package model;



public class Easy extends Level{
	
	private int movementSpeed;
	

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