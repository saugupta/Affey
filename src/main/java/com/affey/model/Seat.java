package com.affey.model;


public class Seat {

	Point point;
	boolean reserved;
	int customer;

	Seat() {

	}

	public Seat(int x, int y) {
		point = new Point(x, y);
		reserved = false;
		customer = -1;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Seat: Point" + point + " Reserved:" + reserved + " Customer: "
				+ customer;
	}
}
