package com.affey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.affey.model.Movie;
import com.affey.model.MovieDAOService;
import com.affey.service.MovieFilters;
import com.affey.service.MovieFilters.MovieFiltersBuilder;
import com.affey.service.MovieService;
import com.affey.util.AffeyException;
import com.affey.util.Validators;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	MovieDAOService movieDAOService;

	@Override
	public Movie addMovie(Movie movie) {
		if(movieDAOService.getMovie(movie.getMovieName())!=null){
		      throw new AffeyException(
		        "A movie with same name already registered. Please try a different name");
		    }
		validateMovie(movie);	
		long movieId= movieDAOService.addMovie(movie);
		return movieDAOService.getMovie(movieId);
	}

	@Override
	public Movie getMovie(Long movieId) {
		return movieDAOService.getMovie(movieId);
	}
	
	void validateMovie(Movie movie){
	
		if (!Validators.patternValidator(movie.getMovieName(), "[A-Za-z0-9 ]+")) {
		      throw new AffeyException("Movie name should contain A-Za-z,0-9 and Space only.");
		    }
		  
		    if (!Validators.patternValidator(movie.getMovieDirector(),"[A-Za-z0-9- ]+")) {
		    	 throw new AffeyException("Movie Director name should contain A-Za-z,0-9 and Space only.");
		   }   
		    if (!Validators.patternValidator(movie.getMovieActors(),"[A-Za-z0-9- ]+")) {
		    	 throw new AffeyException("Movie Actors names should contain A-Za-z,0-9 and Space only.");
		   }
	}

	@Override
	public MovieFiltersBuilder newFiltersBuilder() {
		return new MovieFiltersBuilderImpl();
	}

	@Override
	public List<Movie> listMovies(MovieFilters movieFilters) {
		return movieDAOService.listMovies(movieFilters);
	}	
}
