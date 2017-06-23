package com.affey.model;

import java.util.Arrays;

import com.affey.model.Point;

public interface Theatre {
	public int getSize();
	public Seat[][] getSeats();
	public int getId();
	Seat getSeat(Point point);
	
}
