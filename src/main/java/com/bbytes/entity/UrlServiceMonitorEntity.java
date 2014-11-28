package com.bbytes.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "service_monitoring")
@DiscriminatorValue("url")
public class UrlServiceMonitorEntity extends ServiceMonitorEntity{
	
	public UrlServiceMonitorEntity(){
		
	}
	
	@Column(name="URL")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
