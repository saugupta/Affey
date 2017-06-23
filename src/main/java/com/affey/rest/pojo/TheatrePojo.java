package com.affey.rest.pojo;

import java.util.Arrays;

import com.affey.model.Point;
import com.affey.model.Seat;
import com.affey.model.Theatre;

public class TheatrePojo implements Theatre{

	int id;
	int size;
	SeatPojo seats[][];
	
	@Override
	public int getSize() {
		return size;
	}
	public void setSeats(SeatPojo[][] seats) {
		this.seats = seats;
	}

	private TheatrePojo(){
		
	}
	public TheatrePojo(int id, int size, SeatPojo[][] seats){
		this.id= id;
		this.size=size;
		this.seats=seats;
	}
	
	@Override
	public int getId() {
		return id;
	}
	// Returned copy of Seats instead of original array
	public Seat[][] getSeats() {
		return Arrays.copyOf(seats, seats.length);
	}
	
	@Override
	public Seat getSeat(Point point){
		return seats[point.getX()][point.getY()];
	}
}