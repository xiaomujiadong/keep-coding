package com.saint.bean;

import com.saint.annotation.SaintAutowire;
import com.saint.annotation.SaintBean;

@SaintBean(id="testBean")
public class TestBean {
	
	private String name;
	private String desc;

	@SaintAutowire
	private TestCar testCar;

	@SaintAutowire
	private ICar iCar;
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public void info(){
		System.out.println("这是�?个测试注解类的方法调用�??");
	}
	
	@Override
	public String toString(){
		return "name: "+name+", desc: "+desc+", car: "+testCar.info()+", ---------------icar: "+iCar;
	}
	
}
