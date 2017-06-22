package com.affey.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class Point{
	int x;
	int y;
	Point(){
		
	}
	Point(int x, int y){
		this.x=x;
		this.y=y;
	}
	@ApiModelProperty(required = true)
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	@ApiModelProperty(required = true)
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString(){
		return "{"+ this.x +" , "+this.y+"}";
	}
}