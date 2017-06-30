package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.affey.model.Movie;
import com.affey.model.Show;
import com.affey.model.Theatre;
import com.affey.rest.pojo.MoviePojo;
import com.affey.rest.pojo.ShowPojo;
import com.affey.rest.pojo.ShowReturnPojo;
import com.affey.rest.pojo.TheatrePojo;
import com.affey.service.BookingService;
import com.affey.service.MovieFilters.MovieFiltersBuilder;
import com.affey.service.MovieService;
import com.affey.service.ShowService;
import com.affey.service.TheatreService;
import com.google.gson.JsonObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

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