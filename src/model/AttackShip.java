package model;



public class AttackShip extends Spacecraft{
	
	private int attackSpeed;
	
	
	public AttackShip(TypeSpacecraft ship,double x, int attackSpeed) {
		super(ship,x);
		this.attackSpeed = attackSpeed;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
}
