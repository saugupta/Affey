package com.affey.service;

import com.affey.model.Seat;
import com.affey.rest.pojo.SeatPojo;

public interface SeatService {

	public Seat createSeat(SeatPojo seat);
}
