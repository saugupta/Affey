package com.affey.service;


public interface BookingService {
	public boolean bookTheSeats(Long[] seatId, String userName) ;
	public boolean isSeatFree(long id);
}