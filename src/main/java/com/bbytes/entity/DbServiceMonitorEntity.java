package com.bbytes.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bbytes.entity.ServiceMonitorEntity;

@Entity
@Table(name = "service_monitoring")
@DiscriminatorValue("database")
public class DbServiceMonitorEntity extends ServiceMonitorEntity{

	public DbServiceMonitorEntity() {

	}
     @Column(name = "SCHEMA_NAME")
	 private String schema_name;
     @Column(name="USER_NAME")
     private String user_name;
     @Column(name="PASSWORD")
     private String password;
     @Column(name="SERVICE_TYPE")
 	private String service_type;
     
	
	public String getSchema_name() {
		return schema_name;
	}
	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getService_type() {
		return service_type;
	}
	public void setService_type(String service_type) {
		this.service_type = service_type;
	}
	
	
     
	
}
