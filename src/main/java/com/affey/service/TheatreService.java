package com.affey.service;

import com.affey.model.Point;

public interface TheatreService {

	public boolean bookTheSeats(int theatreId, Point[] locations, int customerId) ;
	public boolean doTheatreExist(int id);
	public boolean isSeatValid(int id,Point point );
	
}
