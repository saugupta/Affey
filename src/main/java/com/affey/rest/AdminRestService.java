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

@Controller
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
	 
	  /*
	   * Show APIs
	   */

	  
	  @RequestMapping(value = "/v1/api/admin/theatre/{theatreId}/movie/{movieId}/addShow/", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Create a show", position = 5)
	  public ResponseEntity<ShowReturnPojo>  addShow(@ApiParam(value = "Theatre Id", required = true) @PathVariable long theatreId,
			  @ApiParam(value = "Movie Id", required = true) @PathVariable long movieId,
			  @ApiParam(value = "Show Parameters", required = true)@Valid @RequestBody ShowPojo show,
	      HttpServletRequest request, HttpServletResponse response) {
		  
		  LOGGER.info("Create Show for theatre : {} and Movie: {}", theatreId, movieId);
	      return new ResponseEntity<ShowReturnPojo>(showService.createShowAndSeats(show, theatreId,movieId),HttpStatus.OK);
	 }
	  
	  
	  @RequestMapping(value = "/v1/api/admin/getShow/{showId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get Show", position = 6)
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
	  
	  /*
	   * Movie APIs
	   */
	  @RequestMapping(value = "/v1/api/admin/addMovie", method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Add a Movie", position = 3)
	  public ResponseEntity<Movie>  addMovie(
	      @ApiParam(value = "Movie Parameters", required = true)@Valid @RequestBody MoviePojo movie,
	      HttpServletRequest request, HttpServletResponse response) {
		  LOGGER.info("Add Movie : {}", movie.getMovieName());
	      return new ResponseEntity<Movie>( movieService.addMovie(movie),HttpStatus.OK);
	 }
	  
	  
	  @RequestMapping(value = "/v1/api/admin/getMovie/{movieId}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
	  @ApiOperation(value = "Get Movie", position = 4)
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
	  @ApiOperation(value = "Get Movies", position = 4)
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