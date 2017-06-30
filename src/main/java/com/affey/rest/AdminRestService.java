package com.affey.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.affey.service.BookingService;
import com.affey.service.MovieService;
import com.affey.service.ShowService;
import com.affey.service.TheatreService;
import com.wordnik.swagger.annotations.Api;

@RestController
@Api(value = "Admin APIs", description = "APIs to handle Shows and Theatres")
public class AdminRestService {
	@Autowired
	BookingService bookingService ;
	
	@Autowired
	TheatreService theatreService;
	
	@Autowired
	ShowService showService;
	
	@Autowired
	MovieService movieService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingRestService.class);
	
	
	  
	  
	  
}  