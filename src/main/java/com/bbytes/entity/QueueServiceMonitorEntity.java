package com.bbytes.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bbytes.entity.ServiceMonitorEntity;

@Entity
@Table(name = "service_monitoring")
@DiscriminatorValue("queue")
public class QueueServiceMonitorEntity extends ServiceMonitorEntity{

	public QueueServiceMonitorEntity() {

	}
	@Column(name="SERVICE_TYPE")
	private String service_type;
     @Column(name = "QUEUE_NAME")
	 private String queue_name;
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	public String getQueue_name() {
		return queue_name;
	}
	public void setQueue_name(String queue_name) {
		this.queue_name = queue_name;
	}
	
	 
	

}
