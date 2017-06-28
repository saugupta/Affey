package com.affey.model.impl;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.affey.model.Movie;
import org.apache.commons.lang3.StringUtils;;

@Entity
@Table(name="Movie")
public class MovieDTO implements Movie{

	@Id
	@Column(name = "movie_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long movieId;
	
	@Column(name="moviename", nullable=false)
	private String movieName;
	
	@Column(name = "director")
	private String director;
	
	@Column(name = "actors")
	private String actors;
	
	MovieDTO(){
		
	}
	MovieDTO(Movie movie){
		setMovieDirector(movie.getMovieDirector());
		setMovieActors(movie.getMovieActors());
		setMovieName(movie.getMovieName());
	}
	  @Override
	  public String[] getMovieActors() {
	    return this.actors != null ? this.actors.split(",") : new String[] {};
	  }

	  public void setActors(String actors) {
	    this.actors = actors;
	  }

	  public void setMovieActors(String[] actors) {
	    this.actors = StringUtils.join(actors, ',');
	  }
	  
	public void setMovieId(Long movieId) {
		this.movieId = movieId;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public void setMovieDirector(String director) {
		this.director = director;
	}
	
	@Override
	public Long getMovieId() {
		return movieId;
	}
	@Override
	public String getMovieName() {
		return movieName;
	}
	@Override
	public String getMovieDirector() {
		return director;
	}
	
}
