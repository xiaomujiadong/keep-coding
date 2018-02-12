package com.saint.bean;

public class Person {
	private String name;
	private int age;
	private Car car;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public void info(){
		System.out.println("name: "+getName()+", age: "+getAge()+", car color: "+car.getColor());
	}
}