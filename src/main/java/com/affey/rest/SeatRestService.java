package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.affey.model.Seat;
import com.affey.service.SeatFilters.SeatFiltersBuilder;
import com.affey.service.SeatService;
import com.google.gson.JsonObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;


@RestController
@Api(value = "Seat APIs", description = "APIs to manage Seats")
public class SeatRestService {

	@Autowired
	SeatService seatService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingRestService.class);
	
	 @RequestMapping(value = "/v1/api/admin/seats", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get Seats", position = 3)
	  public  ResponseEntity<?>  getFilteredShows(@ApiParam(value = "Theatre Id", defaultValue = "0", required = false) @RequestParam(value = "theatreId", defaultValue = "0", required = false) Long theatreId,
			  @ApiParam(value = "Show Id", defaultValue = "0", required = false) @RequestParam(value = "showId", defaultValue = "0", required = false) Long showId,
		      @ApiParam(value = "Reserved ", required = false) @RequestParam(value = "reserved", required = false) Boolean reserved,
		      @ApiParam(value = "Booked by Customer ",  defaultValue = "*", required = false) @RequestParam(value = "userName", defaultValue = "*", required = false) String userName,
		      
		      HttpServletRequest request, HttpServletResponse response) throws ParseException {
		  	SeatFiltersBuilder filters = seatService.newFiltersBuilder();
		    if (showId!=null && 0!=showId) {
		     filters.setShowId(showId);
		    }
		    if (theatreId!=null && 0!=theatreId) {
			     filters.setTheatreId(theatreId);
		    }
		   if(StringUtils.isNotBlank(userName)&&!"*".equals(userName)){
			   filters.setUser_name(userName);
		   }
		   if(reserved!=null){
			   filters.setReserved(reserved);
		   }
			   
		    List<Seat> seats = seatService.listSeats(filters.build());
		  if(seats.isEmpty()){
			  JsonObject error= new JsonObject();
		    	error.addProperty("error", "No Seat found.");
		    	return  new ResponseEntity<String>(error.toString(),HttpStatus.NOT_FOUND );
		  }
		return new ResponseEntity<List<Seat>>(seats, HttpStatus.OK);
	 } 
	 
	 @RequestMapping(value = "/v1/api/admin/unreserveSeats", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Unreserve Seats", position = 3)
	  public  ResponseEntity<?>  unreserveSeats(@ApiParam(value = "Theatre Id", defaultValue = "0", required = false) @RequestParam(value = "theatreId", defaultValue = "0", required = false) Long theatreId,
			  @ApiParam(value = "Show Id", defaultValue = "0", required = false) @RequestParam(value = "showId", defaultValue = "0", required = false) Long showId,
		      @ApiParam(value = "Reserved ", required = false) @RequestParam(value = "reserved", required = false) Boolean reserved,
		      @ApiParam(value = "Booked by Customer ",  defaultValue = "*", required = false) @RequestParam(value = "userName", defaultValue = "*", required = false) String userName,
		      
		      HttpServletRequest request, HttpServletResponse response) throws ParseException {
		  	SeatFiltersBuilder filters = seatService.newFiltersBuilder();
		    if (showId!=null && 0!=showId) {
		     filters.setShowId(showId);
		    }
		    if (theatreId!=null && 0!=theatreId) {
			     filters.setTheatreId(theatreId);
		    }
		   if(StringUtils.isNotBlank(userName)&&!"*".equals(userName)){
			   filters.setUser_name(userName);
		   }
		   if(reserved!=null){
			   filters.setReserved(reserved);
		   }
			   
		    List<Seat> seats = seatService.unReserveSeats(filters.build());
		  if(seats.isEmpty()){
			  JsonObject error= new JsonObject();
		    	error.addProperty("error", "No Seat found.");
		    	return  new ResponseEntity<String>(error.toString(),HttpStatus.NOT_FOUND );
		  }
		return new ResponseEntity<List<Seat>>(seats, HttpStatus.OK);
	 } 
}