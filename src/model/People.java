package model;

import java.io.Serializable;



public abstract class People implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	

	public People(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
