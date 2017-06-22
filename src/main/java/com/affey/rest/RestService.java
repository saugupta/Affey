package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import com.affey.model.Point;
import com.affey.service.TheatreService;
import com.affey.util.AffeyException;
import com.affey.util.Util;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Api(value = "Book Ticket", description = "APIs to book the tickets")
public class RestService {

	@Autowired
	TheatreService theatreService ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RestService.class);
	
	  @RequestMapping(value = "/v1/api/theatre/{theatreId}/bookSeat/customer/{customerId}", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Book a seat", position = 1)
	  public ResponseEntity<?>  bookTicket(@ApiParam(value = "Theatre Id", required = true) @PathVariable int theatreId,
			  @ApiParam(value = "Customer Id", required = true) @PathVariable int customerId,
	      @ApiParam(value = "Run Parameters", required = true) @RequestBody Point[] points,
	      HttpServletRequest request, HttpServletResponse response) {
		  
	    if (!theatreService.doTheatreExist(theatreId)){
	    	JsonObject error= new JsonObject();
	    	error.addProperty("error", "Theatre "+ theatreId+" not found.");
	    	return  new ResponseEntity<>(error.toString(),HttpStatus.NOT_FOUND);
	    }
	    	
	    for( int i=0;i<points.length;i++){
	    if(!theatreService.isSeatValid(theatreId, points[i])){
	    	JsonObject error= new JsonObject();
	    	error.addProperty("error", "Seat "+points[i] +" does not exist in theatre "+ theatreId);
	    	return  new ResponseEntity<>(error.toString(),HttpStatus.NOT_FOUND );
	    }
	    }
	      return new ResponseEntity<>(theatreService.bookTheSeats(theatreId, points, customerId),HttpStatus.OK);
	 }
	 

	  @RequestMapping(value = "/v1/api/theatre/{theatreId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Book a seat", position = 1)
	  public  ResponseEntity<?>  ifTheatreExist(@ApiParam(value = "Theatre Id", required = true) @PathVariable int theatreId,
	      HttpServletRequest request, HttpServletResponse response) {

	   // return theatreService.doTheatreExist(theatreId);
		return new ResponseEntity<>(theatreService.doTheatreExist(theatreId), HttpStatus.OK);
	 }
}
