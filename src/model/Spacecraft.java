package model;



public class Spacecraft {
	
	private TypeSpacecraft ship;
	private Double posX;
	private Double posY;
	private int velocityMovement;
	
	
	public Spacecraft(TypeSpacecraft ship, double x) {
		this.ship = ship;
		setPosX(x);
		velocityMovement = 10;
	}

	public TypeSpacecraft getShip() {
		return ship;
	}

	public Double getPosX() {
		return posX;
	}

	public void setPosX(Double posX) {
		this.posX = posX;
	}
	
	
	
	public void moveLeft() {
		posX = posX - getVelocityMovement();
	}
	
	
	public void moveRight() {
		posX = posX + getVelocityMovement();
	}

	public Double getPosY() {
		return posY;
	}

	public void setPosY(Double posY) {
		this.posY = posY;
	}

	public int getVelocityMovement() {
		return velocityMovement;
	}

	public void setVelocityMovement(int velocityMovement) {
		this.velocityMovement = velocityMovement;
	}
}
