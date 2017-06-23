package com.affey.rest.pojo;

import com.affey.model.Point;
import com.affey.model.Seat;
import com.affey.model.Show;

public class SeatPojo implements Seat{

	private Point point;
	private boolean reserved;
	private int customerId;
	private Show show;
	SeatPojo() {

	}

	public SeatPojo(int x, int y) {
		point = new PointPojo(x, y);
		reserved = false;
		customerId = -1;
	}

	@Override
	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}
	@Override
	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	@Override
	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public Show getShow() {
		return show;
	}
	public void setShow(Show show){
		this.show=show;
	}
	
	@Override
	public String toString() {
		return "Seat: Point" + point + " Reserved:" + reserved + " Customer: "
				+ customerId;
	}

	
}
