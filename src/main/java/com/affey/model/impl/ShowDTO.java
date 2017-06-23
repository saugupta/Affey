package com.affey.model.impl;

import java.util.Date;

import com.affey.model.Show;
import com.affey.model.Theatre;

public class ShowDTO implements Show{

	private long showId;
	private Date startTime;
	private Date endTime;
	private TheatreDTO theatre;
	private long price;
	
	@Override
	public long getShowId() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public Date getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getEndTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getPrice() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Theatre getTheatre() {
		// TODO Auto-generated method stub
		return null;
	}

}
