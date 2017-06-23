package com.affey.model;

import java.util.Date;

public interface Show {

	long getShowId();
	Theatre getTheatre();
	Date getStartTime();
	Date getEndTime();
	long getPrice();
}
