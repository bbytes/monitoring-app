package com.bbytes.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="service_monitoring")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)

public class ServiceMonitorEntity {
	public ServiceMonitorEntity(){
		
	}
	@Id
	@Column(name="ID")
	@GeneratedValue(strategy = IDENTITY)
	private Long id;
	@Column(name="NAME")
	private String name;
	
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="SERVICES_NAME")
	private String services_name;
	@Column(name="IP")
	private String ip;
	@Column(name="PORT")
	private String port;
	@Column(name="ISACTIVE")
	private boolean isactive;
	@Column(name="USERID")
	private long userid;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getServices_name() {
		return services_name;
	}
	public void setServices_name(String services_name) {
		this.services_name = services_name;
	}
	public boolean isIsactive() {
		return isactive;
	}
	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	
	
	
	
	
}
