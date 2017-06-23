package com.affey.model.impl;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.affey.model.Point;

@Embeddable
public class PointDTO implements Point, Comparable<Point>{

	private static final Logger LOGGER = LoggerFactory.getLogger(PointDTO.class);
	
	@Column(name = "x", nullable=false)
	int x;
	
	@Column(name = "y", nullable=false)
	int y;
	PointDTO(){
		
	}
	PointDTO(int x, int y){
		this.x=x;
		this.y=y;
	}
	@Override
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	@Override
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
	@Override
	public int compareTo(Point o) {
		if(x>o.getX() || x==o.getX()&& y> o.getX())
			return 1;
		if(x==o.getX()&&y==o.getY())
			return 0;
		return -1;
	}
}
