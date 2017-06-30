package com.affey.service.impl;

import java.util.Date;
import com.affey.service.ShowFilters;
import com.affey.service.ShowFilters.ShowFiltersBuilder;


public class ShowFiltersBuilderImpl implements ShowFilters, ShowFiltersBuilder{

	public static class Range{
		private Date l;
		private Date m;
		public Date getL(){
			return l;
		}
		public Range(){
			
		}
		public Date getM(){
			return m;
		}
		public Range(Date l, Date m){
			this.l=l;
			this.m=m;
		}
	}
	private Long theatreId;
	private Long movieId;
	private Range range;
	
	@Override
	public Range getRange() {
		return range;
	}
	@Override
	public ShowFiltersBuilder setRange(Date l, Date m) {
		this.range = new Range(l,m);
		return this;
	}
	
	@Override
	public Long getTheatreId() {
		return theatreId;
	}
	@Override
	public ShowFiltersBuilder setTheatreId(Long theatreId) {
		this.theatreId = theatreId;
		return this;
	}
	@Override
	public Long getMovieId() {
		return movieId;
	}
	@Override
	public ShowFiltersBuilder setMovieId(Long movieId) {
		this.movieId = movieId;
		return this;
	}
	@Override
	public ShowFilters build(){
		return this;
	}
}
