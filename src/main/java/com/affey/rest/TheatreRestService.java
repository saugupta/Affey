package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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

import com.affey.model.Theatre;
import com.affey.rest.pojo.TheatrePojo;
import com.affey.service.BookingService;
import com.affey.service.MovieService;
import com.affey.service.ShowService;
import com.affey.service.TheatreService;
import com.google.gson.JsonObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@Api(value = "Theatre APIs", description = "APIs to manage Theatres")
public class TheatreRestService {

	@Autowired
	TheatreService theatreService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingRestService.class);
	/*
	 * Theatre APIs
	 */
	@RequestMapping(value = "/v1/api/admin/addTheatre", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Add a theatre", position = 1)
	  public ResponseEntity<Theatre>  addTheatre(
	      @ApiParam(value = "Theatre Parameters", required = true) @Valid @RequestBody TheatrePojo theatre,
	      HttpServletRequest request, HttpServletResponse response) {
		  LOGGER.info("Create Theatre : {}", theatre.getTheatreName());
	      return new ResponseEntity<Theatre>(theatreService.createTheatre(theatre),HttpStatus.OK);
	 }
	
	@RequestMapping(value = "/v1/api/admin/updateTheatre/{theatreId}", method = PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Update a theatre", position = 2)
	  public ResponseEntity<Theatre>  updateTheatre(@ApiParam(value = "Theatre Id", required = true) @PathVariable long theatreId,
	      @ApiParam(value = "Theatre Parameters", required = true) @Valid @RequestBody TheatrePojo theatre,
	      HttpServletRequest request, HttpServletResponse response) {
		  LOGGER.info("Update Theatre : {}", theatre.getTheatreName());
	      return new ResponseEntity<Theatre>(theatreService.updateTheatre(theatreId, theatre),HttpStatus.ACCEPTED);
	 }
	
	
	  @RequestMapping(value = "/v1/api/admin/getTheatre/{theatreId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get theatre", position = 3)
	  public  ResponseEntity<?>  getTheatre(@ApiParam(value = "Theatre Id", required = true) @PathVariable long theatreId,
	      HttpServletRequest request, HttpServletResponse response) {
		  Theatre theatre= theatreService.getTheatre(theatreId);
		  if(theatre==null){
			  JsonObject error= new JsonObject();
		    	error.addProperty("error", "No Theatre found with id "+ theatreId);
		    	return  new ResponseEntity<String>(error.toString(),HttpStatus.NOT_FOUND );
		  }
		return new ResponseEntity<Theatre>(theatre, HttpStatus.OK);
	 }
	 
}
