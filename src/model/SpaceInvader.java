package model;



public class SpaceInvader {
	private String namePlayerOf ; 
	private int cuantityAlins;
	public void addPeople(String name,int aliens)  {
		
		setNamePlayerOf(name);
		setCuantityAlins(aliens);
	}
	public int getCuantityAlins() {
		return cuantityAlins;
	}
	public void setCuantityAlins(int cuantityAlins) {
		this.cuantityAlins = cuantityAlins;
	}
	public String getNamePlayerOf() {
		return namePlayerOf;
	}
	public void setNamePlayerOf(String namePlayerOf) {
		this.namePlayerOf = namePlayerOf;
	}
	
}
