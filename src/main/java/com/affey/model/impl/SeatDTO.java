package com.affey.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.affey.model.Seat;
import com.affey.model.Show;


@Entity
@Table(name = "seat")
public class SeatDTO implements Seat {
	private static final Logger LOGGER = LoggerFactory.getLogger(SeatDTO.class);

	@Id
	@Column(name = "seat_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seatId;
	
	@Column(name = "reserved")
	private boolean reserved=false;
	
	@ManyToOne
	@JoinColumn(name = "show_id", nullable = false)
	private ShowDTO show;
	
	@ManyToOne
	@JoinColumn(name = "user_name")
	private UserDTO user;
	
	@Override
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public void setSeatId(Long seatId) {
		this.seatId = seatId;
	}

	SeatDTO() {
		
	}

//	public SeatDTO(int x, int y) {
//		point = new PointDTO(x, y);
//		reserved = false;
//		customerId = (long) -1;
//	}
	

	@Override
	public Long getSeatId() {
		return seatId;
	}
	
	public boolean isReserved() {
		return reserved;
	}

	@Override
	public boolean getReserved() {
		return reserved;
	}
	
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}


	@Override
	public Show getShow() {
		return show;
	}
	public void setShow(ShowDTO show){
		this.show=show;
	}
	
	@Override
	public String toString() {
		return "Seat: "+seatId+" Reserved:" + reserved + " User: "
				+ user.toString()+ "Show:"+ show.toString();
	}


}
