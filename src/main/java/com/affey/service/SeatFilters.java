package com.affey.service;

import com.affey.service.MovieFilters.MovieFiltersBuilder;
import com.affey.service.SeatFilters.SeatFiltersBuilder;

public interface SeatFilters {

	public Long getTheatreId();
	public String getUser_name();
	public Long getShowId();
	public Boolean isReserved();
	
	public static interface SeatFiltersBuilder{
	
		public SeatFiltersBuilder setTheatreId(Long theatreId);
		public SeatFiltersBuilder setUser_name(String user_name);
		public SeatFiltersBuilder setShowId(Long showId);
		public SeatFiltersBuilder setReserved(Boolean reserved);
		public SeatFilters build();
	}
}
