package com.affey.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.affey.model.Point;
import com.affey.service.BookingService;
import com.affey.service.TheatreService;

public class Util {

	@Autowired
	TheatreService theatreService;
	
	@Autowired
	BookingService bookingService;
	
}
