package com.affey.service.impl;

import com.affey.service.SeatFilters;
import com.affey.service.SeatFilters.SeatFiltersBuilder;

public class SeatFiltersBuilderImpl implements SeatFilters, SeatFiltersBuilder {

	private Boolean reserved;
	private Long showId;
	private String user_name;
	private Long theatreId;
	
	@Override
	public Boolean isReserved() {
		return reserved;
	}
	@Override
	public SeatFiltersBuilder setReserved(Boolean reserved) {
		this.reserved = reserved;
		return this;
	}
	@Override
	public Long getShowId() {
		return showId;
	}
	@Override
	public SeatFiltersBuilder setShowId(Long showId) {
		this.showId = showId;
		return this;
	}
	
	@Override
	public String getUser_name() {
		return user_name;
	}
	
	@Override
	public SeatFiltersBuilder setUser_name(String user_name) {
		this.user_name = user_name;
		return this;
	}
	
	@Override
	public Long getTheatreId() {
		return theatreId;
	}
	
	@Override
	public SeatFiltersBuilder setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
		return this;
	}
	

	@Override
	public SeatFilters build() {
		return this;
	}
}
