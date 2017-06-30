package com.affey.service;

import java.util.Date;

import com.affey.service.ShowFilters.ShowFiltersBuilder;
import com.affey.service.impl.ShowFiltersBuilderImpl.Range;

public interface ShowFilters {
	
	public Long getMovieId();

	public Long getTheatreId();

	public Range getRange();

	public interface ShowFiltersBuilder{

		public ShowFiltersBuilder setMovieId(Long movieId);

		public ShowFiltersBuilder setTheatreId(Long theatreId);

		public ShowFiltersBuilder setRange(Date l,Date m);
		
		public ShowFilters build();
		
	}

	
}
