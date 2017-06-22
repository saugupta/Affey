package com.affey.model;

import java.util.Arrays;

import com.affey.model.Point;

public class Theatre {

	int id;
	int size;
	public int getSize() {
		return size;
	}
	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}

	Seat seats[][];
	private Theatre(){
		
	}
	public Theatre(int id, int size, Seat[][] seats){
		this.id= id;
		this.size=size;
		this.seats=seats;
	}
	public int getId() {
		return id;
	}
	// Returned copy of Seats instead of original array
	public Seat[][] getSeats() {
		return Arrays.copyOf(seats, seats.length);
	}
	
	public Seat getSeat(Point point){
		return seats[point.x][point.y];
	}
	
}
