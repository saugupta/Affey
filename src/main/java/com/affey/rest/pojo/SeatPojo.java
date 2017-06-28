package com.affey.rest.pojo;

import java.io.Serializable;

import com.affey.model.Point;
import com.affey.model.Seat;
import com.affey.model.Show;
import com.affey.model.User;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class SeatPojo implements Seat, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3817784975947931167L;
	

	@ApiModelProperty(required = true)
	private boolean reserved;
	@ApiModelProperty(required = true)
	private Long customerId;
	
	SeatPojo() {

	}

	public SeatPojo(int x, int y) {
	
		reserved = false;
		customerId = (long)-1;
	}

	@Override
	public Long getSeatId() {
		return null;
	}
	
	@Override
	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	@Override
	public Show getShow() {
		return null;
	}

	@Override
	public String toString() {
		return "Seat:    Reserved:" + reserved + " Customer: "
				+ customerId;
	}

	@Override
	public boolean getReserved() {
		return reserved;
	}

	@Override
	public User getUser() {
		// TODO Auto-generated method stub
		return null;
	}
}
