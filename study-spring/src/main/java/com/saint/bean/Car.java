package com.saint.bean;

public class Car {
	
	private Person owner;
	
	private String color;
	
	public Person getOwner() {
		return owner;
	}

	public void setOwner(Person owner) {
		this.owner = owner;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String info(){
		String info = "color: "+this.color+"car owner is "+owner.getName();
		System.out.println(info);
		return info;
	}
}
