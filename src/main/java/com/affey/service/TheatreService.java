package com.affey.service;

import com.affey.model.Point;

public interface TheatreService {

	public boolean bookTheSeat(int theatreId, Point location, int customerId) ;
	public boolean doTheatreExist(int id);
	public boolean isSeatValid(int id,Point point );
	
}
