package com.example.demo.model;

import com.example.demo.annotation.FruitAddress;
import com.example.demo.annotation.FruitColor;
import com.example.demo.annotation.FruitColor.Color;
import com.example.demo.annotation.FruitName;

public class AppleModel {

	@FruitName("Apple")
	private String appleName;
	
	@FruitColor(fruitColor=Color.RED)
	private String appleColor;
	
	@FruitAddress(id=1,name="陕西红富士集团",address="陕西省西安市延安路89号红富士大厦")
	private String appleProvider;
	
	public String getAppleName() {
		return appleName;
	}

	public void setAppleName(String appleName) {
		this.appleName = appleName;
	}

	public String getAppleColor() {
		return appleColor;
	}

	public void setAppleColor(String appleColor) {
		this.appleColor = appleColor;
	}

	public String getAppleProvider() {
		return appleProvider;
	}

	public void setAppleProvider(String appleProvider) {
		this.appleProvider = appleProvider;
	}
}
