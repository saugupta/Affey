package com.affey.model;

import java.util.Date;
import java.util.List;

public interface Show {

	public Long getShowId();
	public Theatre getTheatre();
	public Date getStartTime();
	public Date getEndTime();
	public Long getPrice();
	public Long getTotalSeats();
	public List<Long> getSeats();
	public Movie getMovie();
}
