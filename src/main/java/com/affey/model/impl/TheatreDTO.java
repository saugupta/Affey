package com.affey.model.impl;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.affey.model.Theatre;

@Entity
@Table(name = "Theatre")
public class TheatreDTO implements Theatre {

	private static final Logger LOGGER = LoggerFactory.getLogger(TheatreDTO.class);
	
	@Id
	@Column(name = "theatre_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long theatreId;

	@Column(name = "theatre_name")
	private String name;
	
	TheatreDTO(){
		
	}
	TheatreDTO(Theatre theatre){
		setTheatreName(theatre.getTheatreName());
	}
	@Override
	public Long getTheatreId() {
		return theatreId;
	}
	@Override
	public String getTheatreName() {
		return name;
	}

	public void setTheatreName(String name){
		this.name= name;
	}
	
	
//	@Column(name = "job_status")
//	private int size;
//	

//	private SeatDTO seats[][];
//	
//	@Override
//	public int getSize() {
//		return size;
//	}
//	public void setSeats(SeatDTO[][] seats) {
//		this.seats = seats;
//	}

	
//	private TheatreDTO(){
//		
//	}
//	public TheatreDTO(int id, int size, SeatDTO[][] seats){
//		this.id= id;
//		this.size=size;
//	//	this.seats=seats;
//	}
//	
	
//	// Returned copy of Seats instead of original array
//	public Seat[][] getSeats() {
//		return Arrays.copyOf(seats, seats.length);
//	}
//	
//	@Override
//	public Seat getSeat(Point point){
//		return seats[point.getX()][point.getY()];
//	}
//	


}
