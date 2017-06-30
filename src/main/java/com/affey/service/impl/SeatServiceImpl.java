package com.affey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.affey.model.Seat;
import com.affey.model.SeatDAOService;
import com.affey.rest.pojo.SeatPojo;
import com.affey.service.SeatFilters;
import com.affey.service.SeatFilters.SeatFiltersBuilder;
import com.affey.service.SeatService;

@Component
public class SeatServiceImpl implements SeatService{

	@Autowired
	SeatDAOService seatDAOService;
	
	@Override
	public Seat createSeat(SeatPojo seat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SeatFiltersBuilder newFiltersBuilder() {
		return new SeatFiltersBuilderImpl();
	}

	@Override
	public List<Seat> listSeats(SeatFilters seatFilters) {
		return seatDAOService.listSeats(seatFilters);
	}

	@Override
	public List<Seat> unReserveSeats(SeatFilters seatFilters) {
		return (List<Seat>) seatDAOService.unReserveSeats(seatFilters);
	}
}
