package com.affey.rest.pojo;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel
public class RangePojo{
	
	@NotNull
	@ApiModelProperty(required=true)
	private Date l;
	@NotNull
	@ApiModelProperty(required=true)
	private Date m;
	public Date getL(){
		return l;
	}
	public RangePojo(){
		
	}
	public Date getM(){
		return m;
	}
	public RangePojo(Date l, Date m){
		this.l=l;
		this.m=m;
	}
}
