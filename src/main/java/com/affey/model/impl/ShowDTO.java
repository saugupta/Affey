package com.affey.model.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.affey.model.Movie;
import com.affey.model.Show;
import com.affey.model.Theatre;

@Entity
@Table(name = "Shows")
public class ShowDTO implements Show{

	private static final Logger LOGGER = LoggerFactory.getLogger(ShowDTO.class);
	
	@Id
	@Column(name = "show_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long showId;
	
	@Column(name = "starttime", nullable=false)
	private Date startTime;
	
	@Column(name = "endtime", nullable=false)
	private Date endTime;
	
	@ManyToOne
	@JoinColumn(name = "theatre_id", nullable = false)
	private TheatreDTO theatre;

	@ManyToOne
	@JoinColumn(name = "movie_id", nullable = false)
	private MovieDTO movie;
	
	public void setMovie(MovieDTO movie) {
		this.movie = movie;
	}
	@Column(name = "price", nullable=false)
	private Long price;
	
	ShowDTO(){
		
	}
	
	ShowDTO(Show show){
		setStartTime(show.getStartTime());
		setEndTime(show.getEndTime());
		setPrice(show.getPrice());
	}
	@Override
	public Long getShowId() {
		return showId;
	}

	@Override
	public Date getStartTime() {
		return startTime;
	}

	@Override
	public Date getEndTime() {
		return endTime;
	}

	@Override
	public Long getPrice() {
		return price;
	}

	@Override
	public Theatre getTheatre() {
		return theatre;
	}
	
	@Override
	public Movie getMovie() {
		return movie;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setTheatre(TheatreDTO theatre) {
		this.theatre = theatre;
	}

	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public Long getTotalSeats() {
		// No need to include it
		return null;
	}
	@Override
	public List<Long> getSeats() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
