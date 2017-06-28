package com.affey.rest.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import com.affey.model.Movie;
import com.affey.model.Show;
import com.affey.model.Theatre;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value="Show", description="Show details")
public class ShowPojo implements Show, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8857838916499845404L;
	@NotNull
	@ApiModelProperty(required = true)
	private Date startTime;
	
	@NotNull
	@ApiModelProperty(required = true)
	private Date endTime;
	
	@NotNull
	@Range(min = 100, max = 500)
	@ApiModelProperty(required = true,allowableValues="range[20,100]", example="20",value="50")
	private Long price;
	
	@NotNull
	@Range(min = 20, max = 100)
	@ApiModelProperty(required = true, allowableValues="range[20,100]", example="50",value="20")
	private Long totalSeats;
	
	@Override
	public Long getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Long totalSeats) {
		this.totalSeats = totalSeats;
	}

	@Override
	public Long getShowId() {
		return null;
	}

	@Override
	public Theatre getTheatre() {
		return null;
	}

	@Override
	public Movie getMovie() {
		// TODO Auto-generated method stub
		return null;
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

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	@Override
	public List<Long> getSeats() {
		return null;
	}
}
