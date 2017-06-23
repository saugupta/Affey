package com.affey.rest.pojo;

import com.affey.model.Point;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class PointPojo  implements Point, Comparable<Point>{
	int x;
	int y;
	PointPojo(){
		
	}
	PointPojo(int x, int y){
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
	@Override
	public int compareTo(Point o) {
		if(x>o.getX() || x==o.getX()&& y> o.getX())
			return 1;
		if(x==o.getX()&&y==o.getY())
			return 0;
		return -1;
	}
}