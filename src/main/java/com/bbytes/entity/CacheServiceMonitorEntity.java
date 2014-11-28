package com.bbytes.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "service_monitoring")
@DiscriminatorValue("cache")
public class CacheServiceMonitorEntity  extends ServiceMonitorEntity{
	
	public CacheServiceMonitorEntity(){
		
	}
	
	@Column(name="SERVICE_TYPE")
	private String service_type;

	public String getService_type() {
		return service_type;
	}

	public void setService_type(String service_type) {
		this.service_type = service_type;
	}

	
	
	

}
