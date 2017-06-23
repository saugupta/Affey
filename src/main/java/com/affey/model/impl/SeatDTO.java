package com.affey.model.impl;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.affey.model.Point;
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
	 
	@Embedded
	PointDTO point;
	
	@Column(name = "reserved", nullable=false)
	boolean reserved;
	
	@ManyToOne
	@JoinColumn(name = "show_id", nullable = false)
	ShowDTO show;
	
	@Column(name = "customer_id")
	int customerId;
	
	SeatDTO() {

	}

	public SeatDTO(int x, int y) {
		point = new PointDTO(x, y);
		reserved = false;
		customerId = -1;
	}
	
	@Override
	public Point getPoint() {
		return point;
	}

	public void setPoint(PointDTO point) {
		this.point = point;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customer) {
		this.customerId = customer;
	}


	@Override
	public Show getShow() {
		return show;
	}
	public void setShowId(ShowDTO showId){
		this.show=showId;
	}
	
	@Override
	public String toString() {
		return "Seat: Point" + point + " Reserved:" + reserved + " Customer: "
				+ customerId;
	}
}
