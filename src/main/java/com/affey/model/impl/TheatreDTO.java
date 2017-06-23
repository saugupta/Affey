package com.affey.model.impl;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.affey.model.Point;
import com.affey.model.Seat;
import com.affey.model.Theatre;

@Entity
@Table(name = "theatre")
public class TheatreDTO implements Theatre {

	@Id
	@Column(name = "invocation_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "job_status")
	private int size;
	
	private SeatDTO seats[][];
	
	@Override
	public int getSize() {
		return size;
	}
	public void setSeats(SeatDTO[][] seats) {
		this.seats = seats;
	}

	
	private TheatreDTO(){
		
	}
	public TheatreDTO(int id, int size, SeatDTO[][] seats){
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
