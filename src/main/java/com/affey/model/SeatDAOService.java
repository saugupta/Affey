package com.affey.model;

import java.util.List;

import com.affey.model.impl.ShowDTO;

public interface SeatDAOService {
	
	List<Long> createSeats(Long totalSeats, Show show);
	List<Seat> getAllSeats();
	public boolean bookTheSeats(Long[] seats, String userName);
		
}
