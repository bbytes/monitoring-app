package com.bbytes.service;

import java.util.List;

import com.bbytes.entity.ServiceMonitorEntity;


public interface ServiceMonitor {
	

	
    
	public boolean ping(int id,String services_name,String ip,String port,String url);
	
	 public List<ServiceMonitorEntity> getAllServices(long uid);
	 
}
