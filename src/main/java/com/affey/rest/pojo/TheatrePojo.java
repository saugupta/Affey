package com.affey.rest.pojo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.affey.model.Theatre;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class TheatrePojo implements Theatre{

	@NotNull
	@Pattern(regexp="[A-Za-z0-9 ]+")
	String name;
	
	@Override
	public Long getTheatreId() {
		return null;
	}

	@Override
	@ApiModelProperty(required = true)
	public String getTheatreName() {
		return name;
	}
	
	public void setTheatreName(String name){
		this.name= name;
	}
}