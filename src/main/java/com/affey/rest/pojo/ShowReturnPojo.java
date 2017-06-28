package com.affey.rest.pojo;

import java.util.Date;
import java.util.List;

import com.affey.model.Movie;
import com.affey.model.Show;
import com.affey.model.Theatre;

public class ShowReturnPojo implements Show {
	private Long showId;
	private Date startTime;
	private Date endTime;
	private Long price;
	private Long totalSeats;
	private List<Long> seats;
	private Theatre theatre;
	private Movie movie;
	public ShowReturnPojo(Show show){
		setShowId(show.getShowId());
		setStartTime(show.getStartTime());
		setEndTime(show.getEndTime());
		setPrice(show.getPrice());
		setTotalSeats(show.getTotalSeats());
		setSeats(show.getSeats());
		setTheatre(show.getTheatre());
		setMovie(show.getMovie());
	}
	@Override
	public Long getShowId() {
		return showId;
	}
	
	public void setShowId(Long showId) {
		this.showId = showId;
	}
	@Override
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	@Override
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	@Override
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	@Override
	public Long getTotalSeats() {
		return totalSeats;
	}
	public void setTotalSeats(Long totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	@Override
	public List<Long> getSeats() {
		return seats;
	}
	public void setSeats(List<Long> seats) {
		if(seats!=null)
			this.totalSeats=(long) seats.size();
		this.seats = seats;
	}

	@Override
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre){
		this.theatre=theatre;
	}
	@Override
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
}