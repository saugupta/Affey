package com.affey.model;


public interface Seat {

	public Point getPoint();
	public int getCustomerId();
	public boolean isReserved();
	public Show getShow();
}
