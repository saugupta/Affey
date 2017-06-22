package com.affey.util;

import org.springframework.beans.factory.annotation.Autowired;


import com.affey.model.Point;
import com.affey.service.TheatreService;

public class Util {

	@Autowired
	TheatreService theatreService;
	
	public boolean checkSeatValid(int theatreId, Point point){
		
		if(!theatreService.doTheatreExist(theatreId))
		return false;
		return true;
	}
}
