package com.affey.model;

import java.util.List;


public interface Seat {
	
	public User getUser();
	public boolean isReserved();
	public Show getShow();
	public Long getSeatId();
	public boolean getReserved();
}
