package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.affey.model.Show;
import com.affey.rest.pojo.ShowPojo;
import com.affey.rest.pojo.ShowReturnPojo;
import com.affey.service.MovieService;
import com.affey.service.ShowFilters.ShowFiltersBuilder;
import com.affey.service.ShowService;
import com.affey.service.impl.ShowFiltersBuilderImpl.Range;
import com.google.gson.JsonObject;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@RestController
@Api(value = "Show APIs", description = "APIs to manage the Shows")
public class ShowsRestService {

	@Autowired
	ShowService showService;
	
	@Autowired
	MovieService movieService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingRestService.class);
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	/*
	   * Show APIs
	   */

	  
	  @RequestMapping(value = "/v1/api/admin/theatre/{theatreId}/movie/{movieId}/addShow/", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Create a show", position = 1)
	  public ResponseEntity<ShowReturnPojo>  addShow(@ApiParam(value = "Theatre Id", required = true) @PathVariable long theatreId,
			  @ApiParam(value = "Movie Id", required = true) @PathVariable long movieId,
			  @ApiParam(value = "Show Parameters", required = true)@Valid @RequestBody ShowPojo show,
	      HttpServletRequest request, HttpServletResponse response) {
		  
		  LOGGER.info("Create Show for theatre : {} and Movie: {}", theatreId, movieId);
	      return new ResponseEntity<ShowReturnPojo>(showService.createShowAndSeats(show, theatreId,movieId),HttpStatus.OK);
	 }
	  
	  
	  @RequestMapping(value = "/v1/api/admin/getShow/{showId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get Show", position = 2)
	  public  ResponseEntity<?>  getShow(@ApiParam(value = "Show Id", required = true) @PathVariable long showId,
	      HttpServletRequest request, HttpServletResponse response) {
		  Show show= showService.getShow(showId);
		  if(show==null){
			  JsonObject error= new JsonObject();
		    	error.addProperty("error", "No Show found with id "+ showId);
		    	return  new ResponseEntity<>(error.toString(),HttpStatus.NOT_FOUND );
		  }
		return new ResponseEntity<Show>(show, HttpStatus.OK);
	 }
	  @RequestMapping(value = "/v1/api/admin/shows", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get Shows", position = 3)
	  public  ResponseEntity<?>  getFilteredShows(@ApiParam(value = "Theatre Id", defaultValue = "0", required = false) @RequestParam(value = "theatreId", defaultValue = "0", required = false) Long theatreId,
		      @ApiParam(value = "Movie Id",  defaultValue = "0", required = false) @RequestParam(value = "movieId", defaultValue = "0", required = false) Long movieId,
		      @ApiParam(value = "Started  After Filter", allowableValues = "*, yyyy-MM-dd format or epoch time", defaultValue = "*", required = false) @RequestParam(value = "startedAfter", defaultValue = "*", required = false) String startedAfter,
		      @ApiParam(value = "Ended before Filter", allowableValues = "*, yyyy-MM-dd format or epoch time", defaultValue = "*", required = false) @RequestParam(value = "endedBefore", defaultValue = "*", required = false) String endedBefore,
		       HttpServletRequest request, HttpServletResponse response) throws ParseException {
		  	ShowFiltersBuilder filters = showService.newFiltersBuilder();
		    if (movieId!=null && 0!=movieId) {
		     filters.setMovieId(movieId);
		    }
		    if (theatreId!=null && 0!=theatreId) {
			     filters.setTheatreId(theatreId);
		    }
		    Date startedAfterDate = null;
		    if (StringUtils.isNotBlank(startedAfter) && !"*".equals(startedAfter)) {
				startedAfterDate= DATE_FORMAT.parse(startedAfter);
		      }
		    Date endedBeforeDate = null;
		      if (StringUtils.isNotBlank(endedBefore) && !"*".equals(endedBefore)) {
		        endedBeforeDate= DATE_FORMAT.parse(endedBefore);
		      } 
		     if(startedAfterDate!=null && endedBeforeDate!=null){
		    	 filters.setRange(startedAfterDate, endedBeforeDate);
		     }
		    List<Show> shows = showService.listShows(filters.build());
		  if(shows.isEmpty()){
			  JsonObject error= new JsonObject();
		    	error.addProperty("error", "No Show found.");
		    	return  new ResponseEntity<String>(error.toString(),HttpStatus.NOT_FOUND );
		  }
		return new ResponseEntity<List<Show>>(shows, HttpStatus.OK);
	 } 
}
