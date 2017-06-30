package com.affey.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

import com.affey.model.Movie;
import com.affey.rest.pojo.MoviePojo;
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
@Api(value = "Movie APIs", description = "APIs to manage Movie")
public class MovieRestService {

	@Autowired
	BookingService bookingService ;
	
	@Autowired
	TheatreService theatreService;
	
	@Autowired
	ShowService showService;
	
	@Autowired
	MovieService movieService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingRestService.class);
	
	/*
	   * Movie APIs
	   */
	  @RequestMapping(value = "/v1/api/admin/addMovie", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Add a Movie", position = 1)
	  public ResponseEntity<Movie>  addMovie(
	      @ApiParam(value = "Movie Parameters", required = true)@Valid @RequestBody MoviePojo movie,
	      HttpServletRequest request, HttpServletResponse response) {
		  LOGGER.info("Add Movie : {}", movie.getMovieName());
	      return new ResponseEntity<Movie>( movieService.addMovie(movie),HttpStatus.OK);
	 }
	  
	  
	  @RequestMapping(value = "/v1/api/admin/getMovie/{movieId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get Movie", position = 2)
	  public  ResponseEntity<?>  getMovie(@ApiParam(value = "Movie Id", required = true) @PathVariable long movieId,
	      HttpServletRequest request, HttpServletResponse response) {
		  Movie movie = movieService.getMovie(movieId);
		  if(movie==null){
			  JsonObject error= new JsonObject();
		    	error.addProperty("error", "No Movie found with id "+ movieId);
		    	return  new ResponseEntity<String>(error.toString(),HttpStatus.NOT_FOUND );
		  }
		return new ResponseEntity<Movie>(movie, HttpStatus.OK);
	 }
	  @RequestMapping(value = "/v1/api/admin/movies", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get Movies", position = 3)
	  public  ResponseEntity<?>  getFilteredMovie(@ApiParam(value = "Director", defaultValue = "*", required = false) @RequestParam(value = "director", defaultValue = "*", required = false) String director,
		      @ApiParam(value = "Movie Name",  defaultValue = "*", required = false) @RequestParam(value = "movieName", defaultValue = "*", required = false) String movieName,
		       HttpServletRequest request, HttpServletResponse response) {
		  MovieFiltersBuilder filters = movieService.newFiltersBuilder();
		    if (StringUtils.isNotBlank(movieName) && !"*".equals(movieName)) {
		     filters.setMovieName(movieName);
		    }
		    if (StringUtils.isNotBlank(director) && !"*".equals(director)) {
			     filters.setMovieDirector(director);
		    }
		    List<Movie> movies= movieService.listMovies(filters.build());
		  if(movies.isEmpty()){
			  JsonObject error= new JsonObject();
		    	error.addProperty("error", "No Movie found.");
		    	return  new ResponseEntity<String>(error.toString(),HttpStatus.NOT_FOUND );
		  }
		return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
	 } 
}
