package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.affey.model.Point;
import com.affey.service.BookingService;
import com.affey.service.TheatreService;
import com.google.gson.JsonObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@Api(value = "Book Ticket", description = "APIs to book the tickets")
public class BookingRestService {

	@Autowired
	TheatreService theatreService ;
	
	@Autowired
	BookingService bookingService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingRestService.class);
	
	  @RequestMapping(value = "/v1/api/bookSeat/user/{userName}", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Book seats", position = 1)
	  public ResponseEntity<?>  bookTicket(@ApiParam(value = "Seat Id", required = true) @RequestBody Long[] seatIds,
			  @ApiParam(value = "User Name", required = true) @PathVariable String userName,
			  
			  HttpServletRequest request, HttpServletResponse response) {
		  
//	    if (!bookingService.doTheatreExist(theatreId)){
//	    	JsonObject error= new JsonObject();
//	    	error.addProperty("error", "Theatre "+ theatreId+" not found.");
//	    	return  new ResponseEntity<>(error.toString(),HttpStatus.NOT_FOUND);
//	    }
//	    	
//	    for( int i=0;i<points.length;i++){
//	    if(!bookingService.isSeatValid(theatreId, points[i])){
//	    	JsonObject error= new JsonObject();
//	    	error.addProperty("error", "Seat "+points[i] +" does not exist in theatre "+ theatreId);
//	    	return  new ResponseEntity<>(error.toString(),HttpStatus.NOT_FOUND );
//	    }
//	    }
	      return new ResponseEntity<>(bookingService.bookTheSeats(seatIds, userName),HttpStatus.OK);
	 }
	 
//
//	  @RequestMapping(value = "/v1/api/theatre/{theatreId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	  @ApiOperation(value = "Book a seat", position = 1)
//	  public  ResponseEntity<?>  ifTheatreExist(@ApiParam(value = "Theatre Id", required = true) @PathVariable int theatreId,
//	      HttpServletRequest request, HttpServletResponse response) {
//
//	   // return theatreService.doTheatreExist(theatreId);
//		return new ResponseEntity<>(bookingService.doTheatreExist(theatreId), HttpStatus.OK);
//	 }
}
