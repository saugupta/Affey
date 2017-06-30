package com.affey.model;

import java.util.List;

import com.affey.service.SeatFilters;

public interface SeatDAOService {
	
	List<Long> createSeats(Long totalSeats, Show show);
	List<Seat> getAllSeats();
	public boolean bookTheSeats(Long[] seats, String userName);
	public List<Seat> listSeats(SeatFilters seatFilters);
	public List<? extends Seat> unReserveSeats(SeatFilters seatFilters);

}
