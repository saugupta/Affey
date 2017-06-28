package com.affey.rest.pojo;
import java.io.Serializable;
import javax.validation.constraints.NotNull;
import com.affey.model.Movie;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class MoviePojo implements Movie, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2433122234373292430L;

	@NotNull
	@ApiModelProperty(required = true)
	
	private String movieName;
	
	private String director;
	private String[] actors;
	@Override
	public Long getMovieId() {
	return null;
	}
	
	@Override
	public String getMovieName() {
		return movieName;
	}
	@Override
	@ApiModelProperty
	public String getMovieDirector() {
		return director;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setActors(String[] actors) {
		this.actors = actors;
	}
	@Override
	@ApiModelProperty
	public String[] getMovieActors() {
		return actors;
	}
	
}
