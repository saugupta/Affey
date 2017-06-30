package com.affey.service;

import java.util.List;

import com.affey.model.Seat;
import com.affey.rest.pojo.SeatPojo;
import com.affey.service.SeatFilters.SeatFiltersBuilder;

public interface SeatService {

	public Seat createSeat(SeatPojo seat);
	public List<Seat> listSeats(SeatFilters seatFiltersBuilder);
	public SeatFiltersBuilder newFiltersBuilder();
	public List<Seat> unReserveSeats(SeatFilters seatFilters);
}
