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

import com.affey.model.Point;
import com.affey.service.TheatreService;
import com.google.gson.JsonObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@Api(value = "Theatre and Show Control", description = "APIs to handle Shows and Theatres")
public class AdminRestService {

	@Autowired
	TheatreService theatreService ;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserRestService.class);
	

	  @RequestMapping(value = "/v1/api/admin/theatre/{theatreId}/show", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Book a seat", position = 1)
	  public  ResponseEntity<?>  ifTheatreExist(@ApiParam(value = "Theatre Id", required = true) @PathVariable int theatreId,
	      HttpServletRequest request, HttpServletResponse response) {

	   // return theatreService.doTheatreExist(theatreId);
		return new ResponseEntity<>(theatreService.doTheatreExist(theatreId), HttpStatus.OK);
	 }
}  