package com.bbytes.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bbytes.entity.ServiceMonitorEntity;


public interface ServiceMonitorDao {
	
	public List<ServiceMonitorEntity> save(ServiceMonitorEntity serviceMonitorEntity);
	public List<ServiceMonitorEntity> saveActive(long id,boolean isactive);
	public List<ServiceMonitorEntity> stopActive(long id,boolean isactive);
    public List<ServiceMonitorEntity> getAllServices(long uid);
    public List<ServiceMonitorEntity> getServiceById(long id, String services_name);
    public List<ServiceMonitorEntity> deleteService(long id, String services_name);
    public List<ServiceMonitorEntity> getAllServices();
	
}
